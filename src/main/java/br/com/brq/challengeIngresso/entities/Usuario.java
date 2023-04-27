package br.com.brq.challengeIngresso.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Data
public class Usuario {

    @Id
    private String id;
    private String cpf;
    private String email;
    private String nomeCompleto;
    private String senha;
    private String apelido;
    private String dataNascimento;
    private BigDecimal celular;
    private String sexo;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;
    @Embedded
    private Endereco endereco;
}
