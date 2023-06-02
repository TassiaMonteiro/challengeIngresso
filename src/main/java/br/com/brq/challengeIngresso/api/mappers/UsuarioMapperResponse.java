package br.com.brq.challengeIngresso.api.mappers;

import br.com.brq.challengeIngresso.api.models.dto.SenhaDto;
import br.com.brq.challengeIngresso.domain.entities.Sexo;
import br.com.brq.challengeIngresso.domain.entities.Usuario;
import br.com.brq.challengeIngresso.api.models.dto.UsuarioDto;
import br.com.brq.challengeIngresso.api.models.dto.UsuarioDtoResumo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapperResponse {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDto toDto(Usuario usuario) {

        UsuarioDto usuarioDto = modelMapper.map(usuario, UsuarioDto.class);
        usuarioDto.setSexo(Sexo.buscarSigla(usuario.getSexo()));
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

    public SenhaDto codigoToDto(Usuario usuario){
        SenhaDto senhaDto = new SenhaDto();
        senhaDto.setCodigoSeguranca(usuario.getCodigoSeguranca());
        return senhaDto;
    }

}
