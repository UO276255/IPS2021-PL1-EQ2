package com.uniovi.muebleria.maven.controlador.producto;

import java.util.List;

import javax.swing.DefaultListModel;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoModel;
import com.uniovi.muebleria.maven.vista.VistaAsignaTransporte;

public class ProductoController {
	private VistaAsignaTransporte vista;
	private ProductoModel model;

	private DefaultListModel<ProductoDTO> modeloListProd = new DefaultListModel<ProductoDTO>();
	private DefaultListModel<ProductoDTO> modeloListMontados = new DefaultListModel<ProductoDTO>();
	
	public ProductoController(ProductoModel m, VistaAsignaTransporte v) {
		this.vista = v;
		this.model = m;
		this.initView();
	}
	
	public void initView() {
		vista.setVisible(true);
		setListProductos();
	}
	
	public ProductoDTO[] getListaProductos() {
		List<ProductoDTO> listProductos = model.getListaProductosTrans();
		ProductoDTO[] arrayProductos = toArray(listProductos);
		return arrayProductos;
	}
	
	public ProductoDTO[] toArray(List<ProductoDTO> listProductos) {
		ProductoDTO[] arrayProductos = new ProductoDTO[listProductos.size()];
		for(int i=0;i<listProductos.size();i++) {
			arrayProductos[i] = listProductos.get(i);
		}
		return arrayProductos;
	}
	
	private void setListProductos() {
		modeloListProd = new DefaultListModel<ProductoDTO>();
		vista.setListProductos(modeloListProd);
		modeloListMontados = new DefaultListModel<ProductoDTO>();
		vista.setListProductos(modeloListMontados);
		ProductoDTO[] arrayProductos = getListaProductos();
		for(int i=0;i<arrayProductos.length;i++) {
			modeloListProd.addElement(arrayProductos[i]);
		}
	}
}
