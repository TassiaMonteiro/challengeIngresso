package br.com.brq.challengeIngresso.models;

import br.com.brq.challengeIngresso.entities.Endereco;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class UsuarioDto {

    private String id;
    private String cpf;
    private String email;
    private String nomeCompleto;
    private String senha;
    private String apelido;
    private String dataNascimento;
    private BigDecimal celular;
    private int sexo;
    private OffsetDateTime dataCadastro;
    private Endereco endereco;
}
