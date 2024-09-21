package com.gusdev.estudos.handler;
import com.gusdev.estudos.model.error.ErrorMessage;
import com.gusdev.estudos.model.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* Manipulador de Exceções Rest
* handler -> são classes que conseguem manipular coisas específicas
* Esse handler vai ficar ouvindo tod0 erro na aplicação e vai tomar uma ação quando acontecer o erro
* Qualquer exceção que acontecer dentro de uma requisição rest, vai cair nesse cara
* Para ele saber que é um manipulador de eventos, vamos passar o controle para ele --> @ControllerAdvice
* Digos que ele é um controlador dentro da nossa aplicação */
@ControllerAdvice
public class RestExceptionHandler {

    /*Método que irá retornar um ResponseEntity de qq coisa <?> */
    /*@ExceptionHandler --> Digo para o método ficar escutando as Exception dentro dessa API Rest */
    /*(ResourceNotFoundException.class) --> Quando acontecer qq exception do tipo: ResourceNotFoundException, quero que execute o método: handlerResourceNotFoundException,
    * e passe essa exeção para poder ter ela aqui no método*/
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException ex){

        ErrorMessage error = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
