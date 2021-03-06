package com.cms.config.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 401
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleIllegalArgumentException(final IllegalArgumentException ex) {
//        log.warn("error", ex);
        return ex.getMessage();
    }

    // 401
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleNullUserException(final UsernameNotFoundException ex) {
//        log.warn("error", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    // 400
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> BadRequestException(final RuntimeException ex) {
//        log.warn("error", ex);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(final Exception ex) {
//        log.info(ex.getClass().getName());
//        log.error("error", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public String BadRequestException(final HttpMessageNotReadableException ex) {
//        log.warn("error", ex);
//        return ex.getMessage();
//    }
}
