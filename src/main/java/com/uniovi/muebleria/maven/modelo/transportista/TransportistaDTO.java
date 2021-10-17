package com.uniovi.muebleria.maven.modelo.transportista;

import java.sql.Time;
import java.util.Date;

public class TransportistaDTO {
	private int idTransp;
	private String nombre;
	private int numTelefono;
	private Time horarioInicio;
	private Time horarioFin;
	
	public TransportistaDTO() {}
	public TransportistaDTO(int idTransp, String nombre, int numTelefono, Time horarioInicio, Time horarioFin) {
		this.idTransp = idTransp;
		this.nombre = nombre;
		this.numTelefono = numTelefono;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
	}
	
	public int getIdTransp() { return this.idTransp; }
	public String getNombre() { return this.nombre; }
	public int getNumTelefono() { return this.numTelefono; }
	public Date getHorarioIn() { return this.horarioInicio; }
	public Date getHorarioFin() { return this.horarioFin; }
	
	public void setIdTransp(int idTransp) { this.idTransp=idTransp; }
	public void setNombre(String nombre) { this.nombre=nombre; }
	public void setNumTelefono(int numTelefono) { this.numTelefono=numTelefono; }
	public void setHorarioIn(Time horarioInicio) { this.horarioInicio=horarioInicio; }
	public void setHorarioFin(Time horarioFin) { this.horarioFin=horarioFin; }
	
	public String toString() {
		String cadena = "Nombre:" + getNombre()  + ", Número de Tlfn: " + getNumTelefono() + ", horario de entrada: "
				+ getHorarioIn().toString() + ", horario de salida: " + getHorarioFin().toString();
		return cadena;
	}
}
