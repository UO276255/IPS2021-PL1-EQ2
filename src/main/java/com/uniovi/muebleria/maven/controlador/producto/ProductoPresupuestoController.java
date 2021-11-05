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
	
	private ProductoDTO[] toArray(List<ProductoDTO> listProductos) {
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
	
	public void setListProductosFiltrados(int value, String selectedItem, boolean productosPrecioSuperior) {
		List<ProductoDTO> listProductos = model.getListaProductosFiltrados(value, selectedItem, productosPrecioSuperior);
		ProductoDTO[] arrayProductos = toArray(listProductos);
		setListProductos(arrayProductos);
	}
	
	public int getPrecioProducto(int id) {
		return model.getPrecioProd(id);
	}
	
	public void crearPresupuesto(int precio) {
		model.crearPresupuesto(precio);
	}
	
	public int getIdPres() {
		return model.idPresupuesto();
	}

	public int getIdSolicitud() {
		return model.idSolicitud();
	}

	public void crearSolicitudes(int idPres, ProductoDTO[] ListProductos) {
		for (ProductoDTO producto : ListProductos) {
			if(model.existeIdProdIdPres(idPres, producto.getId())) {
				//model.actualizaNumProdIdSol(idPres, producto.getId());
			}else {
				model.crearSolicitudes(getIdSolicitud()+1, idPres, producto);
			}
		}
	}
}
