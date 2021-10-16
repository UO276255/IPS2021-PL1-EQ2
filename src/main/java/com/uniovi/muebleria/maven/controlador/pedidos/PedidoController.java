package com.uniovi.muebleria.maven.controlador.pedidos;

import com.uniovi.muebleria.maven.modelo.pedidos.PedidoDTO;
import com.uniovi.muebleria.maven.modelo.pedidos.PedidoModel;
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
		PedidoDTO ped = model.getProductosProveedor(id);
		return ped;
	}

	public int getMaxId() {
		return model.getMaxId();
	}

	public void setPedidoRegistrado(int id) {
		model.setPedidoRecibido(id);
	}
	
}
