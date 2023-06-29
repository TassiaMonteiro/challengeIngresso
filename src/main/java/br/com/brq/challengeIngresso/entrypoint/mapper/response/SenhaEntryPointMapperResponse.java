package br.com.brq.challengeIngresso.entrypoint.mapper.response;

import br.com.brq.challengeIngresso.entrypoint.model.response.SenhaDto;
import br.com.brq.challengeIngresso.usecase.domain.UsuarioDomain;

public class SenhaEntryPointMapperResponse {
    public static SenhaDto convertToModel(UsuarioDomain usuarioDomain){

        return SenhaDto.builder()
                .codigoSeguranca(usuarioDomain.getCodigoSeguranca())
                .build();
    }
}
