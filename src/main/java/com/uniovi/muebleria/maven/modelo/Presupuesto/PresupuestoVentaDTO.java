package com.uniovi.muebleria.maven.modelo.Presupuesto;

import java.util.Date;

public class PresupuestoVentaDTO {
	private int IdPresupuesto ;
	private int precio;
	private Date fechaCaducidad;
	private String nombreCliente;
	private Date fechaCreacion;
	private boolean aceptado;
	
	public PresupuestoVentaDTO() {
		
	}
	
	@SuppressWarnings("deprecation")
	public PresupuestoVentaDTO(int IdPresupuesto, int precio,Date fechaCaducidad,String nombreCliente) {
		this.IdPresupuesto = IdPresupuesto;
		this.precio = precio;
		this.fechaCaducidad = fechaCaducidad;
		this.fechaCreacion = fechaCaducidad;
		this.nombreCliente = nombreCliente;
		this.fechaCreacion.setDate(getFechaCaducidad().getDate() - 15);
	}
	public PresupuestoVentaDTO(int IdPresupuesto, int precio,Date fechaCaducidad,String nombreCliente,boolean aceptado) {
		this.IdPresupuesto = IdPresupuesto;
		this.precio = precio;
		this.fechaCaducidad = fechaCaducidad;
		this.fechaCreacion = fechaCaducidad;
		this.nombreCliente = nombreCliente;
		this.fechaCreacion.setDate(getFechaCaducidad().getDate() - 15);
		this.aceptado=aceptado;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombre) {
		this.nombreCliente = nombre;
	}
	
	public int getIdPresupuesto() {
		return IdPresupuesto;
	}

	public void setIdPresupuesto(int idPresupuesto) {
		IdPresupuesto = idPresupuesto;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	
	public String toString() {		
		String cadena = "Nombre cliente: " + getNombreCliente()  +" - Creación: "
				+ getFechaCreacion().toString();
		return cadena;
	}
}
