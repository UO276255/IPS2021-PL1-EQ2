package com.uniovi.muebleria.maven.modelo.transporte;

import java.util.Date;

public class TransportistaDTO {
	private String nombre;
	private int numTelefono;
	private Date horario;
	
	public TransportistaDTO() {}
	public TransportistaDTO(String nombre, int numTelefono, Date horario) {
		this.nombre = nombre;
		this.numTelefono = numTelefono;
		this.horario = horario;
	}
	
	public String getNombre() { return this.nombre; }
	public int getNumTelefono() { return this.numTelefono; }
	public Date getHorario() { return this.horario; }
	
	public void setNombre(String nombre) { this.nombre=nombre; }
	public void setNumTelefono(int numTelefono) { this.numTelefono=numTelefono; }
	public void setHorario(Date horario) { this.horario=horario; }
}
