package br.com.brq.challengeIngresso.models.input;

import br.com.brq.challengeIngresso.entities.Endereco;
import br.com.brq.challengeIngresso.validation.CPF;
import br.com.brq.challengeIngresso.validation.Celular;
import br.com.brq.challengeIngresso.validation.DataNascimento;
import br.com.brq.challengeIngresso.validation.NomeCompleto;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
public class UsuarioInput {

    @CPF
    @NotBlank(message = "CPF é obrigatório.")
    private String cpf;

    @Email
    @NotBlank(message = "Email é obrigatório.")
    private String email;

    @NomeCompleto
    @NotBlank(message = "Nome é obrigatório.")
    @Length(max = 100)
    private String nomeCompleto;

    @NotBlank(message = "Senha é obrigatório.")
    @Length(max = 100)
    private String senha;

    @Length(max = 20)
    private String apelido;

    @DataNascimento
    @NotBlank(message = "Data de nascimento é obrigatório.")
    private String dataNascimento;

    @Celular
    @NotNull(message = "Celular é obrigatório.")
    private Long celular;

    @NotBlank(message = "Sexo é obrigatório.")
    @Length(max = 2)
    private String sexo;

    @Valid
    @NotNull(message = "Endereço é obrigatório.")
    private Endereco endereco;

}
