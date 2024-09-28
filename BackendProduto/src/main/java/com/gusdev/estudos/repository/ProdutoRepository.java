package com.gusdev.estudos.repository;

import com.gusdev.estudos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* 1° Coisa a se fazer a criar uma repository, estou dizendo pro spring que isso é um repositório,
    o spring terá controle sobre ele, com isso poderemos utilizar:
    Injeção de dependência e Inversão de controle
    Damos a possibilidade do spring ter essa dependência, injetamos essa dependência no spring
    Quando precisamos dela, vamos pedir o controle pro spring, ele devolve uma instância pronta */

//JpaRepository --> Interface do Spring JPA (é uma interface genérica que recebe dois parâmetros no seu construtor)
    //1° - é o modelo que ela vai utilizar como exemplo, como objeto
    //2° - é o tipo de id do objeto; Ela precisa saber para gerar os métodos dinâmicos
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {


}
