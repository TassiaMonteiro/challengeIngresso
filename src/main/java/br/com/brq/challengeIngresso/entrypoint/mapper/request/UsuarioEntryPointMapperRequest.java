package br.com.brq.challengeIngresso.entrypoint.mapper.request;

import br.com.brq.challengeIngresso.entrypoint.model.request.UsuarioInput;
import br.com.brq.challengeIngresso.entrypoint.model.request.UsuarioInputResumo;
import br.com.brq.challengeIngresso.usecase.domain.UsuarioDomain;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoField;

public class UsuarioEntryPointMapperRequest {

    public static UsuarioDomain convertToDomain(UsuarioInput usuarioInput){
        return UsuarioDomain.builder()
                .cpf(usuarioInput.getCpf())
                .email(usuarioInput.getEmail())
                .nomeCompleto(usuarioInput.getNomeCompleto())
                .senha(usuarioInput.getSenha())
                .apelido(usuarioInput.getApelido())
                .dataCadastro(OffsetDateTime.now().with(ChronoField.MILLI_OF_SECOND, 0))
                .dataNascimento(usuarioInput.getDataNascimento())
                .celular(usuarioInput.getCelular())
                .sexo(usuarioInput.getSexo())
                .endereco(EnderecoEntryPointMapperRequest.convertToDomain(usuarioInput.getEndereco()))
                .build();
    }

    public static UsuarioDomain convertToDomainUpdate(UsuarioInputResumo usuarioInputResumo) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        UsuarioDomain usuario = objectMapper.convertValue(usuarioInputResumo, UsuarioDomain.class);

        return usuario;
    }
}
