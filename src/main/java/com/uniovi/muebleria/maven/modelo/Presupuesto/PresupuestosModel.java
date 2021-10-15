package com.uniovi.muebleria.maven.modelo.Presupuesto;

import java.util.List;

import com.uniovi.muebleria.maven.util.Database;
public class PresupuestosModel {
	
	private Database db = new Database();
	
	public static final String SQL_LISTA_PRESUPUESTOS_NO_ASIGNADOS = "SELECT * FROM Presupuestos where aceptado = 0 and id_cliente is null";
	public static final String SQL_LISTA_PRESUPUESTOS_NO_ACEPTADOS = "SELECT * FROM Presupuestos p , cliente c where p.aceptado = 0 and p.id_cliente != 0 and "
			+ "CURRENT_DATE <= p.Fecha_cad and c.id = p.id_cliente";
	
	public static final String SQL_ASIGNAR_PRESUPUESTO = "UPDATE Presupuestos set id_cliente = ? where id_pres = ?";
	
	public List<PresupuestoDTO> obtenerPresupuestosSinAsignar(){
		return db.recogerPresupuestos(SQL_LISTA_PRESUPUESTOS_NO_ASIGNADOS);	
	}
	
	public List<PresupuestoDTO> obtenerPresupuestosSinAceptar() {
		return db.recogerPresupuestosYClientes(SQL_LISTA_PRESUPUESTOS_NO_ACEPTADOS);	
	}
	
	public void AsignarClienteAPresupuesto(int idclient, int idpresupuesto) {
		db.AsignarPresupuestoACliente(SQL_ASIGNAR_PRESUPUESTO,idclient,idpresupuesto);
	}
	

}

