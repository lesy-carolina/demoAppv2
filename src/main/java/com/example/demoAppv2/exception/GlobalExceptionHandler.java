package com.example.demoAppv2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)

    public ResponseEntity<Map<String, Object >> handleBadRequest(BadRequestException badRequestException) {
    Map<String, Object>  body=new LinkedHashMap <>();
    body.put("timestamp", LocalDateTime.now().toLocalDate().toString());
    body.put("status", HttpStatus.BAD_REQUEST.value());
    body.put("error", "Mala petition");
    body.put("message", badRequestException.getMessage());

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
