package br.com.brq.challengeIngresso.controller;

import br.com.brq.challengeIngresso.entities.Usuario;
import br.com.brq.challengeIngresso.mappers.UsuarioMapperRequest;
import br.com.brq.challengeIngresso.mappers.UsuarioMapperResponse;
import br.com.brq.challengeIngresso.models.UsuarioDto;
import br.com.brq.challengeIngresso.models.UsuarioInput;
import br.com.brq.challengeIngresso.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/challengebrq/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioMapperRequest mapperRequest;

    @Autowired
    private UsuarioMapperResponse mapperResponse;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto cadastrar(@RequestBody UsuarioInput usuarioInput){
        Usuario usuario = mapperRequest.toEntity(usuarioInput);
        usuario = usuarioService.salvar(usuario);

        return mapperResponse.toDto(usuario);
    }
}