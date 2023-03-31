package ru.tinkoff.edu.java.bot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(
        basePackageClasses = ChatsController.class
)
public class GlobalExceptionHandlerBot extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<Object> handleNullPointerException(Exception ex) {
        logger.error("Exception", ex);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

}