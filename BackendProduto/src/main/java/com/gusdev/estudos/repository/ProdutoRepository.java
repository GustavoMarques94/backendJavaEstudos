package com.gusdev.estudos.repository;

import com.gusdev.estudos.model.Produto;
import com.gusdev.estudos.model.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

/* 1° Coisa a se fazer a criar uma repository, estou dizendo pro spring que isso é um repositório,
    o spring terá controle sobre ele, com isso poderemos utilizar:
    Injeção de dependência e Inversão de controle
    Damos a possibilidade do spring ter essa dependência, injetamos essa dependência no spring
    Quando precisamos dela, vamos pedir o controle pro spring, ele devolve uma instância pronta */
@Repository
public class ProdutoRepository {
    // Vamos simular um BD
    private ArrayList<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoID = 0;

    /**
     * Método para retornar uma lista de produtos.
     * @return Lista de produtos.
     */
    public Optional<List<Produto>> obterTodos(){
        return Optional.ofNullable(this.produtos);
    }

    /**
     * Método para retornar um produto encontrado pelo seu id.
     * @param id do produto que será localizado.
     * @return Retorna um produto caso seja encontrado.
     */
    public Optional<Produto> obterPorId(Integer id){
        return this.produtos
                .stream()
                .filter(produto -> produto.getId().equals(id))
                .findFirst();
    }

    /**
     * Método para adicionar um produto na lista.
     * @param produto que será adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public Produto adicionar(Produto produto){

        this.ultimoID ++;

        produto.setId(ultimoID);
        this.produtos.add(produto);

        return produto;
    }

    /**
     * Método para deletar o produto por id.
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
        //removeIf --> sabe remover de dentro de uma lista, de acordo com o filtro que você passar
        this.produtos.removeIf(produto -> produto.getId().equals(id));
    }

    /**
     * Método para atualizar o produto na lista.
     * @param produto que será atualizado.
     * @return Retorna o produto após atualizar a lista.
     */
    public Produto atualizar(Produto produto){
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());

        if(produtoEncontrado.isEmpty()){
            throw new ResourceNotFoundException("Produto não encontrado.");
        }

        deletar(produto.getId());

        this.produtos.add(produto);

        return produto;
    }
}
