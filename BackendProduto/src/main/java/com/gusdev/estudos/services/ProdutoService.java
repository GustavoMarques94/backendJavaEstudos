package com.gusdev.estudos.services;

import com.gusdev.estudos.model.Produto;
import com.gusdev.estudos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/* 1° Coisa a se fazer ao criar uma service, estou dizendo pro spring que isso é um serviço,
    injeto essa dependência dentro do spring, o spring terá controle sobre ele, com isso poderemos utilizar:
    Injeção de dependência e Inversão de controle */
@Service
public class ProdutoService {

    /*Sinzalizo que estou invertendo o controle, quem está no controle somos nós
     o spring nos devolve uma instância pronta de repositório para podermos utilizar, sem precisar instanciar */
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Método para retornar uma lista de produtos.
     * @return Lista de produtos.
     */
    public Optional<List<Produto>> obterTodos(){
        //colocar regra caso tenha...
        return produtoRepository.obterTodos();
    }

    /**
     * Método para retornar um produto encontrado pelo seu id.
     * @param id do produto que será localizado.
     * @return Retorna um produto caso seja encontrado.
     */
    public Optional<Produto> obterPorId(Integer id){
        if(Objects.isNull(id)){
            return Optional.empty();
        }
        return produtoRepository.obterPorId(id);
    }

    /**
     * Método para adicionar um produto na lista.
     * @param produto que será adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public Produto adicionar(Produto produto){
        if(Objects.isNull(produto)){
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        return produtoRepository.adicionar(produto);
    }

    /**
     * Método para deletar o produto por id.
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        produtoRepository.deletar(id);
    }

    /**
     * Método para atualizar o produto na lista.
     * @param produto que será atualizado.
     * @return Retorna o produto após atualizar a lista.
     */
    public Produto atualizar(Integer id, Produto produto){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        // Na requisição de um método update, o id não vem no corpo do objeto, vem separado
        produto.setId(id);
        return produtoRepository.atualizar(produto);
    }
}
