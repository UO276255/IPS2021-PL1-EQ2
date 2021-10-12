package com.uniovi.muebleria.maven.modelo.transportista;

import java.util.Date;

public class TransportistaDTO {
	private String nombre;
	private int numTelefono;
	private Date horarioInicio;
	private Date horarioFin;
	
	public TransportistaDTO() {}
	public TransportistaDTO(String nombre, int numTelefono, Date horarioInicio, Date horarioFin) {
		this.nombre = nombre;
		this.numTelefono = numTelefono;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
	}
	
	public String getNombre() { return this.nombre; }
	public int getNumTelefono() { return this.numTelefono; }
	public Date getHorarioIn() { return this.horarioInicio; }
	public Date getHorarioFin() { return this.horarioFin; }
	
	public void setNombre(String nombre) { this.nombre=nombre; }
	public void setNumTelefono(int numTelefono) { this.numTelefono=numTelefono; }
	public void setHorarioIn(Date horarioInicio) { this.horarioInicio=horarioInicio; }
	public void setHorarioFin(Date horarioFin) { this.horarioFin=horarioFin; }
	
	public String toString() {
		String str = "Nombre: " + getNombre() + ", Número de tlfn: " + getNumTelefono() + ", Entra a las " + 
				getHorarioIn() + ", Sale a las " + getHorarioFin();
		return str;
	}
}
