package com.uniovi.muebleria.maven.controlador.Venta;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.uniovi.muebleria.maven.modelo.Cliente.ClienteDTO;
import com.uniovi.muebleria.maven.modelo.empleado.EmpleadoDTO;
import com.uniovi.muebleria.maven.modelo.producto.AddProductoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.ventas.ProductoVentaDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaModel;
import com.uniovi.muebleria.maven.util.SendMail;
import com.uniovi.muebleria.maven.vista.VistaActualizarPrecios;
import com.uniovi.muebleria.maven.vista.VistaCreacionVentas;
import com.uniovi.muebleria.maven.vista.VistaDeterminaFecha;
import com.uniovi.muebleria.maven.vista.VistaEntregarPedido;
import com.uniovi.muebleria.maven.vista.VistaHistorial;

public class VentaController {

	private VistaDeterminaFecha vistaFecha;
	@SuppressWarnings("unused")
	private VistaCreacionVentas vistaVenta;
	private VentaModel model;
	private VistaHistorial vistaHistorial;
	private VistaEntregarPedido vistaEntregarPedido;
	
	
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
	
	public VentaController(VentaModel m, VistaEntregarPedido v) {
		this.vistaEntregarPedido = v;
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
	
	@SuppressWarnings("unchecked")
	public void getListaVentasConTransporte() {
		List<VentaDTO> listVentas = model.getListaVentasConTransporte();
		VentaDTO[] arrayVentas = toArray(listVentas);
		vistaEntregarPedido.getListVentas().setModel(new DefaultComboBoxModel<VentaDTO>(arrayVentas));
	}
	
	public ArrayList<AddProductoDTO> crearVenta(Date fecha,int precio,int idPresupuesto) {
		int idVenta = model.CrearVenta(fecha,precio,idPresupuesto);
		int idAlmacen = 1;
		
		VentaDTO venta = model.getVenta(idVenta);
		ProductoDTO[] productos = getListaProductos(venta);
		ArrayList<AddProductoDTO> listaProductosNueva = new ArrayList<AddProductoDTO>();
		for (int i=0; i < productos.length;i++) {
			if (model.actualizaAlmacen(productos[i], idAlmacen) > 0) {
				AddProductoDTO nuevoProd = new AddProductoDTO(productos[i],1);
				if (listaProductosNueva.contains(nuevoProd))
					listaProductosNueva.get(listaProductosNueva.indexOf(nuevoProd)).addUnidades(1);
				else
					listaProductosNueva.add(nuevoProd);
			}
		}
		return listaProductosNueva;
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
		EmpleadoDTO transp = model.getTransportista(venta.getIdTransp());
		horaEntrada = new java.util.Date(0,0,0,transp.getHorarioIn().getHours(),transp.getHorarioIn().getMinutes());
		horaSalida = new java.util.Date(0,0,0,transp.getHorarioFin().getHours(),transp.getHorarioFin().getMinutes());
		java.util.Date hora = new java.util.Date(0,0,0,time.getHours(), time.getMinutes());
		
		if (hora.getTime()<horaEntrada.getTime() || hora.getTime() > horaSalida.getTime())
			return false;
		java.util.Date fecha = new java.util.Date(date.getYear(),date.getMonth(),date.getDate(), time.getHours(), time.getMinutes());
		model.asignaFechaVentas(venta, fecha);
		return true;
	}

	public EmpleadoDTO getTransportista(VentaDTO venta) {
		return model.getTransportista(venta.getIdTransp());
	}

	public void initViewHistorial() {
		vistaHistorial.setVisible(true);
		vistaHistorial.setLocationRelativeTo(null);
		
		
	}
	
	public void initViewEntregaPedido() {
		vistaEntregarPedido.setVisible(true);
		vistaEntregarPedido.setLocationRelativeTo(null);
		getListaVentasConTransporte();
		
		
	}
	
	public void initViewActualizaPrecios() {
		vistaEntregarPedido.setVisible(true);
		vistaEntregarPedido.setLocationRelativeTo(null);
		getListaVentasConTransporte();
		
		
	}

	@SuppressWarnings("unchecked")
	public void historialVentas(java.util.Date date, java.util.Date date2) {
		List<VentaDTO> listVentas = model.getListaVentasFechas(date,date2);
		VentaDTO[] arrayVentas = toArray(listVentas);
		for (int i=0; i<arrayVentas.length;i++) {
			ProductoDTO[] productos = getListaProductos(arrayVentas[i]);
			ProductoVentaDTO[] arrayProdVentas = new ProductoVentaDTO[productos.length];
			for (int j=0; j<productos.length;j++) {
				ProductoVentaDTO prodVenta = new ProductoVentaDTO(productos[j]);
				prodVenta.setNumUnidades(model.contarUnidades(arrayVentas[i].getId_pres(), productos[j].getId()));
				arrayProdVentas[j] = prodVenta;
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
		}
		vistaHistorial.getListProductos().clearSelection();
		vistaHistorial.getListProductos().setListData(arrayProdVentas);
		
		vistaHistorial.getTxSumaMontaje().setText(""+sumaMontaje+" €");
		vistaHistorial.getTxSumaTotal().setText(""+precio+" €");
		
	}

	public void entregarPedido() {
		VentaDTO venta = (VentaDTO)vistaEntregarPedido.getListVentas().getSelectedValue();
		String sep="\n";
		
		ClienteDTO cliente = model.getCliente(venta.getId_pres());
		ProductoDTO[] productos = getListaProductos(venta);
		String subject = "Mueblería - Entrega de Pedido con ID: "+venta.getId_venta();
		StringBuilder msg = new StringBuilder();
		msg.append("Estimado/a "+cliente.getNombre()+" "+cliente.getApellido()+": ")
			.append(sep)
			.append("Se le notifica que se ha realizado la entrega del pedido con id:" + venta.getId_venta() + " y con fecha prevista de entrega"
				+ " para el " + venta.getFecha() + ".")
			.append(sep)
			.append("El detalle de los productos del pedido es el siguiente: ")
			.append(sep);
		for (int i=0; i<productos.length;i++) {
			int cantidad = model.contarUnidades(venta.getId_pres(), productos[i].getId());
			msg.append("\t " + productos[i].toStringPedido(cantidad))
				.append(sep);
		}
		msg.append(sep)
			.append("El precio final de la venta es: "+venta.getPrecio()+"€")
			.append(sep)
			.append(sep)
			.append("Atentamente, el equipo de atención al cliente");		
		
		SendMail.sendMailWithTTLS("adrian.estrada2001@gmail.com", subject, msg.toString());
		SendMail.sendMailWithTTLS(cliente.getEmail(), subject, msg.toString());
	}
	
	public ArrayList<java.util.Date> getDiaInicioVacaciones(int id) {
		return model.getDiaInicioVacaciones(id);
	}
	
	public ArrayList<java.util.Date> getDiaFinalVacaciones(int id) {
		return model.getDiaFinalVacaciones(id);
	}
}
