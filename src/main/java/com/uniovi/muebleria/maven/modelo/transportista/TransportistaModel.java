package com.uniovi.muebleria.maven.modelo.transportista;

import java.sql.Time;
import java.util.List;

import com.uniovi.muebleria.maven.modelo.ventas.VentaDTO;
import com.uniovi.muebleria.maven.util.Database;
public class TransportistaModel {
	
	private Database db = new Database();
	
	private static final String SQL_TRABAJADORES = "SELECT * FROM Transportista";
	private static final String SQL_VENTAS = "SELECT * FROM Venta";
	private static final String SQL_ASIGNAR = "UPDATE Venta set id_transp = ?";
	private static final String SQL_ID_TRANSP = "SELECT id_transp FROM Venta WHERE id_venta = ?";
	public static final String SQL_CONTAR_TRANSPORTISTAS = "SELECT count(*) FROM Transportista";
	private static final String SQL_AÑADIR_TRANSP ="insert into Transportista (id_transp,Nombre,Numero_tel,hora_entrada,hora_salida,Apellido,DNI,Usuario,Contraseña) values (?,?,?,?,?,?,?,?,?)";
	
	public List<TransportistaDTO> getListaTransportistas(){
		return db.recogerTransportistas(SQL_TRABAJADORES);
	}
	
	public List<VentaDTO> getListaVentas(){
		return db.recogerVentas(SQL_VENTAS);
	}

	public void asignaTransportista(int idTransp) {
		db.asignaTransportistaVenta(SQL_ASIGNAR, idTransp);
	}

	public int getIdTransp(int idVenta) {
		return db.buscaIdTranspPorIdVenta(SQL_ID_TRANSP, idVenta);
	}

	public void crearTrabajador(String nombre,int numero_tel,Time hora_entrada, Time hora_salida, String apellido,String DNI, String usuario,String contraseña) {
		int id = contarTransportista() + 1;
		db.añadirTransportista(SQL_AÑADIR_TRANSP,id,nombre,numero_tel,hora_entrada,hora_salida,apellido,DNI,usuario,contraseña);
		
	}
	
	public int contarTransportista() {
		return db.contarDatos(SQL_CONTAR_TRANSPORTISTAS);
	}
}
