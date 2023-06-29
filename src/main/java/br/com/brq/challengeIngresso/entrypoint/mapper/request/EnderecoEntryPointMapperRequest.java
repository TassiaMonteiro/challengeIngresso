package br.com.brq.challengeIngresso.entrypoint.mapper.request;

import br.com.brq.challengeIngresso.entrypoint.model.request.EnderecoInput;
import br.com.brq.challengeIngresso.entrypoint.model.request.UsuarioInput;
import br.com.brq.challengeIngresso.usecase.domain.EnderecoDomain;
import br.com.brq.challengeIngresso.usecase.domain.UsuarioDomain;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoField;

public class EnderecoEntryPointMapperRequest {

    public static EnderecoDomain convertToDomain(EnderecoInput enderecoInput){

        return EnderecoDomain.builder()
                .cep(enderecoInput.getCep())
                .numero(enderecoInput.getNumero())
                .logradouro(enderecoInput.getLogradouro())
                .bairro(enderecoInput.getBairro())
                .complemento(enderecoInput.getComplemento())
                .build();
    }
}
