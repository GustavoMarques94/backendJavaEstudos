package com.gusdev.estudos.services;

import com.gusdev.estudos.model.Produto;
import com.gusdev.estudos.model.exception.ResourceNotFoundException;
import com.gusdev.estudos.repository.ProdutoRepository;
import com.gusdev.estudos.shared.ProdutoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ProdutoDTO> obterTodos(){

        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
                .collect(Collectors.toList()); //ao final da operação map, pega tudo que estiver lá e me retorne uma lista
    }

    /**
     * Método para retornar um produto encontrado pelo seu id.
     * @param id do produto que será localizado.
     * @return Retorna um produto caso seja encontrado.
     */
    public Optional<ProdutoDTO> obterPorId(Integer id){

        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado");
        }

        ProdutoDTO produtoDTO = new ModelMapper().map(produto.get(), ProdutoDTO.class); //Para extrair valores do Optional se utiliza o get()

        return Optional.of(produtoDTO); //Crio um optional de produtoDTO
    }

    /**
     * Método para adicionar um produto na lista.
     * @param produtoDTO que será adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDTO){
        //Removendo o id para conseguir fazer o cadastro
        produtoDTO.setId(null);

        //Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        //Converter o nosso produtoDTO em um Produto, sem id
        Produto produto = mapper.map(produtoDTO, Produto.class);

        //Salvar o Produto do banco, com id
        produto = produtoRepository.saveAndFlush(produto);

        produtoDTO.setId(produto.getId());

        //Retornar o ProdutoDTO atualizado
        return produtoDTO;
    }

    /**
     * Método para deletar o produto por id.
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){

        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Não foi posível deletar o produto com o id: " + id + " - Produto não existe");
        }

        produtoRepository.deleteById(id);
    }

    /**
     * Método para atualizar o produto na lista.
     * @param produtoDTO que será atualizado.
     * @param id do produto que será atualizado.
     * @return Retorna o produto após atualizar a lista.
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDTO){

        produtoDTO.setId(id);

        ModelMapper mapper = new ModelMapper();

        Produto produto = mapper.map(produtoDTO, Produto.class);

        //o Save serve tanto para adicionar, quanto atualizar --> se tiver 'id' ele sabe que tem que atualizar, se não tiver ele cadastra
        produtoRepository.saveAndFlush(produto);

        return produtoDTO;
    }
}
