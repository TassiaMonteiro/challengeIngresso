package br.com.brq.challengeIngresso.domain.service;

import br.com.brq.challengeIngresso.domain.entities.Usuario;
import br.com.brq.challengeIngresso.domain.exception.EntidadeEmConflitoException;
import br.com.brq.challengeIngresso.domain.exception.EntidadeNaoEncontradaException;
import br.com.brq.challengeIngresso.domain.exception.NegocioException;
import br.com.brq.challengeIngresso.domain.repository.UsuarioRepository;
import br.com.brq.challengeIngresso.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validarCadastro(Usuario usuario){

        if (usuarioRepository.existsByCpf(usuario.getCpf())){
            throw new EntidadeEmConflitoException("CPF já existe.");
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new EntidadeEmConflitoException("Email já existe.");
        }

        if (ValidaEstado.isEstado(usuario.getEndereco().getEstado()) == false){
            throw new NegocioException("Estado inválido.");
        }

        if (!usuario.getEndereco().getPais().equals("BR")){
            throw new NegocioException("O país deve ser BR.");
        }

    }

    public Usuario salvar(Usuario usuario){
        usuario.setId(UUID.randomUUID().toString());
        OffsetDateTime date = OffsetDateTime.now();
        usuario.setDataCadastro(date.truncatedTo(ChronoUnit.SECONDS));
        return usuarioRepository.save(usuario);
    }

    public Usuario buscar(String id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O usuario com o id " + id + " especificado, nao foi encontrado na base de dados."));
    }

    public void remover(Usuario usuario){
        usuarioRepository.deleteById(usuario.getId());
    }

    public Usuario atualizar(Usuario usuario){
        dataAtualizacao(usuario);
        return usuarioRepository.save(usuario);
    }

    private void dataAtualizacao(Usuario usuario) {
        OffsetDateTime date = OffsetDateTime.now();
        usuario.setDataAtualizacao(date.truncatedTo(ChronoUnit.SECONDS));
    }

    @Transactional
    public void alterarSenha(String id, String senhaAtual, String novaSenha) {
        Usuario usuario = buscar(id);
        dataAtualizacao(usuario);

        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha informada não coincide com a senha do usuário.");
        }
        usuario.setSenha(novaSenha);
    }


    public Usuario gerarCodigo(Usuario usuario){
        LocalDateTime date = LocalDateTime.now();
        usuario.setDataHoraCodigoSeguranca(date);

        String uuid = UUID.randomUUID().toString();
        usuario.setCodigoSeguranca(uuid);

        return usuarioRepository.save(usuario);
    }

    public void isCodigoValido(String codigoSeguranca, Usuario usuario){
        if(!codigoSeguranca.equals(usuario.getCodigoSeguranca())){
            throw new NegocioException("Codigo de segurança incorreto.");
        }
    }

    public void isdataHoraCodigoSegurancaValida(Usuario usuario){
        LocalDateTime dataInicio = usuario.getDataHoraCodigoSeguranca();
        LocalDateTime dataFim = dataInicio.plus(5, ChronoUnit.MINUTES);
        LocalDateTime dataAtual = LocalDateTime.now();

        if (dataAtual.isAfter(dataFim)){
            throw new NegocioException("Seu código de segurança expirou.");
        }
    }

    public void isNovaSenhaValida(String novaSenha, Usuario usuario){
        if(novaSenha.equals(usuario.getSenha())){
            throw new NegocioException("Nova senha não pode ser igual à senha anterior.");
        }
    }

    @Transactional
    public void novaSenha(String id, String codigoSeguranca,String novaSenha){
        Usuario usuario = buscar(id);
        isCodigoValido(codigoSeguranca, usuario);
        isdataHoraCodigoSegurancaValida(usuario);
        isNovaSenhaValida(novaSenha, usuario);
        dataAtualizacao(usuario);
        usuario.setSenha(novaSenha);
    }

}
