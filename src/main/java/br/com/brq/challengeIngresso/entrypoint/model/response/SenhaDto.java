package br.com.brq.challengeIngresso.entrypoint.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SenhaDto {

    private String codigoSeguranca;
}
