package br.com.brq.challengeIngresso.usecase.mapper;

import br.com.brq.challengeIngresso.entrypoint.model.response.EnderecoDtoViaCep;
import br.com.brq.challengeIngresso.usecase.domain.EnderecoDomain;

public class EnderecoViaCepUseCaseMapperRequest {

    public static EnderecoDomain converteToDomain(EnderecoDtoViaCep endereco){
        return EnderecoDomain.builder()
                .cep(endereco.getCep())
                .logradouro(endereco.getLogradouro())
                .complemento(endereco.getComplemento())
                .bairro(endereco.getBairro())
                .cidade(endereco.getLocalidade())
                .estado(endereco.getUf())
                .build();
    }
}
