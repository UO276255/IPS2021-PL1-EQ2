package com.uniovi.muebleria.maven.modelo.Almacen;

public class AlmacenDTO {

	private String nombre;
	private int idAlmacen ;
	private int numProductos;
	
	public AlmacenDTO(String nombre,int idAlmacen, int numProductos) {
		this.nombre = nombre;
		this.numProductos = numProductos;
		this.idAlmacen = idAlmacen;
	}
	
	public int getIdAlmacen() {
		return this.idAlmacen;
	}
	
	public int getProductos() {
		return this.numProductos;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String toString() {
		return "ID del Almacen: " + idAlmacen + " - Nombre: " +getNombre()+ " - Numero productos: " + getProductos();
	}
	
}
