/**
 * 
 */
package com.openwebinars.rest.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.openwebinars.rest.dto.CreateProductoDTO;
import com.openwebinars.rest.dto.ProductoDTO;
import com.openwebinars.rest.modelo.Producto;

import lombok.RequiredArgsConstructor;

/**
 *  Producto converter
 */
@Component
@RequiredArgsConstructor
public class ProductoDTOConverter {

	private final ModelMapper modelMapper;
	
	
	public ProductoDTO convertToDto(Producto producto) {
		return this.modelMapper.map(producto, ProductoDTO.class);
	}
	
	public Producto convertToProducto(CreateProductoDTO dto) {
		return this.modelMapper.map(dto, Producto.class);
	}
}
