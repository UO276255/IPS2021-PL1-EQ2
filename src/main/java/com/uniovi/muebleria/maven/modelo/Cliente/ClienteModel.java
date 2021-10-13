package com.uniovi.muebleria.maven.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestoDTO;
import com.uniovi.muebleria.maven.util.Database;

public class ClienteModel {

	private Database db = new Database();
	
	public static final String SQL_LISTA_ClIENTE = "SELECT * FROM Cliente";
	
	public List<ClienteDTO> obtenerClientes(){
		return db.recogerClientes(SQL_LISTA_ClIENTE);

		
	}
}
