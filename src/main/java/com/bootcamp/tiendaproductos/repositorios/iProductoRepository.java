package com.bootcamp.tiendaproductos.repositorios;


import com.bootcamp.tiendaproductos.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface iProductoRepository extends JpaRepository<Producto,Long> {
    List<Producto> findProductoByCategoria (String categoria);
    Optional<Producto> findProductoByNombre (String nombre);

    Producto findFirstByCodigo(String codigo);
}
