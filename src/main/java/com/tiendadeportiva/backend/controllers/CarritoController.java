package com.tiendadeportiva.backend.controllers;

import com.tiendadeportiva.backend.models.*;
import com.tiendadeportiva.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CarritoRepository carritoRepository;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ProductoRepositorio ProductoRepositorio; 

    @PostMapping("/checkout")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> checkout(Principal principal) {
      
        Usuario usuario = usuarioRepository.findByEmail(principal.getName())
                            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Carrito carrito = carritoRepository.findByUsuarioId(usuario.getId())
                            .orElseThrow(() -> new RuntimeException("Carrito vacío"));

        
        for (ItemCarrito item : carrito.getItems()) {
            Producto producto = item.getProducto();
            if (producto.getStock() < item.getCantidad()) {
                return ResponseEntity.badRequest().body(
                    "Error: Stock insuficiente para '" + producto.getNombre() + 
                    "'. Stock disponible: " + producto.getStock()
                );
            }
        }

        
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        
        carrito.getItems().forEach(item -> {
            
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProducto(item.getProducto());
            itemPedido.setCantidad(item.getCantidad());
            pedido.getItems().add(itemPedido);
            
            
            Producto producto = item.getProducto();
            producto.setStock(producto.getStock() - item.getCantidad());
            ProductoRepositorio.save(producto);
        });

        
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        carrito.getItems().clear();
        carritoRepository.save(carrito);

        return ResponseEntity.ok(pedidoGuardado);
    }
}