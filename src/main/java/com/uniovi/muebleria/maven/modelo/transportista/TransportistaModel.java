package com.uniovi.muebleria.maven.modelo.transportista;

import java.util.List;

import com.uniovi.muebleria.maven.util.Database;
public class TransportistaModel {
	
	private Database db = new Database();
	
	private static final String SQL_TRABAJADORES = "SELECT * FROM Transportista";
	private static final String SQL_ASIGNAR = "UPDATE Venta set id_transp = ?";
	private static final String SQL_ID_TRANSP = "SELECT id_transp FROM Venta WHERE id_venta = ?";
	
	public List<TransportistaDTO> getListaTransportistas(){
		return db.recogerTransportistas(SQL_TRABAJADORES);
	}

	public void asignaTransportista(int idTransp) {
		db.asignaTransportistaVenta(SQL_ASIGNAR, idTransp);
	}

	public int getIdTransp(int idVenta) {
		return db.buscaIdTranspPorIdVenta(SQL_ID_TRANSP, idVenta);
	}
}
