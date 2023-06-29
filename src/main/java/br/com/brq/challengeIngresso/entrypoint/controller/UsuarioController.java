package br.com.brq.challengeIngresso.entrypoint.controller;

import br.com.brq.challengeIngresso.entrypoint.mapper.request.RecuperarSenhaEntryPointMapperRequest;
import br.com.brq.challengeIngresso.entrypoint.mapper.request.SenhaEntryPointMapperRequest;
import br.com.brq.challengeIngresso.entrypoint.mapper.request.UsuarioEntryPointMapperRequest;
import br.com.brq.challengeIngresso.entrypoint.mapper.response.SenhaEntryPointMapperResponse;
import br.com.brq.challengeIngresso.entrypoint.mapper.response.UsuarioEntryPointMapperResponse;
import br.com.brq.challengeIngresso.entrypoint.model.request.NovaSenhaInput;
import br.com.brq.challengeIngresso.entrypoint.model.request.SenhaInput;
import br.com.brq.challengeIngresso.entrypoint.model.request.UsuarioInput;
import br.com.brq.challengeIngresso.entrypoint.model.request.UsuarioInputResumo;
import br.com.brq.challengeIngresso.entrypoint.model.response.SenhaDto;
import br.com.brq.challengeIngresso.entrypoint.model.response.UsuarioDto;
import br.com.brq.challengeIngresso.entrypoint.model.response.UsuarioDtoResumo;
import br.com.brq.challengeIngresso.usecase.domain.RecuperarSenhaDomain;
import br.com.brq.challengeIngresso.usecase.domain.SenhaDomain;
import br.com.brq.challengeIngresso.usecase.domain.UsuarioDomain;
import br.com.brq.challengeIngresso.usecase.service.UsuarioUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/challengebrq/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioUseCase usuarioUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto cadastrarUsuario(@RequestBody @Valid UsuarioInput usuarioInput){
        UsuarioDomain usuarioDomain = UsuarioEntryPointMapperRequest.convertToDomain(usuarioInput);
        usuarioDomain = usuarioUseCase.cadastrar(usuarioDomain);
        UsuarioDto usuarioDto = UsuarioEntryPointMapperResponse.convertToModel(usuarioDomain);
        return usuarioDto;
    }

    @GetMapping
    public List<UsuarioDtoResumo> listarUsuario(){
        List<UsuarioDomain> listaUsuarioDomain = usuarioUseCase.listarUsuarios();
        List<UsuarioDtoResumo> usuarios = UsuarioEntryPointMapperResponse.convertToModelList(listaUsuarioDomain);
        return usuarios;
    }

    @GetMapping("/{id}")
    public UsuarioDto detalharUsuario(@PathVariable String id){
        UsuarioDomain usuarioDomain = usuarioUseCase.buscarId(id);
        UsuarioDto usuario = UsuarioEntryPointMapperResponse.convertToModel(usuarioDomain);
        return usuario;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerUsuario(@PathVariable String id){
        usuarioUseCase.removerUsuario(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDto atualizarUsuario(@PathVariable String id, @RequestBody @Valid UsuarioInputResumo usuarioInputResumo){

        UsuarioDomain usuarioDomain = UsuarioEntryPointMapperRequest.convertToDomainUpdate(usuarioInputResumo);
        usuarioDomain = usuarioUseCase.atualizarUsuario(id, usuarioDomain);
        UsuarioDto usuarioDto = UsuarioEntryPointMapperResponse.convertToModel(usuarioDomain);

        return usuarioDto;
    }

    @PutMapping("/{id}/senhas")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable String id, @RequestBody SenhaInput senha){
        SenhaDomain senhaDomain = SenhaEntryPointMapperRequest.convertToDomain(senha);
        usuarioUseCase.alterarSenha(id, senhaDomain);
    }

    @GetMapping("/{id}/senhas")
    @ResponseStatus(HttpStatus.OK)
    public SenhaDto gerarCodigoAlteracaoSenha(@PathVariable String id){
        UsuarioDomain usuarioDomain = usuarioUseCase.buscarId(id);
        usuarioDomain  = usuarioUseCase.gerarCodigoAlteracaoSenha(usuarioDomain);
        SenhaDto senhaDto = SenhaEntryPointMapperResponse.convertToModel(usuarioDomain);
        return senhaDto;
    }

    @PostMapping("/{id}/senhas")
    @ResponseStatus(HttpStatus.CREATED)
    public void recuperarSenha(@PathVariable String id, @RequestBody NovaSenhaInput novaSenhaInput){
        RecuperarSenhaDomain senha = RecuperarSenhaEntryPointMapperRequest.convertToDomain(novaSenhaInput);
        UsuarioDomain usuarioDomainAtualizado = usuarioUseCase.novaSenha(id,senha);
        UsuarioDto usuarioDtoAtualizado = UsuarioEntryPointMapperResponse.convertToModel(usuarioDomainAtualizado);
    }

}
