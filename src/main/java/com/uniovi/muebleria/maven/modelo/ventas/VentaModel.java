package com.uniovi.muebleria.maven.modelo.ventas;

import java.util.Date;
import java.util.List;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.util.Database;

public class VentaModel {

	private Database db = new Database();
	
	public static final String SQL_PRODUCTO = "SELECT * FROM Presupuestos pres "
			+ "JOIN Solicitudes sol ON pres.id_pres = sol.id_pres "
			+ "JOIN productos prod ON sol.id_prod = prod.id_prod "
			+ "WHERE id_pres = ? AND Transporte = ?";
	public static final String SQL_VENTAS = "SELECT * FROM Venta";
	
	public static final String SQL_FECHAS = "UPDATE Venta SET Fecha_venta = ? WHERE Id_venta = ?";
	
public static final String SQL_CONTAR_VENTAS = "SELECT count(*) from venta";

public static final String SQL_CREAR_VENTA = "insert into venta(id_venta,fecha_venta,precio,transporte,id_pres,id_transp) "
											+ "values (?,?,?,0,?,null)";

	
	public VentaModel() {
		
	}
	
	public List<VentaDTO> getListaVentas(){
		return db.recogerVentas(SQL_VENTAS);
	}

	public ProductoDTO[] getListaProductos(int id_pres, boolean conTransporte) {
		ProductoDTO[] listaProductos = toArray(db.recogerProductosPresupuesto(SQL_PRODUCTO, id_pres, conTransporte));
		return listaProductos;
	}
	public void CrearVenta(Date fecha,int precio,int idPresupuesto) {
		java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
		int id = contarVentas() + 1;
		db.CrearVenta(SQL_CREAR_VENTA,id,fechaSql,precio,idPresupuesto);	
	}

	public int contarVentas() {
		return db.contarDatos(SQL_CONTAR_VENTAS);
	}
	public ProductoDTO[] toArray(List<ProductoDTO> listProductos) {
		ProductoDTO[] arrayProductos = new ProductoDTO[listProductos.size()];
		for(int i=0;i<listProductos.size();i++) {
			arrayProductos[i] = listProductos.get(i);
		}
		return arrayProductos;
	}

	public void asignaFechaVentas(VentaDTO venta, Date dateTime) {
		java.sql.Date fecha = new java.sql.Date(dateTime.getTime());
		db.asignaFechaAVenta(SQL_FECHAS,venta,fecha);
		
	}
	
	
}
	


//package com.uniovi.muebleria.maven.modelo.ventas;
//
//import java.sql.Date;
//import java.util.List;
//import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
//import com.uniovi.muebleria.maven.util.Database;
//
//public class VentaModel {
//
//	private Database db = new Database();
//	
//	public static final String SQL_PRODUCTO = "SELECT * FROM Presupuestos pres "
//			+ "JOIN Solicitudes sol ON pres.id_pres = sol.id_pres "
//			+ "JOIN productos prod ON sol.id_prod = prod.id_prod "
//			+ "WHERE id_pres = ? AND Transporte = ?";
//	
//	public static final String SQL_VENTAS = "SELECT * FROM Venta";
//	
//	public static final String SQL_CONTAR_VENTAS = "SELECT count(*) from venta";
//	
//	public static final String SQL_CREAR_VENTA = "insert into venta(id_venta,fecha_venta,precio,transporte,id_pres,id_transp) "
//													+ "values (?,?,?,0,?,null)";
//	
//	
//	public VentaModel() {
//		
//	}
//	
//	public List<VentaDTO> getListaVentas(){
//		return db.recogerVentas(SQL_VENTAS);
//	}
//
//	public ProductoDTO[] getListaProductos(int id_pres, boolean conTransporte) {
//		ProductoDTO[] listaProductos = toArray(db.recogerProductosPresupuesto(SQL_PRODUCTO, id_pres, conTransporte));
//		return listaProductos;
//	}
//	
//	
//	public void CrearVenta(Date fecha,int precio,int idPresupuesto) {
//		int id = contarVentas() + 1;
//		db.CrearVenta(SQL_CREAR_VENTA,id,fecha,precio,idPresupuesto);	
//	}
//	
//	public int contarVentas() {
//		return db.contarDatos(SQL_CONTAR_VENTAS);
//	}
//
//
//	public ProductoDTO[] toArray(List<ProductoDTO> listProductos) {
//		ProductoDTO[] arrayProductos = new ProductoDTO[listProductos.size()];
//		for(int i=0;i<listProductos.size();i++) {
//			arrayProductos[i] = listProductos.get(i);
//		}
//		return arrayProductos;
//	}
//	
//	
//}
//	
