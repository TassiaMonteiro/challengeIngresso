package br.com.brq.challengeIngresso.dataprovider.mapper.request;

import br.com.brq.challengeIngresso.dataprovider.entities.UsuarioEntity;
import br.com.brq.challengeIngresso.dataprovider.enums.Sexo;
import br.com.brq.challengeIngresso.usecase.domain.UsuarioDomain;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoField;

public class UsuarioRequestMapperProvider {

    public static UsuarioEntity convertToEntity(UsuarioDomain usuarioDomain){

        return UsuarioEntity.builder()
                .id(usuarioDomain.getId())
                .cpf(usuarioDomain.getCpf())
                .email(usuarioDomain.getEmail())
                .nomeCompleto(usuarioDomain.getNomeCompleto())
                .senha(usuarioDomain.getSenha())
                .apelido(usuarioDomain.getApelido())
                .dataCadastro(usuarioDomain.getDataCadastro())
                .dataNascimento(usuarioDomain.getDataNascimento())
                .celular(usuarioDomain.getCelular())
                .sexo(Sexo.buscarValor(usuarioDomain.getSexo()))
                .endereco(EnderecoRequestMapperProvider.convertToEntity(usuarioDomain.getEndereco()))
                .build();
    }

    public static UsuarioEntity convertToEntityUpdate(UsuarioDomain usuarioDomain) {

        return UsuarioEntity.builder()
                .id(usuarioDomain.getId())
                .cpf(usuarioDomain.getCpf())
                .email(usuarioDomain.getEmail())
                .senha(usuarioDomain.getSenha())
                .dataAtualizacao(OffsetDateTime.now().with(ChronoField.MILLI_OF_SECOND, 0))
                .nomeCompleto(usuarioDomain.getNomeCompleto())
                .apelido(usuarioDomain.getApelido())
                .dataCadastro(OffsetDateTime.now().with(ChronoField.MILLI_OF_SECOND, 0))
                .dataNascimento(usuarioDomain.getDataNascimento())
                .celular(usuarioDomain.getCelular())
                .sexo(Sexo.buscarValor(usuarioDomain.getSexo()))
                .codigoSeguranca(usuarioDomain.getCodigoSeguranca())
                .dataHoraCodigoSeguranca(usuarioDomain.getDataHoraCodigoSeguranca())
                .endereco(EnderecoRequestMapperProvider.convertToEntity(usuarioDomain.getEndereco()))
                .build();
    }
}
