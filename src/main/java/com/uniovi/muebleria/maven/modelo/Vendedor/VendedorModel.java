package com.uniovi.muebleria.maven.modelo.Vendedor;

import java.sql.Time;
import java.util.List;

import com.uniovi.muebleria.maven.util.Database;

public class VendedorModel {
	
	private Database db = new Database();
	
	private static final String SQL_AÑADIR_VENDEDOR ="insert into Empleado (id_empleado,Nombre,Apellido,DNI,Telefono,Usuario,Contraseña,hora_entrada,hora_salida,oficio) values (?,?,?,?,?,?,?,?,?,?)";
	public static final String SQL_CONTAR_VENDEDORES = "SELECT count(*) FROM Empleado";
	public static final String SQL_USUARIO_CONTRASEÑA_VENDEDORES = "SELECT usuario,contraseña FROM Empleado where oficio = 'v'";
	
	public void crearVendedor(String nombre, String apellido,String DNI, int telefono,String usuario,String contraseña,Time hora_entrada, Time hora_salida, String oficio) {
		
		int id = contarVendedores() + 1;
		
		db.crearVendedor(SQL_AÑADIR_VENDEDOR,id,nombre,apellido,DNI,telefono,usuario,contraseña,hora_entrada,hora_salida,oficio);
	}

	public int contarVendedores() {
		return db.contarDatos(SQL_CONTAR_VENDEDORES);
	}
	
	public List<VendedorDTO> getVendedoresLogin() {
        return db.getVendedores(SQL_USUARIO_CONTRASEÑA_VENDEDORES);
    }
}
