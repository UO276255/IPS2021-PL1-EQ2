package com.uniovi.muebleria.maven.controlador.pedidos;

import java.util.Iterator;
import java.util.List;

import com.uniovi.muebleria.maven.modelo.pedidos.PedidoDTO;
import com.uniovi.muebleria.maven.modelo.pedidos.PedidoModel;
import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.vista.VistaSeguimientoPedido;

public class PedidoController {
	private VistaSeguimientoPedido vista;
	private PedidoModel model;
	
	public PedidoController(PedidoModel m, VistaSeguimientoPedido v) {
		this.vista = v;
		this.model = m;
	}
	
	public void initView() {
		vista.setVisible(true);
	}
	
	public PedidoDTO getPedido(int id) {
		PedidoDTO ped = model.getRepuestoPedido(id);
		return ped;
	}

	public int getMaxIdPedido() {
		return model.getMaxIdPedido();
	}

	public void setPedidoRegistrado(int id) {
		model.setPedidoRecibido(id);
	}

	public void añadirAlmacen(List<ProductoDTO> productos, List<Integer> numProductos) {
		for (int i = 0; i < productos.size(); i++) {
			model.añadirAlmacen(productos.get(i), numProductos.get(i));
		}
	}
	
}

