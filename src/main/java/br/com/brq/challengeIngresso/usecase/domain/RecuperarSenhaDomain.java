package br.com.brq.challengeIngresso.usecase.domain;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecuperarSenhaDomain {

    private String senhaAtual;
    private String novaSenha;
    private String codigoSeguranca;
}
