package com.uniovi.muebleria.maven.modelo.producto;

public class AddProductoDTO {
	
	ProductoDTO prod;
	int nUnidades;
	
	public AddProductoDTO(ProductoDTO prod, int nUnidades) {
		this.prod = prod;
		this.nUnidades = nUnidades;
	}
	
	public String toString() {
		return prod.getNombre() + ", " + prod.getPrecio() + " €/ud, Nº Uds: "+getnUnidades();
	}

	public ProductoDTO getProd() {
		return prod;
	}

	public int getnUnidades() {
		return nUnidades;
	}

	public void addUnidades(int nUds) {
		this.nUnidades+=nUds;
		
	}

	public void removeUnidades(int nUds) {
		this.nUnidades -= nUds;
		
	}
	

}