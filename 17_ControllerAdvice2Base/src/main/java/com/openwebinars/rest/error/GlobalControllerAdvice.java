package com.openwebinars.rest.error;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler{
	
	

	/**
	 * Handle ExceptionInternal method. or common handling
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, 
			Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, ex.getMessage());
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}
	
	/**
	 * Handle HTTP message NotReadable
	 */
   @Override
   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       String error = "Malformed JSON request";
       return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error));
   }
   
   /**
    * handleEntityNotFound metyhod.
    * A custom Exceptions. Handles an exception not yet declared inside Spring 
    * Boot’s ResponseEntityExceptionHandler.
    * @param apiError
    * @return ResponseEntity
    */
   @ExceptionHandler(EntityNotFoundException.class)
   protected ResponseEntity<Object> handleEntityNotFound(
           EntityNotFoundException ex) {
       ApiError apiError = new ApiError();
       apiError.setEstado(HttpStatus.NOT_FOUND);
       apiError.setMensaje(ex.getMessage());
       return buildResponseEntity(apiError);
   }
   
   /**
    * Like handleEntityNotFound metyhod. Producto not found Exception.
    * @param ex
    * @return ResponseEntity
    */
	@ExceptionHandler(ProductoNotFoundException.class)
	public ResponseEntity<ApiError> handleProductoNoEncontrado(ProductoNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
   
   
   //util
   private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
       return new ResponseEntity<>(apiError, apiError.getEstado());
   }
}
