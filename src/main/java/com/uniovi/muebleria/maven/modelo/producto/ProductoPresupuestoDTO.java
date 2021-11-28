package com.uniovi.muebleria.maven.modelo.producto;

public class ProductoPresupuestoDTO {
	private int id;
	
	private int idProducto;
	private String nombre;
	private int precioPresupuesto;
	private String categoria;
	private int cantidad;
	private int precioActual;
	
	public ProductoPresupuestoDTO() {	}

	public ProductoPresupuestoDTO(int id, int idProducto, String nombre, int precioPres, String categoria, int cantidad, int precioActual) {
		this.id = id;
		this.idProducto=idProducto;
		this.nombre=nombre;
		this.precioPresupuesto=precioPres;
		this.categoria=categoria;
		this.cantidad = cantidad;
		this.precioActual=precioActual;
	}
	
	public int getId() {
		return id;
	}
	public int getIdProducto() {
		return idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPrecioPresupuesto() {
		return precioPresupuesto;
	}

	public String getCategoria() {
		return categoria;
	}

	public int getCantidad() {
		return cantidad;
	}

	public int getPrecioActual() {
		return precioActual;
	}
	
	public String toString() {
		return getNombre()+ " - " + getCantidad() + " uds - Precio Presupuestado: "+getPrecioPresupuesto()+ " - Precio Actual: "+getPrecioActual();
	}
	public void incCantidad() {
		this.cantidad++;
	}
}
