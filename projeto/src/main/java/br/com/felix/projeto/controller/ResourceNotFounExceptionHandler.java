package br.com.felix.projeto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFounExceptionHandler extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public ResourceNotFounExceptionHandler(String ex) {
        super(ex);
    }
}
