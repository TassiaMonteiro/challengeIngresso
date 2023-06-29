package br.com.brq.challengeIngresso.entrypoint.mapper.request;

import br.com.brq.challengeIngresso.entrypoint.model.request.SenhaInput;
import br.com.brq.challengeIngresso.usecase.domain.SenhaDomain;

public class SenhaEntryPointMapperRequest {

    public static SenhaDomain convertToDomain(SenhaInput senhaInput){

        return SenhaDomain.builder()
                .senhaAtual(senhaInput.getSenhaAtual())
                .novaSenha(senhaInput.getNovaSenha())
                .build();

    }
}
