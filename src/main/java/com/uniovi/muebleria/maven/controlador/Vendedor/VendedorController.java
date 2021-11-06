package com.uniovi.muebleria.maven.controlador.Vendedor;

import java.util.Date;
import java.sql.Time;

import com.uniovi.muebleria.maven.modelo.Vendedor.VendedorModel;
import com.uniovi.muebleria.maven.vista.VistaCrearEmpleado;

public class VendedorController {
	private VistaCrearEmpleado vista;
	private VendedorModel model;
	
	public VendedorController(VendedorModel m, VistaCrearEmpleado v) {
		this.vista = v;
		this.model = m;
	}
	
	public void crearTrabajador(String nombre, String apellido,String DNI, int telefono,String usuario,String contraseña,Date hora_entrada, Date hora_salida) {
		Time horaEntrada = new Time(hora_entrada.getHours(),hora_entrada.getMinutes(),0);
		Time horaSalida = new Time(hora_salida.getHours(),hora_salida.getMinutes(),0);
		model.crearVendedor(nombre,apellido,DNI,telefono,usuario,contraseña,horaEntrada,horaSalida);
	}
}
