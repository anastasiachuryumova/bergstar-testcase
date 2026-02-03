package org.bergstar.testcase.controller;

import org.jsoup.HttpStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpStatusException.class)
    public ResponseEntity<String> handleJsoupError(HttpStatusException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body("Ошибка при парсинге: " + ex.getMessage());
    }
}
