package com.gerenciadortarefas.controller;

import com.gerenciadortarefas.entity.Tarefa;
import com.gerenciadortarefas.request.CadastrarTarefaRequest;
import com.gerenciadortarefas.response.CadastrarTarefaResponse;
import com.gerenciadortarefas.response.ObterTarefasPaginadaResponse;
import com.gerenciadortarefas.response.ObterTarefasResponse;
import com.gerenciadortarefas.service.GerenciadorTarefasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<ObterTarefasPaginadaResponse> obterTarefas(
            @RequestParam(required = false) String titulo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<Tarefa> tarefasPaginada = null;

        if (titulo == null) {
            tarefasPaginada = this.gerenciadorTarefasService.obtemTodasTarefas(PageRequest.of(page, size));
        } else {
            tarefasPaginada = this.gerenciadorTarefasService.obtemTarefasPorTitulo(titulo, PageRequest.of(page, size));
        }

        List<ObterTarefasResponse> tarefas = tarefasPaginada
                .getContent()
                .stream()
                .map(tarefa -> {
                    return ObterTarefasResponse
                            .builder()
                            .id(tarefa.getId())
                            .titulo(tarefa.getTitulo())
                            .descricao(tarefa.getDescricao())
                            .responsavel(tarefa.getResponsavel() != null ? tarefa.getResponsavel().getUsername() : "NAO_ATRIBUIDA")
                            .criador(tarefa.getCriador().getUsername())
                            .status(tarefa.getStatus())
                            .quantidadeHosrasEstimadas(tarefa.getQuantidadeHosrasEstimadas())
                            .quantidadeHorasRealizadas(tarefa.getQuantidadeHorasRealizadas())
                            .dataCadastro(tarefa.getDataCadastro())
                            .dataAtualizacao(tarefa.getDataAtualizacao())
                            .build();

                })
                .toList();

        ObterTarefasPaginadaResponse response = ObterTarefasPaginadaResponse
                .builder()
                .paginaAtual(tarefasPaginada.getNumber())
                .totalPaginas(tarefasPaginada.getTotalPages())
                .totalItens(tarefasPaginada.getTotalElements())
                .tarefas(tarefas)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
