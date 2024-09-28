package com.gusdev.estudos.model;

import lombok.Data;
import javax.persistence.*;

@Data
//@Table("produto") --> o @Entity já identifica o nome da classe como uma Entidade e já vira uma tabela no BD, digo para o Spring tomar conta desse modelo, e esse modelo vai dar uma entidade, uma tabela no BD
@Entity
public class Produto {

    @Id //Chave Primária
    @GeneratedValue(strategy = GenerationType.AUTO) //Qual estratégia de atualização da base queremos utilizar
    private Integer id;
    private String nome;
    private Integer quantidade;
    private Double valor;
    private String observacao;
}
