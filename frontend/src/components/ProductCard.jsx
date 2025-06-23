import React from 'react';
import { Card, CardContent, Typography, Button } from '@mui/material';

function ProductCard({ product }) {
  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {product.nombre}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          ${product.precio}
        </Typography>
        <Button size="small" variant="contained" sx={{ mt: 2 }}>
          Añadir al carrito
        </Button>
      </CardContent>
    </Card>
  );
}

export default ProductCard;