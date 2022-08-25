package com.bootcamp.tiendaproductos.servicios;

import com.bootcamp.tiendaproductos.dto.VentaDTO;
import com.bootcamp.tiendaproductos.entidades.Producto;
import com.bootcamp.tiendaproductos.entidades.Vendedor;
import com.bootcamp.tiendaproductos.entidades.Venta;
import com.bootcamp.tiendaproductos.excepciones.NullException;
import com.bootcamp.tiendaproductos.repositorios.iProductoRepository;
import com.bootcamp.tiendaproductos.repositorios.iVendedorRepository;
import com.bootcamp.tiendaproductos.repositorios.iVentaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements iVentaService {

    private final iVentaRepository ventaRepository;
    private final iProductoRepository productoRepository;
    private final iVendedorRepository vendedorRepository;
    private final VendedorService vendedorService;

    public VentaService(iVentaRepository ventaRepository, iProductoRepository productoRepository, iVendedorRepository vendedorRepository, VendedorService vendedorService1) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.vendedorRepository = vendedorRepository;
        this.vendedorService = vendedorService1;
    }


    @Override
    public Venta nuevaVenta(Venta venta) throws NullException {
        if (venta != null) {
            return this.ventaRepository.save(venta);
        } else {
            throw new NullException();
        }
    }

    @Override
    public List<Venta> getAll() {
        return this.ventaRepository.findAll();
    }

    @Override
    public List<Venta> findVentaByVendedor(Long id) {
        return this.ventaRepository.findVentasByVendedor(id);
    }

    @Override
    public List<Venta> findVentaByProducto(String codigo) {
        return this.ventaRepository.findVentasByProductos(codigo);

    }


    // A partir de VentaDTO creo un objeto venta con sus productos, calculo su precio y su comision.
    // realizo un mapeo con logica de negocio
    public Venta ventaFromDTO(VentaDTO ventaDTO) {
        List<Producto> productos = new ArrayList<>();
        Double precioTotal = 0.0;
        for (Long id : ventaDTO.getIdsProductos()
        ) {
            Producto producto = this.productoRepository.findById(id).get();
            precioTotal += producto.getPrecio();
            productos.add(producto);
        }
        Venta venta = new Venta();
        Double comision = 0.0;

        if (productos.size() <= 2) {
            comision = precioTotal * 0.05;
        } else {
            comision = precioTotal * 0.10;
        }
        venta.setProductos(productos);
        venta.setPrecioTotal(precioTotal);
        venta.setComision(comision);
        Vendedor vendedor = this.vendedorRepository.findById(ventaDTO.getVendedorId()).get();
//        this.vendedorService.addComision(vendedor, comision);
        venta.setVendedor(vendedor);
        return venta;
    }
    public VentaDTO dtoFromVenta(Venta venta){

        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO.setId(venta.getId());
        ventaDTO.setVendedorId(venta.getVendedor().getId());
        List<Long> productosIds = new ArrayList<>();
        for (Producto producto:venta.getProductos()
             ) {
            productosIds.add(producto.getId());
        }
        ventaDTO.setIdsProductos(productosIds);
        return ventaDTO;
    }
}




