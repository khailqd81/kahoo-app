package com.khai.kahooclone.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception ex){
		return new ResponseEntity<>(
				new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),System.currentTimeMillis()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(EntityNotFoundException ex){
		return new ResponseEntity<>(
				new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(),System.currentTimeMillis()),HttpStatus.NOT_FOUND);
	}
}
