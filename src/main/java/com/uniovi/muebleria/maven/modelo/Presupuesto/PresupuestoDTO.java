package com.uniovi.muebleria.maven.modelo.Presupuesto;

import java.util.Date;

public class PresupuestoDTO {
	
	private int IdPresupuesto ;
	private int precio;
	private boolean aceptado;
	private Date fechaCaducidad;
	private int idCliente;
	private String nombreCliente;
	private Date fechaCreacion;
	
	public PresupuestoDTO() {
		
	}
	
	@SuppressWarnings("deprecation")
	public PresupuestoDTO(int IdPresupuesto, int precio, 
			boolean aceptado,Date fechaCaducidad,int idCliente,String nombreCliente) {
		this.IdPresupuesto = IdPresupuesto;
		this.precio = precio;
		this.aceptado = aceptado;
		this.fechaCaducidad = fechaCaducidad;
		this.idCliente = idCliente;
		this.fechaCreacion = fechaCaducidad;
		this.nombreCliente = nombreCliente;
		this.fechaCreacion.setDate(getFechaCaducidad().getDate() - 15);
	}

	public String getNombreCliente() {
		return nombreCliente;
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

	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
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

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String toString() {		
		String cadena = "Id:" + getIdPresupuesto()  + " - Precio: " + getPrecio() + " - Creación: "
				+ getFechaCreacion().toString() + " - Id Cliente: " + getIdCliente();
		return cadena;
	}
	
	public String toString2() {		
		String cadena = "Nombre cliente: " + getNombreCliente()  +" - Creación: "
				+ getFechaCreacion().toString();
		return cadena;
	}
}
