package com.uniovi.muebleria.maven.controlador;

import java.util.ArrayList;

import com.uniovi.muebleria.maven.modelo.MuebleriaModel;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;
import com.uniovi.muebleria.maven.vista.VistaMuebleria;

public class MuebleriaControlador {
	private MuebleriaModel model;
	private VistaMuebleria vista;
	
	public MuebleriaControlador(MuebleriaModel m, VistaMuebleria v) {
		this.model = m;
		//this.vista = v;
		vista = new VistaMuebleria();
		iniciar();
	}
	
	public void iniciar() {
		vista.getFrame().setVisible(true);
	}
	
	public ArrayList<TransportistaDTO> getListaTransportistas() {
		ArrayList<TransportistaDTO> listTransportistas = model.getListaTransportistas();
		return listTransportistas;
	}
}
