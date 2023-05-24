package br.com.brq.challengeIngresso.api.mappers;

import br.com.brq.challengeIngresso.api.models.input.SenhaInput;
import br.com.brq.challengeIngresso.domain.entities.Sexo;
import br.com.brq.challengeIngresso.domain.entities.Usuario;
import br.com.brq.challengeIngresso.api.models.input.UsuarioInput;
import br.com.brq.challengeIngresso.api.models.input.UsuarioInputResumo;
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

        if (isCampoEmbranco(usuarioInputResumo.getNomeCompleto())){
            usuarioInputResumo.setNomeCompleto(usuario.getNomeCompleto());
        }

        if (isCampoEmbranco(usuarioInputResumo.getApelido())){
            usuarioInputResumo.setApelido(usuario.getApelido());
        }

        if (isCampoEmbranco(usuarioInputResumo.getDataNascimento())){
            usuarioInputResumo.setDataNascimento(usuario.getDataNascimento());
        }

        if (usuarioInputResumo.getCelular() == null){
            usuarioInputResumo.setCelular(usuario.getCelular());
        }

        if(usuarioInputResumo.getSexo() != null) {
            usuarioInputResumo.setSexo(Sexo.buscarValor(usuarioInputResumo.getSexo()));
        }

        if (usuarioInputResumo.getSexo() == null){
            usuarioInputResumo.setSexo(usuario.getSexo());
        }

        if (usuarioInputResumo.getEndereco() == null){
            usuarioInputResumo.setEndereco(usuario.getEndereco());
        } else {
            if (isCampoEmbranco(usuarioInputResumo.getEndereco().getLogradouro())){
                usuarioInputResumo.getEndereco().setLogradouro(usuario.getEndereco().getLogradouro());
            }
            if (isCampoEmbranco(usuarioInputResumo.getEndereco().getNumero())){
                usuarioInputResumo.getEndereco().setNumero(usuario.getEndereco().getNumero());
            }
            if (isCampoEmbranco(usuarioInputResumo.getEndereco().getBairro())){
                usuarioInputResumo.getEndereco().setBairro(usuario.getEndereco().getBairro());
            }
            if (isCampoEmbranco(usuarioInputResumo.getEndereco().getCidade())){
                usuarioInputResumo.getEndereco().setCidade(usuario.getEndereco().getCidade());
            }
            if (isCampoEmbranco(usuarioInputResumo.getEndereco().getEstado())){
                usuarioInputResumo.getEndereco().setEstado(usuario.getEndereco().getEstado());
            }
            if (isCampoEmbranco(usuarioInputResumo.getEndereco().getPais())){
                usuarioInputResumo.getEndereco().setPais(usuario.getEndereco().getPais());
            }
            if (isCampoEmbranco(usuarioInputResumo.getEndereco().getCep())){
                usuarioInputResumo.getEndereco().setCep(usuario.getEndereco().getCep());
            }
        }

        modelMapper.map(usuarioInputResumo, usuario);
    }

    private static boolean isCampoEmbranco(String campo){
        return campo == null || campo.isBlank();
    }

}
