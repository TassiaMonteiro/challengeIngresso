package br.com.brq.challengeIngresso.entrypoint.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SenhaInput {

    private String senhaAtual;
    private String novaSenha;
}