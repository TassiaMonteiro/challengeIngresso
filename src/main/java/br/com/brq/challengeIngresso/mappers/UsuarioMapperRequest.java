package br.com.brq.challengeIngresso.mappers;

import br.com.brq.challengeIngresso.entities.Usuario;
import br.com.brq.challengeIngresso.models.UsuarioInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperRequest {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toEntity(UsuarioInput usuarioInput){
        return modelMapper.map(usuarioInput, Usuario.class);
    }
}
