package com.uniovi.muebleria.maven.controlador.producto;

import java.util.List;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoPresupuestoModel;
import com.uniovi.muebleria.maven.vista.VistaCrearPresupuesto;

public class ProductoPresupuestoController {

	private VistaCrearPresupuesto vista;
	private ProductoPresupuestoModel model;
	
	public ProductoPresupuestoController(ProductoPresupuestoModel m, VistaCrearPresupuesto v) {
		this.vista = v;
		this.model = m;
	}
	
	public void initViewProdPres() {
		vista.setVisible(true);
		setListProductos(getListaProductos());
	}
	
	public ProductoDTO[] getListaProductos() {
		List<ProductoDTO> listProductos = model.getListaProductos();
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
	
	private void setListProductos(ProductoDTO[] arrayProductos) {
		for(int i=0;i<arrayProductos.length;i++) {
			vista.addModeloListProductos(arrayProductos[i]);
		}
	}
	
	public int getPrecioProducto(int id) {
		return model.getPrecioProd(id);
	}
}
