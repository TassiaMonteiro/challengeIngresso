package br.com.brq.challengeIngresso.mappers;

import br.com.brq.challengeIngresso.entities.Sexo;
import br.com.brq.challengeIngresso.entities.Usuario;
import br.com.brq.challengeIngresso.models.dto.UsuarioDto;
import br.com.brq.challengeIngresso.models.dto.UsuarioDtoResumo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class UsuarioMapperResponse {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDto toDto(Usuario usuario) {

        UsuarioDto usuarioDto = modelMapper.map(usuario, UsuarioDto.class);
        usuarioDto.setSexo(Sexo.buscarSigla(usuario.getSexo()));
        usuarioDto.getEndereco().setEstado(usuario.getEndereco().getEstado().toUpperCase());
        return usuarioDto;
    }

    public UsuarioDtoResumo toResumodto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDtoResumo.class);
    }

    public List<UsuarioDtoResumo> toCollectionDto(List<Usuario> usuarios){
        return usuarios.stream()
                .map(usuario -> toResumodto(usuario))
                .collect(Collectors.toList());
    }

}
