package com.uniovi.muebleria.maven.modelo.producto;

import java.util.Date;
import java.util.List;

import com.uniovi.muebleria.maven.modelo.Almacen.AlmacenDTO;
import com.uniovi.muebleria.maven.util.Database;

public class ProductoModel {
	
private Database db = new Database();
	
	public static final String SQL_PRODUCTO_VENTA = "SELECT * FROM Productos prod "
			  + "JOIN Solicitudes sol ON prod.id_prod = sol.id_prod "
			  + "JOIN Presupuestos pres ON pres.id_pres = sol.id_pres "
			  + "JOIN Venta ven ON ven.id_pres = pres.id_pres "
			  + "WHERE id_venta = ?";
	
	public static final String SQL_ACTUALIZA_TRANSPORTE = "UPDATE Solicitudes set Transporte = ? where id_solic = ?";
	public static final String SQL_ACTUALIZA_MONTAJE = "UPDATE Solicitudes set Montaje = ? where id_solic = ?";
	
	private static final String SQL_MAXIMO_ID = "SELECT MAX(id_venta) max_id_venta FROM Venta";
	private static final String SQL_PRODUCTOS = "SELECT * FROM Productos";
	
	private static final String SQL_PRODUCTOS_POR_ALMACEN = "select t.nombre_prod,r.cantidad from productos t, registrado r where r.id_almacen = ? and r.id_prod = t.id_prod";
	
	private static final String SQL_ALMACEN_ACTIVO = "select * from almacen where id_almacen=1";
	
	private static final String SQL_CREAR_PEDIDO = "INSERT INTO Pedido(Id_Pedido, Estado, Id_Prov) VALUES (?, 0, 1)";
	
	private static final String SQL_CREAR_REPUESTO = "INSERT INTO Repuesto(ID_REPUESTO, ID_PEDIDO, ID_PROD, CANTIDAD_PROD) VALUES (?,?,?,?)";
	
	private static final String SQL_CREAR_REGISTRADO = "INSERT INTO Registrado(ID_REG, ID_PROD, ID_ALMACEN, CANTIDAD) VALUES (?,?,?,?)";
	
	private static final String SQL_FECHA_INICIO = "SELECT inicio_vacaciones FROM Vacaciones where id_empleado = ?";
	private static final String SQL_FECHA_FINAL = "SELECT fin_vacaciones FROM Vacaciones where id_empleado = ?";

	private static final String SQL_ACTUALIZA_PRECIO_PROD = "UPDATE Productos set precio_prod = ? WHERE id_prod = ?";
	
	
	public List<ProductoDTO> getListaProductosVentaNoTransp(int id_venta) {
		return db.recogerProductosVentaNoTransp(SQL_PRODUCTO_VENTA, id_venta);
	}
	
	public List<ProductoDTO> getListaProductosVentaTransp(int id_venta){
		return db.recogerProductosVentaTransp(SQL_PRODUCTO_VENTA, id_venta);
	}
	
	public List<ProductoDTO> getListaProductosVentaNoMontar(int id_venta){
		return db.recogerProductosVentaNoMontar(SQL_PRODUCTO_VENTA, id_venta);
	}
	
	public List<ProductoDTO> getListaProductosVentaMontar(int id_venta){
		return db.recogerProductosVentaMontar(SQL_PRODUCTO_VENTA, id_venta);
	}
	
	public void ActualizarATransporte(int bit, int id_prod, int id_venta) {
		db.AsignarATransporte(SQL_ACTUALIZA_TRANSPORTE,bit,id_prod,id_venta);
	}
	
	public void ActualizarARecogida(int bit, int id_prod, int id_venta) {
		db.AsignarARecogida(SQL_ACTUALIZA_TRANSPORTE,bit,id_prod,id_venta);
	}
	
	public void ActualizarAMontaje(int bit, int id_prod, int id_venta) {
		db.AsignarAMontaje(SQL_ACTUALIZA_MONTAJE,bit,id_prod,id_venta);
	}
	
	public void ActualizarANoMontaje(int bit, int id_prod, int id_venta) {
		db.AsignarANoMontaje(SQL_ACTUALIZA_MONTAJE,bit,id_prod,id_venta);
	}
	
	public List<ProductoDTO> getProductosPorAlmacen(int idAlmacen){
		return db.productosPorAlmacen(SQL_PRODUCTOS_POR_ALMACEN,idAlmacen);
	}

	public int getMaxId() {
		return db.recogerMaxValorIdPedido(SQL_MAXIMO_ID);
	}
	
	public List<ProductoDTO> getProductos(){
		return db.productos(SQL_PRODUCTOS);
	}

	public AlmacenDTO getAlmacenActivo() {
		return db.almacenActivo(SQL_ALMACEN_ACTIVO);
	}

	public int crearPedido() {
		int idPedido = db.crearPedido(SQL_CREAR_PEDIDO);
		
		return idPedido;
	}

	public void crearRepuesto(int idPedidoCreado, int idProducto, int cantidad) {
		db.crearRepuesto(SQL_CREAR_REPUESTO, idPedidoCreado, idProducto, cantidad);
	}

	public void crearRegistrado(int idProducto, int idAlmacen, int nUnidades) {
		db.crearRegistrado(SQL_CREAR_REGISTRADO, idProducto,idAlmacen,nUnidades);
		
	}

	public Date getDateInicioTransportista(int id) {
		return db.getFecha(SQL_FECHA_INICIO, id);
	}
	
	public Date getDateFinalTransportista(int id) {
		return db.getFecha(SQL_FECHA_FINAL, id);
	}

	public void actualizaPrecioProducto(ProductoDTO productoDTO) {
		db.actualizaPrecioProducto(SQL_ACTUALIZA_PRECIO_PROD, productoDTO.getId(), productoDTO.getPrecio());
		
	}
}
