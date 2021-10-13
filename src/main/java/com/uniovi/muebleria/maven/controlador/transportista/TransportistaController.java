package com.uniovi.muebleria.maven.controlador.transportista;

import java.util.List;
import javax.swing.DefaultComboBoxModel;

import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaModel;
import com.uniovi.muebleria.maven.vista.VistaAsignaTransporte;

public class TransportistaController {
	
	private VistaAsignaTransporte vista;
	private TransportistaModel model;
	
	public TransportistaController(TransportistaModel m, VistaAsignaTransporte v) {
		this.vista = v;
		this.model = m;
		this.initView();
	}
	
	public void initView() {
		vista.setVisible(true);
		getListaTransportistas();
	}
	
	public void getListaTransportistas() {
		List<TransportistaDTO> listTransportistas = model.getListaTransportistas();
		TransportistaDTO[] arrayTransportistas = toArray(listTransportistas);
		vista.getComboBoxListaTransportistas().setModel(new DefaultComboBoxModel<TransportistaDTO>(arrayTransportistas));
	}
	
	public TransportistaDTO[] toArray(List<TransportistaDTO> listTransportistas) {
		TransportistaDTO[] arrayTransportistas = new TransportistaDTO[listTransportistas.size()];
		for(int i=0;i<listTransportistas.size();i++) {
			arrayTransportistas[i] = listTransportistas.get(i);
		}
		return arrayTransportistas;
	}
}
