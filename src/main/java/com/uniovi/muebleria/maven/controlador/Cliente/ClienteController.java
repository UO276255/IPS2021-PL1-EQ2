package com.uniovi.muebleria.maven.controlador.Cliente;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.uniovi.muebleria.maven.modelo.Cliente.ClienteDTO;
import com.uniovi.muebleria.maven.modelo.Cliente.ClienteModel;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestosModel;
import com.uniovi.muebleria.maven.vista.VistaAsignarPresupuesto;

public class ClienteController {
	
	
	private ClienteModel model;
	private VistaAsignarPresupuesto view;
	
	public ClienteController(ClienteModel m, VistaAsignarPresupuesto v) {
		this.model = m;
		this.view = v;
		this.initView();
	}
	
	public void initView() {
		setListaClientes();
	}
	
	public void setListaClientes() {
		List<ClienteDTO> clientes = model.obtenerClientes();
		ClienteDTO[] listaClientes = convertirArray(clientes);
		view.getComboBoxClientesExistentes().setModel(new DefaultComboBoxModel<ClienteDTO>(listaClientes));
	}

	private ClienteDTO[] convertirArray(List<ClienteDTO> clientes) {
		ClienteDTO[] lista = new ClienteDTO[clientes.size()];
		for(int i=0;i<clientes.size();i++) {
			lista[i] = clientes.get(i);
		}
		return lista;
	}

	public void crearNuevoCliente(String nombre, String apellido, String fecha) {
		model.crearCliente(nombre,apellido,fecha);
		
	}
	
	public int contarClientes() {
		return model.contarClientes();
	}
}
