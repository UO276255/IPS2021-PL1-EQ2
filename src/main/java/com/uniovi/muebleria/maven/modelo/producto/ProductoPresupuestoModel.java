package com.uniovi.muebleria.maven.modelo.producto;

import java.util.List;

import com.uniovi.muebleria.maven.util.Database;

public class ProductoPresupuestoModel {

	private Database db = new Database();
	
	private static final String SQL_PRODUCTO_VENTA = "SELECT * FROM Productos";
	private static final String SQL_PRECIO_PRODUCTO = "SELECT * FROM Productos where id_prod = ?";
	
	public List<ProductoDTO> getListaProductos() {
		return db.recogerProductos(SQL_PRODUCTO_VENTA);
	}
	
	public int getPrecioProd(int id) {
		return db.precioProducto(SQL_PRECIO_PRODUCTO, id);
	}
}
