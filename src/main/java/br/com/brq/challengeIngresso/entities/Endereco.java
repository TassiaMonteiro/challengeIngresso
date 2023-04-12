package br.com.brq.challengeIngresso.entities;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Endereco {

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
}
