package br.com.felix.projeto.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public RequiredObjectIsNullException() {
        super("Não é permitido persistir um objeto null");
    }

    public RequiredObjectIsNullException(String ex) {
        super(ex);
    }
}
