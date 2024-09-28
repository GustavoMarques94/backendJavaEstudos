package com.gusdev.estudos.view.model;

import lombok.Getter;
import lombok.Setter;

//Tipo de dado que é esperado ao receber quando alguém fizer uma requisição pro backend
@Getter
@Setter
public class ProdutoRequest {
    private String nome;
    private Integer quantidade;
    private Double valor;
    private String observacao;
}
