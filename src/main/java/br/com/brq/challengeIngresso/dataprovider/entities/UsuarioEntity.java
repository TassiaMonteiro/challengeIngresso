package br.com.brq.challengeIngresso.dataprovider.entities;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UsuarioEntity {

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
    private String codigoSeguranca;
    private OffsetDateTime dataHoraCodigoSeguranca;
    @Embedded
    private Endereco endereco;

}
