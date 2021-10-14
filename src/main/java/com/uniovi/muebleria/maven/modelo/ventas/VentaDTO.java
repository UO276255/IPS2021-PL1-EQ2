package com.uniovi.muebleria.maven.modelo.ventas;

import java.util.Date;

public class VentaDTO {
	private int id_venta;
	private Date fecha;
	private int precio;
	private boolean transporte;
	private int id_pres;
	
	public VentaDTO() {}
	
	public VentaDTO(int id_venta, Date fecha, int precio, boolean transporte,int id_pres) {
		this.id_venta = id_venta;
		this.fecha = fecha;
		this.precio = precio;
		this.transporte = transporte;
		this.id_pres = id_pres;
	}
	
	public int getId_pres() {
		return id_pres;
	}

	public void setId_pres(int id_pres) {
		this.id_pres = id_pres;
	}

	public int getId_venta() {
		return id_venta;
	}
	public void setId_venta(int id_venta) {
		this.id_venta = id_venta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public boolean isTransporte() {
		return transporte;
	}
	public void setTransporte(boolean transporte) {
		this.transporte = transporte;
	}

}
