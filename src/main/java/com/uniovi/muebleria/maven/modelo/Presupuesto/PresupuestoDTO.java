package com.uniovi.muebleria.maven.modelo.Presupuesto;

import java.util.Date;

public class PresupuestoDTO {
	
	private int IdPresupuesto ;
	private int precio;
	private boolean aceptado;
	private Date fechaCaducidad;
	private int idCliente;
	
	public PresupuestoDTO() {
		
	}
	
	public PresupuestoDTO(int IdPresupuesto, int precio, 
			boolean aceptado,Date fechaCaducidad,int idCliente) {
		this.IdPresupuesto = IdPresupuesto;
		this.precio = precio;
		this.aceptado = aceptado;
		this.fechaCaducidad = fechaCaducidad;
		this.idCliente = idCliente;
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
		String cadena = "Id:" + getIdPresupuesto()  + " - Precio: " + getPrecio() + " - Caducidad: "
				+ getFechaCaducidad().toString() + " - Id Cliente: " + getIdCliente();
		return cadena;
	
	}
}
