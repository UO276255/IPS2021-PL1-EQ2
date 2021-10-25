package com.uniovi.muebleria.maven.controlador.producto;

import java.util.List;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoModel;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;
import com.uniovi.muebleria.maven.vista.VistaAsignaTransporte;

public class ProductoController {
	private VistaAsignaTransporte vista;
	private ProductoModel model;
	
	public ProductoController(ProductoModel m, VistaAsignaTransporte v) {
		this.vista = v;
		this.model = m;
	}
	
//	public void initView() {
//		setListProductosVenta();
//		setListProductosTransp();
//		setListProductosNoMontar();
//		setListProductosMontar();
//	}
	
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

	public int getMaxId() {
		return model.getMaxId();
	}

}
