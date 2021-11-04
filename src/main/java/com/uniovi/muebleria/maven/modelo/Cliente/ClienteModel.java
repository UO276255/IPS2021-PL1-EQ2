package com.uniovi.muebleria.maven.modelo.Cliente;

import java.sql.Date;
import java.util.List;

import com.uniovi.muebleria.maven.util.Database;

public class ClienteModel {

	private Database db = new Database();
	
	public static final String SQL_LISTA_ClIENTE = "SELECT * FROM Cliente";
	public static final String SQL_AGREGAR_CLIENTE = "insert into Cliente (id,Nombre,Apellido,Fecha_nac,dni,email) values (?,?,?,?,?,?);";
	public static final String SQL_CONTAR_ClIENTE = "SELECT count(*) FROM Cliente";
	
	public List<ClienteDTO> obtenerClientes(){
		return db.recogerClientes(SQL_LISTA_ClIENTE);	
	}

	public void crearCliente(String nombre, String apellido,String fecha,int dni, String email) {
		int id = contarClientes() + 1;
		Date fecha_final = convertirADate(fecha);
		db.crearCliente(SQL_AGREGAR_CLIENTE,id,nombre,apellido,fecha_final,dni,email);		
	}
	public int contarClientes() {
		return db.contarDatos(SQL_CONTAR_ClIENTE);
	}

	private Date convertirADate(String fecha) {
		Date date = Date.valueOf(fecha);
		return date;
	}
}
