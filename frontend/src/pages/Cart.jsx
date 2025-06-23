import React, { useEffect, useState } from 'react';
import api from '../api';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button } from '@mui/material';

function Cart() {
  const [cart, setCart] = useState({ items: [] });

  useEffect(() => {
    api.get('/carrito')
      .then(response => setCart(response.data))
      .catch(error => console.error('Error fetching cart:', error));
  }, []);

  const handleCheckout = () => {
    api.post('/carrito/checkout')
      .then(() => alert('Pedido realizado con éxito!'))
      .catch(error => alert(`Error: ${error.response.data}`));
  };

  return (
    <TableContainer component={Paper}>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>Producto</TableCell>
            <TableCell>Cantidad</TableCell>
            <TableCell>Precio</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {cart.items.map(item => (
            <TableRow key={item.id}>
              <TableCell>{item.producto.nombre}</TableCell>
              <TableCell>{item.cantidad}</TableCell>
              <TableCell>${item.producto.precio * item.cantidad}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <Button variant="contained" color="success" onClick={handleCheckout} sx={{ mt: 2 }}>
        Finalizar Compra
      </Button>
    </TableContainer>
  );
}

export default Cart;