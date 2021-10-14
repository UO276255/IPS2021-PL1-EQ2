package com.uniovi.muebleria.maven.controlador.producto;

import java.util.List;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoModel;
import com.uniovi.muebleria.maven.vista.VistaAsignaTransporte;

public class ProductoController {
	private VistaAsignaTransporte vista;
	private ProductoModel model;
	
	public ProductoController(ProductoModel m, VistaAsignaTransporte v) {
		this.vista = v;
		this.model = m;
		this.initView();
	}
	
	public void initView() {
		vista.setVisible(true);
		setListProductosNoTransp();
		setListProductosTransp();
		setListProductosNoMontar();
		setListProductosMontar();
	}
	
	public ProductoDTO[] getListaProductosNoTransp() {
		List<ProductoDTO> listProductosNoTransp = model.getListaProductosNoTransp();
		ProductoDTO[] arrayProductosNoTransp = toArray(listProductosNoTransp);
		return arrayProductosNoTransp;
	}
	
	public ProductoDTO[] getListaProductosTransp() {
		List<ProductoDTO> listProductosTransp = model.getListaProductosTransp();
		ProductoDTO[] arrayProductosTransp = toArray(listProductosTransp);
		return arrayProductosTransp;
	}
	
	public ProductoDTO[] getListaProductosNoMontar() {
		List<ProductoDTO> listProductosNoMontar = model.getListaProductosNoMontar();
		ProductoDTO[] arrayProductosNoMontar = toArray(listProductosNoMontar);
		return arrayProductosNoMontar;
	}
	
	public ProductoDTO[] getListaProductosMontar() {
		List<ProductoDTO> listProductosMontar = model.getListaProductosMontar();
		ProductoDTO[] arrayProductosMontar = toArray(listProductosMontar);
		return arrayProductosMontar;
	}
	
	private void setListProductosNoTransp() {
		ProductoDTO[] arrayProductos = getListaProductosNoTransp();
		for(int i=0;i<arrayProductos.length;i++) {
			vista.addModeloListProdNoTransp(arrayProductos[i]);
		}
	}
	
	private void setListProductosTransp() {
		ProductoDTO[] arrayProductos = getListaProductosTransp();
		for(int i=0;i<arrayProductos.length;i++) {
			vista.addModeloListProdTransp(arrayProductos[i]);
		}
	}
	
	private void setListProductosNoMontar() {
		ProductoDTO[] arrayProductos = getListaProductosNoMontar();
		for(int i=0;i<arrayProductos.length;i++) {
			vista.addModeloListProdNoMontar(arrayProductos[i]);
		}
	}
	
	private void setListProductosMontar() {
		ProductoDTO[] arrayProductos = getListaProductosMontar();
		for(int i=0;i<arrayProductos.length;i++) {
			vista.addModeloListProdMontar(arrayProductos[i]);
		}
	}
	
	public ProductoDTO[] toArray(List<ProductoDTO> listProductos) {
		ProductoDTO[] arrayProductos = new ProductoDTO[listProductos.size()];
		for(int i=0;i<listProductos.size();i++) {
			arrayProductos[i] = listProductos.get(i);
		}
		return arrayProductos;
	}
}
