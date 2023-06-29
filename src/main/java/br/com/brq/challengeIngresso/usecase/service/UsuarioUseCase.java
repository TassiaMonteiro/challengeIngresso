package br.com.brq.challengeIngresso.usecase.service;

import br.com.brq.challengeIngresso.usecase.domain.RecuperarSenhaDomain;
import br.com.brq.challengeIngresso.usecase.domain.SenhaDomain;
import br.com.brq.challengeIngresso.usecase.domain.UsuarioDomain;

import java.util.List;

public interface UsuarioUseCase {
    UsuarioDomain cadastrar(UsuarioDomain usuarioDomain);

    List<UsuarioDomain> listarUsuarios();

    UsuarioDomain buscarId(String id);

    void removerUsuario(String id);
    UsuarioDomain atualizarUsuario(String id, UsuarioDomain usuarioRequest);

    void alterarSenha(String id, SenhaDomain senha);

    UsuarioDomain gerarCodigoAlteracaoSenha(UsuarioDomain usuarioDomain);

    UsuarioDomain novaSenha(String id, RecuperarSenhaDomain senhaDomain);

}
