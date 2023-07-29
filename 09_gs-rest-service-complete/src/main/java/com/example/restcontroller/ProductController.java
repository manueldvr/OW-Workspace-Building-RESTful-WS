package com.example.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.modelo.ProductoRepositorio;
import com.example.restservice.modelo.Producto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductController {

	
	
	
	private final ProductoRepositorio productoRepositorio;
	
	
	
	@GetMapping("/productos")
	public List<Producto> productos() {
		return productoRepositorio.findAll();
	}	
}
