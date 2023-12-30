package com.gerenciadortarefas.entity;

import com.gerenciadortarefas.status.TarefaStatusEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "tarefas")
@Data
@Getter
@Setter
@Builder
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TarefaStatusEnum status;

    @Column(nullable = false)
    private Usuario responsavel;

    @Column(nullable = false)
    private Usuario criador;

    @Column(nullable = false)
    private int quantidadeHosrasEstimadas;

    @Column
    private Integer quantidadeHorasRealizadas;

    @Column
    @CreationTimestamp
    private LocalTime dataCadastro;

    @Column
    @UpdateTimestamp
    private LocalTime dataAtualizacao;

    @Column
    private LocalTime tempoRealizado;


}
