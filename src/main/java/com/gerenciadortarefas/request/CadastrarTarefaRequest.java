package com.gerenciadortarefas.request;

import com.gerenciadortarefas.status.TarefaStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CadastrarTarefaRequest {

    @NotBlank(message = "{cadastrar.tarefa.request.titulo.obrigatorio}")
    private String titulo;

    @Length(min = 10, max = 100, message = "{cadastrar.tarefa.request.descricao.limite}")
    private String descricao;

    private Long criadorId;

    @NotNull(message = "{cadastrar.tarefa.request.quantidadeHosrasEstimadas.obrigatorio}")
    private Integer quantidadeHosrasEstimadas;

}
