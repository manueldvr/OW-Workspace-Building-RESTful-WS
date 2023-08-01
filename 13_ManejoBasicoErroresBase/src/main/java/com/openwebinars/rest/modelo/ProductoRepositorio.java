package com.openwebinars.rest.modelo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

	Optional<Producto> findProductoByNombre(String nombre);

}
