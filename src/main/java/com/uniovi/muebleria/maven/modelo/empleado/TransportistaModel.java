package com.uniovi.muebleria.maven.modelo.empleado;

import java.sql.Time;
import java.util.List;

import com.uniovi.muebleria.maven.modelo.ventas.VentaDTO;
import com.uniovi.muebleria.maven.util.Database;
public class TransportistaModel {
	
	private Database db = new Database();
	
	private static final String SQL_TRANSPORTISTAS = "SELECT * FROM Empleado where oficio = 't'";
	private static final String SQL_VENTAS = "SELECT * FROM Venta";
	private static final String SQL_ASIGNAR = "UPDATE Venta set Id_empleado = ? where id_venta = ?";
	private static final String SQL_ACTUALIZA = "UPDATE Venta set transporte = 1 where id_venta = ?";
	private static final String SQL_ID_TRANSP = "SELECT Id_empleado FROM Venta WHERE id_venta = ?";
	public static final String SQL_CONTAR_TRANSPORTISTAS = "SELECT count(*) FROM Empleado where oficio = 't'";
	private static final String SQL_AÑADIR_TRANSP ="insert into Empleado (Id_empleado,Nombre,Apellido,DNI,Numero_tel,Usuario,Contraseña,hora_entrada,hora_salida,inicio_vacaciones,fin_vacaciones,oficio) values (?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SQL_USUARIO_CONTRASEÑA_TRANSP = "SELECT usuario,contraseña FROM Empleado where oficio = 't'";
	
	public List<EmpleadoDTO> getListaTransportistas(){
		return db.recogerEmpleados(SQL_TRANSPORTISTAS);
	}
	
	public List<VentaDTO> getListaVentas(){
		return db.recogerVentas(SQL_VENTAS);
	}

	public void asignaTransportista(int idTransp, int idVenta) {
		db.asignaTransportistaVenta(SQL_ASIGNAR, idTransp, idVenta);
		db.actualiceTransporteVenta(SQL_ACTUALIZA, idVenta);
	}

	public int getIdTransp(int idVenta) {
		return db.buscaIdTranspPorIdVenta(SQL_ID_TRANSP, idVenta);
	}

	public void crearTrabajador(String nombre,int numero_tel,Time hora_entrada, Time hora_salida, String apellido,String DNI, String usuario,String contraseña,String oficio) {
		int id = contarTransportista() + 1;
		db.añadirTransportista(SQL_AÑADIR_TRANSP,id,nombre,numero_tel,hora_entrada,hora_salida,apellido,DNI,usuario,contraseña,oficio);
		
	}
	
	public int contarTransportista() {
		return db.contarDatos(SQL_CONTAR_TRANSPORTISTAS);
	}

	public List<EmpleadoDTO> getTransportistasLogin() {
		return db.getTransportistasLogin(SQL_USUARIO_CONTRASEÑA_TRANSP);
	}
}
