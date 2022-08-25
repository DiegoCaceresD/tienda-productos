package com.bootcamp.tiendaproductos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;
    private String codigo;
    private String nombre;
    private Double precio;
    private String categoria;
    private int stock;

}
