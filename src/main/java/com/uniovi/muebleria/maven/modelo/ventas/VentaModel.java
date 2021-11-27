package com.uniovi.muebleria.maven.modelo.ventas;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.uniovi.muebleria.maven.modelo.Cliente.ClienteDTO;
import com.uniovi.muebleria.maven.modelo.empleado.EmpleadoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
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
	
	
	public static final String SQL_TRANSPORTISTA = "SELECT * FROM Empleado WHERE id_empleado = ?";
	
	
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
	
	public static final String SQL_VENTA = "SELECT * FROM Venta WHERE Id_Venta = ?";
	public static final String SQL_VENTAS_TRP = "SELECT * FROM Venta WHERE transporte = 1";
	public static final String SQL_ALMACEN_VENTAS = "UPDATE Registrado SET cantidad = ? WHERE Id_Almacen=? AND Id_Prod=?";
	public static final String SQL_CANT_PROD_ALM = "SELECT Cantidad FROM Registrado WHERE Id_Almacen=? AND Id_Prod=?";
	
	public static final String SQL_CLIENTE = "SELECT cli.* FROM "
			+ "Presupuestos pre JOIN Cliente cli ON pre.ID_CLIENTE = cli.ID WHERE pre.ID_PRES = ? ";
	
	private static final String SQL_INICIO_VACACIONAL = "SELECT inicio_vacaciones FROM Vacaciones where Id_empleado = ?";
	
	private static final String SQL_FINAL_VACACIONAL = "SELECT fin_vacaciones FROM Vacaciones where Id_empleado = ?";
	private static final String QSL_VENTA_POR_MES = "select * from venta where Month(fecha_venta) = ?";
	private static final String QSL_VENTA_POR_MES_Y_VENDEDOR = "select * from venta where Month(fecha_venta) = ? and id_empleado = ?";
	
	public VentaModel() {
		
	}
	
	public List<VentaDTO> getListaVentas(){
		return db.recogerVentas(SQL_VENTAS);
	}
	
	public List<VentaDTO> getListaVentasPorMes(int mes){
		return db.recogerVentasPorMes(QSL_VENTA_POR_MES,mes);
	}
	
	public List<VentaDTO> getListaVentasConTransporte(){
		return db.recogerVentasTransporte(SQL_VENTAS_TRP);
	}

	public ProductoDTO[] getListaProductos(int id_pres, boolean conTransporte) {
		ProductoDTO[] listaProductos = toArray(db.recogerProductosPresupuesto(SQL_PRODUCTO, id_pres, conTransporte));
		return listaProductos;
	}
	
	public ProductoDTO[] getListaProductosTotal(int id_pres) {
		ProductoDTO[] listaProductos = toArray(db.recogerProductosPresupuestoTotal(SQL_PRODUCTO_VENTA, id_pres));
		return listaProductos;
	}
	
	public int CrearVenta(Date fecha,int precio,int idPresupuesto) {
		int id = contarVentas() + 1;
		db.CrearVenta(SQL_CREAR_VENTA,id,fecha,precio,idPresupuesto);
		return id;
	}
	
	public int contarVentas() {
		return db.contarDatos(SQL_CONTAR_VENTAS);
	}
	
	public void asignaFechaVentas(VentaDTO venta, java.util.Date dateTime) {
		java.sql.Date fecha = new java.sql.Date(dateTime.getTime());
		db.asignaFechaAVenta(SQL_FECHAS,venta,fecha);
		
	}

	public EmpleadoDTO getTransportista(int idTransp) {
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

	public VentaDTO getVenta(int idVenta) {
		VentaDTO venta = db.getVenta(SQL_VENTA, idVenta);
		return venta;
	}

	public int actualizaAlmacen(ProductoDTO producto, int idAlmacen) {
		int cantidad = db.getCantidadProdAlmacen(SQL_CANT_PROD_ALM, idAlmacen, producto.getId());
		cantidad--;
		if (cantidad < 0)
			return 1;
		db.actualizaAlmacen(SQL_ALMACEN_VENTAS, idAlmacen, producto.getId(), cantidad);
		return 0;
	}

	public ClienteDTO getCliente(int id_pres) {
		return db.getCliente(SQL_CLIENTE, id_pres);
	}
	
	public ArrayList<java.util.Date> getDiaInicioVacaciones(int id) {
		return db.fechaVacacional(SQL_INICIO_VACACIONAL, id);
	}
	
	public ArrayList<java.util.Date> getDiaFinalVacaciones(int id) {
		return db.fechaVacacional(SQL_FINAL_VACACIONAL, id);
	}

	public List<VentaDTO> getListaVentasPorMesyVendedor(int mes, int idVendedor) {
		return db.getListaVentasPorMesyVendedor(QSL_VENTA_POR_MES_Y_VENDEDOR,mes,idVendedor);	
		}
}
	
