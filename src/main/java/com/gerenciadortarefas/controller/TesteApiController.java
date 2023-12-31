package com.gerenciadortarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteApiController {
    @GetMapping("/teste-api")
    private String teste() {
        return "Sucesso!";
    }
    @GetMapping("/saudacao")
    public String Saudacao(@RequestParam(name = "nome") String nome) {
        return "Olá! " + nome + ", Seja bem vindo!";
    }
}
