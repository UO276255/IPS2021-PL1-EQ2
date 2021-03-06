package com.uniovi.muebleria.maven.controlador.producto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.uniovi.muebleria.maven.modelo.Almacen.AlmacenDTO;
import com.uniovi.muebleria.maven.modelo.empleado.EmpleadoDTO;
import com.uniovi.muebleria.maven.modelo.pedidos.PedidoDTO;
import com.uniovi.muebleria.maven.modelo.producto.AddProductoDTO;
import com.uniovi.muebleria.maven.modelo.producto.CrearProductoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoModel;
import com.uniovi.muebleria.maven.vista.VistaActualizarPrecios;
import com.uniovi.muebleria.maven.vista.VistaAlmacenes;
import com.uniovi.muebleria.maven.vista.VistaAsignaTransporte;
import com.uniovi.muebleria.maven.vista.VistaCrearPedido;

public class ProductoController {
	private VistaAsignaTransporte vista;
	private VistaAlmacenes vistaAlm;
	private ProductoModel model;
	private VistaCrearPedido vistaPedido;
	private VistaActualizarPrecios vistaActualizarPrecios;
	private List<ProductoDTO> listaProd;
	private boolean inicia;
	


	public ProductoController(ProductoModel m, VistaAsignaTransporte v) {
		this.vista = v;
		this.model = m;
	}
	
	public ProductoController(ProductoModel m, VistaAlmacenes v) {
		this.vistaAlm = v;
		this.model = m;
	}
	
	public ProductoController(ProductoModel m, VistaCrearPedido v) {
		this.vistaPedido = v;
		this.model = m;
		initView();
	}
	
	public ProductoController(ProductoModel m, VistaActualizarPrecios v) {
		this.vistaActualizarPrecios = v;
		this.model = m;
		listaProd = model.getProductos();
		initViewActualizaPrecios();
		vistaActualizarPrecios.setListaProductos(toArray(listaProd));
		
		
	}
	public ProductoController(ProductoModel m, VistaActualizarPrecios v, boolean noInit) {
		this.vistaActualizarPrecios = v;
		this.model = m;
	}
	public ProductoController(ProductoModel m, VistaCrearPedido v, boolean noInit) {
		this.vistaPedido = v;
		this.model = m;
	}
	public void initView() {
		vistaPedido.setVisible(true);
		vistaPedido.setLocationRelativeTo(null);
		a??adirProductos();
		seleccionarAlmacen();
	}
	
	public void initViewActualizaPrecios() {
		vistaActualizarPrecios.setVisible(true);
		vistaActualizarPrecios.setLocationRelativeTo(null);
		a??adirProductosActualizarPrecios();
		
	}
	
	public boolean isInicia() {
		return inicia;
	}

	public void setInicia(boolean inicia) {
		this.inicia = inicia;
	}
	
	private void seleccionarAlmacen() {
		AlmacenDTO almacen = model.getAlmacenActivo();
		vistaPedido.getTxAlmacen().setText(almacen.getNombre());
	}

	public ProductoDTO[] getListaProductosVentaNoTransp(int id) {
		List<ProductoDTO> listProductosNoTransp = model.getListaProductosVentaNoTransp(id);
		ProductoDTO[] arrayProductosNoTransp = toArray(listProductosNoTransp);
		return arrayProductosNoTransp;
	}
	
	public ProductoDTO[] getListaProductosVentaTransp(int id) {
		List<ProductoDTO> listProductosTransp = model.getListaProductosVentaTransp(id);
		ProductoDTO[] arrayProductosTransp = toArray(listProductosTransp);
		return arrayProductosTransp;
	}
	
	public ProductoDTO[] getListaProductosVentaNoMontar(int id) {
		List<ProductoDTO> listProductosNoMontar = model.getListaProductosVentaNoMontar(id);
		ProductoDTO[] arrayProductosNoMontar = toArray(listProductosNoMontar);
		return arrayProductosNoMontar;
	}
	
