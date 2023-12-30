package com.gerenciadortarefas.entity;

import com.gerenciadortarefas.status.TarefaStatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "tarefas")
@Data
@Getter
@Setter
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String titulo;
    private String descricao;
    private TarefaStatusEnum status;
    private Usuario responsavel;
    private Usuario criador;
    private int quantidadeHosrasEstimadas;
    private Integer quantidadeHorasRealizadas;
    private LocalTime dataCadastro;
    private LocalTime dataAtualizacao;
    private LocalTime tempoRealizado;


}
