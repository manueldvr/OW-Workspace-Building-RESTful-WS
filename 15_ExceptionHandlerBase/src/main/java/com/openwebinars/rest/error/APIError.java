/**
 * 
 */
package com.openwebinars.rest.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
public class APIError {

		private HttpStatus estado;
		
		@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy hh:mm:mm")
		private LocalDateTime fecha;
		
		private String mensaje;	
}
