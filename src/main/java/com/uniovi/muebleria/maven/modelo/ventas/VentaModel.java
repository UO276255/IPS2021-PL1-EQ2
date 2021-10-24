package com.uniovi.muebleria.maven.modelo.ventas;

import java.sql.Date;
import java.util.List;
import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;
import com.uniovi.muebleria.maven.util.Database;

public class VentaModel {

	private Database db = new Database();
	
	public static final String SQL_PRODUCTO = "SELECT prod.* FROM Presupuestos pres "
			+ "JOIN Solicitudes sol ON pres.id_pres = sol.id_pres "
			+ "JOIN productos prod ON sol.id_prod = prod.id_prod "
			+ "WHERE id_pres = ? AND Transporte = ?";	
	public static final String SQL_VENTAS = "SELECT * FROM Venta";
	public static final String SQL_FECHAS = "UPDATE Venta SET Fecha_venta = ? WHERE Id_venta = ?";
	public static final String SQL_CONTAR_VENTAS = "SELECT count(*) from Venta";
	public static final String SQL_CREAR_VENTA = "insert into venta(id_venta,fecha_venta,precio,transporte,id_pres,id_transp) "
													+ "values (?,?,?,0,?,null)";
	
	
	public static final String SQL_TRANSPORTISTA = "SELECT * FROM Transportista WHERE id_transp = ?";
	
	
	private static final String SQL_VENTAS_FECHA = "SELECT * FROM Venta WHERE fecha_venta >= ? AND fecha_venta <= ?";
	public static final String SQL_PRODUCTO_VENTA = "SELECT prod.* FROM Presupuestos pres "
			+ "JOIN Solicitudes sol ON pres.id_pres = sol.id_pres "
			+ "JOIN productos prod ON sol.id_prod = prod.id_prod "
			+ "WHERE id_pres = ?";
	public static final String SQL_NUM_PRODUCTOS_VENTA = "SELECT COUNT(prod.id_prod) NumProductos "
			+ "FROM Presupuestos pres "
			+ "JOIN Solicitudes sol ON pres.id_pres = sol.id_pres "
			+ "JOIN Productos prod ON sol.id_prod = prod.id_prod "
			+ "WHERE id_pres = ? and id_prod=?";
	
	public VentaModel() {
		
	}
	
	public List<VentaDTO> getListaVentas(){
		return db.recogerVentas(SQL_VENTAS);
	}

	public ProductoDTO[] getListaProductos(int id_pres, boolean conTransporte) {
		ProductoDTO[] listaProductos = toArray(db.recogerProductosPresupuesto(SQL_PRODUCTO, id_pres, conTransporte));
		return listaProductos;
	}
	
	public ProductoDTO[] getListaProductosTotal(int id_pres) {
		ProductoDTO[] listaProductos = toArray(db.recogerProductosPresupuestoTotal(SQL_PRODUCTO_VENTA, id_pres));
		return listaProductos;
	}
	
	public void CrearVenta(Date fecha,int precio,int idPresupuesto) {
		int id = contarVentas() + 1;
		db.CrearVenta(SQL_CREAR_VENTA,id,fecha,precio,idPresupuesto);	
	}
	
	public int contarVentas() {
		return db.contarDatos(SQL_CONTAR_VENTAS);
	}
	
	public void asignaFechaVentas(VentaDTO venta, java.util.Date dateTime) {
		java.sql.Date fecha = new java.sql.Date(dateTime.getTime());
		db.asignaFechaAVenta(SQL_FECHAS,venta,fecha);
		
	}

	public TransportistaDTO getTransportista(int idTransp) {
		return db.recogerTransportista(SQL_TRANSPORTISTA, idTransp);
	}

	public List<VentaDTO> getListaVentasFechas(java.util.Date date, java.util.Date date2) {
		java.sql.Date fecha = new java.sql.Date(date.getTime());
		java.sql.Date fecha2 = new java.sql.Date(date2.getTime());
		return db.recogerVentasFecha(SQL_VENTAS_FECHA,fecha,fecha2);
	}

	public int contarUnidades(int id_pres, int id) {
		return db.contarUnidades(SQL_NUM_PRODUCTOS_VENTA, id_pres, id);
	}


	public ProductoDTO[] toArray(List<ProductoDTO> listProductos) {
		ProductoDTO[] arrayProductos = new ProductoDTO[listProductos.size()];
		for(int i=0;i<listProductos.size();i++) {
			arrayProductos[i] = listProductos.get(i);
		}
		return arrayProductos;
	}
	
	
}
	
