package com.example.restservice;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.modelo.Producto;
import com.example.restservice.modelo.ProductoRepositorio;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class GreetingController {

	
	private final ProductoRepositorio productoRepositorio;

	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	//--s
	
	
	/**
	 * Obtenemos todos los productos.
	 * Retorna con cod 200, o 400 para recurso no encontrado.
	 * @return List
	 */
	@GetMapping("/producto")
	public ResponseEntity<?> obtenerTodos() {
		List<Producto> productos = productoRepositorio.findAll();
		if (productos.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(productos);
		}
	}
	
	/**
	 * Obtenemos un producto en base a su ID.
	 * Retorna con cod 200, o 400 para recurso no encontrado.
	 * @param id
	 * @return Null si no encuentra el producto
	 */
	@GetMapping("/producto/{id}")
	public ResponseEntity<?> obtenerUno(@PathVariable Long id) {
		Producto found = productoRepositorio.findById(id).orElse(null);
		if (found==null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(found);
		}
	}
	
	/**
	 * Insertamos un nuevo producto
	 * @param nuevo
	 * @return producto insertado
	 */
	@PostMapping("/producto")
	public ResponseEntity<Producto> nuevoProducto(@RequestBody Producto nuevo) {
		Producto saved = productoRepositorio.save(nuevo);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	/**
	 * Editar un producto
	 * @param producto
	 * @param id
	 * @return producto
	 */
	@PutMapping("/producto/{id}")
	public ResponseEntity<?> editarProducto(@RequestBody Producto producto, @PathVariable Long id) {
		return this.productoRepositorio.findById(id).map(p -> {
			p.setNombre(producto.getNombre());
			p.setPrecio(producto.getPrecio());
			return ResponseEntity.ok(this.productoRepositorio.save(p));
		}).orElseGet(() -> {
			return ResponseEntity.notFound().build();
		});
	}
	
	/**
	 * Borra un producto del catálogo en base a su id
	 * @param id
	 * @return producto
	 */
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<?> borrarProducto(@PathVariable Long id) {
		if (productoRepositorio.existsById(id)) {
			productoRepositorio.deleteById(id);
			return ResponseEntity.noContent().build();
		} else
			return ResponseEntity.notFound().build();
	}
}