	public ProductoDTO[] getListaProductosVentaMontar(int id) {
		List<ProductoDTO> listProductosMontar = model.getListaProductosVentaMontar(id);
		ProductoDTO[] arrayProductosMontar = toArray(listProductosMontar);
		return arrayProductosMontar;
	}
	
	public void setListProductosVenta(int id) {
		ProductoDTO[] arrayProductos = getListaProductosVentaNoTransp(id);
		for(int i=0;i<arrayProductos.length;i++) {
			vista.addModeloListProdNoTransp(arrayProductos[i]);
		}
	}
	
	private void setListProductosNoMontar(ProductoDTO[] arrayProductos) {
		for(int i=0;i<arrayProductos.length;i++) {
			vista.addModeloListProdNoMontar(arrayProductos[i]);
		}
	}
	
	private void setListProductosMontar(int id) {
		ProductoDTO[] arrayProductos = getListaProductosVentaMontar(id);
		for(int i=0;i<arrayProductos.length;i++) {
			vista.addModeloListProdMontar(arrayProductos[i]);
		}
	}
	
	public void actualizarATransporte(int bit, int id_prod, int id_venta) {
		model.ActualizarATransporte(bit, id_prod, id_venta);
	}
	
	public void actualizarARecogida(int bit, int id_prod, int id_venta) {
		model.ActualizarARecogida(bit, id_prod, id_venta);
	}
	
	public void actualizarAMontaje(int bit, int id_prod, int id_venta) {
		model.ActualizarAMontaje(bit, id_prod, id_venta);
	}
	
	public void actualizarANoMontaje(int bit, int id_prod, int id_venta) {
		model.ActualizarANoMontaje(bit, id_prod, id_venta);
	}
	
	public void actualizaListaMontaje(int id) {
		vista.clearListaMontaje();
		setListProductosMontar(id);
	}
	
	public void actualizaListaNoMontaje(ProductoDTO[] arrayProductos) {
		vista.clearListaNoMontaje();
		setListProductosNoMontar(arrayProductos);
	}
	
	public ProductoDTO[] toArray(List<ProductoDTO> listProductos) {
		ProductoDTO[] arrayProductos = new ProductoDTO[listProductos.size()];
		for(int i=0;i<listProductos.size();i++) {
			arrayProductos[i] = listProductos.get(i);
		}
		return arrayProductos;
	}
	
	public List<ProductoDTO> ProductosPorAlmacen(int idAlmacen) {
		return model.getProductosPorAlmacen(idAlmacen);
	} 

	public void a??adirAlmacenes(int idAlmacen) {
		List<ProductoDTO> lista = ProductosPorAlmacen(idAlmacen);
		String[] cabecera = {"NOMBRE DEL PRODUCTO","CANTIDAD"};
		String[][] datos = convertirAMatriz(lista);
		DefaultTableModel model = new DefaultTableModel(datos, cabecera);
		vistaAlm.getTable().setModel(model);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		vistaAlm.getTable().getColumnModel().getColumn(0).setCellRenderer(tcr);
		vistaAlm.getTable().getColumnModel().getColumn(1).setCellRenderer(tcr);
	}
	private String[][] convertirAMatriz(List<ProductoDTO> lista) {
		String[][] datos = new String[lista.size()][2];
		for(int i= 0; i<lista.size();i++) {
			datos[i][0] = lista.get(i).getNombre();
			datos[i][1] = ""+lista.get(i).getCantidadAlmacen();
		}
		return datos;
	}

	public int getMaxId() {
		return model.getMaxId();
	}
	
	@SuppressWarnings("unchecked")
	public void a??adirProductos() {
		List<ProductoDTO> listaProd = model.getProductos();
		ProductoDTO[] prod = toArray(listaProd);
		CrearProductoDTO[] crearProd = new CrearProductoDTO[prod.length];
		for (int i=0; i<prod.length;i++) {
			crearProd[i] = new CrearProductoDTO(prod[i], 0);
		}
	
		vistaPedido.getListProducts().setListData(crearProd);
	}
	
