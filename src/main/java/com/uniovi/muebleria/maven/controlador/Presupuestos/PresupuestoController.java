package com.uniovi.muebleria.maven.controlador.Presupuestos;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestoDTO;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestosModel;
import com.uniovi.muebleria.maven.vista.VistaAsignarPresupuesto;
import com.uniovi.muebleria.maven.vista.VistaCreacionVentas;

public class PresupuestoController {

	private PresupuestosModel model;
	private VistaAsignarPresupuesto viewPresupuesto;
	private VistaCreacionVentas viewVentas;
	
	public PresupuestoController(PresupuestosModel m, VistaAsignarPresupuesto v) {
		this.model = m;
		this.viewPresupuesto = v;
		this.initViewPresupuesto();
	}
	
	public PresupuestoController(PresupuestosModel m, VistaCreacionVentas v) {
		this.model = m;
		this.viewVentas = v;
		this.initViewVentas();
	}

	public void initViewPresupuesto() {
		viewPresupuesto.setVisible(true);
		setListaPresupuestosSinCliente();	
	}
	
	public void initViewVentas() {
		viewVentas.setVisible(true);
		setListaPresupuestosSinAceptar();	
	}
	
	public void setListaPresupuestosSinCliente() {
		List<PresupuestoDTO> presupuestos = model.obtenerPresupuestosSinAsignar();
		PresupuestoDTO[] listaPresupuestos = convertirArray(presupuestos);
		viewPresupuesto.getComboBoxPresupuestos().setModel(new DefaultComboBoxModel<PresupuestoDTO>(listaPresupuestos));
	}
	
	public void asignarClienteAPresupuesto(int Idclient, int Idpresupuesto) {
		model.AsignarClienteAPresupuesto(Idclient,Idpresupuesto);
	}
	
	public void setListaPresupuestosSinAceptar() {
		List<PresupuestoDTO> presupuestos = model.obtenerPresupuestosSinAceptar();
		PresupuestoDTO[] listaPresupuestos = convertirArray(presupuestos);
		combertirAformato(listaPresupuestos);
		viewVentas.getComboBoxPresupuestoSinAceptar().setModel(new DefaultComboBoxModel<String>(combertirAformato(listaPresupuestos)));
	}

	private String[] combertirAformato(PresupuestoDTO[] listaPresupuestos) {
		String[] lista = new String[listaPresupuestos.length];
		for(int i=0; i< listaPresupuestos.length; i++) {
			lista[i] = listaPresupuestos[i].toString2();
		}
		return lista;
	}

	private PresupuestoDTO[] convertirArray(List<PresupuestoDTO> presupuestos) {
		PresupuestoDTO[] lista = new PresupuestoDTO[presupuestos.size()];
		for(int i=0;i<presupuestos.size();i++) {
			lista[i] = presupuestos.get(i);
		}
		return lista;
	}
}
