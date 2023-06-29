package br.com.brq.challengeIngresso.entrypoint.model.request;

import br.com.brq.challengeIngresso.dataprovider.entities.Endereco;
import br.com.brq.challengeIngresso.entrypoint.validation.Celular;
import br.com.brq.challengeIngresso.entrypoint.validation.DataNascimento;
import br.com.brq.challengeIngresso.entrypoint.validation.NomeCompleto;
import br.com.brq.challengeIngresso.usecase.domain.EnderecoDomain;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UsuarioInputResumo {

    @NomeCompleto
    private String nomeCompleto;
    private String apelido;
    @DataNascimento
    private String dataNascimento;
    @Celular
    private Long celular;
    private String sexo;
    private EnderecoInput endereco;
}