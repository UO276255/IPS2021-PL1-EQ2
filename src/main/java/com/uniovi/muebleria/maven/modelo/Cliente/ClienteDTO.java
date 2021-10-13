package com.uniovi.muebleria.maven.modelo.Cliente;

import java.util.Date;

public class ClienteDTO {
	
	private int IdCliente ;
	private String nombre;
	private String apellido;
	private Date fechaNac;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(int idCliente, String nombre, 
			String apellido,Date fechaNac) {
		this.IdCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
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

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String toString() {
		String cadena = "Id:" + getIdCliente()  + " - Nombre: " + getNombre() + " - Apellido: "
				+ getApellido() + " - Fecha Nacimiento: " + getFechaNac().toString();
		return cadena;
	
	}
}
