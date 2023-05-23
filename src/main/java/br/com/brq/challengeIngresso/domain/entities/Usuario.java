package br.com.brq.challengeIngresso.domain.entities;

import lombok.Data;

import javax.persistence.*;
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
    private Long celular;
    private String sexo;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;
    @Embedded
    private Endereco endereco;

    public boolean senhaCoincideCom(String senha) {
        return getSenha().equals(senha);
    }

    public boolean senhaNaoCoincideCom(String senha) {
        return !senhaCoincideCom(senha);
    }
}
