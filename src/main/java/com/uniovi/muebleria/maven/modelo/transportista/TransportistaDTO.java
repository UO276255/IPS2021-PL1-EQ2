package com.uniovi.muebleria.maven.modelo.transportista;

import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class TransportistaDTO {
	private int id_transp;
	private String nombre;
	private int numTelefono;
	private Time horarioInicio;
	private Time horarioFin;
	
	public TransportistaDTO() {}
	public TransportistaDTO(int id_transp, String nombre, int numTelefono, Time horarioInicio, Time horarioFin) {
		this.id_transp=id_transp;
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
	public void setHorarioIn(Time horarioInicio) { this.horarioInicio=horarioInicio; }
	public void setHorarioFin(Time horarioFin) { this.horarioFin=horarioFin; }
	
	public String toString() {
		String cadena = "Nombre:" + getNombre()  + ", Tlf: " + getNumTelefono() + ", Desde: "
				+ getTime(getHorarioIn()) + ", Hasta: " + getTime(getHorarioFin());
		
		return cadena;
	}
	public static String getTime(Date date) {
		return date.toString();
    }
	
}
