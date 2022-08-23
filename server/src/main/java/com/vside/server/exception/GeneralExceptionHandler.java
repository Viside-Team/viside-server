package com.vside.server.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice("com.vside.server")
public class GeneralExceptionHandler {

    @ExceptionHandler({
            IllegalStateException.class, IllegalArgumentException.class,
            TypeMismatchException.class, HttpMessageNotReadableException.class,
            HttpMediaTypeException.class, MethodArgumentNotValidException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception e) {
        log.debug("Bad request exception occurred: {}", e.getMessage(), e);
        return ResponseEntity.badRequest().body(new ErrorResponse(ErrorMessage.INVALID_INPUT_VALUE.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("Handle access denied exception occurred: {}", e.getMessage(), e);
        return ResponseEntity.badRequest().body(new ErrorResponse(ErrorMessage.HANDLE_ACCESS_DENIED.getMessage()));
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Unexpected exception occurred: {}", e.getMessage(), e);
        return ResponseEntity.internalServerError().body(new ErrorResponse(ErrorMessage.INTERNAL_SERVER_ERROR.getMessage()));
    }
}
