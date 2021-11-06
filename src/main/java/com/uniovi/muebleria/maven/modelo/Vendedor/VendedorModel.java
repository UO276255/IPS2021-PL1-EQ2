package com.uniovi.muebleria.maven.modelo.Vendedor;

import java.sql.Time;

import com.uniovi.muebleria.maven.util.Database;

public class VendedorModel {
	
	private Database db = new Database();
	
	private static final String SQL_AÑADIR_VENDEDOR ="insert into Vendedor (id_vendedor,Nombre,Apellido,DNI,telefono,usuario,contraseña,hora_entrada,hora_salida) values (?,?,?,?,?,?,?,?,?)";
	public static final String SQL_CONTAR_VENDEDORES = "SELECT count(*) FROM Vendedor";
	
	public void crearVendedor(String nombre, String apellido,String DNI, int telefono,String usuario,String contraseña,Time hora_entrada, Time hora_salida) {
		
		int id = contarVendedores() + 1;
		
		db.crearVendedor(SQL_AÑADIR_VENDEDOR,id,nombre,apellido,DNI,telefono,usuario,contraseña,hora_entrada,hora_salida);
	}

	public int contarVendedores() {
		return db.contarDatos(SQL_CONTAR_VENDEDORES);
	}
	
}
