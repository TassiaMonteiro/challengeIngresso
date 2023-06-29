package br.com.brq.challengeIngresso.usecase.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SenhaDomain {

    private String senhaAtual;
    private String novaSenha;
    private String codigoSeguranca;
}
