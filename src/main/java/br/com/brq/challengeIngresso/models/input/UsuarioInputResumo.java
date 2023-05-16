package br.com.brq.challengeIngresso.models.input;

import br.com.brq.challengeIngresso.entities.Endereco;
import br.com.brq.challengeIngresso.validation.Celular;
import br.com.brq.challengeIngresso.validation.DataNascimento;
import br.com.brq.challengeIngresso.validation.NomeCompleto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UsuarioInputResumo {

    @NomeCompleto
    private String nomeCompleto;
    private String apelido;
    @DataNascimento
    private String dataNascimento;
    @Celular
    private Long celular;
    private String sexo;
    private Endereco endereco;
}
