package com.uniovi.muebleria.maven.controlador.Venta;

import java.sql.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;
import com.uniovi.muebleria.maven.modelo.ventas.ProductoVentaDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaModel;
import com.uniovi.muebleria.maven.vista.VistaCreacionVentas;
import com.uniovi.muebleria.maven.vista.VistaDeterminaFecha;
import com.uniovi.muebleria.maven.vista.VistaHistorial;

public class VentaController {

	private VistaDeterminaFecha vistaFecha;
	@SuppressWarnings("unused")
	private VistaCreacionVentas vistaVenta;
	private VentaModel model;
	private VistaHistorial vistaHistorial;
	
	public VentaController(VentaModel m, VistaDeterminaFecha v) {
		this.vistaFecha = v;
		this.model = m;
		this.initView();
	}
	
	public VentaController(VentaModel m, VistaCreacionVentas v) {
		this.vistaVenta = v;
		this.model = m;
	}
	
	public VentaController(VentaModel m, VistaHistorial v) {
		this.vistaHistorial = v;
		this.model = m;
	}
	
	public void initView() {
		vistaFecha.setVisible(true);
		vistaFecha.setLocationRelativeTo(null);
		getListaVentas();
	}
	
	@SuppressWarnings("unchecked")
	public void getListaVentas() {
		List<VentaDTO> listVentas = model.getListaVentas();
		VentaDTO[] arrayVentas = toArray(listVentas);
		vistaFecha.getCbSeleccionarVenta().setModel(new DefaultComboBoxModel<VentaDTO>(arrayVentas));
	}
	
	public void cearVenta(Date fecha,int precio,int idPresupuesto) {
		model.CrearVenta(fecha,precio,idPresupuesto);
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
	
	public ProductoDTO[] getListaProductos(VentaDTO venta) {
		ProductoDTO[] listaProductos = model.getListaProductosTotal(venta.getId_pres());
		return listaProductos;
	}

	@SuppressWarnings("deprecation")
	public boolean asignaFechaVenta(VentaDTO venta, java.util.Date date, java.util.Date time) {
		java.util.Date horaEntrada = new java.util.Date(0,0,0,6,0);
		java.util.Date horaSalida = new java.util.Date(0,0,0,14,0);
		TransportistaDTO transp = model.getTransportista(venta.getIdTransp());
		horaEntrada = new java.util.Date(0,0,0,transp.getHorarioIn().getHours(),transp.getHorarioIn().getMinutes());
		horaSalida = new java.util.Date(0,0,0,transp.getHorarioFin().getHours(),transp.getHorarioFin().getMinutes());
		java.util.Date hora = new java.util.Date(0,0,0,time.getHours(), time.getMinutes());
		
		if (hora.getTime()<horaEntrada.getTime() || hora.getTime() > horaSalida.getTime())
			return false;
		java.util.Date fecha = new java.util.Date(date.getYear(),date.getMonth(),date.getDay(), time.getHours(), time.getMinutes());
		model.asignaFechaVentas(venta, fecha);
		return true;
	}

	public TransportistaDTO getTransportista(VentaDTO venta) {
		return model.getTransportista(venta.getIdTransp());
	}

	public void initViewHistorial() {
		vistaHistorial.setVisible(true);
		vistaHistorial.setLocationRelativeTo(null);
		
		
	}

	@SuppressWarnings("unchecked")
	public void historialVentas(java.util.Date date, java.util.Date date2) {
		List<VentaDTO> listVentas = model.getListaVentasFechas(date,date2);
		VentaDTO[] arrayVentas = toArray(listVentas);
		for (int i=0; i<arrayVentas.length;i++) {
			ProductoDTO[] productos = getListaProductos(arrayVentas[i]);
			ProductoVentaDTO[] arrayProdVentas = new ProductoVentaDTO[productos.length];
			for (int j=0; j<productos.length;j++) {
				ProductoVentaDTO prodVenta = new ProductoVentaDTO(productos[i]);
				prodVenta.setNumUnidades(model.contarUnidades(arrayVentas[i].getId_pres(), productos[i].getId()));
				arrayProdVentas[i] = prodVenta;
				if (productos[i].getMontaje())
					arrayVentas[i].setPrecio(arrayVentas[i].getPrecio()+5);
			}
		}
		
		vistaHistorial.getListVentas().setListData(arrayVentas);
	}

	@SuppressWarnings("unchecked")
	public void ventaSeleccionada() {
		VentaDTO venta = (VentaDTO)vistaHistorial.getListVentas().getSelectedValue();
		double precio = venta.getPrecio();
		ProductoDTO[] productos = getListaProductos(venta);
		ProductoVentaDTO[] arrayProdVentas = new ProductoVentaDTO[productos.length];
		int sumaMontaje=0;
		for (int i=0; i<productos.length;i++) {
			ProductoVentaDTO prodVenta = new ProductoVentaDTO(productos[i]);
			prodVenta.setNumUnidades(model.contarUnidades(venta.getId_pres(), productos[i].getId()));
			arrayProdVentas[i] = prodVenta;
			if (productos[i].getMontaje())
				sumaMontaje += 5;
		}
		vistaHistorial.getListProductos().clearSelection();
		vistaHistorial.getListProductos().setListData(arrayProdVentas);
		
		vistaHistorial.getTxSumaMontaje().setText(""+sumaMontaje+" €");
		vistaHistorial.getTxSumaTotal().setText(""+precio+" €");
		
	}
	
	
	
}
