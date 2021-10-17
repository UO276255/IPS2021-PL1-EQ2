package com.uniovi.muebleria.maven.modelo.ventas;



import java.util.List;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;

public class ProductoVentaDTO {
	private ProductoDTO producto;
	private int numUnidades;
	
	
	public int getNumUnidades() {
		return numUnidades;
	}

	public void setNumUnidades(int numUnidades) {
		this.numUnidades = numUnidades;
	}

	public ProductoVentaDTO(ProductoDTO prod) {
		this.producto=prod;
	}
	
	public ProductoDTO getProducto() {
		return producto;
	}
	
	public String toString() {
		String cadena = "Nombre: " + producto.getNombre();
		cadena += ", Trp: ";
		cadena += producto.getTransporte()?"SI":"NO";
		cadena += ", Montaje: ";
		cadena += producto.getMontaje()?"SI":"NO";
		cadena += "   " +"x" + getNumUnidades() + " uds";
		
		return cadena;
	}
	
}
