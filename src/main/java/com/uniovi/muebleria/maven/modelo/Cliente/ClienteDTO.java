package com.uniovi.muebleria.maven.modelo.Cliente;

import java.util.Date;

public class ClienteDTO {
	
	private int IdCliente ;
	private String nombre;
	private String apellido;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(int idCliente, String nombre, 
			String apellido) {
		this.IdCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
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
				+ getApellido();
		return cadena;
	
	}
}
