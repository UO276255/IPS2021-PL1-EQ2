package com.uniovi.muebleria.maven.modelo.empleado;

import java.util.List;

import com.uniovi.muebleria.maven.util.Database;

public class VacacionesModel {

	private Database db = new Database();
	
	private static final String SQL_TRANSPORTISTAS = "SELECT * FROM Transportista";
	private static final String SQL_VENDEDORES = "SELECT * FROM Vendedor";
	private static final String SQL_TRABAJADORES_ALMACEN = "SELECT * FROM Personalalmacen";
	
	public List<EmpleadoDTO> getListaEmpleadosTransportistas() {
		return db.recogerEmpleados(SQL_TRANSPORTISTAS);
	}

	public List<EmpleadoDTO> getListaEmpleadosVendedores() {
		return db.recogerEmpleados(SQL_VENDEDORES);
	}

	public List<EmpleadoDTO> getListaEmpleadosAlmacen() {
		return db.recogerEmpleados(SQL_TRABAJADORES_ALMACEN);
	}

}
