package com.gerenciadortarefas.request;

import com.gerenciadortarefas.status.TarefaStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AtualizarTarefaRequest {

    @NotBlank(message = "{atualizar.tarefa.request.titulo.obrigatorio}")
    private String titulo;

    @Length(min = 10, max = 100, message = "{atualizar.tarefa.request.descricao.limite}")
    private String descricao;

    private TarefaStatusEnum status;

    private Long responsavelId;

    @NotNull(message = "{atualizar.tarefa.request.quantidadeHosrasEstimadas.obrigatorio}")
    private Integer quantidadeHorasEstimadas;

    private Integer quantidadeHorasRealizadas;
}
