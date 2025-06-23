package com.tiendadeportiva.backend.repositories;

import com.tiendadeportiva.backend.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
   
}