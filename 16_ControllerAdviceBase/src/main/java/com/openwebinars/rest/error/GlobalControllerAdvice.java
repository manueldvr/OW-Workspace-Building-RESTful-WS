/**
 * 
 */
package com.openwebinars.rest.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * GlobalControllerAdvice class
 * 
 * @see RestControllerAdvice
 */
@RestControllerAdvice
public class GlobalControllerAdvice {
	
	/**
	 * 
	 * 
	 * @param ex
	 * @param status
	 * @return ResponseEntity
	 */
	private ResponseEntity<APIError> handleProductoNoEncontrado(ProductoNotFoundException ex, HttpStatus status) {
		APIError apiError = new APIError(status, ex.getMessage());
		return ResponseEntity.status(status).body(apiError);	
	}
	
	
	@ExceptionHandler(ProductoNotFoundException.class)
	public ResponseEntity<APIError> handleProductoNoEncontrado(ProductoNotFoundException ex) {
		APIError apiError = new APIError(HttpStatus.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);	
	}
	
	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<APIError> handleJsonMappingException(JsonMappingException ex) {
		APIError apiError = new APIError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
}
