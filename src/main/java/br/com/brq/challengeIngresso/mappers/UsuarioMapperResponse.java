package br.com.brq.challengeIngresso.mappers;

import br.com.brq.challengeIngresso.entities.Usuario;
import br.com.brq.challengeIngresso.models.UsuarioDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperResponse {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDto toDto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDto.class);
    }
}
