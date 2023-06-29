package br.com.brq.challengeIngresso.entrypoint.model.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NovaSenhaInput {

    private String codigoSeguranca;
    private String novaSenha;
}