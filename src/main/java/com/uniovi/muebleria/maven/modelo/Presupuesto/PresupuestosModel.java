package com.uniovi.muebleria.maven.modelo.Presupuesto;

import java.util.List;

import com.uniovi.muebleria.maven.util.Database;
public class PresupuestosModel {
	
	private Database db = new Database();
	
	public static final String SQL_LISTA_PRESUPUESTOS = "SELECT * FROM Presupuestos where aceptado = 0 and id_cliente is null";
	
	public List<PresupuestoDTO> obtenerPresupuestos(){
		return db.recogerPresupuestos(SQL_LISTA_PRESUPUESTOS);
		
	}
}
