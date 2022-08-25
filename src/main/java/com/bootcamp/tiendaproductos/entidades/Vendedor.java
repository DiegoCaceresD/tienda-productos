package com.bootcamp.tiendaproductos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "vendedor")
@Data

public class Vendedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vendedor")
    private Long id;
    private String nombre;
    private Double sueldo;
    private Double comisionTotal;
    @OneToMany(
            mappedBy = "vendedor", //bidireccional
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties({"vendedor"})
    private List<Venta> ventas;

    public Vendedor(Long id, String nombre, Double sueldo, Double comisionTotal, List<Venta> ventas) {
        this.id = id;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.comisionTotal = comisionTotal;
        this.ventas = ventas;
    }

    public Vendedor() {
    }
}
