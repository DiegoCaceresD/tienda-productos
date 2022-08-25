package com.bootcamp.tiendaproductos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ventas")
    private Long id;
    @Column(name = "precio_total")
    private Double precioTotal;
    @Column(name = "comision")
    private Double comision;


//    @OneToMany
//    private List<Producto> productos;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "venta_producto",
            joinColumns = @JoinColumn(name = "id_ventas"),
            inverseJoinColumns = @JoinColumn(name = "id_productos")
    )
    private List<Producto> productos;

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Vendedor vendedor;

}
