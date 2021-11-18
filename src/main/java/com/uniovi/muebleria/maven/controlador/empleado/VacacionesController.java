package com.uniovi.muebleria.maven.controlador.empleado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.uniovi.muebleria.maven.modelo.empleado.EmpleadoDTO;
import com.uniovi.muebleria.maven.modelo.empleado.VacacionesModel;
import com.uniovi.muebleria.maven.vista.VistaGestionaVacaciones;

public class VacacionesController {

	private VistaGestionaVacaciones vista;
	private VacacionesModel model;
	
	public VacacionesController(VacacionesModel m, VistaGestionaVacaciones v) {
		this.vista = v;
		this.model = m;
	}
	
	public void initView() {
		vista.setVisible(true);
		getListaEmpleados();
	}

	public EmpleadoDTO[] getListaEmpleados() {
		List<EmpleadoDTO> listEmpleados = new ArrayList<EmpleadoDTO>();
		List<EmpleadoDTO> listEmpleadosTransportistas = model.getListaEmpleadosTransportistas();
		añadeALista(listEmpleados, listEmpleadosTransportistas);
		List<EmpleadoDTO> listEmpleadosVendedores = model.getListaEmpleadosVendedores();
		añadeALista(listEmpleados, listEmpleadosVendedores);
		List<EmpleadoDTO> listEmpleadosAlmacen = model.getListaEmpleadosAlmacen();
		añadeALista(listEmpleados, listEmpleadosAlmacen);
		EmpleadoDTO[] arrayEmpleados = toArray(listEmpleados);
		return arrayEmpleados;
	}
	
	private EmpleadoDTO[] toArray(List<EmpleadoDTO> listTransportistas) {
		EmpleadoDTO[] arrayTransportistas = new EmpleadoDTO[listTransportistas.size()];
		for(int i=0;i<listTransportistas.size();i++) {
			arrayTransportistas[i] = listTransportistas.get(i);
		}
		return arrayTransportistas;
	}
	
	private void añadeALista(List<EmpleadoDTO> listaReceptor, List<EmpleadoDTO> listaEmisor) {
		for (EmpleadoDTO empl : listaEmisor) {
			listaReceptor.add(empl);
		}
	}

	@SuppressWarnings("deprecation")
	public void asignaFechaInicioTransportista(int id, Date date) {
		Date fecha = new Date(date.getYear(),date.getMonth(),date.getDate(), 0, 0);
		model.asignaFechaInicioTransportista(id, fecha);
	}
	
	@SuppressWarnings("deprecation")
	public void asignaFechaFinalTransportista(int id, Date date) {
		Date fecha = new Date(date.getYear(),date.getMonth(),date.getDate(), 0, 0);
		model.asignaFechaFinalTransportista(id, fecha);
	}

	@SuppressWarnings("deprecation")
	public void asignaFechaInicioVendedor(int id, Date date) {
		Date fecha = new Date(date.getYear(),date.getMonth(),date.getDate(), 0, 0);
		model.asignaFechaInicioVendedor(id, fecha);
	}
	
	@SuppressWarnings("deprecation")
	public void asignaFechaFinalVendedor(int id, Date date) {
		Date fecha = new Date(date.getYear(),date.getMonth(),date.getDate(), 0, 0);
		model.asignaFechaFinalVendedor(id, fecha);
	}

	@SuppressWarnings("deprecation")
	public void asignaFechaInicioPersonalAlmacen(int id, Date date) {
		Date fecha = new Date(date.getYear(),date.getMonth(),date.getDate(), 0, 0);
		model.asignaFechaInicioPersonalAlmacen(id, fecha);
	}
	
	@SuppressWarnings("deprecation")
	public void asignaFechaFinalPersonalAlmacen(int id, Date date) {
		Date fecha = new Date(date.getYear(),date.getMonth(),date.getDate(), 0, 0);
		model.asignaFechaFinalPersonalAlmacen(id, fecha);
	}

	public Date getDiaInicioVacaciones(EmpleadoDTO empl) {
		if(empl.getOficio().equals("t")) {
			return model.getDiaInicioVacacionesTransp(empl.getId());
		}else if(empl.getOficio().equals("v")) {
			return model.getDiaInicioVacacionesVend(empl.getId());
		}else if(empl.getOficio().equals("pa")) {
			return model.getDiaInicioVacacionesPerAl(empl.getId());
		}else {
			return null;
		}
	}
}
