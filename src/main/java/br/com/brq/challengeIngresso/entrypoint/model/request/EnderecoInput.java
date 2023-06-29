package br.com.brq.challengeIngresso.entrypoint.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Builder
@Getter
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