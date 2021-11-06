package com.uniovi.muebleria.maven.controlador.transportista;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaModel;
import com.uniovi.muebleria.maven.modelo.ventas.VentaDTO;
import com.uniovi.muebleria.maven.vista.VistaAsignaTransporte;

public class TransportistaController {
	
	private VistaAsignaTransporte vista;
	private TransportistaModel model;
	
	public TransportistaController(TransportistaModel m, VistaAsignaTransporte v) {
		this.vista = v;
		this.model = m;
	}
	
	public TransportistaController(TransportistaModel m) {
		model = m;
	}

	public void initView() {
		vista.setVisible(true);
		getListaTransportistas();
		getListaVentas();
	}
	
	@SuppressWarnings("deprecation")
	public void crearTrabajador(String nombre,int numero_tel,Date horaEntrada, Date horaSalida, String apellido,String DNI, String usuario,String contraseña) {
		
		Time hora_entrada = new Time(horaEntrada.getHours(),horaEntrada.getMinutes(),0);
		Time hora_salida = new Time(horaSalida.getHours(),horaSalida.getMinutes(),0);
		
		model.crearTrabajador(nombre,numero_tel,hora_entrada,hora_salida,apellido,DNI,usuario,contraseña);
	}
	
	public void getListaTransportistas() {
		List<TransportistaDTO> listTransportistas = model.getListaTransportistas();
		TransportistaDTO[] arrayTransportistas = toArray(listTransportistas);
		vista.getComboBoxListaTransportistas().setModel(new DefaultComboBoxModel<TransportistaDTO>(arrayTransportistas));
	}
	
	public void getListaVentas() {
		List<VentaDTO> listVenta = model.getListaVentas();
		VentaDTO[] arrayVentas = toArrayVentas(listVenta);
		vista.getComboBoxListaVentas().setModel(new DefaultComboBoxModel<VentaDTO>(arrayVentas));
	}
	
	public TransportistaDTO[] toArray(List<TransportistaDTO> listTransportistas) {
		TransportistaDTO[] arrayTransportistas = new TransportistaDTO[listTransportistas.size()];
		for(int i=0;i<listTransportistas.size();i++) {
			arrayTransportistas[i] = listTransportistas.get(i);
		}
		return arrayTransportistas;
	}
	
	public VentaDTO[] toArrayVentas(List<VentaDTO> listVenta) {
		VentaDTO[] arrayVentas = new VentaDTO[listVenta.size()];
		for(int i=0;i<listVenta.size();i++) {
			arrayVentas[i] = listVenta.get(i);
		}
		return arrayVentas;
	}

	public void asignaTransportista(int idTransp) {
		model.asignaTransportista(idTransp);
	}

	public int getTransportistaPorVenta(int idVenta) {
		return model.getIdTransp(idVenta);
	}
}
