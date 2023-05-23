package br.com.brq.challengeIngresso.api.models.input;

import lombok.Data;

@Data
public class NovaSenhaInput {

    private String codigoSeguranca;
    private String novaSenha;
}
