package com.bootcamp.tiendaproductos.controlador;

import com.bootcamp.tiendaproductos.dto.ProductoDTO;
import com.bootcamp.tiendaproductos.entidades.Producto;
import com.bootcamp.tiendaproductos.excepciones.NullException;
import com.bootcamp.tiendaproductos.excepciones.InexistentExcpetion;
import com.bootcamp.tiendaproductos.repositorios.iProductoRepository;
import com.bootcamp.tiendaproductos.servicios.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
//@CrossOrigin(origins = "http://http://localhost:4200/")
public class ProductoController {
    private Logger logger = LoggerFactory.getLogger(ProductoController.class);


    private ProductoService productoServicio;
    private iProductoRepository productoRepository;

    public ProductoController(ProductoService productoServicio) {

        this.productoServicio = productoServicio;
    }

    @GetMapping("/todos")
    public ResponseEntity<?> listarProductos(){
        Map<String, Object> mensaje = new HashMap<>();
        List<Producto> listaProductos = this.productoServicio.getAll();
        if(listaProductos.isEmpty()){
            mensaje.put("mensaje", "no hay productos");
        }else {
            mensaje.put("productos", listaProductos);
        }
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping()
    public ResponseEntity<?> altaProducto(@RequestBody ProductoDTO productoDTO) {
        ObjectMapper om = new ObjectMapper();
        try {
            // mappeo
            Producto producto = om.convertValue(productoDTO, Producto.class);

            Producto productoReturn = this.productoServicio.nuevoProducto(producto);

            ProductoDTO resultado = om.convertValue(productoReturn, ProductoDTO.class);
            return ResponseEntity.ok(resultado);
        } catch (InexistentExcpetion e) {
            logger.error("El producto ya existe", e);
            return ResponseEntity.badRequest().body("ya existe el producto" + e.getMessage());
        } catch (NullException productoNulo) {
            productoNulo.printStackTrace();
            return ResponseEntity.badRequest().body("el producto no puede ser nulo");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<?> buscarProductoPorCategoria(@PathVariable String categoria) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Producto> productos = this.productoServicio.buscarPorCategoria(categoria);
        if (productos.isEmpty()){
            mensaje.put("mensaje", "Categoria inexistente");
        } else {
            mensaje.put("datos", productos);
        }
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> buscarProductoPorNombre(@PathVariable String nombre) {
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Producto> productos = this.productoServicio.buscarPorNombre(nombre);
        if (!productos.isPresent()){
            mensaje.put("mensaje", "Producto inexistente");
        }else {
            mensaje.put("productos", productos);
        }
        return ResponseEntity.ok(mensaje);
    }

}
