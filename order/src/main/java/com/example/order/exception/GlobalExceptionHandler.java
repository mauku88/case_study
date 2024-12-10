package com.example.order.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;



@RestControllerAdvice

public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleValidationExceptions(Exception ex) {

        CustomErrorResponse errorResponse=new CustomErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage( "Sistem Hatası!!! " );
        errorResponse.setTimestamp( System.currentTimeMillis());
        errorResponse.setException(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleValidationExceptions(ConstraintViolationException ex) {
        CustomErrorResponse errorResponse=new CustomErrorResponse();
        // Create a custom error response
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage( ex.getMessage());
        errorResponse.setTimestamp( System.currentTimeMillis());
        errorResponse.setException("ConstraintViolationException");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        CustomErrorResponse errorResponse=new CustomErrorResponse();
        // Create a custom error response
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage( "Yanlış metot parametresi: "+ ex.getParameter().getParameterName() );
        errorResponse.setTimestamp( System.currentTimeMillis());
        errorResponse.setException("MethodArgumentNotValidException");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handlerAgumentTypeMismatchExceptions(MethodArgumentTypeMismatchException ex) {
        CustomErrorResponse errorResponse=new CustomErrorResponse();
        // Create a custom error response
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage( "Yanlış Tipte Parametre: "+ ex.getParameter().getParameterName() );
        errorResponse.setTimestamp( System.currentTimeMillis());
        errorResponse.setException("MethodArgumentTypeMismatchException");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
        String parameterName = ex.getParameterName();
        CustomErrorResponse errorResponse=new CustomErrorResponse();
        // Create a custom error response
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage( parameterName +" boş olamaz.");
        errorResponse.setTimestamp( System.currentTimeMillis());
        errorResponse.setException("MissingServletRequestParameterException");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    class CustomErrorResponse {
        private int status;
        private String message;
        private long timestamp;
        private String exception;



    }
}