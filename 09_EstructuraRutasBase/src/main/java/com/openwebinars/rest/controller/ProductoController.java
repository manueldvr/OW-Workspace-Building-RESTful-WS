package com.openwebinars.rest.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.example.restservice.Greeting;
import com.openwebinars.rest.modelo.Producto;
import com.openwebinars.rest.modelo.ProductoRepositorio;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class ProductoController {

	private final ProductoRepositorio productoRepositorio;

	

	
	/**
	 * Obtenemos todos los productos
	 * @return List
	 */
	@GetMapping("/producto")
	public List<Producto> obtenerTodos() {
		return productoRepositorio.findAll();
	}
	
	/**
	 * Obtenemos un producto en base a su ID
	 * @param id
	 * @return Null si no encuentra el producto
	 */
	@GetMapping("/producto/{id}")
	public Producto obtenerUno(@PathVariable Long id) {
		return productoRepositorio.findById(id).orElse(null);
	}
	
	/**
	 * Insertamos un nuevo producto
	 * @param nuevo
	 * @return producto insertado
	 */
	@PostMapping("/producto")
	public Producto nuevoProducto(@RequestBody Producto nuevo) {
		return productoRepositorio.save(nuevo);
	}
	
	/**
	 * Editar un producto
	 * @param producto
	 * @param id
	 * @return producto
	 */
	@PutMapping("/producto/{id}")
	public Producto editarProducto(@RequestBody Producto producto, @PathVariable Long id) {
		if (productoRepositorio.existsById(id)) {
			producto.setId(id);
			return productoRepositorio.save(producto);
		} else {
			return null;
		}
	}
	
	/**
	 * Borra un producto del catálogo en base a su id
	 * @param id
	 * @return producto
	 */
	@DeleteMapping("/producto/{id}")
	public Producto borrarProducto(@PathVariable Long id) {
		if (productoRepositorio.existsById(id)) {
			Producto result = productoRepositorio.findById(id).get();
			productoRepositorio.deleteById(id);
			return result;
		} else
			return null;
	}



}
