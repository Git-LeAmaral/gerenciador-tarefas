package com.gerenciadortarefas.controller;

import com.gerenciadortarefas.entity.Usuario;
import com.gerenciadortarefas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> salvarUsuario(@RequestBody Usuario usuario) {

        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);

        return new ResponseEntity<>("Novo usuário criado " + usuarioSalvo.getUsername(), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<String> atualizaUsuario(@RequestBody Usuario usuario) {

        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);

        return new ResponseEntity<>("Usuário atualizado " + usuarioSalvo.getUsername(), HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<Usuario>> obtemUsuarios() {
        return new ResponseEntity<>(usuarioService.obtemUsuarios(), HttpStatus.OK);
    }
    @DeleteMapping
    public void excluirUsuario(@RequestBody Usuario usuario) {
        usuarioService.excluirUsuario(usuario);
    }

}
