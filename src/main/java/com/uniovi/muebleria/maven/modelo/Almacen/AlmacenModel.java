package com.uniovi.muebleria.maven.modelo.Almacen;

import java.util.List;

import com.uniovi.muebleria.maven.util.Database;

public class AlmacenModel {
	
	private Database db = new Database();
	
	public static final String SQL_ALMACEN_INFO = "SELECT * FROM ALMACEN";
	
	public List<AlmacenDTO> obtenerAlmacenes(){
		return db.obtenerAlmacenes(SQL_ALMACEN_INFO);	
	}
	
}
