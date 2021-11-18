package com.uniovi.muebleria.maven.modelo.empleado;

import java.util.Date;
import java.util.List;

import com.uniovi.muebleria.maven.util.Database;

public class VacacionesModel {

	private Database db = new Database();
	
	private static final String SQL_TRANSPORTISTAS = "SELECT * FROM Transportista";
	private static final String SQL_VENDEDORES = "SELECT * FROM Vendedor";
	private static final String SQL_TRABAJADORES_ALMACEN = "SELECT * FROM Personalalmacen";
	
	private static final String SQL_FECHA_INICIO_TRANSPORTISTA = "UPDATE Transportista set inicio_vacaciones = ? where id_transp = ?";
	private static final String SQL_FECHA_FINAL_TRANSPORTISTA = "UPDATE Transportista set fin_vacaciones = ? where id_transp = ?";
	private static final String SQL_FECHA_INICIO_VENDEDOR = "UPDATE Vendedor set inicio_vacaciones = ? where Id_vendedor = ?";
	private static final String SQL_FECHA_FINAL_VENDEDOR = "UPDATE Vendedor set fin_vacaciones = ? where Id_vendedor = ?";
	private static final String SQL_FECHA_INICIO_PERSONALALMACEN = "UPDATE PersonalAlmacen set inicio_vacaciones = ? where Id_perAlmacen = ?";
	private static final String SQL_FECHA_FINAL_PERSONALALMACEN = "UPDATE PersonalAlmacen set fin_vacaciones = ? where Id_perAlmacen = ?";
	
	private static final String SQL_INICIO_VACACIONAL_TRANSP = "SELECT inicio_vacaciones FROM Transportista where id_transp = ?";
	private static final String SQL_INICIO_VACACIONAL_VEND = "SELECT inicio_vacaciones FROM Vendedor where Id_vendedor = ?";
	private static final String SQL_INICIO_VACACIONAL_PERSAL = "SELECT inicio_vacaciones FROM PersonalAlmacen where Id_perAlmacen = ?";
	
	public List<EmpleadoDTO> getListaEmpleadosTransportistas() {
		return db.recogerEmpleados(SQL_TRANSPORTISTAS);
	}

	public List<EmpleadoDTO> getListaEmpleadosVendedores() {
		return db.recogerEmpleados(SQL_VENDEDORES);
	}

	public List<EmpleadoDTO> getListaEmpleadosAlmacen() {
		return db.recogerEmpleados(SQL_TRABAJADORES_ALMACEN);
	}

	public void asignaFechaInicioTransportista(int id, Date date) {
		long timeInMilliSeconds = date.getTime();
        java.sql.Date fecha = new java.sql.Date(timeInMilliSeconds);
		db.asignaFecha(SQL_FECHA_INICIO_TRANSPORTISTA, fecha, id);
	}
	
	public void asignaFechaFinalTransportista(int id, Date date) {
		long timeInMilliSeconds = date.getTime();
        java.sql.Date fecha = new java.sql.Date(timeInMilliSeconds);
		db.asignaFecha(SQL_FECHA_FINAL_TRANSPORTISTA, fecha, id);
	}

	public void asignaFechaInicioVendedor(int id, Date date) {
		long timeInMilliSeconds = date.getTime();
        java.sql.Date fecha = new java.sql.Date(timeInMilliSeconds);
		db.asignaFecha(SQL_FECHA_INICIO_VENDEDOR, fecha, id);
	}
	
	public void asignaFechaFinalVendedor(int id, Date date) {
		long timeInMilliSeconds = date.getTime();
        java.sql.Date fecha = new java.sql.Date(timeInMilliSeconds);
		db.asignaFecha(SQL_FECHA_FINAL_VENDEDOR, fecha, id);
	}
	
	public void asignaFechaInicioPersonalAlmacen(int id, Date date) {
		long timeInMilliSeconds = date.getTime();
        java.sql.Date fecha = new java.sql.Date(timeInMilliSeconds);
		db.asignaFecha(SQL_FECHA_INICIO_PERSONALALMACEN, fecha, id);
	}
	
	public void asignaFechaFinalPersonalAlmacen(int id, Date date) {
		long timeInMilliSeconds = date.getTime();
        java.sql.Date fecha = new java.sql.Date(timeInMilliSeconds);
		db.asignaFecha(SQL_FECHA_FINAL_PERSONALALMACEN, fecha, id);
	}

	public Date getDiaInicioVacacionesTransp(int id) {
		return db.inicioVacacional(SQL_INICIO_VACACIONAL_TRANSP, id);
	}

	public Date getDiaInicioVacacionesVend(int id) {
		return db.inicioVacacional(SQL_INICIO_VACACIONAL_VEND, id);
	}

	public Date getDiaInicioVacacionesPerAl(int id) {
		return db.inicioVacacional(SQL_INICIO_VACACIONAL_PERSAL, id);
	}
}
