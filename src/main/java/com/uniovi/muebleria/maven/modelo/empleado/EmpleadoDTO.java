package com.uniovi.muebleria.maven.modelo.empleado;

import java.sql.Time;
import java.util.Date;

public class EmpleadoDTO {
	private int id;
	private String nombre;
	private String apellido;
	private String DNI;
	private String usuario;
	private String contraseña;
	private int numTelefono;
	private Time horarioInicio;
	private Time horarioFin;
	private String oficio;
	
	public EmpleadoDTO() {}
	public EmpleadoDTO(int id, String nombre, String apellido, int numTelefono, Time horarioInicio, Time horarioFin, String oficio) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.numTelefono = numTelefono;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
		this.oficio = oficio;
	}
	
	public EmpleadoDTO(int id, String nombre, int numTelefono, Time horarioInicio, Time horarioFin,String apellido,String DNI,String usuario, String contraseña, String oficio) {
		this.id = id;
		this.nombre = nombre;
		this.numTelefono = numTelefono;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
		this.apellido = apellido;
		this.DNI = DNI;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.oficio = oficio;
	}
	
	public String getApellido() {return apellido;}
	public String getDNI() {return DNI;}
	public String getUsuario() {return usuario;}
	public String getContraseña() {return contraseña;}
	public Time getHorarioInicio() {return horarioInicio;}
	public int getId() { return this.id; }
	public String getNombre() { return this.nombre; }
	public int getNumTelefono() { return this.numTelefono; }
	public Date getHorarioIn() { return this.horarioInicio; }
	public Date getHorarioFin() { return this.horarioFin; }
	public String getOficio() { return this.oficio; }
	
	public void setHorarioInicio(Time horarioInicio) {this.horarioInicio = horarioInicio;}
	public void setContraseña(String contraseña) {this.contraseña = contraseña;}
	public void setUsuario(String usuario) {this.usuario = usuario;}
	public void setDNI(String dNI) {DNI = dNI;}
	public void setApellido(String apellido) {this.apellido = apellido;}
	public void setId(int id) { this.id=id; }
	public void setNombre(String nombre) { this.nombre=nombre; }
	public void setNumTelefono(int numTelefono) { this.numTelefono=numTelefono; }
	public void setHorarioIn(Time horarioInicio) { this.horarioInicio=horarioInicio; }
	public void setHorarioFin(Time horarioFin) { this.horarioFin=horarioFin; }
	public void setOficio(String oficio) { this.oficio = oficio; }
	
	public String toString() {
		String cadena = "Nombre: " + getNombre() + " " + getApellido() + ", Número de Tlfn: " + getNumTelefono() + ", horario de entrada: "
				+ getHorarioIn().toString() + ", horario de salida: " + getHorarioFin().toString();
		return cadena;
	}
}
