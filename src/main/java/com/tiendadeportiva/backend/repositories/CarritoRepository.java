package com.tiendadeportiva.backend.repositories;

import com.tiendadeportiva.backend.models.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    
    
    Optional<Carrito> findByUsuarioId(Long usuarioId);
}