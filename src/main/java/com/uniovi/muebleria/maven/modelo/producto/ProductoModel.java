package com.uniovi.muebleria.maven.modelo.producto;

import java.util.List;

import com.uniovi.muebleria.maven.util.Database;

public class ProductoModel {
	
private Database db = new Database();
	
	public static final String SQL_PRODUCTOS_TRANSPORTADOS = "SELECT * Convert(bit, 1) as active FROM Productos";
	
	public List<ProductoDTO> getListaProductosTrans(){
		return db.recogerProductosTransp(SQL_PRODUCTOS_TRANSPORTADOS);
	}
}
