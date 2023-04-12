package br.com.brq.challengeIngresso.service;

import br.com.brq.challengeIngresso.entities.Usuario;
import br.com.brq.challengeIngresso.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        usuario.setId(UUID.randomUUID().toString());
        OffsetDateTime date = OffsetDateTime.now();
        usuario.setDataCadastro(date);
        return usuarioRepository.save(usuario);
    }
}
