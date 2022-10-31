package br.com.felix.projeto.Exception;

import br.com.felix.projeto.Exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public final ResponseEntity<ExceptionResponse> exceptionsAllHandler(Exception ex,  WebRequest web) {

        ExceptionResponse response = new ExceptionResponse(

                new Date(),
                ex.getMessage(),
                web.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    public final ResponseEntity<ExceptionResponse> ResourceNotFounExceptionHandler(Exception ex,  WebRequest web) {

        ExceptionResponse response = new ExceptionResponse(

                new Date(),
                ex.getMessage(),
                web.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
