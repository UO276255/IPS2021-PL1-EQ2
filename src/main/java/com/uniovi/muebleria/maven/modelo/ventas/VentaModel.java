package com.uniovi.muebleria.maven.modelo.ventas;

import java.util.List;

import com.uniovi.muebleria.maven.util.Database;

public class VentaModel {

private Database db = new Database();
	
	public static final String SQL_VENTAS = "SELECT * FROM Venta";
	
	public List<VentaDTO> getListaVentas(){
		return db.recogerVentas(SQL_VENTAS);
	}
}
	
