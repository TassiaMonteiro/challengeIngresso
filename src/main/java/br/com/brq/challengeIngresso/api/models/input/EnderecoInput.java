package br.com.brq.challengeIngresso.api.models.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class EnderecoInput {

    @NotBlank(message = "CEP é obrigatório.")
    @Length(max = 8)
    private String cep;
    @NotBlank(message = "Número é obrigatório.")
    @Length(max = 10)
    private String numero;
    @Length(max = 100)
    private String logradouro;
    @Length(max = 20)
    private String bairro;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Length(max = 50)
    private String complemento;
}
