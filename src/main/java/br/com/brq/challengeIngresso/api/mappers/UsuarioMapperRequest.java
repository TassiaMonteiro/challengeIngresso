package br.com.brq.challengeIngresso.api.mappers;

import br.com.brq.challengeIngresso.api.models.dto.EnderecoDto;
import br.com.brq.challengeIngresso.domain.entities.Sexo;
import br.com.brq.challengeIngresso.domain.entities.Usuario;
import br.com.brq.challengeIngresso.api.models.input.UsuarioInput;
import br.com.brq.challengeIngresso.api.models.input.UsuarioInputResumo;
import br.com.brq.challengeIngresso.domain.exception.NegocioException;
import br.com.brq.challengeIngresso.domain.service.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperRequest {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsuarioService usuarioService;

    public Usuario toEntity(UsuarioInput usuarioInput){
        Usuario usuario = modelMapper.map(usuarioInput, Usuario.class);
        usuario.setSexo(Sexo.buscarValor(usuarioInput.getSexo()));

        buscaEndereco(usuario);

        return usuario;
    }

    private void buscaEndereco(Usuario usuario) {
        EnderecoDto enderecoCorreio = usuarioService.executa(usuario.getEndereco().getCep());

        if (StringUtils.isBlank(enderecoCorreio.getLogradouro())){
            usuario.getEndereco().setLogradouro(usuario.getEndereco().getLogradouro());
        } else {
            usuario.getEndereco().setLogradouro(enderecoCorreio.getLogradouro());
        }

        if (StringUtils.isBlank(enderecoCorreio.getBairro())){
            usuario.getEndereco().setBairro(usuario.getEndereco().getBairro());
        } else {
            usuario.getEndereco().setBairro(enderecoCorreio.getBairro());
        }

        usuario.getEndereco().setNumero(usuario.getEndereco().getNumero());

        usuario.getEndereco().setComplemento(usuario.getEndereco().getComplemento());

        usuario.getEndereco().setCidade(enderecoCorreio.getLocalidade());

        usuario.getEndereco().setEstado(enderecoCorreio.getUf());
    }

    public void copyToEntity(UsuarioInputResumo usuarioInputResumo, Usuario usuario){

        if (StringUtils.isBlank(usuarioInputResumo.getNomeCompleto())){
            usuarioInputResumo.setNomeCompleto(usuario.getNomeCompleto());
        }

        if (StringUtils.isBlank(usuarioInputResumo.getApelido())){
            usuarioInputResumo.setApelido(usuario.getApelido());
        }

        if (StringUtils.isBlank(usuarioInputResumo.getDataNascimento())){
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
        }

        if (usuarioInputResumo.getEndereco().getCep() != null){
            alteraEndereco(usuarioInputResumo);
            usuarioInputResumo.getEndereco().setPais(usuario.getEndereco().getPais());
        }else {
            if (StringUtils.isBlank(usuarioInputResumo.getEndereco().getCep())){
                usuarioInputResumo.getEndereco().setCep(usuario.getEndereco().getCep());
            }
            if (StringUtils.isBlank(usuarioInputResumo.getEndereco().getLogradouro())) {
                usuarioInputResumo.getEndereco().setLogradouro(usuario.getEndereco().getLogradouro());
            }
            if (StringUtils.isBlank(usuarioInputResumo.getEndereco().getNumero())) {
                usuarioInputResumo.getEndereco().setNumero(usuario.getEndereco().getNumero());
            }
            if (StringUtils.isBlank(usuarioInputResumo.getEndereco().getBairro())) {
                usuarioInputResumo.getEndereco().setBairro(usuario.getEndereco().getBairro());
            }
            if (StringUtils.isBlank(usuarioInputResumo.getEndereco().getCidade())) {
                usuarioInputResumo.getEndereco().setCidade(usuario.getEndereco().getCidade());
            }
            if (StringUtils.isBlank(usuarioInputResumo.getEndereco().getEstado())) {
                usuarioInputResumo.getEndereco().setEstado(usuario.getEndereco().getEstado());
            }
            if (StringUtils.isBlank(usuarioInputResumo.getEndereco().getPais())) {
                usuarioInputResumo.getEndereco().setPais(usuario.getEndereco().getPais());
            }
        }

        modelMapper.map(usuarioInputResumo, usuario);
    }

    private void alteraEndereco(UsuarioInputResumo usuarioInputResumo) {
        EnderecoDto enderecoCorreio = usuarioService.executa(usuarioInputResumo.getEndereco().getCep());

        if (StringUtils.isBlank(enderecoCorreio.getLogradouro())){
            usuarioInputResumo.getEndereco().setLogradouro(usuarioInputResumo.getEndereco().getLogradouro());
        } else {
            usuarioInputResumo.getEndereco().setLogradouro(enderecoCorreio.getLogradouro());
        }

        if (StringUtils.isBlank(enderecoCorreio.getBairro())){
            usuarioInputResumo.getEndereco().setBairro(usuarioInputResumo.getEndereco().getBairro());
        } else {
            usuarioInputResumo.getEndereco().setBairro(enderecoCorreio.getBairro());
        }

        if (StringUtils.isBlank(usuarioInputResumo.getEndereco().getNumero())){
            throw new NegocioException("O campo número deve ser preenchido");
        } else {
            usuarioInputResumo.getEndereco().setNumero(usuarioInputResumo.getEndereco().getNumero());
        }

        usuarioInputResumo.getEndereco().setComplemento(usuarioInputResumo.getEndereco().getComplemento());

        usuarioInputResumo.getEndereco().setCidade(enderecoCorreio.getLocalidade());

        usuarioInputResumo.getEndereco().setEstado(enderecoCorreio.getUf());
    }

}
