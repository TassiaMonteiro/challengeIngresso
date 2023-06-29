package br.com.brq.challengeIngresso.entrypoint.mapper.response;

import br.com.brq.challengeIngresso.entrypoint.model.response.EnderecoDto;
import br.com.brq.challengeIngresso.entrypoint.model.response.UsuarioDto;
import br.com.brq.challengeIngresso.usecase.domain.EnderecoDomain;
import br.com.brq.challengeIngresso.usecase.domain.UsuarioDomain;

public class EnderecoEntryPointMapperResponse {

    public static EnderecoDto convertToModel(EnderecoDomain enderecoDomain){

        return EnderecoDto.builder()
                .logradouro(enderecoDomain.getLogradouro())
                .complemento(enderecoDomain.getComplemento())
                .numero(enderecoDomain.getNumero())
                .bairro(enderecoDomain.getBairro())
                .cidade(enderecoDomain.getCidade())
                .estado(enderecoDomain.getEstado())
                .pais(enderecoDomain.getPais())
                .cep(enderecoDomain.getCep())
                .build();
    }
}
