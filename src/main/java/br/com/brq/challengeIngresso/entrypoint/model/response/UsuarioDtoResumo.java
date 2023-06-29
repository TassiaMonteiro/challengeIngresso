package br.com.brq.challengeIngresso.entrypoint.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UsuarioDtoResumo {

    private String id;
    private String cpf;
    private String email;
    private String nomeCompleto;
}
