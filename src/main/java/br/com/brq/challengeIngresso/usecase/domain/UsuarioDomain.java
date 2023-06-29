package br.com.brq.challengeIngresso.usecase.domain;

import lombok.*;
import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDomain {

    String id;
    String cpf;
    String email;
    String nomeCompleto;
    String senha;
    String apelido;
    String dataNascimento;
    Long celular;
    String sexo;
    OffsetDateTime dataCadastro;
    OffsetDateTime dataAtualizacao;
    String codigoSeguranca;
    OffsetDateTime dataHoraCodigoSeguranca;
    EnderecoDomain endereco;

    public boolean senhaCoincideCom(String senha) {
        return getSenha().equals(senha);
    }

    public boolean senhaNaoCoincideCom(String senha) {
        return !senhaCoincideCom(senha);
    }
}
