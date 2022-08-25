package com.bootcamp.tiendaproductos.servicios;

import com.bootcamp.tiendaproductos.entidades.Vendedor;
import com.bootcamp.tiendaproductos.excepciones.InexistentExcpetion;
import com.bootcamp.tiendaproductos.excepciones.NullException;
import com.bootcamp.tiendaproductos.repositorios.iVendedorRepository;
import org.springframework.stereotype.Service;

@Service
public class VendedorService implements iVendedorService{

    private final iVendedorRepository iVendedorRepository;

    public VendedorService(iVendedorRepository iVendedorRepository) {
        this.iVendedorRepository = iVendedorRepository;
    }


    public void addComision(Vendedor vendedor, Double comision){
        vendedor.setComisionTotal(vendedor.getComisionTotal() + comision);

        this.iVendedorRepository.save(vendedor);
    }

    public Vendedor nuevoVendedor(Vendedor vendedor) throws InexistentExcpetion, NullException{
        if (vendedor!=null){
            if (this.iVendedorRepository.findById(vendedor.getId()) != null){
                throw new RuntimeException("Ya existe un vendedor con ese Id");
            }
            return this.iVendedorRepository.save(vendedor);
        }else {
            throw new NullException();
        }
    }
}
