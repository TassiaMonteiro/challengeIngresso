package br.com.brq.challengeIngresso.service;

import br.com.brq.challengeIngresso.entities.Usuario;
import br.com.brq.challengeIngresso.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validarCadastro(Usuario usuario){

        if (usuarioRepository.existsByCpf(usuario.getCpf())){
            throw new RuntimeException("CPF deve ser único.");
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new RuntimeException("Email deve ser único");
        }

        if (!isDataNascimentoValida(usuario.getDataNascimento())){
            throw new RuntimeException("Data de nascimento deve ser anterior à data atual");
        }
    }

    public void validarAtualizacao(Usuario usuario){
        if (usuario.getDataNascimento() != null && !isDataNascimentoValida(usuario.getDataNascimento())){
            throw new RuntimeException("Data de nascimento deve ser anterior à data atual");
        }
    }

    public static LocalDate buscarData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate d = LocalDate.parse(data, formatter);
        return d;
    }

    public static boolean isDataNascimentoValida(String dataNascimento) {
        LocalDate dataAtual = LocalDate.now();
        LocalDate data = buscarData(dataNascimento);

        if (dataAtual.isBefore(data)){
            return false;
        }
        return true;
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
        OffsetDateTime date = OffsetDateTime.now();
        usuario.setDataAtualizacao(date.truncatedTo(ChronoUnit.SECONDS));
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void alterarSenha(String id, String senhaAtual, String novaSenha) {
        Usuario usuario = buscar(id);

        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new RuntimeException("Senha informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(novaSenha);
    }

}
