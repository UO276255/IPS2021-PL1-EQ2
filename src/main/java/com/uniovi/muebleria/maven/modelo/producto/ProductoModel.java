package com.uniovi.muebleria.maven.modelo.producto;

import java.util.List;

import com.uniovi.muebleria.maven.util.Database;

public class ProductoModel {
	
private Database db = new Database();
	
	public static final String SQL_PRODUCTOS_NO_TRANSP = "SELECT * FROM Productos where Transporte = 0";
	public static final String SQL_PRODUCTOS_TRANSP = "SELECT * FROM Productos where Transporte = 1";
	public static final String SQL_PRODUCTOS_NO_MONTAR = "SELECT * FROM Productos where Montaje = 0 and Transporte = 1";
	public static final String SQL_PRODUCTOS_MONTAR = "SELECT * FROM Productos where Montaje = 1 and Transporte = 1";
	public static final String SQL_PRODUCTO_VENTA = "SELECT * FROM Productos prod "
			  + "JOIN Solicitudes sol ON prod.id_prod = sol.id_prod "
			  + "JOIN Presupuestos pres ON pres.id_pres = sol.id_pres "
			  + "JOIN Venta ven ON ven.id_pres = pres.id_pres "
			  + "WHERE id_venta = ?";
	public static final String SQL_ACTUALIZA_TRANSPORTE = "UPDATE Productos set Transporte = ? where id_prod= ?";
	public static final String SQL_ACTUALIZA_MONTAJE = "UPDATE Productos set Montaje = ? where id_prod= ?";
	private static final String SQL_MAXIMO_ID = "SELECT MAX(id_venta) max_id_venta FROM Venta";
	
	public List<ProductoDTO> getListaProductosVentaNoTransp(int id_venta) {
		return db.recogerProductosVentaNoTransp(SQL_PRODUCTO_VENTA, id_venta);
	}
	
	public List<ProductoDTO> getListaProductosVentaTransp(int id_venta){
		return db.recogerProductosVentaTransp(SQL_PRODUCTO_VENTA, id_venta);
	}
	
	public List<ProductoDTO> getListaProductosVentaNoMontar(int id_venta){
		return db.recogerProductosVentaNoMontar(SQL_PRODUCTO_VENTA, id_venta);
	}
	
	public List<ProductoDTO> getListaProductosVentaMontar(int id_venta){
		return db.recogerProductosVentaMontar(SQL_PRODUCTO_VENTA, id_venta);
	}
	
	public void ActualizarTransporte(int bit, int id) {
		db.AsignarTransporte(SQL_ACTUALIZA_TRANSPORTE,bit,id);
	}
	
	public void ActualizarMontaje(int bit, int id) {
		db.AsignarTransporte(SQL_ACTUALIZA_MONTAJE,bit,id);
	}

	public int getMaxId() {
		return db.recogerMaxValorIdProv(SQL_MAXIMO_ID);
	}

}
