package com.gerenciadortarefas.request;

import com.gerenciadortarefas.status.TarefaStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarTarefaRequest {

    private String titulo;
    private String descricao;
    private TarefaStatusEnum status;
    private Long responsavelId;
    private int quantidadeHorasEstimadas;
    private Integer quantidadeHorasRealizadas;
}
