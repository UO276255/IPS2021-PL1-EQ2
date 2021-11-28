package com.uniovi.muebleria.maven.modelo.pedidos;

import java.util.Date;
import java.util.List;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;

public class PedidoDTO {
	private int idPedido;
	private List<ProductoDTO> productos;
	private List<Integer> numProductos;
	private boolean estado;
	private int idProv;
	private Date fecha;
	
	public PedidoDTO(int idPedido, List<ProductoDTO> productos, List<Integer> numProductos, boolean estado, int idProv) {
		this.idPedido = idPedido;
		this.productos = productos;
		this.numProductos = numProductos;
		this.estado = estado;
		this.idProv = idProv;
	}
	
	public PedidoDTO(int idPedido,boolean estado, Date fecha,int idProv) {
		this.idPedido = idPedido;
		this.productos = productos;
		this.numProductos = numProductos;
		this.estado = estado;
		this.idProv = idProv;
	}

	public int getId() { return idPedido;	}
	public List<ProductoDTO> getProductos() { return productos;	}
	public List<Integer> getNumProductos() { return numProductos;	}
	public boolean isEstado() { return estado; }
	public int getIdProv() { return idProv;	}
	public void setId(int idPedido) { this.idPedido = idPedido; }
	public Date getFecha() { return fecha;	}
	public void setFecha(Date fecha) { this.fecha =  fecha; }
	public void setProductos(List<ProductoDTO> productos) { this.productos = productos; }
	public void setNumProductos(List<Integer> numProductos) { this.numProductos = numProductos; }
	public void setEstado(boolean estado) { this.estado = estado; }
	public void setIdProv(int idProv) { this.idProv = idProv; }

	public String toString() {
		String cadena = "Id: " + getId();
		for(int i=0; i<productos.size(); i++) {
			cadena = cadena + "\n" + numProductos.get(i) + " unidades de " + productos.get(i).getNombre().toString(); 
		}
		if(isEstado()) {
			cadena = cadena + "\nEstado recibido";
		}else {
			cadena = cadena + "\nEstado solicitado";
		}
		return cadena;
	}
}
