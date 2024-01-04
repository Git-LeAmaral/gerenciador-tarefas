package com.gerenciadortarefas.service;

import com.gerenciadortarefas.entity.Tarefa;
import com.gerenciadortarefas.exceptions.NaoPermitidoAlterarStatusException;
import com.gerenciadortarefas.exceptions.NaoPermitirExcluirException;
import com.gerenciadortarefas.exceptions.TarefaExistenteException;
import com.gerenciadortarefas.repository.GerenciadorTarefasRepository;
import com.gerenciadortarefas.request.AtualizarTarefaRequest;
import com.gerenciadortarefas.request.CadastrarTarefaRequest;
import com.gerenciadortarefas.status.TarefaStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GerenciadorTarefasService {

    @Autowired
    private GerenciadorTarefasRepository gerenciadorTarefasRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Tarefa salvarTarefa(CadastrarTarefaRequest request) {

        Tarefa tarefaValidacao = gerenciadorTarefasRepository.findByTituloOrDescricao(request.getTitulo(), request.getDescricao());

        if (tarefaValidacao != null) {
            throw new TarefaExistenteException("Ja existe uma tarefa com o mesmo titulo ou descrição");
        }

        Tarefa tarefa = Tarefa.builder()
                .quantidadeHosrasEstimadas(request.getQuantidadeHosrasEstimadas())
                .status(TarefaStatusEnum.ESPERA)
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .criador(usuarioService.obterUsuarioId(request.getCriadorId()).get())
                .build();

        return this.gerenciadorTarefasRepository.save(tarefa);

    }

    public Page<Tarefa> obtemTarefasPorTitulo(String titulo, Pageable pageable) {
        return this.gerenciadorTarefasRepository.findByTituloContaining(titulo, pageable);
    }

    public Page<Tarefa> obtemTodasTarefas( Pageable pageable) {
        return this.gerenciadorTarefasRepository.findAll(pageable);
    }

    public Tarefa atualizarTarefa(Long id, AtualizarTarefaRequest request) {

        Tarefa tarefa = this.gerenciadorTarefasRepository.findById(id).get();

        if (tarefa.getStatus().equals(TarefaStatusEnum.ESPERA) && request.getStatus().equals(TarefaStatusEnum.FINALIZADA)) {
            throw new NaoPermitidoAlterarStatusException("Não é possivel Alterar a tarefa para FINALIZADA se ainda estiver com o status em ESPERA");
        }

        if (tarefa.getStatus().equals(TarefaStatusEnum.BLOQUEADA) && request.getStatus().equals(TarefaStatusEnum.FINALIZADA)) {
            throw new NaoPermitidoAlterarStatusException("Não é possivel Alterar a tarefa para FINALIZADA se ainda estiver com o status em BLOQUEADA");
        }

        if (tarefa.getStatus().equals(TarefaStatusEnum.FINALIZADA)) {
            throw new NaoPermitidoAlterarStatusException("Não é possivel Alterar a tarefa que ja está FINALIZADA");
        }

        tarefa.setQuantidadeHosrasEstimadas(request.getQuantidadeHorasEstimadas());
        tarefa.setStatus(request.getStatus());
        tarefa.setTitulo(request.getTitulo());
        tarefa.setDescricao(request.getDescricao());
        tarefa.setResponsavel(usuarioService.obterUsuarioId(request.getResponsavelId()).get());
        tarefa.setQuantidadeHorasRealizadas(request.getQuantidadeHorasRealizadas());

        this.gerenciadorTarefasRepository.save(tarefa);

        return tarefa;
    }

    public void excluirTarefa(Long id) {

        Tarefa tarefa = this.gerenciadorTarefasRepository.findById(id).get();

        if (!TarefaStatusEnum.ESPERA.equals(tarefa.getStatus())) {
            throw new NaoPermitirExcluirException();
        }

        this.gerenciadorTarefasRepository.deleteById(id);
    }
}
