package com.ProdutosQuitanda.Produtos.model.ExceptionHandler;

import com.ProdutosQuitanda.Produtos.model.Exceptions.ErrorExceptions;
import com.ProdutosQuitanda.Produtos.model.Exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorExceptions> handleNotFoundExceptions(
            Exception ex, WebRequest request){
        ErrorExceptions errorExceptions = new ErrorExceptions(
                new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorExceptions, HttpStatus.NOT_FOUND);
    }

}
