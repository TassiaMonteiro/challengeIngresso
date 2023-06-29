package br.com.brq.challengeIngresso.entrypoint.mapper.request;

import br.com.brq.challengeIngresso.entrypoint.model.request.NovaSenhaInput;
import br.com.brq.challengeIngresso.usecase.domain.RecuperarSenhaDomain;

public class RecuperarSenhaEntryPointMapperRequest {

    public static RecuperarSenhaDomain convertToDomain(NovaSenhaInput senha){

        return RecuperarSenhaDomain.builder()
                .codigoSeguranca(senha.getCodigoSeguranca())
                .novaSenha(senha.getNovaSenha())
                .build();
    }
}
