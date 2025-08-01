package me.dio.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBusinessException(IllegalArgumentException businessException){
        return  new ResponseEntity<>(businessException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFoundException(NoSuchElementException notFoundException){
        return new ResponseEntity<>("Resource ID not found",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNotFoundStaticResource(NoResourceFoundException e) {
        return new ResponseEntity<>("Static resource not found", HttpStatus.NOT_FOUND);
    }


   @ExceptionHandler(Throwable.class)
   public ResponseEntity<String> handleUnexpectedException(Throwable unexpectedException){
       logger.error("Unexpected server error", unexpectedException); // já faz log do stacktrace
       return new ResponseEntity<>(unexpectedException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
   }





}
