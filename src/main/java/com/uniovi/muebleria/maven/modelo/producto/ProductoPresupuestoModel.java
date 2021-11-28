package com.uniovi.muebleria.maven.modelo.producto;

import java.util.List;

import com.uniovi.muebleria.maven.util.Database;

public class ProductoPresupuestoModel {

	private Database db = new Database();
	
	private static final String SQL_PRODUCTOS = "SELECT * FROM Productos";
	private static final String SQL_TODOS_PRODUCTOS_FILTRADOS_ENCIMA = "SELECT * FROM Productos where precio_prod >= ?";
	private static final String SQL_TODOS_PRODUCTOS_FILTRADOS_DEBAJO = "SELECT * FROM Productos where precio_prod <= ?";
	private static final String SQL_PRODUCTOS_FILTRADOS_ENCIMA = "SELECT * FROM Productos where precio_prod >= ? and categoria = ?";
	private static final String SQL_PRODUCTOS_FILTRADOS_DEBAJO = "SELECT * FROM Productos where precio_prod <= ? and categoria = ?";
	private static final String SQL_PRECIO_PRODUCTO = "SELECT * FROM Productos where id_prod = ?";
	private static final String SQL_CREAR_PRESUPUESTO = "insert into Presupuestos (id_pres,Precio,Aceptado,Fecha_cad,id_cliente) values (?,?,?,?,?);";
	private static final String SQL_CREAR_SOLICITUDES = "insert into Solicitudes (id_solic,id_pres,id_prod,precio_prod,transporte,montaje) values (?,?,?,?,?,?);";
	private static final String SQL_EXISTE_PROD_IDSOL = "SELECT id_prod FROM Solicitudes where id_pres = ?";
	//private static final String SQL_ACTUALIZA_PRESUPUESTO = "UPDATE Solicitudes set cantidad_prod = ? where id_pres = ? and id_prod = ?";
	
	
	public List<ProductoDTO> getListaProductos() {
		return db.recogerProductos(SQL_PRODUCTOS);
	}
	
	public int getPrecioProd(int id) {
		return db.precioProducto(SQL_PRECIO_PRODUCTO, id);
	}
	
	public void crearPresupuesto(int precio) {
		db.creaPresupuesto(SQL_CREAR_PRESUPUESTO, precio);
	}
	
	public void crearSolicitudes(int idSol, int idPres, ProductoDTO producto) {
		db.creaSolicitudes(SQL_CREAR_SOLICITUDES, idSol, idPres, producto);
	}

	public int idPresupuesto() {
		return db.calcularNumPresupuesto();
	}

	public int idSolicitud() {
		return db.calcularNumSolicitud();
	}
	
	public boolean existeIdProdIdPres(int idPres, int idProd) {
		return db.existeIdProdIdPres(SQL_EXISTE_PROD_IDSOL, idPres, idProd);
	}

//	public void actualizaNumProdIdSol(int idPres, int idProd) {
//		db.actualizaPresupuesto(SQL_ACTUALIZA_PRESUPUESTO, idPres, idProd);
//	}

	public List<ProductoDTO> getListaProductosFiltrados(int value, String selectedItem, boolean productosPrecioSuperior) {
		if(selectedItem.equals("Todas")) {
			if(productosPrecioSuperior) {
				return db.recogerTodosProductosFiltrados(SQL_TODOS_PRODUCTOS_FILTRADOS_ENCIMA, value);
			}else {
				return db.recogerTodosProductosFiltrados(SQL_TODOS_PRODUCTOS_FILTRADOS_DEBAJO, value);
			}
		}else {
			if(productosPrecioSuperior) {
				return db.recogerProductosFiltrados(SQL_PRODUCTOS_FILTRADOS_ENCIMA, value, selectedItem);
			}else {
				return db.recogerProductosFiltrados(SQL_PRODUCTOS_FILTRADOS_DEBAJO, value, selectedItem);
			}
		}
	}
}
