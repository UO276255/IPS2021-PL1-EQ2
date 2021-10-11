package com.uniovi.muebleria.maven.modelo;

import java.util.ArrayList;

import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;
import com.uniovi.muebleria.maven.util.Database;

public class MuebleriaModel {
	
	private Database db = new Database();
	
	public ArrayList<TransportistaDTO> getListaTransportistas(){
		String sql =
				 "SELECT * FROM Transportista";
		return db.recogeTransportistas(sql);
	}
}
