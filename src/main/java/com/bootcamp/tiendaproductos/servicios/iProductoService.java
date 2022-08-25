package com.bootcamp.tiendaproductos.servicios;

import com.bootcamp.tiendaproductos.entidades.Producto;
import com.bootcamp.tiendaproductos.excepciones.NullException;
import com.bootcamp.tiendaproductos.excepciones.InexistentExcpetion;

import java.util.List;
import java.util.Optional;

public interface iProductoService {

    Producto nuevoProducto (Producto producto) throws InexistentExcpetion, NullException;
    List<Producto> getAll();

    Optional<Producto> obtenerId(Long id);

    List<Producto> buscarPorCategoria(String categoria);

    Optional<Producto> buscarPorNombre(String nombre);

    Producto findFirstByCodigo(String codigo);

}
