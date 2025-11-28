package com.cosmotech.inventorymgmt.exception;

import com.cosmotech.inventorymgmt.core.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ApiResponse<?>> handleDuplicateException(DuplicateException e) {
        ApiResponse<?> apiResponse = new ApiResponse<>(false, e.getMessage(), HttpStatus.CONFLICT.value(), LocalDateTime.now());
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiResponse<?>> handelInvalidTokenException (InvalidTokenException e) {
        ApiResponse<?> apiResponse = new ApiResponse<>(false, e.getMessage(), HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now());
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationErrors(MethodArgumentNotValidException e) {
        String error = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return new ResponseEntity<>(new ApiResponse<>(false, error, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        ApiResponse<?> apiResponse = new ApiResponse<>(false,e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
