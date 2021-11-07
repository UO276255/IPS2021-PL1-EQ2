package com.uniovi.muebleria.maven.modelo.producto;

public class CrearProductoDTO {
	
	ProductoDTO prod;
	int nUnidades;
	
	public CrearProductoDTO(ProductoDTO prod, int nUnidades) {
		this.prod = prod;
		this.nUnidades = nUnidades;
	}
	
	public String toString() {
		return prod.getNombre() + ", " + prod.getPrecio() + " €/ud";
	}

	public ProductoDTO getProd() {
		return prod;
	}

	public int getnUnidades() {
		return nUnidades;
	}
	

}