package br.com.brq.challengeIngresso.mappers;

import br.com.brq.challengeIngresso.entities.Sexo;
import br.com.brq.challengeIngresso.entities.Usuario;
import br.com.brq.challengeIngresso.models.UsuarioInput;
import br.com.brq.challengeIngresso.models.UsuarioInputResumo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperRequest {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toEntity(UsuarioInput usuarioInput){

        Usuario usuario = modelMapper.map(usuarioInput, Usuario.class);
        usuario.setSexo(Sexo.buscarValor(usuarioInput.getSexo()));
        return usuario;
    }

    public void copyToEntity(UsuarioInputResumo usuarioInputResumo, Usuario usuario){

        if (usuarioInputResumo.getNomeCompleto() == null){
            usuarioInputResumo.setNomeCompleto(usuario.getNomeCompleto());
        }

        if (usuarioInputResumo.getApelido() == null){
            usuarioInputResumo.setApelido(usuario.getApelido());
        }

        if (usuarioInputResumo.getDataNascimento() == null){
            usuarioInputResumo.setDataNascimento(usuario.getDataNascimento());
        }

        if (usuarioInputResumo.getCelular() == null){
            usuarioInputResumo.setCelular(usuario.getCelular());
        } else

        if(usuarioInputResumo.getSexo() != null) {
            usuarioInputResumo.setSexo(Sexo.buscarValor(usuarioInputResumo.getSexo()));
        }

        if (usuarioInputResumo.getSexo() == null){
            usuarioInputResumo.setSexo(usuario.getSexo());
        }

        if (usuarioInputResumo.getEndereco() == null){
            usuarioInputResumo.setEndereco(usuario.getEndereco());
        }

        modelMapper.map(usuarioInputResumo, usuario);
    }

}
