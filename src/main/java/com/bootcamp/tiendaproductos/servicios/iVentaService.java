package com.bootcamp.tiendaproductos.servicios;

import com.bootcamp.tiendaproductos.entidades.Producto;
import com.bootcamp.tiendaproductos.entidades.Vendedor;
import com.bootcamp.tiendaproductos.entidades.Venta;
import com.bootcamp.tiendaproductos.excepciones.NullException;

import java.util.List;


public interface iVentaService {

    Venta nuevaVenta (Venta venta) throws NullException;

    List<Venta> getAll();

    List<Venta> findVentaByVendedor(Long id);

    List<Venta> findVentaByProducto(String codigo);
}
