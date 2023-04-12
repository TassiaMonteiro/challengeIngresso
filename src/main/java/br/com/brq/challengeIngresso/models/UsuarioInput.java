package br.com.brq.challengeIngresso.models;

import br.com.brq.challengeIngresso.entities.Endereco;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UsuarioInput {

    private String cpf;
    private String email;
    private String nomeCompleto;
    private String senha;
    private String apelido;
    private String dataNascimento;
    private BigDecimal celular;
    private int sexo;
    private Endereco endereco;

}
