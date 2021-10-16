package com.uniovi.muebleria.maven.controlador.Venta;

import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaModel;
import com.uniovi.muebleria.maven.vista.VistaDeterminaFecha;

public class VentaController {

	private VistaDeterminaFecha vista;
	private VentaModel model;
	
	public VentaController(VentaModel m, VistaDeterminaFecha v) {
		this.vista = v;
		this.model = m;
		this.initView();
	}
	
	public void initView() {
		vista.setVisible(true);
		getListaVentas();
	}
	
	@SuppressWarnings("unchecked")
	public void getListaVentas() {
		List<VentaDTO> listVentas = model.getListaVentas();
		VentaDTO[] arrayVentas = toArray(listVentas);
		vista.getCbSeleccionarVenta().setModel(new DefaultComboBoxModel<VentaDTO>(arrayVentas));
	}
	
	public VentaDTO[] toArray(List<VentaDTO> listVentas) {
		VentaDTO[] arrayVentas = new VentaDTO[listVentas.size()];
		for(int i=0;i<listVentas.size();i++) {
			arrayVentas[i] = listVentas.get(i);
		}
		return arrayVentas;
	}
	
	public ProductoDTO[] getListaProductos(VentaDTO venta, boolean conTransporte) {
		ProductoDTO[] listaProductos = model.getListaProductos(venta.getId_pres(), conTransporte);
		return listaProductos;
	}

	@SuppressWarnings("deprecation")
	public boolean asignaFechaVenta(VentaDTO venta, Date date, Date time) {
		Date horaEntrada = new Date(0,0,0,6,0);
		Date horaSalida = new Date(0,0,0,14,0);
		TransportistaDTO transp = model.getTransportista(venta.getId_transp());
		horaEntrada = transp.getHorarioIn();
		horaSalida = transp.getHorarioFin();
		
		Date hora = new Date(0,0,0,time.getHours(), time.getMinutes());
		if (hora.getTime()<horaEntrada.getTime() || hora.getTime() > horaSalida.getTime())
			return false;
		Date fecha = new Date(date.getYear(),date.getMonth(),date.getDay(), time.getHours(), time.getMinutes());
		model.asignaFechaVentas(venta, fecha);
		return true;
	}
	
}
