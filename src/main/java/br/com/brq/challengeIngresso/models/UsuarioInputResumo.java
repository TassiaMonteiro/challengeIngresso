package br.com.brq.challengeIngresso.models;

import br.com.brq.challengeIngresso.entities.Endereco;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UsuarioInputResumo {

    private String nomeCompleto;
    private String apelido;
    private String dataNascimento;
    private BigDecimal celular;
    private String sexo;
    private Endereco endereco;
}
