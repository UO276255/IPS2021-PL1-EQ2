package com.uniovi.muebleria.maven.controlador.Almacen;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.uniovi.muebleria.maven.modelo.Almacen.AlmacenDTO;
import com.uniovi.muebleria.maven.modelo.Almacen.AlmacenModel;
import com.uniovi.muebleria.maven.modelo.Cliente.ClienteDTO;
import com.uniovi.muebleria.maven.vista.VistaAlmacenes;

public class AlmacenController {

	private AlmacenModel model = new AlmacenModel();
	private VistaAlmacenes view;
	
	public AlmacenController(AlmacenModel m, VistaAlmacenes v) {
		this.model = m;
		this.view = v;
	}

	public void initView() {
		view.clearModelos();
		view.setVisible(true);
		mostrarAlmacenes();	
	}

	private void mostrarAlmacenes() {
		List<AlmacenDTO> almacenes = model.obtenerAlmacenes();
		AlmacenDTO[] listaAlmacenes = convertirArray(almacenes);
		view.getComboBoxAlmacenes().setModel(new DefaultComboBoxModel<AlmacenDTO>(listaAlmacenes));	
	}
	
	private AlmacenDTO[] convertirArray(List<AlmacenDTO> clientes) {
		AlmacenDTO[] lista = new AlmacenDTO[clientes.size()];
		for(int i=0;i<clientes.size();i++) {
			lista[i] = clientes.get(i);
		}
		return lista;
	}
}
