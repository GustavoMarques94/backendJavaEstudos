package com.gusdev.estudos.view.controller;

import com.gusdev.estudos.services.ProdutoService;
import com.gusdev.estudos.shared.ProdutoDTO;
import com.gusdev.estudos.view.model.ProdutoRequest;
import com.gusdev.estudos.view.model.ProdutoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    /*ResponseEntity --> ele sabe trabalhar com respostas, fica dentro do pacote Spring */
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodos(){
        List<ProdutoDTO> produtosDTO = produtoService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<ProdutoResponse> resposta = produtosDTO.stream()
                .map(produtoDTO -> mapper.map(produtoDTO, ProdutoResponse.class)) //Depois que converter todos os dtos em produtoResponse
                .collect(Collectors.toList()); //retorna uma lista

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> obterPorId(@PathVariable Integer id){ /*@PathVariable -> ele sabe que o
        {id} é uma variável que está no "caminho", então ele vai pegar e tentar transformar em um id do tipo inteiro */
        Optional<ProdutoDTO> produtoDTO = produtoService.obterPorId(id);
        ModelMapper mapper = new ModelMapper();

        ProdutoResponse resposta = mapper.map(produtoDTO, ProdutoResponse.class);

        return new ResponseEntity<>(Optional.of(resposta), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest produtoReq){ /*@RequestBody -> Digo ao Spring para pegar o que vier no JSON e tentar fazer um bind, transformar em um produto */
        ModelMapper mapper = new ModelMapper();

        ProdutoDTO produtoDTO = mapper.map(produtoReq, ProdutoDTO.class);

        produtoDTO = produtoService.adicionar(produtoDTO);

        ProdutoResponse resposta = mapper.map(produtoDTO, ProdutoResponse.class);

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){ // ? indica que posso retorar qualquer coisa
        produtoService.deletar(id);

        String resposta = "Produto com id: " + id + " foi deletado com sucesso!";

        return new ResponseEntity<>(resposta, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@RequestBody ProdutoRequest produtoReq, @PathVariable Integer id){
        ModelMapper mapper = new ModelMapper();

        ProdutoDTO produtoDTO = mapper.map(produtoReq, ProdutoDTO.class);

        produtoDTO = produtoService.atualizar(id, produtoDTO);

        ProdutoResponse resposta = mapper.map(produtoDTO, ProdutoResponse.class);

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
