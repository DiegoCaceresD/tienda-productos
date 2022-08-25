package com.bootcamp.tiendaproductos.controlador;

import com.bootcamp.tiendaproductos.dto.VendedorDTO;
import com.bootcamp.tiendaproductos.dto.VentaDTO;
import com.bootcamp.tiendaproductos.entidades.Vendedor;
import com.bootcamp.tiendaproductos.excepciones.InexistentExcpetion;
import com.bootcamp.tiendaproductos.excepciones.NullException;
import com.bootcamp.tiendaproductos.servicios.VendedorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {
    private Logger logger = LoggerFactory.getLogger(VendedorController.class);
    private VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @PostMapping()
    public ResponseEntity<?> altaVendedor (@RequestBody VendedorDTO vendedorDTO){
        ObjectMapper om = new ObjectMapper();
        try {
            logger.error("11111111");

            Vendedor vendedor = om.convertValue(vendedorDTO, Vendedor.class);

            Vendedor vendedorReturn = this.vendedorService.nuevoVendedor(vendedor);
            VendedorDTO resultado = om.convertValue(vendedorReturn, VendedorDTO.class);
            return ResponseEntity.ok(resultado);
        }catch (InexistentExcpetion e){
            logger.error("22222222");

            logger.error("El vendedor ya existe", e);
            return ResponseEntity.badRequest().body("ya existe el vendedor" + e.getMessage());
        }catch (NullException vendedorNulo){
            logger.error("333333333");

            vendedorNulo.printStackTrace();
            return ResponseEntity.badRequest().body("el vendedor no puede ser nulo");
        }catch (Exception e){
            logger.error("falloooooo");
            return ResponseEntity.internalServerError().build();
        }
    }
}
