package com.tiendadeportiva.backend.dto;

import com.tiendadeportiva.backend.models.ItemPedido;
import com.tiendadeportiva.backend.models.Pedido;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDTO {
    private Long id;
    private LocalDateTime fecha;
    private List<ItemPedidoDTO> items;

    
    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.fecha = pedido.getFecha();
        this.items = pedido.getItems().stream()
            .map(ItemPedidoDTO::new)
            .collect(Collectors.toList());
    }

    
    public Long getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public List<ItemPedidoDTO> getItems() {
        return items;
    }
}