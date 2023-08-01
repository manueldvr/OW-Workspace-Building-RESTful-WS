/**
 * 
 */
package com.openwebinars.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
* Class ProductFoundException
*
* @author ManuelDVR
* @version 1.0
*/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 6301963272459927316L;

	public ProductFoundException(String name) {
		super("No se puede crear, ya existe el producto con el nombre: " + name);
	}
}
