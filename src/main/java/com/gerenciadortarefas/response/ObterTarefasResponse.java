package com.gerenciadortarefas.response;

import com.gerenciadortarefas.status.TarefaStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class ObterTarefasResponse {


    private Long id;
    private String titulo;
    private String descricao;
    private TarefaStatusEnum status;
    private String responsavel;
    private String criador;
    private int quantidadeHosrasEstimadas;
    private Integer quantidadeHorasRealizadas;
    private LocalTime dataCadastro;
    private LocalTime dataAtualizacao;
}
