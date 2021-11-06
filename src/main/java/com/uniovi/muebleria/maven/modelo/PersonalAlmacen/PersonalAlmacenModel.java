package com.uniovi.muebleria.maven.modelo.PersonalAlmacen;

import java.sql.Time;

import com.uniovi.muebleria.maven.util.Database;

public class PersonalAlmacenModel {

	private Database db = new Database();
	private static final String SQL_AÑADIR_PERSONAL_ALMACEN ="insert into PersonalAlmacen (id_perAlmacen,Nombre,Apellido,DNI,telefono,usuario,contraseña,hora_entrada,hora_salida) values (?,?,?,?,?,?,?,?,?)";
	public static final String SQL_CONTAR_PERSONAL_ALMACEN = "SELECT count(*) FROM PersonalAlmacen";
	
	public void crearVendedor(String nombre, String apellido, String DNI, int telefono, String usuario,
			String contraseña, Time horaEntrada, Time horaSalida) {	
		int id = contarPersonalAlmacen() + 1;		
		db.crearPersonalAlmacen(SQL_AÑADIR_PERSONAL_ALMACEN,id,nombre,apellido,DNI,telefono,usuario,contraseña,horaEntrada,horaSalida);
	}
	
	public int contarPersonalAlmacen() {
		return db.contarDatos(SQL_CONTAR_PERSONAL_ALMACEN);
	}

}
