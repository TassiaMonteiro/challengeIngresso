package br.com.brq.challengeIngresso.service;

import br.com.brq.challengeIngresso.entities.Usuario;
import br.com.brq.challengeIngresso.models.dto.SenhaDto;
import br.com.brq.challengeIngresso.repository.UsuarioRepository;
import br.com.brq.challengeIngresso.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validarCadastro(Usuario usuario){

        if (usuarioRepository.existsByCpf(usuario.getCpf())){
            throw new RuntimeException("CPF já existe.");
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new RuntimeException("Email já existe.");
        }

        if (ValidaEstado.isEstado(usuario.getEndereco().getEstado()) == false){
            throw new RuntimeException("Estado inválido");
        }

        if (!usuario.getEndereco().getPais().equals("BR")){
            throw new RuntimeException("O país deve ser BR.");
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
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
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
            throw new RuntimeException("Senha informada não coincide com a senha do usuário.");
        }
        usuario.setSenha(novaSenha);
    }

    public SenhaDto gerarUUID(String id){
        SenhaDto senhaDto = new SenhaDto();
        String uuid = UUID.randomUUID().toString();
        senhaDto.setId(uuid);
        return senhaDto;
    }

    public void isUUID(String codigo){
        String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        if(!codigo.matches(UUID_REGEX)){
            throw new RuntimeException("Codigo de segurança deve ser do tipo UUID.");
        }
    }

    @Transactional
    public void novaSenha(String id, String novaSenha){
        Usuario usuario = buscar(id);
        dataAtualizacao(usuario);
        usuario.setSenha(novaSenha);
    }
}
