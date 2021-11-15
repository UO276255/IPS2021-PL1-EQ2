package com.uniovi.muebleria.maven.modelo.pedidos;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.util.Database;

public class PedidoModel {
	private Database db = new Database();
	
	private static final String SQL_PEDIDO = "SELECT * FROM Repuesto where id_pedido = ?";
	private static final String SQL_MAXIMO_ID = "SELECT MAX(Id_pedido) max_id_pedido FROM Pedido";
	
	public static final String SQL_ACTUALIZA_PEDIDO = "UPDATE Pedido set estado = 1 where id_pedido = ?";
	public static final String SQL_AÑADE_PRODUCTOS = "UPDATE Registrado set cantidad = ? where id_prod = ?";
	
	public PedidoDTO getRepuestoPedido(int id) {
		return db.recogerRepuestoPedido(SQL_PEDIDO , id);
	}

	public int getMaxIdPedido() {
		return db.recogerMaxValorIdPedido(SQL_MAXIMO_ID);
	}

	public void setPedidoRecibido(int id) {
		db.actualizaPedido(SQL_ACTUALIZA_PEDIDO, id);
	}

	public void añadirAlmacen(ProductoDTO producto, int num) {
		db.añadirAlmacen(SQL_AÑADE_PRODUCTOS, producto, num);
	}
}
