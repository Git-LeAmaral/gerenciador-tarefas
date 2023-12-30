package com.gerenciadortarefas.controller;

import com.gerenciadortarefas.entity.Tarefa;
import com.gerenciadortarefas.entity.Usuario;
import com.gerenciadortarefas.request.CadastrarTarefaRequest;
import com.gerenciadortarefas.response.CadastrarTarefaResponse;
import com.gerenciadortarefas.service.GerenciadorTarefasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gerenciador-tarefas")
public class GerenciadorTarefasController {

    @Autowired
    private GerenciadorTarefasService gerenciadorTarefasService;

    @PostMapping
    public ResponseEntity<CadastrarTarefaResponse> salvarTerefa(@RequestBody CadastrarTarefaRequest request) {
        Tarefa tarefaSalva = gerenciadorTarefasService.salvarTarefa(request);

        CadastrarTarefaResponse response = CadastrarTarefaResponse.builder()
                .id(tarefaSalva.getId())
                .titulo(tarefaSalva.getTitulo())
                .descricao(tarefaSalva.getDescricao())
                .criador(tarefaSalva.getCriador().getUsername())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
