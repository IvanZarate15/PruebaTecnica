package com.tiendadeportiva.backend.dto;

import com.tiendadeportiva.backend.models.ItemPedido;

public class ItemPedidoDTO {
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    
    public ItemPedidoDTO(ItemPedido item) {
        this.nombreProducto = item.getProducto().getNombre();
        this.cantidad = item.getCantidad();
        this.precioUnitario = item.getPrecioUnitario();
        this.subtotal = item.getCantidad() * item.getPrecioUnitario();
    }

  
    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }
}