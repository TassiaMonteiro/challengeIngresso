package br.com.brq.challengeIngresso.service;

import br.com.brq.challengeIngresso.entities.Usuario;
import br.com.brq.challengeIngresso.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(Usuario usuario){

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

    public Usuario salvar(Usuario usuario){
        usuario.setId(UUID.randomUUID().toString());
        OffsetDateTime date = OffsetDateTime.now();
        usuario.setDataCadastro(date);
        return usuarioRepository.save(usuario);
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

}
