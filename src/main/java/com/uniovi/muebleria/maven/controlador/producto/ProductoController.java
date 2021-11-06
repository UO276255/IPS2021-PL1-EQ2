package com.uniovi.muebleria.maven.controlador.producto;

import java.util.List;

import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoModel;
import com.uniovi.muebleria.maven.vista.VistaAlmacenes;
import com.uniovi.muebleria.maven.vista.VistaAsignaTransporte;
import com.uniovi.muebleria.maven.vista.VistaCrearPedido;

public class ProductoController {
	private VistaAsignaTransporte vista;
	private VistaAlmacenes vistaAlm;
	private ProductoModel model;
	private VistaCrearPedido vistaPedido;
	
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
	
	public void initView() {
		vistaPedido.setVisible(true);
		vistaPedido.setLocationRelativeTo(null);
		añadirProductos();
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
	
//	private void setListProductosTransp() {
//		ProductoDTO[] arrayProductos = getListaProductosTransp();
//		for(int i=0;i<arrayProductos.length;i++) {
//			vista.addModeloListProdTransp(arrayProductos[i]);
//		}
//	}
	
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
	
	public void actualizarTransporte(int bit, int id_prod, int id_venta) {
		model.ActualizarTransporte(bit, id_prod, id_venta);
	}
	
	public void actualizarMontaje(int bit, int id_prod, int id_venta) {
		model.ActualizarMontaje(bit, id_prod, id_venta);
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

	public void añadirAlmacenes(int idAlmacen) {
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
	
	public void añadirProductos() {
		List<ProductoDTO> listaProd = model.getProductos();
		ProductoDTO[] prod = toArray(listaProd);
		for (int i=0; i<prod.length;i++) {
			vistaPedido.getListProducts().add(prod[i].getNombre() + ", " + prod[i].getPrecio() + "€ ," + prod[i].getCategoria());
		}
	}

}
