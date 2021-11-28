package com.uniovi.muebleria.maven.modelo.Presupuesto;

import java.util.ArrayList;
import java.util.List;

import com.uniovi.muebleria.maven.modelo.producto.ProductoPresupuestoDTO;
import com.uniovi.muebleria.maven.util.Database;
public class PresupuestosModel {
	
	private Database db = new Database();
	
	public static final String SQL_LISTA_PRESUPUESTOS_NO_ASIGNADOS = "SELECT * FROM Presupuestos where aceptado = 0 and id_cliente is null";
	public static final String SQL_LISTA_PRESUPUESTOS_NO_ACEPTADOS = "SELECT * FROM Presupuestos p , cliente c where p.aceptado = 0 and p.id_cliente != 0 and "
			+ "CAST(CURRENT_TIMESTAMP AS DATE) <= p.Fecha_cad and c.id = p.id_cliente";
	
	public static final String SQL_ASIGNAR_PRESUPUESTO = "UPDATE Presupuestos set id_cliente = ? where id_pres = ?";
	public static final String SQL_BORRAR_PRESUPUESTO = "UPDATE Presupuestos set Aceptado = 1 where id_pres = ?";
	public static final String SQL_BORRAR_SOLICITUDES_DE_PRESUPUESTO = "DELETE from SOLICITUDES where id_pres = ?";

	private static final String SQL_PRES = "SELECT p.id_pres, p.precio, p.aceptado,p.fecha_cad, c.nombre, c.apellido FROM Presupuestos p JOIN Cliente c ON p.id_cliente=c.id";

	private static final String SQL_PROD_PRES = "SELECT s.id_pres, s.precio_prod, p.id_prod, p.nombre_prod, p.precio_prod, p.categoria"
			+ " FROM Solicitudes s JOIN Productos p ON s.id_prod=p.id_prod"
			+ " WHERE s.id_pres=?";

	private static final String SQL_ACTUALIZA_PRESUPUESTO = "UPDATE Solicitudes SET precio_prod = ? WHERE id_pres=? and id_prod=?";

	private static final String SQL_GET_TOTPRES = "SELECT SUM(precio_prod) FROM Solicitudes WHERE id_pres=? GROUP BY id_pres";

	private static final String SQL_ACTUALIZA_TOTAL_PRES = "UPDATE Presupuestos SET Precio = ? WHERE Id_Pres = ?";
	
	public List<PresupuestoDTO> obtenerPresupuestosSinAsignar(){
		return db.recogerPresupuestos(SQL_LISTA_PRESUPUESTOS_NO_ASIGNADOS);	
	}
	
	public List<PresupuestoDTO> obtenerPresupuestosSinAceptar() {
		return db.recogerPresupuestosYClientes(SQL_LISTA_PRESUPUESTOS_NO_ACEPTADOS);	
	}
	
	public void AsignarClienteAPresupuesto(int idclient, int idpresupuesto) {
		db.AsignarPresupuestoACliente(SQL_ASIGNAR_PRESUPUESTO,idclient,idpresupuesto);
	}

	public void removePresupuesto(int idPresupuesto) {
		db.cancelPresupuesto(SQL_BORRAR_PRESUPUESTO,idPresupuesto);
		
	}

	public List<PresupuestoVentaDTO> obtenerPresupuestos() {
		return db.getPresupuestos(SQL_PRES);
	}

	public List<ProductoPresupuestoDTO> obtenerProductos(int idPresupuesto) {
		List<ProductoPresupuestoDTO> lista = db.obtenerProductos(SQL_PROD_PRES, idPresupuesto); 

		List<ProductoPresupuestoDTO> newlista = new ArrayList<ProductoPresupuestoDTO>();
		for (int i=0; i<lista.size();i++) {
			ProductoPresupuestoDTO prodPres = buscaProducto(lista.get(i).getNombre(), newlista);
			if (prodPres!=null)
				prodPres.incCantidad();
			else {
				prodPres = lista.get(i);
				prodPres.incCantidad();
				newlista.add(prodPres);
			}
		}
		
		return newlista;
	}
	ProductoPresupuestoDTO buscaProducto(String nombre, List<ProductoPresupuestoDTO> lista) {
		for (int i=0;i< lista.size();i++)
			if (lista.get(i).getNombre().equals(nombre)) return lista.get(i);
		
		return null;
	}

	public void actualizaPresupuesto(PresupuestoVentaDTO pres, ProductoPresupuestoDTO producto) {
		if (producto.getPrecioActual() < producto.getPrecioPresupuesto())
			db.actualizaPresupuesto(SQL_ACTUALIZA_PRESUPUESTO, pres.getIdPresupuesto(),producto.getIdProducto(), producto.getPrecioActual());
	}
	
	public void actualizaPrecioPresupuesto(PresupuestoVentaDTO pres) {
		int total = db.getSumaTotalPresupuesto(SQL_GET_TOTPRES, pres.getIdPresupuesto());
		db.actualizaTotalPresupuesto(SQL_ACTUALIZA_TOTAL_PRES, pres.getIdPresupuesto(),total);
	}

}

