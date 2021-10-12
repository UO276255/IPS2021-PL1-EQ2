package com.uniovi.muebleria.maven.modelo.transportista;

import java.util.List;

import com.uniovi.muebleria.maven.util.Database;

public class TransportistaModel {
	
	private Database db = new Database();
	
	public static final String SQL_TRABAJADORES = "SELECT * FROM Transportista";
	
	public List<TransportistaDTO> getListaTransportistas(){
		return db.recogeTransportistas(SQL_TRABAJADORES);
	}
}
