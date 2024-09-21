package com.gusdev.estudos.controller;

import com.gusdev.estudos.model.Produto;
import com.gusdev.estudos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/* 1° Coisa a se fazer ao criar um controller, é dizer ao spring que isso é um controlador,
    injeto essa dependência dentro do spring, o spring terá controle sobre ele, com isso poderemos utilizar:
    Injeção de dependência e Inversão de controle
    - Também preciso dizer qual rota ele deve ficar ouvindo*/
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    /*Para o controller saber que esse método será uma consulta, como não estou utilizando
    nenhum mapeamento adicional, chama esse método quando fizer uma requisição para: /api/produtos */
    @GetMapping
    public Optional<List<Produto>> obterTodos(){
        return produtoService.obterTodos();
    }

    @GetMapping(path = "/{id}")
    public Optional<Produto> obterPorId(@PathVariable Integer id){ /*@PathVariable -> ele sabe que o
        {id} é uma variável que está no "caminho", então ele vai pegar e tentar transformar em um id do tipo inteiro */
        return produtoService.obterPorId(id);
    }

    @PostMapping
    public Produto adicionar(@RequestBody Produto produto){ /*@RequestBody -> Digo ao Spring para pegar o que
        vier no JSON e tentar fazer um bind, transformar em um produto */
        return produtoService.adicionar(produto);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Integer id){
        produtoService.deletar(id);
        return "Produto com id: " + id + " foi deletado com sucesso!";
    }

    @PutMapping("/{id}")
    public Produto atualizar(@RequestBody Produto produto, @PathVariable Integer id){
        return produtoService.atualizar(id, produto);
    }
}
