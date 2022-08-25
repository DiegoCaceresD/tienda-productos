package com.bootcamp.tiendaproductos.repositorios;

import com.bootcamp.tiendaproductos.entidades.Producto;
import com.bootcamp.tiendaproductos.entidades.Vendedor;
import com.bootcamp.tiendaproductos.entidades.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface iVentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findVentasByVendedor(Long id);

    List<Venta> findVentasByProductos(String codigo);

}
