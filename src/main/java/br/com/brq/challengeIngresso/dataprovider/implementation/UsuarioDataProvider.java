package br.com.brq.challengeIngresso.dataprovider.implementation;

import br.com.brq.challengeIngresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.challengeIngresso.dataprovider.exception.EntidadeNaoEncontradaException;
import br.com.brq.challengeIngresso.dataprovider.feign.ViaCepFeign;
import br.com.brq.challengeIngresso.dataprovider.mapper.request.UsuarioRequestMapperProvider;
import br.com.brq.challengeIngresso.dataprovider.mapper.response.UsuarioResponseMapperProvider;
import br.com.brq.challengeIngresso.dataprovider.repository.UsuarioRepository;
import br.com.brq.challengeIngresso.entrypoint.model.response.EnderecoDtoViaCep;
import br.com.brq.challengeIngresso.usecase.domain.UsuarioDomain;
import br.com.brq.challengeIngresso.usecase.exception.ApiExternaException;
import br.com.brq.challengeIngresso.usecase.gateway.UsuarioGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioDataProvider implements UsuarioGateway {

    @Autowired
    private ViaCepFeign viaCepFeign;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDomain cadastrar(UsuarioDomain usuarioDomain) {
        UsuarioEntity usuarioEntity = UsuarioRequestMapperProvider.convertToEntity(usuarioDomain);
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        usuarioDomain = UsuarioResponseMapperProvider.convertToDomain(usuarioEntity);

        return usuarioDomain;
    }

    @Override
    public boolean verificaCpfExiste(String cpf) {
        return usuarioRepository.existsByCpf(cpf);
    }

    @Override
    public boolean verificaEmailExiste(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public EnderecoDtoViaCep executa(String CEP) {
        try {
            return viaCepFeign.buscaEnderecoCep(CEP);
        }catch (Exception e){
            throw new ApiExternaException("Houve um erro de comunicação com a api externa.");
        }
    }

    @Override
    public List<UsuarioDomain> listarUsuarios() {
        List<UsuarioEntity> usuariosEntity = usuarioRepository.findAll();
        List<UsuarioDomain> usuariosDomain = UsuarioResponseMapperProvider.convertToDomainList(usuariosEntity);
        return usuariosDomain;
    }

    @Override
    public UsuarioDomain detalharUsuario(String id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O usuario com o id " + id + " especificado, nao foi encontrado na base de dados."));
        UsuarioDomain usuarioDomain = UsuarioResponseMapperProvider.convertToDomain(usuarioEntity);
        return usuarioDomain;
    }

    @Override
    public void removerUsuario(String id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("O usuario com o id " + id + " especificado, nao foi encontrado na base de dados."));
        usuarioRepository.delete(usuarioEntity);
    }

    @Override
    public UsuarioDomain atualizarUsuario(UsuarioDomain usuarioDomain) {
        UsuarioEntity usuarioEntity = UsuarioRequestMapperProvider.convertToEntityUpdate(usuarioDomain);
        usuarioEntity = usuarioRepository.save(usuarioEntity);

        usuarioDomain = UsuarioResponseMapperProvider.convertToDomain(usuarioEntity);
        return usuarioDomain;
    }
}
