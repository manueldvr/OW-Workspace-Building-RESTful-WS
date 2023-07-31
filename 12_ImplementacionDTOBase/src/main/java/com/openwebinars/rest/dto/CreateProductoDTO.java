/**
 * 
 */
package com.openwebinars.rest.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Contiene informacion  desde el front.
 */
@Getter
@Setter
public class CreateProductoDTO {

	private String nombre;
	private float precio;
	private long categoriaId;
	
}
