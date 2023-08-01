/**
 * 
 */
package com.openwebinars.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
* Class AllDataException
*
* @author ManuelDVR
* @version 1.0
*/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AllDataException extends RuntimeException {

	private static final long serialVersionUID = 1877141064269207069L;

	public AllDataException() {
		super("Failed to retrieve all data");
	}

}
