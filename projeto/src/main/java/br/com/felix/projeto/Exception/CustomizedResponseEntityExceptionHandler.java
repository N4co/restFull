package br.com.felix.projeto.Exception;


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
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> exceptionsAllHandler(Exception ex,  WebRequest web) {

        ExceptionResponse response = new ExceptionResponse(

                new Date(),
                ex.getMessage(),
                web.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(ResourceNotFounExceptionHandler.class)
    public final ResponseEntity<ExceptionResponse> ResourceNotFounExceptionHandler(Exception ex,  WebRequest web) {

        ExceptionResponse response = new ExceptionResponse(

                new Date(),
                ex.getMessage(),
                web.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RequiredObjectIsNullException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex,  WebRequest web) {

        ExceptionResponse response = new ExceptionResponse(

                new Date(),
                ex.getMessage(),
                web.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidJWTAuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> InvalidJWTAuthenticationException(Exception ex,  WebRequest web) {

        ExceptionResponse response = new ExceptionResponse(

                new Date(),
                ex.getMessage(),
                web.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }


}
