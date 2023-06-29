package br.com.brq.challengeIngresso.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
@Builder
@Getter
public class UsuarioDto {

    private String id;
    private String cpf;
    private String email;
    private String nomeCompleto;
    private String apelido;
    private String dataNascimento;
    private Long celular;
    private String sexo;
    private OffsetDateTime dataCadastro;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private OffsetDateTime dataAtualizacao;
    private EnderecoDto endereco;
}
