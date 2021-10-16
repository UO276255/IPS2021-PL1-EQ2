package com.uniovi.muebleria.maven.modelo.pedidos;

import com.uniovi.muebleria.maven.util.Database;

public class PedidoModel {
	private Database db = new Database();
	
	private static final String SQL_PEDIDO = "SELECT * FROM Repuesto where id_pedido = ?";
	private static final String SQL_MAXIMO_ID = "SELECT MAX(id_prov) max_id_prov FROM Pedido";
	
	public static final String SQL_ACTUALIZA_PEDIDO = "UPDATE Pedido set estado = 1 where id_pedido = ?";
	
	public PedidoDTO getRepuestoPedido(int id) {
		return db.recogerRepuestoPedido(SQL_PEDIDO , id);
	}

	public int getMaxId() {
		return db.recogerMaxValorIdProv(SQL_MAXIMO_ID);
	}

	public void setPedidoRecibido(int id) {
		db.actualizaPedido(SQL_ACTUALIZA_PEDIDO, id);
	}
}