	@SuppressWarnings("unchecked")
	public void a??adirProductosActualizarPrecios() {
		List<ProductoDTO> listaProd = model.getProductos();
		ProductoDTO[] prod = toArray(listaProd);
	
		vistaActualizarPrecios.getListProductos().setListData(prod);
	}

	@SuppressWarnings("unchecked")
	public boolean seleccionarProducto(CrearProductoDTO prodSelec) {
		boolean added = false;
		int nUds = Integer.valueOf(vistaPedido.getTxUnidades().getText());
		
		if (nUds <= 0)
			return false;
	
		List<AddProductoDTO> lista = new ArrayList<AddProductoDTO>();
		for (int i=0; i<vistaPedido.getListProductosA??adidos().getModel().getSize();i++) {
			lista.add((AddProductoDTO) vistaPedido.getListProductosA??adidos().getModel().getElementAt(i));
			if (lista.get(i).getProd().getId()==prodSelec.getProd().getId()) {
				lista.get(i).addUnidades(nUds);
				added=true;
			}
		}
		if (!added) 
			lista.add(new AddProductoDTO(prodSelec.getProd(), nUds));
		
		vistaPedido.getListProductosA??adidos().setListData(lista.toArray());
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean eliminarProducto(AddProductoDTO prodToDelete) {
		int nUds = Integer.valueOf(vistaPedido.getTxUnidades().getText());
	
		if (nUds <= 0)
			return false;
		
		List<AddProductoDTO> lista = new ArrayList<AddProductoDTO>();
		for (int i=0; i < vistaPedido.getListProductosA??adidos().getModel().getSize();i++)
			lista.add((AddProductoDTO) vistaPedido.getListProductosA??adidos().getModel().getElementAt(i));
		
		for (int i=0; i<lista.size();i++) {
			if (lista.get(i).getProd().getId()==prodToDelete.getProd().getId()) {
				lista.get(i).removeUnidades(nUds);
				if (lista.get(i).getnUnidades()<=0)
					lista.remove(i);
			}
		}
		vistaPedido.getListProductosA??adidos().setListData(lista.toArray());
		
		return true;
	}

	public boolean crearPedido() {
		ArrayList<AddProductoDTO> lista = new ArrayList<AddProductoDTO>();
		for (int i=0; i < vistaPedido.getListProductosA??adidos().getModel().getSize();i++)
			lista.add((AddProductoDTO) vistaPedido.getListProductosA??adidos().getModel().getElementAt(i));
		
		return crearPedido(lista);
	}
	
	public boolean crearPedido(ArrayList<AddProductoDTO> lista)
	{
		int idPedidoCreado = model.crearPedido();
		for (int i=0; i < lista.size();i++) {
			model.crearRepuesto(idPedidoCreado, lista.get(i).getProd().getId(), lista.get(i).getnUnidades());
//			model.crearRegistrado(lista.get(i).getProd().getId(), 1, lista.get(i).getnUnidades());
		}
		
		return true;
	}

	public Date getDateInicioTransportista(int id) {
		return model.getDateInicioTransportista(id);
	}

	public Date getDateFinalTransportista(int id) {
		return model.getDateFinalTransportista(id);
	}

	public ProductoDTO[] actualizarPrecio(ProductoDTO product, ProductoDTO[] listaProductos) {
		
		for (int i=0; i<listaProductos.length;i++) {
			if (listaProductos[i].getId() == product.getId()) {
				listaProductos[i].setPrecio(Integer.valueOf(vistaActualizarPrecios.getTxPrecio().getText()));
				vistaActualizarPrecios.getTxProducto().setText(listaProductos[i].toStringActualizarPrecios());
			}
		}
		
		vistaActualizarPrecios.getListProductos().setListData(listaProductos);
		return listaProductos;
	}

	public void modificarPrecios(ProductoDTO[] listaProductos) {
		for (int i=0; i<listaProductos.length; i++) {
			model.actualizaPrecioProducto(listaProductos[i]);
		}
		
	}

	

}
