package com.uniovi.muebleria.maven.controlador.Presupuestos;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.uniovi.muebleria.maven.modelo.producto.PresupuestoDTO;
import com.uniovi.muebleria.maven.modelo.producto.PresupuestosModel;
import com.uniovi.muebleria.maven.vista.VistaAsignarPresupuesto;

public class PresupuestoController {

	private PresupuestosModel model;
	private VistaAsignarPresupuesto view;
	
	public PresupuestoController(PresupuestosModel m, VistaAsignarPresupuesto v) {
		this.model = m;
		this.view = v;
		this.initView();
	}

	public void initView() {
		view.setVisible(true);
		setListaPresupuestos();
		
	}
	
	public void setListaPresupuestos() {
		List<PresupuestoDTO> presupuestos = model.obtenerPresupuestos();
		PresupuestoDTO[] listaPresupuestos = convertirArray(presupuestos);
		view.getComboBoxPresupuestos().setModel(new DefaultComboBoxModel<PresupuestoDTO>(listaPresupuestos));
	}

	private PresupuestoDTO[] convertirArray(List<PresupuestoDTO> presupuestos) {
		PresupuestoDTO[] lista = new PresupuestoDTO[presupuestos.size()];
		for(int i=0;i<presupuestos.size();i++) {
			lista[i] = presupuestos.get(i);
		}
		return lista;
	}
}
