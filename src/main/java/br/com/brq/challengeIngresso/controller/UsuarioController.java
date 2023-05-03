package br.com.brq.challengeIngresso.controller;

import br.com.brq.challengeIngresso.entities.Usuario;
import br.com.brq.challengeIngresso.mappers.UsuarioMapperRequest;
import br.com.brq.challengeIngresso.mappers.UsuarioMapperResponse;
import br.com.brq.challengeIngresso.models.*;
import br.com.brq.challengeIngresso.repository.UsuarioRepository;
import br.com.brq.challengeIngresso.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challengebrq/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapperRequest mapperRequest;

    @Autowired
    private UsuarioMapperResponse mapperResponse;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto cadastrar(@RequestBody UsuarioInput usuarioInput){
        Usuario usuario = mapperRequest.toEntity(usuarioInput);
        usuarioService.validarCadastro(usuario);
        usuario = usuarioService.salvar(usuario);

        return mapperResponse.toDto(usuario);
    }

    @GetMapping
    public List<UsuarioDtoResumo> listar(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return mapperResponse.toCollectionDto(usuarios);
    }

    @GetMapping("/{id}")
    public UsuarioDto buscar(@PathVariable String id){
        Usuario usuario = usuarioService.buscar(id);
        return mapperResponse.toDto(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable String id){
        Usuario usuario = usuarioService.buscar(id);
        usuarioService.remover(usuario);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDto atualizar(@PathVariable String id, @RequestBody UsuarioInputResumo usuarioInputResumo){
        Usuario usuario = usuarioService.buscar(id);
        mapperRequest.copyToEntity(usuarioInputResumo, usuario);
        usuarioService.validarAtualizacao(usuario);
        usuario = usuarioService.atualizar(usuario);

        return mapperResponse.toDto(usuario);
    }

    @PutMapping("/{id}/senhas")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable String id, @RequestBody SenhaInput senha) {
        usuarioService.alterarSenha(id, senha.getSenhaAtual(), senha.getNovaSenha());
    }

    @GetMapping("/{id}/senhas")
    @ResponseStatus(HttpStatus.OK)
    public SenhaDto gerarCodigo(@PathVariable String id){
        Usuario usuario = usuarioService.buscar(id);
        SenhaDto senhaDto = new SenhaDto();
        return usuarioService.gerarUUID(senhaDto.getId());
    }

    @PostMapping("/{id}/senhas")
    @ResponseStatus(HttpStatus.CREATED)
    public void novaSenha(@PathVariable String id, @RequestBody NovaSenhaInput novaSenhaInput){
        usuarioService.isUUID(novaSenhaInput.getCodigoSeguranca());
        usuarioService.novaSenha(id, novaSenhaInput.getNovaSenha());
    }

}
