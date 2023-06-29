package br.com.brq.challengeIngresso.entrypoint.mapper.response;

import br.com.brq.challengeIngresso.entrypoint.model.response.UsuarioDto;
import br.com.brq.challengeIngresso.entrypoint.model.response.UsuarioDtoResumo;
import br.com.brq.challengeIngresso.usecase.domain.UsuarioDomain;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioEntryPointMapperResponse {

    public static UsuarioDto convertToModel(UsuarioDomain usuarioDomain){

        return UsuarioDto.builder()
                .id(usuarioDomain.getId())
                .cpf(usuarioDomain.getCpf())
                .email(usuarioDomain.getEmail())
                .nomeCompleto(usuarioDomain.getNomeCompleto())
                .apelido(usuarioDomain.getApelido())
                .dataCadastro(usuarioDomain.getDataCadastro())
                .dataNascimento(usuarioDomain.getDataNascimento())
                .dataAtualizacao(usuarioDomain.getDataAtualizacao())
                .celular(usuarioDomain.getCelular())
                .sexo(usuarioDomain.getSexo())
                .endereco(EnderecoEntryPointMapperResponse.convertToModel(usuarioDomain.getEndereco()))
                .build();
    }

    public static List<UsuarioDtoResumo> convertToModelList(List<UsuarioDomain> listaUsuariosDomain){

        return listaUsuariosDomain.stream()
                .map(usuarioDomain -> convert(usuarioDomain))
                .collect(Collectors.toList());
    }

    public static UsuarioDtoResumo convert(UsuarioDomain usuarioDomain){

        return UsuarioDtoResumo.builder()
                .id(usuarioDomain.getId())
                .cpf(usuarioDomain.getCpf())
                .email(usuarioDomain.getEmail())
                .nomeCompleto(usuarioDomain.getNomeCompleto())
                .build();
    }
}
