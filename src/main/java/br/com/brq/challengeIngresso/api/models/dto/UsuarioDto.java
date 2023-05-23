package br.com.brq.challengeIngresso.api.models.dto;

import br.com.brq.challengeIngresso.domain.entities.Endereco;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
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
    private Endereco endereco;
}
