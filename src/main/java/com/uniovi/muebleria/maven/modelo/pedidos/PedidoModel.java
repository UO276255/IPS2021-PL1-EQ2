package com.uniovi.muebleria.maven.modelo.pedidos;

import com.uniovi.muebleria.maven.util.Database;

public class PedidoModel {
	private Database db = new Database();
	
	private static final String SQL_PEDIDO_PROVEEDOR = "SELECT * FROM Pedido where id_prov = ?";
	private static final String SQL_MAXIMO_ID = "SELECT MAX(id_prov) max_id_prov FROM Pedido";
	
	public static final String SQL_ACTUALIZA_PEDIDO = "UPDATE Pedido set estado = 1 where id_prov = ?";
	
	public PedidoDTO getProductosProveedor(int id) {
		//return db.recogerPedidoProveedor(SQL_PEDIDO_PROVEEDOR , id);
		return null;
	}

	public int getMaxId() {
		return db.recogerMaxValorIdProv(SQL_MAXIMO_ID);
	}

	public void setPedidoRecibido(int id) {
		db.actualizaPedido(SQL_ACTUALIZA_PEDIDO, id);
	}
}
