/**
* Class ProductNotFoundException
*
* @see ResponseStatus
* @author ManuelDVR
* @version 1.0
*/
package com.openwebinars.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * con @ResponseStatus se obtiene un error 404, NOT FOUND.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4492754235786925940L;
	
	
	public ProductNotFoundException(Long id) {
		super("No se puede encontrar el producto con el ID: " + id);
	}
}
