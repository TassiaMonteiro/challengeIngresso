package br.com.brq.challengeIngresso.domain.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
@Data
public class Endereco {

    @NotBlank(message = "Logradouro é obrigatório.")
    @Length(max = 100)
    private String logradouro;

    @NotBlank(message = "Número é obrigatório.")
    @Length(max = 10)
    private String numero;

    @NotBlank(message = "Bairro é obrigatório.")
    @Length(max = 20)
    private String bairro;

    @NotBlank(message = "Cidade é obrigatório.")
    @Length(max = 20)
    private String cidade;

    @NotBlank(message = "Estado é obrigatório.")
    @Length(max = 2)
    private String estado;

    @NotBlank(message = "País é obrigatório.")
    @Length(max = 2)
    private String pais;

    @NotBlank(message = "CEP é obrigatório.")
    @Length(max = 8)
    private String cep;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Length(max = 50)
    private String complemento;
}
