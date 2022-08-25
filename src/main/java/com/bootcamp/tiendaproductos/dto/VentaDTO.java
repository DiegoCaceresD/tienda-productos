package com.bootcamp.tiendaproductos.dto;


import com.bootcamp.tiendaproductos.entidades.Vendedor;
import com.bootcamp.tiendaproductos.entidades.Venta;
import lombok.Data;

import java.util.List;

@Data
public class VentaDTO {
    private Long id;
    private List<Long> idsProductos;
    private Long vendedorId;

}
