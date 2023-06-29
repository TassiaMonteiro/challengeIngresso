package br.com.brq.challengeIngresso.usecase.gateway;

import br.com.brq.challengeIngresso.entrypoint.model.response.EnderecoDto;
import br.com.brq.challengeIngresso.entrypoint.model.response.EnderecoDtoViaCep;
import br.com.brq.challengeIngresso.usecase.domain.UsuarioDomain;

import java.util.List;

public interface UsuarioGateway {

    UsuarioDomain cadastrar(UsuarioDomain usuarioDomain);
    boolean verificaCpfExiste(String cpf);
    boolean verificaEmailExiste(String email);
    EnderecoDtoViaCep executa(String CEP);
    List<UsuarioDomain> listarUsuarios();
    UsuarioDomain detalharUsuario(String id);
    void removerUsuario(String id);
    UsuarioDomain atualizarUsuario(UsuarioDomain usuarioDomain);
}
