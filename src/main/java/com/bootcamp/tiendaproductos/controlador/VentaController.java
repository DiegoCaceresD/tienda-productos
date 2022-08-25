package com.bootcamp.tiendaproductos.controlador;

import com.bootcamp.tiendaproductos.dto.VentaDTO;
import com.bootcamp.tiendaproductos.entidades.Venta;
import com.bootcamp.tiendaproductos.excepciones.NullException;
import com.bootcamp.tiendaproductos.servicios.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
    @RequestMapping("/ventas")
public class VentaController {
    private VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping()
    public ResponseEntity<?> nuevaVenta(@RequestBody VentaDTO ventaDTO) {
        try {
            //mappeo manual**

            Venta venta = this.ventaService.ventaFromDTO(ventaDTO);

            Venta ventaReturn = this.ventaService.nuevaVenta(venta);

            VentaDTO resultado = this.ventaService.dtoFromVenta(ventaReturn);

            return ResponseEntity.ok(resultado);
        } catch (NullException ventaNula) {
            ventaNula.printStackTrace();
            return ResponseEntity.badRequest().body("La venta no puede ser nula");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/listadoventas")
    public ResponseEntity<?> getAllVentas() {
        Map<String, Object> mensaje = new HashMap<>();
        List<Venta> listaVentas = this.ventaService.getAll();
        if (listaVentas.isEmpty()) {
            mensaje.put("mensaje", "No hay ventas");
        } else {
            mensaje.put("ventas", listaVentas);
        }
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/vendedor/{id}")
    public ResponseEntity<?> getVentaByVendedor(@PathVariable Long id) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Venta> listaVentas = this.ventaService.findVentaByVendedor(id);
        if (listaVentas.isEmpty()) {
            mensaje.put("mensjae", "Este vendedor no tiene registrada niguna venta");

        } else {
            mensaje.put("ventas", listaVentas);
        }
        return ResponseEntity.ok(mensaje);
    }

}
