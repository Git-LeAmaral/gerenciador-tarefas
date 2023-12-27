package com.gerenciadortarefas.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true, length = 20)
    private String nome;
    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

}
