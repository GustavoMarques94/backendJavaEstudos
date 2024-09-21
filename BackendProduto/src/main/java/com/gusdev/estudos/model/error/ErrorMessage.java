package com.gusdev.estudos.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
* Reponsabilidade da classe: Ter atributos que irá retornar, quando algum erro for lançado*/
@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private String titulo;
    private Integer status;
    private String mensagem;
}
