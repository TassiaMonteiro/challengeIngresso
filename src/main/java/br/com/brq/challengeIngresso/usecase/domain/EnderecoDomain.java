package br.com.brq.challengeIngresso.usecase.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDomain {

    String logradouro;
    String numero;
    String bairro;
    String cidade;
    String estado;
    String pais;
    String cep;
    @JsonIgnoreProperties(ignoreUnknown = true)
    String complemento;
}
