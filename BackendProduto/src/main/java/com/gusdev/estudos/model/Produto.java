package com.gusdev.estudos.model;

import lombok.Data;

@Data
public class Produto {
    private Integer id;
    private String nome;
    private Integer quantidade;
    private Double valor;
    private String observacao;
}
