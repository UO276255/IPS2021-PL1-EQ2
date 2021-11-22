package com.uniovi.muebleria.maven.modelo.PersonalAlmacen;

import java.sql.Time;
import java.util.List;

import com.uniovi.muebleria.maven.util.Database;

public class PersonalAlmacenModel {

	private Database db = new Database();
	private static final String SQL_AÑADIR_PERSONAL_ALMACEN ="insert into Empleado (id_empleado,Nombre,Apellido,DNI,Telefono,Usuario,Contraseña,hora_entrada,hora_salida,inicio_vacaciones,fin_vacaciones,oficio) values (?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SQL_CONTAR_PERSONAL_ALMACEN = "SELECT count(*) FROM Empleado";
	public static final String SQL_USUARIO_CONTRASEÑA_ALMACEN = "SELECT usuario,contraseña FROM Empleado where oficio = 'pa'";
	
	public void crearPersonalAlmacen(String nombre, String apellido, String DNI, int telefono, String usuario,
			String contraseña, Time horaEntrada, Time horaSalida, String oficio) {	
		int id = contarPersonalAlmacen() + 1;		
		db.crearPersonalAlmacen(SQL_AÑADIR_PERSONAL_ALMACEN,id,nombre,apellido,DNI,telefono,usuario,contraseña,horaEntrada,horaSalida,oficio);
	}
	
	public int contarPersonalAlmacen() {
		return db.contarDatos(SQL_CONTAR_PERSONAL_ALMACEN);
	}

	public List<PersonalAlmacenDTO> getPersonalLogin() {
        return db.getAlmacenLogin(SQL_USUARIO_CONTRASEÑA_ALMACEN);
    }
}
