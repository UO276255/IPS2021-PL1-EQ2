package com.uniovi.muebleria.maven.modelo.PersonalAlmacen;

import java.sql.Time;
import java.util.Date;

public class PersonalAlmacenDTO {
	private int idPerAlmacen;
	private String nombre;
	private String apellido;
	private String DNI;
	private String usuario;
	private String contraseña;
	private int numTelefono;
	private Time horarioInicio;
	private Time horarioFin;
	
	public PersonalAlmacenDTO() {}
	
	public PersonalAlmacenDTO(int idPerAlmacen, String nombre, int numTelefono, Time horarioInicio, Time horarioFin,String apellido,String DNI,String usuario, String contraseña) {
		this.idPerAlmacen = idPerAlmacen;
		this.nombre = nombre;
		this.numTelefono = numTelefono;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
		this.apellido = apellido;
		this.DNI = DNI;
		this.usuario = usuario;
		this.contraseña = contraseña;
	}
	
	public String getApellido() {return apellido;}
	public String getDNI() {return DNI;}
	public String getUsuario() {return usuario;}
	public String getContraseña() {return contraseña;}
	public Time getHorarioInicio() {return horarioInicio;}
	public int getIdPersonalAlmacen() { return this.idPerAlmacen; }
	public String getNombre() { return this.nombre; }
	public int getNumTelefono() { return this.numTelefono; }
	public Date getHorarioIn() { return this.horarioInicio; }
	public Date getHorarioFin() { return this.horarioFin; }
	
	public void setHorarioInicio(Time horarioInicio) {this.horarioInicio = horarioInicio;}
	public void setContraseña(String contraseña) {this.contraseña = contraseña;}
	public void setUsuario(String usuario) {this.usuario = usuario;}
	public void setDNI(String dNI) {DNI = dNI;}
	public void setApellido(String apellido) {this.apellido = apellido;}
	public void setIdPersonalAlmacen(int idPerAlmacen) { this.idPerAlmacen=idPerAlmacen; }
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
