package com.uniovi.muebleria.maven.controlador.Vendedor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.uniovi.muebleria.maven.modelo.Vendedor.VendedorDTO;
import com.uniovi.muebleria.maven.modelo.Vendedor.VendedorModel;
import com.uniovi.muebleria.maven.vista.VistaCrearEmpleado;

public class VendedorController {
	private VistaCrearEmpleado vista;
	private VendedorModel model;
	
	public VendedorController(VendedorModel m, VistaCrearEmpleado v) {
		this.vista = v;
		this.model = m;
	}
	
	public VendedorController(VendedorModel vendedorModel) {
		this.model = vendedorModel;
	}

	@SuppressWarnings("deprecation")
	public void crearTrabajador(String nombre, String apellido,String DNI, int telefono,String usuario,String contraseña,Date hora_entrada, Date hora_salida, String oficio) {
		Time horaEntrada = new Time(hora_entrada.getHours(),hora_entrada.getMinutes(),0);
		Time horaSalida = new Time(hora_salida.getHours(),hora_salida.getMinutes(),0);
		model.crearVendedor(nombre,apellido,DNI,telefono,usuario,contraseña,horaEntrada,horaSalida,oficio);
	}
	
	public boolean LoginDeVendedor(String usuario, String contraseña) {
        List<VendedorDTO> lista = model.getVendedoresLogin();
        for(int i=0; i<lista.size();i++) {
            if(lista.get(i).getUsuario().equals(usuario) && lista.get(i).getContraseña().equals(contraseña)) {
                return true;
            }
        }
        return false;
    }
	
	public List<VendedorDTO> getVendedores(){
		return model.getVendedores();
	}
}
