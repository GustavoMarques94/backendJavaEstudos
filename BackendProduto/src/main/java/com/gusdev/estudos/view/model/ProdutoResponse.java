package com.gusdev.estudos.view.model;

import lombok.Getter;
import lombok.Setter;

//Tipo de dado que irá retornar quando alguém fizer uma requisição pro backend
@Getter
@Setter
public class ProdutoResponse {
    private Integer id;
    private String nome;
    private Integer quantidade;
    private Double valor;
    private String observacao;
}
