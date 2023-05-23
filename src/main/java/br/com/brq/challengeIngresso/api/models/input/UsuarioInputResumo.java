package br.com.brq.challengeIngresso.api.models.input;

import br.com.brq.challengeIngresso.domain.entities.Endereco;
import br.com.brq.challengeIngresso.validation.Celular;
import br.com.brq.challengeIngresso.validation.DataNascimento;
import br.com.brq.challengeIngresso.validation.NomeCompleto;
import lombok.Data;

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
