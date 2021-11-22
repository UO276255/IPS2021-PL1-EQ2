package com.uniovi.muebleria.maven.modelo.empleado;

import java.util.Date;
import java.util.List;

import com.uniovi.muebleria.maven.util.Database;

public class VacacionesModel {

	private Database db = new Database();
	
	private static final String SQL_TRANSPORTISTAS = "SELECT * FROM Empleado where oficio = 't'";
	private static final String SQL_VENDEDORES = "SELECT * FROM Empleado where oficio = 'v'";
	private static final String SQL_TRABAJADORES_ALMACEN = "SELECT * FROM Empleado where oficio = 'pa'";
	
	private static final String SQL_INSERTAR_FECHA = "insert into Vacaciones (Id_vacacion,inicio_vacaciones,fin_vacaciones,Id_empleado) values (?,?,?,?)";
	
	public List<EmpleadoDTO> getListaEmpleadosTransportistas() {
		return db.recogerEmpleados(SQL_TRANSPORTISTAS);
	}

	public List<EmpleadoDTO> getListaEmpleadosVendedores() {
		return db.recogerEmpleados(SQL_VENDEDORES);
	}

	public List<EmpleadoDTO> getListaEmpleadosAlmacen() {
		return db.recogerEmpleados(SQL_TRABAJADORES_ALMACEN);
	}
	
	public void asignaFechaEmpleados(int id, Date dateIn, Date dateFin) {
		long timeInMilliSeconds = dateIn.getTime();
        java.sql.Date fechaIn = new java.sql.Date(timeInMilliSeconds);
        timeInMilliSeconds = dateFin.getTime();
        java.sql.Date fechaFin = new java.sql.Date(timeInMilliSeconds);
		db.asignaFecha(SQL_INSERTAR_FECHA, fechaIn, fechaFin, id);
	}
	
}
