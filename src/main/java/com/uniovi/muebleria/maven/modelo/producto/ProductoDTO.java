package com.uniovi.muebleria.maven.modelo.producto;

public class ProductoDTO {
	private int id;
	private String nombre;
	private int precio;
	private String categoria;
	private int cantidadenAlmacen;
	public ProductoDTO() {	}
	
	public ProductoDTO(int id, String nombre, int precio, String categoria) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
	}
	
	public ProductoDTO(String nombre, int cantidad) {
		this.nombre = nombre;
		this.cantidadenAlmacen = cantidad;
	}
	
	public int getId() { return this.id; }
	public String getNombre() { return this.nombre; }
	public int getPrecio() { return this.precio; }
	public String getCategoria() { return this.categoria; }
	public int getCantidadAlmacen() {return this.cantidadenAlmacen;};
	
	public void setId(int id) { this.id=id; }
	public void setNombre(String nombre) { this.nombre=nombre; }
	public void setPrecio(int precio) { this.precio=precio; }
	public void setCategoria(String categoria) { this.categoria=categoria; }
	
	public String toString() {
		String cadena = "Id: " + getId() + ", Nombre: " + getNombre() + ", precio: "
				+ getPrecio() + ", categoria: " + getCategoria();
		return cadena;
	}
	
	public String toStringPedido(int cantidad) {
		String cadena ="Nombre: " + getNombre() + ", precio: "
				+ getPrecio() + ", categoria: " + getCategoria() + ", x " + cantidad + " uds";
		return cadena;
	}
}
