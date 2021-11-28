package com.uniovi.muebleria.maven.controlador.Presupuestos;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ListModel;

import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestoDTO;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestoVentaDTO;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestosModel;
import com.uniovi.muebleria.maven.modelo.producto.ProductoPresupuestoDTO;
import com.uniovi.muebleria.maven.vista.VistaAsignarPresupuesto;
import com.uniovi.muebleria.maven.vista.VistaCreacionVentas;
import com.uniovi.muebleria.maven.vista.VistaVisualizarPresupuestos;

public class PresupuestoController {

	private PresupuestosModel model;
	private VistaAsignarPresupuesto viewPresupuesto;
	private VistaCreacionVentas viewVentas;
	private VistaVisualizarPresupuestos viewVistaPresupuesto;
	
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

	public PresupuestoController(PresupuestosModel m, VistaVisualizarPresupuestos viewVisualizarPresupuestos, boolean init) {
		this.model = m;
		this.viewVistaPresupuesto = viewVisualizarPresupuestos;
		if (init) 
			this.initViewVisualizarPres();
		
	}

	private void initViewVisualizarPres() {
		viewVistaPresupuesto.setLocationRelativeTo(null);
		viewVistaPresupuesto.setVisible(true);
		setListaPresupuestos();
		
		
	}

	private void setListaPresupuestos() {
		List<PresupuestoVentaDTO> presupuestos = model.obtenerPresupuestos();
		PresupuestoVentaDTO[] listaPresupuestos = convertirArrayVentaPres(presupuestos);
		viewVistaPresupuesto.getCbPresupuestos().setModel(new DefaultComboBoxModel<PresupuestoVentaDTO>(listaPresupuestos));
		
	}

	public void cargarProductos() {
		PresupuestoVentaDTO pres = (PresupuestoVentaDTO) viewVistaPresupuesto.getCbPresupuestos().getSelectedItem();
		List<ProductoPresupuestoDTO> productos = model.obtenerProductos(pres.getIdPresupuesto());
		ProductoPresupuestoDTO[] lProd = toArrayProd(productos);
		viewVistaPresupuesto.getListProductos().setListData(lProd);
	}
	
	private ProductoPresupuestoDTO[] toArrayProd(List<ProductoPresupuestoDTO> productos) {
		ProductoPresupuestoDTO[] lista = new ProductoPresupuestoDTO[productos.size()];
		for(int i=0;i<productos.size();i++) {
			lista[i] = productos.get(i);
		}
		return lista;
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
		PresupuestoVentaDTO[] listaPresupuestos = convertirArrayAFormato(presupuestos);
		viewVentas.getComboBoxPresupuestoSinAceptar().setModel(new DefaultComboBoxModel<PresupuestoVentaDTO>(listaPresupuestos));
  
	}
	
	public void removePresupuesto(int idPresupuesto) {
		model.removePresupuesto(idPresupuesto);
		
	}
	
	private PresupuestoVentaDTO[] convertirArrayAFormato(List<PresupuestoDTO> listaPresupuestos) {
		PresupuestoVentaDTO dto = null;
		PresupuestoVentaDTO[] lista = new PresupuestoVentaDTO[listaPresupuestos.size()];
		for(int i=0;i<listaPresupuestos.size();i++) {
			dto =  new PresupuestoVentaDTO(listaPresupuestos.get(i).getIdPresupuesto(),listaPresupuestos.get(i).getPrecio(),
					listaPresupuestos.get(i).getFechaCaducidad(),listaPresupuestos.get(i).getNombreCliente());
			lista[i] = dto;
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
	private PresupuestoVentaDTO[] convertirArrayVentaPres(List<PresupuestoVentaDTO> presupuestos) {
		PresupuestoVentaDTO[] lista = new PresupuestoVentaDTO[presupuestos.size()];
		for(int i=0;i<presupuestos.size();i++) {
			lista[i] = presupuestos.get(i);
		}
		return lista;
	}

	public void actualizarPresupuesto() {
		PresupuestoVentaDTO pres = (PresupuestoVentaDTO) viewVistaPresupuesto.getCbPresupuestos().getSelectedItem();
		ListModel productos = viewVistaPresupuesto.getListProductos().getModel();
		for (int i = 0; i < productos.getSize();i++) {
			model.actualizaPresupuesto(pres, (ProductoPresupuestoDTO) productos.getElementAt(i));
		}
		model.actualizaPrecioPresupuesto(pres);
	}
}
