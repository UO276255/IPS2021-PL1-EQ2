package com.uniovi.muebleria.maven.controlador.PersonalAlmacen;

import java.sql.Time;
import java.util.Date;

import com.uniovi.muebleria.maven.modelo.PersonalAlmacen.PersonalAlmacenModel;
import com.uniovi.muebleria.maven.vista.VistaCrearEmpleado;

public class PersonalAlmacenController {
	
	
	private VistaCrearEmpleado vista;
	private PersonalAlmacenModel model;
	
	public PersonalAlmacenController(PersonalAlmacenModel m, VistaCrearEmpleado v) {
		this.vista = v;
		this.model = m;
	}
	
	@SuppressWarnings("deprecation")
	public void crearTrabajador(String nombre, String apellido,String DNI, int telefono,String usuario,String contraseña,Date hora_entrada, Date hora_salida, String oficio) {
		Time horaEntrada = new Time(hora_entrada.getHours(),hora_entrada.getMinutes(),0);
		Time horaSalida = new Time(hora_salida.getHours(),hora_salida.getMinutes(),0);
		model.crearPersonalAlmacen(nombre,apellido,DNI,telefono,usuario,contraseña,horaEntrada,horaSalida,oficio);
	}
}
