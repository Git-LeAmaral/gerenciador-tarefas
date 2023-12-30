package com.gerenciadortarefas.service;

import com.gerenciadortarefas.entity.Tarefa;
import com.gerenciadortarefas.repository.GerenciadorTarefasRepository;
import com.gerenciadortarefas.request.CadastrarTarefaRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GesrenciadorTarefasService {

    @Autowired
    private GerenciadorTarefasRepository gerenciadorTarefasRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Tarefa salvarTarefa(CadastrarTarefaRequest request) {

        Tarefa tarefa = Tarefa.builder()
                .quantidadeHosrasEstimadas(request.getQuantidadeHosrasEstimadas())
                .status(request.getStatus())
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .responsavel(usuarioService.obterUsuarioId(request.getResponsavelId()).get())
                .criador(usuarioService.obterUsuarioId(request.getCriadorId()).get())
                .build();

        return this.gerenciadorTarefasRepository.save(tarefa);

    }
}
