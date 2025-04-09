package com.gruposv.microservice_stock.exception;

import com.gruposv.microservice_stock.dto.ApiResponseDTO;
import com.gruposv.microservice_stock.modules.product.exception.DuplicateNameProductException;
import com.gruposv.microservice_stock.modules.product.exception.DuplicateSkuCodeException;
import com.gruposv.microservice_stock.modules.product.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateNameProductException.class)
    public ResponseEntity<ApiResponseDTO<String>> handleDuplicateNameProductException(DuplicateNameProductException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponseDTO<>("error", HttpStatus.CONFLICT.value(), null, ex.getMessage()));
    }

    @ExceptionHandler(DuplicateSkuCodeException.class)
    public ResponseEntity<ApiResponseDTO<String>> handleDuplicateSkuCodeException(DuplicateSkuCodeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponseDTO<>("error", HttpStatus.CONFLICT.value(), null, ex.getMessage()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<String>> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDTO<>("error", HttpStatus.NOT_FOUND.value(), null, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponseDTO<>("error", HttpStatus.BAD_REQUEST.value(), errors, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<String>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseDTO<>("error", HttpStatus.INTERNAL_SERVER_ERROR.value(), null, ex.getMessage()));
    }

}
