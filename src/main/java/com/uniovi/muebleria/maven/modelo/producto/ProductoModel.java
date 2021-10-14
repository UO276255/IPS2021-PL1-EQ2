package com.uniovi.muebleria.maven.modelo.producto;

import java.util.List;

import com.uniovi.muebleria.maven.util.Database;

public class ProductoModel {
	
private Database db = new Database();
	
	public static final String SQL_PRODUCTOS_NO_TRANSP = "SELECT * FROM Productos where Transporte = 0";
	public static final String SQL_PRODUCTOS_TRANSP = "SELECT * FROM Productos where Transporte = 1";
	public static final String SQL_PRODUCTOS_NO_MONTAR = "SELECT * FROM Productos where Montaje = 0 and Transporte = 1";
	public static final String SQL_PRODUCTOS_MONTAR = "SELECT * FROM Productos where Montaje = 1 and Transporte = 1";
	
	public static final String SQL_ACTUALIZA_TRANSPORTE = "UPDATE Productos set Transporte = ? where id_prod= ?";
	public static final String SQL_ACTUALIZA_MONTAJE = "UPDATE Productos set Montaje = ? where id_prod= ?";
	
	public List<ProductoDTO> getListaProductosNoTransp(){
		return db.recogerProductos(SQL_PRODUCTOS_NO_TRANSP);
	}
	
	public List<ProductoDTO> getListaProductosTransp(){
		return db.recogerProductos(SQL_PRODUCTOS_TRANSP);
	}
	
	public List<ProductoDTO> getListaProductosNoMontar(){
		return db.recogerProductos(SQL_PRODUCTOS_NO_MONTAR);
	}
	
	public List<ProductoDTO> getListaProductosMontar(){
		return db.recogerProductos(SQL_PRODUCTOS_MONTAR);
	}
	
	public void ActualizarTransporte(int bit, int id) {
		db.AsignarTransporte(SQL_ACTUALIZA_TRANSPORTE,bit,id);
	}
	
	public void ActualizarMontaje(int bit, int id) {
		db.AsignarTransporte(SQL_ACTUALIZA_MONTAJE,bit,id);
	}
}
