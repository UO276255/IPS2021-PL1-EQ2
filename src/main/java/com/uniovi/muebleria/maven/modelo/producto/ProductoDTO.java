package com.uniovi.muebleria.maven.modelo.producto;

public class ProductoDTO {
	private int id;
	private String nombre;
	private int precio;
	private String categoria;
	
	public ProductoDTO() {	}
	public ProductoDTO(int id, String nombre, int precio, String categoria) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
	}
	
	public int getId() { return this.id; }
	public String getNombre() { return this.nombre; }
	public int getPrecio() { return this.precio; }
	public String getCategoria() { return this.categoria; }
	
	public void setId(int id) { this.id=id; }
	public void setNombre(String nombre) { this.nombre=nombre; }
	public void setPrecio(int precio) { this.precio=precio; }
	public void setCategoria(String categoria) { this.categoria=categoria; }
}
