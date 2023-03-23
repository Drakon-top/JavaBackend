package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import ru.tinkoff.edu.java.scrapper.web.controller.LinksController;

@RestControllerAdvice(
        basePackageClasses = LinksController.class
)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleNullPointerException(Exception ex) {
        logger.error("Exception", ex);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

}