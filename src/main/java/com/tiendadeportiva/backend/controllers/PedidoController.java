package com.tiendadeportiva.backend.controllers;

import com.tiendadeportiva.backend.models.Pedido;
import com.tiendadeportiva.backend.repositories.PedidoRepository;
import com.tiendadeportiva.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

   
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Pedido> getPedidos(Principal principal) {
        Long usuarioId = usuarioRepository.findByEmail(principal.getName())
                            .orElseThrow().getId();
        return pedidoRepository.findByUsuarioId(usuarioId);
    }
}