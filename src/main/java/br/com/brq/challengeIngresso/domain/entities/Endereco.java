package br.com.brq.challengeIngresso.domain.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.Embeddable;

@Embeddable
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;

}
