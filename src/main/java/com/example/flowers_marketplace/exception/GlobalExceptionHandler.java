package com.example.flowers_marketplace.exception;

import com.example.flowers_marketplace.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> exceptionHandler(Exception e) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(e.getMessage(), HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(errorMessageDto.getErrorCode()).body(errorMessageDto.getErrorMessage());
    }
}
