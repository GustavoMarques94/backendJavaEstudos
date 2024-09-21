package com.gusdev.estudos.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND) /*Irei executar essa classe quando acontecer um código httpsStatus NOT_FOUND*/
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String mensagem){
        //Porque o Super? Essa classe herda de um RuntimeException um construtor pai
        super(mensagem);
    }
}
