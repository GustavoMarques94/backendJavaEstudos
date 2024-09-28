package com.gusdev.estudos.shared;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {
        private Integer id;
        private String nome;
        private Integer quantidade;
        private Double valor;
        private String observacao;
}
