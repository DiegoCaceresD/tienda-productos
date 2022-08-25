package com.bootcamp.tiendaproductos.servicios;

import com.bootcamp.tiendaproductos.entidades.Producto;
import com.bootcamp.tiendaproductos.excepciones.NullException;
import com.bootcamp.tiendaproductos.excepciones.InexistentExcpetion;
import com.bootcamp.tiendaproductos.repositorios.iProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements iProductoService{

    private final iProductoRepository productoRepository;

    public ProductoService(iProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto nuevoProducto(Producto producto) throws InexistentExcpetion, NullException {
        if (producto != null){
            if (this.productoRepository.findFirstByCodigo(producto.getCodigo()) != null){
                throw new RuntimeException("Ya existe un producto con ese codigo");
            }
            return this.productoRepository.save(producto);
        }else {
            throw new NullException();
        }

    }


    @Override
    public List<Producto> getAll() {
       return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> obtenerId(Long id) {

        return this.productoRepository.findById(id);
    }

    @Override
    public List<Producto> buscarPorCategoria(String categoria){
        if (productoRepository.findProductoByCategoria(categoria) == null){
            throw new RuntimeException("La Categoria no existe");
        }else{
            return productoRepository.findProductoByCategoria(categoria);
        }

    }

    @Override
    public Optional<Producto> buscarPorNombre(String nombre) {
        if (productoRepository.findProductoByNombre(nombre) == null) {
            throw new RuntimeException("La Categoria no existe");
        } else {
            return productoRepository.findProductoByNombre(nombre);
        }
    }

    @Override
    public Producto findFirstByCodigo(String codigo) {
        return null;
    }
}
