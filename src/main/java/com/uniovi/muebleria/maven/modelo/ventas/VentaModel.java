package com.uniovi.muebleria.maven.modelo.ventas;

import java.util.Date;
import java.util.List;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;
import com.uniovi.muebleria.maven.util.Database;

public class VentaModel {

	private Database db = new Database();
	
	public static final String SQL_PRODUCTO = "SELECT * FROM Presupuestos pres "
			+ "JOIN Solicitudes sol ON pres.id_pres = sol.id_pres "
			+ "JOIN productos prod ON sol.id_prod = prod.id_prod "
			+ "WHERE id_pres = ? AND Transporte = ?";
	public static final String SQL_VENTAS = "SELECT * FROM Venta";
	
	public static final String SQL_FECHAS = "UPDATE Venta SET Fecha_venta = ? WHERE Id_venta = ?";
	
	public static final String SQL_TRANSPORTISTA = "SELECT * FROM Transportista WHERE Id_transp=?";
	
	public VentaModel() {
		
	}
	
	public List<VentaDTO> getListaVentas(){
		return db.recogerVentas(SQL_VENTAS);
	}

	public ProductoDTO[] getListaProductos(int id_pres, boolean conTransporte) {
		ProductoDTO[] listaProductos = toArray(db.recogerProductosPresupuesto(SQL_PRODUCTO, id_pres, conTransporte));
		return listaProductos;
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

	public TransportistaDTO getTransportista(int id_transp) {
		return db.getTransportista(SQL_TRANSPORTISTA, id_transp);
	}
	
	
}
	
