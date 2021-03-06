package com.uniovi.muebleria.maven.modelo.Cliente;

import java.util.Date;

public class ClienteDTO {
	
	private int IdCliente ;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private int dni;
	private	String email;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(int idCliente, String nombre, 
			String apellido, Date fechaNacimiento,int dni, String email) {
		this.IdCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.dni = dni;
		this.email = email;
	}
	
	
	
	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdCliente() {
		return IdCliente;
	}

	public void setIdCliente(int idCliente) {
		IdCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String toString() {
		String cadena = "Id:" + getIdCliente()  + " - Nombre: " + getNombre() + " - Apellido: "
				+ getApellido() + " - " + getFechaNacimiento();
		return cadena;
	
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fecha_nacimiento) {
		this.fechaNacimiento = fecha_nacimiento;
	}
}
