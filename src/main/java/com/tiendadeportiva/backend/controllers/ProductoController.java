package com.tiendadeportiva.backend.controllers;

import com.tiendadeportiva.backend.models.Producto;
import com.tiendadeportiva.backend.repositories.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepositorio ProductoRepositorio;

    
    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return ProductoRepositorio.findAll();
    }

    
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = ProductoRepositorio.save(producto);
        return ResponseEntity.ok(nuevoProducto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        ProductoRepositorio.deleteById(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }
}