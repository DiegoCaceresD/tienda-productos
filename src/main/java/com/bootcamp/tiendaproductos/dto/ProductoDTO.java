package com.bootcamp.tiendaproductos.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductoDTO {

    private Long id;
    @NotEmpty
    @NotNull
    private String nombre;
    @NotNull(message = "Debes especificar el código")
    @Size(min = 1, max = 50, message = "El código debe medir entre 1 y 50")
    private String codigo;
    @NotNull(message = "Debes especificar el precio")
    @Min(value = 0, message = "El precio mínimo es 0")
    private Double precio;
    private String categoria;
    @NotNull(message = "Debes especificar el Stock")
    @Min(value = 0, message = "El stock mínimo es 0")
    private Float stock;
}
