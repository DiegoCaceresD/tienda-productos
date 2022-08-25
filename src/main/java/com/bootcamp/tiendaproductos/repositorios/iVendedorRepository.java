package com.bootcamp.tiendaproductos.repositorios;

import com.bootcamp.tiendaproductos.entidades.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iVendedorRepository extends JpaRepository<Vendedor, Long> {


}
