package com.uniovi.muebleria.maven.modelo.producto;

public class ProductoDTO {
	private int id;
	private String nombre;
	private int precio;
	private String categoria;
	private boolean montaje;
	private boolean transporte;
	
	public ProductoDTO() {	}
	public ProductoDTO(int id, String nombre, int precio, String categoria) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
	}
	
	public ProductoDTO(int id, String nombre, int precio, String categoria, boolean montaje, boolean transporte) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.montaje = montaje;
		this.transporte = transporte;
	}
	
	public int getId() { return this.id; }
	public String getNombre() { return this.nombre; }
	public int getPrecio() { return this.precio; }
	public String getCategoria() { return this.categoria; }
	public boolean getMontaje() {return this.montaje;}
	public boolean getTransporte() {return this.transporte;}
	
	public void setId(int id) { this.id=id; }
	public void setNombre(String nombre) { this.nombre=nombre; }
	public void setPrecio(int precio) { this.precio=precio; }
	public void setCategoria(String categoria) { this.categoria=categoria; }
	public void setMontaje(boolean montaje) {this.montaje=montaje;}
	public void setTransporte(boolean transporte) {this.transporte=transporte;}
	
	public String toString() {
		String cadena = "Id: " + getId() + ", Nombre: " + getNombre() + ", precio: "
				+ getPrecio() + ", categoria: " + getCategoria();
		return cadena;
	}
}
