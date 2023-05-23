package br.com.brq.challengeIngresso.api.models.dto;

import lombok.Data;

@Data
public class UsuarioDtoResumo {

    private String id;
    private String cpf;
    private String email;
    private String nomeCompleto;
}
