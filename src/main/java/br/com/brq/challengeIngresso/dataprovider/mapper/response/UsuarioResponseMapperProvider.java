package br.com.brq.challengeIngresso.dataprovider.mapper.response;

import br.com.brq.challengeIngresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.challengeIngresso.dataprovider.enums.Sexo;
import br.com.brq.challengeIngresso.dataprovider.mapper.request.EnderecoRequestMapperProvider;
import br.com.brq.challengeIngresso.usecase.domain.UsuarioDomain;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioResponseMapperProvider {

    public static UsuarioDomain convertToDomain(UsuarioEntity usuarioEntity){

        return UsuarioDomain.builder()
                .id(usuarioEntity.getId())
                .cpf(usuarioEntity.getCpf())
                .email(usuarioEntity.getEmail())
                .nomeCompleto(usuarioEntity.getNomeCompleto())
                .senha(usuarioEntity.getSenha())
                .apelido(usuarioEntity.getApelido())
                .dataCadastro(usuarioEntity.getDataCadastro())
                .dataNascimento(usuarioEntity.getDataNascimento())
                .dataAtualizacao(usuarioEntity.getDataAtualizacao())
                .celular(usuarioEntity.getCelular())
                .sexo(Sexo.buscarSigla(usuarioEntity.getSexo()))
                .codigoSeguranca(usuarioEntity.getCodigoSeguranca())
                .dataHoraCodigoSeguranca(usuarioEntity.getDataHoraCodigoSeguranca())
                .endereco(EnderecoResponseMapperProvider.convertToModel(usuarioEntity.getEndereco()))
                .build();
    }

    public static List<UsuarioDomain> convertToDomainList(List<UsuarioEntity> usuariosEntity) {
        return usuariosEntity.stream()
                .map(usuarioEntity -> convert(usuarioEntity))
                .collect(Collectors.toList());
    }

    public static UsuarioDomain convert(UsuarioEntity usuarioEntity){
        return UsuarioDomain.builder()
                .id(usuarioEntity.getId())
                .cpf(usuarioEntity.getCpf())
                .email(usuarioEntity.getEmail())
                .nomeCompleto(usuarioEntity.getNomeCompleto())
                .build();
    }
}
