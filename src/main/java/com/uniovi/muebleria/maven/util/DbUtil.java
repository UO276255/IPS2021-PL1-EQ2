package com.uniovi.muebleria.maven.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import com.uniovi.muebleria.maven.modelo.Almacen.AlmacenDTO;
import com.uniovi.muebleria.maven.modelo.Cliente.ClienteDTO;
import com.uniovi.muebleria.maven.modelo.PersonalAlmacen.PersonalAlmacenDTO;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestoDTO;
import com.uniovi.muebleria.maven.modelo.Vendedor.VendedorDTO;
import com.uniovi.muebleria.maven.modelo.empleado.EmpleadoDTO;
import com.uniovi.muebleria.maven.modelo.pedidos.PedidoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaDTO;

public abstract class DbUtil {

	/** Obtencion de la url de conexion que debe implementarse en la subclase */
	public abstract String getUrl();
	protected static final String USER = "SA";
	protected static final String PASSWORD = "";
	
	/**
	 * Metodo simple para ejecutar todas las sentencias sql que se encuentran en un archivo, teniendo en cuenta:
	 * <br/>- Cada sentencia DEBE finalizar en ; pudiendo ocupar varias lineas
	 * <br/>- Se permiten comentarios de linea (--)
	 * <br/>- Todas las sentencias drop se ejecutan al principio, 
	 * y se ignoran los fallos en caso de que no exista la tabla (solo para drop)
	 */
	public void executeScript(String fileName) {
		List<String> lines;
		try {
			lines=Files.readAllLines(Paths.get(fileName));
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
		//separa las sentencias sql en dos listas, una para drop y otra para el resto pues se ejecutaran de forma diferente
		List<String> batchUpdate=new ArrayList<>();
		List<String> batchDrop=new ArrayList<>();
		StringBuilder previousLines=new StringBuilder(); //guarda lineas anteriores al separador (;)
		for (String line : lines) {
			line=line.trim();
			if (line.length()==0 || line.startsWith("--")) //ignora lineas vacias comentarios de linea
				continue;
			if (line.endsWith(";")) {
				String sql=previousLines.toString()+line;
				//separa drop del resto
				if (line.toLowerCase().startsWith("drop"))
					batchDrop.add(sql);
				else
					batchUpdate.add(sql);
				//nueva linea
				previousLines=new StringBuilder();
			} else {
				previousLines.append(line+" ");
			}
		}
		//Ejecuta todas las sentencias, primero los drop (si existen)
		if (!batchDrop.isEmpty())
			try {
				this.executeBatchNoFail(batchDrop);
			} catch (UnexpectedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (!batchUpdate.isEmpty())
			try {
				this.executeBatch(batchUpdate);
			} catch (UnexpectedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(getUrl());
	}
	
	/**
	 * Ejecuta un conjunto de sentencias sql de actualizacion en un unico batch
	 */
	public void executeBatch(String[] sqls) {
		try {
			executeBatch(Arrays.asList(sqls));
		} catch (UnexpectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Ejecuta un conjunto de sentencias sql de actualizacion en un unico batch
	 * @throws UnexpectedException 
	 */
	public void executeBatch(List<String> sqls) throws UnexpectedException {
		try (Connection cn=DriverManager.getConnection(getUrl(),USER,PASSWORD);
			Statement stmt = cn.createStatement()) {
				for (String sql : sqls)
					stmt.addBatch(sql);
				stmt.executeBatch();
		} catch (SQLException e) {
			//Ojo, no dejar pasar las excepciones (no limitarse a dejar el codigo autoegenerado por Eclipse con printStackTrace)
			throw new UnexpectedException(e.getMessage());
		}
	}
	
	/**
	 * Ejecuta un conjunto de sentencias sql de actualizacion en un unico batch, sin causar excepcion cuando falla la ejecucion
	 * (usado normalmente para borrar tablas de la bd, que fallarian si no existen)
	 * @throws UnexpectedException 
	 */
	public void executeBatchNoFail(List<String> sqls) throws UnexpectedException {
		try (Connection cn=DriverManager.getConnection(getUrl(),USER,PASSWORD);
			Statement stmt = cn.createStatement()) {
				for (String sql : sqls)
					executeWithoutException(stmt,sql);
		} catch (SQLException e) {
			throw new UnexpectedException(e.getMessage());
		}
	}
	
	private void executeWithoutException(Statement stmt, String sql) {
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			//no causa excepcion intencionaamente
		}		
	}
	
	public ArrayList<EmpleadoDTO> recogerEmpleados(String sql){
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		EmpleadoDTO tra = null;
		ArrayList<EmpleadoDTO> list = new ArrayList<EmpleadoDTO>();
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				tra = new EmpleadoDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(5), rs.getTime(8), rs.getTime(9), rs.getString(12));
				list.add(tra);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return list;
	}
	
	public EmpleadoDTO recogerTransportista(String sql, int idTransp){
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		EmpleadoDTO tra = null;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, idTransp);
			rs = pst.executeQuery();
			if (rs.next()) {
				tra = new EmpleadoDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(5),rs.getTime(8), rs.getTime(9), rs.getString(12));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return tra;
	}
	
	public ArrayList<VentaDTO> recogerVentas(String sql){
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		VentaDTO tra = null;
		ArrayList<VentaDTO> list = new ArrayList<VentaDTO>();
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				tra = new VentaDTO(rs.getInt(1), rs.getDate(2),rs.getInt(3), rs.getBoolean(4),rs.getInt(5),rs.getInt(6));
				list.add(tra);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return list;
	}
	
	public ArrayList<VentaDTO> recogerVentasTransporte(String sql){
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		VentaDTO tra = null;
		ArrayList<VentaDTO> list = new ArrayList<VentaDTO>();
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				tra = new VentaDTO(rs.getInt(1), rs.getDate(2),rs.getInt(3), rs.getBoolean(4),rs.getInt(5),rs.getInt(6));
				list.add(tra);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return list;
	}
	
	public VentaDTO getVenta(String sqlVenta, int idVenta) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		VentaDTO venta = null;
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlVenta);
			pst.setInt(1, idVenta);
			rs = pst.executeQuery();
			if (rs.next()) {
				venta = new VentaDTO(rs.getInt(1), rs.getDate(2),rs.getInt(3), rs.getBoolean(4),rs.getInt(5),rs.getInt(6));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return venta;
	}

	public int getCantidadProdAlmacen(String sql, int idAlmacen, int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int cantidad = 0;
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, idAlmacen);
			pst.setInt(2, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				cantidad = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return cantidad;
	}
	
	public void actualizaAlmacen(String sql, int idAlmacen, int id, int cantidad) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1,cantidad);
			pst.setInt(2,idAlmacen);
			pst.setInt(3,  id);
			pst.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Alguno de los campos esta vació o no es correcto");
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}

	
	public ArrayList<PresupuestoDTO> recogerPresupuestos(String sql){
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PresupuestoDTO pres = null;
		ArrayList<PresupuestoDTO> list = new ArrayList<PresupuestoDTO>();
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				pres = new PresupuestoDTO(rs.getInt(1), rs.getInt(2),presupuestoAceptado(rs.getInt(3)),rs.getDate(4), rs.getInt(5),"");
				list.add(pres);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return list;
	}
	
	public ArrayList<PresupuestoDTO> recogerPresupuestosYClientes(String sql){
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PresupuestoDTO pres = null;
		ArrayList<PresupuestoDTO> list = new ArrayList<PresupuestoDTO>();
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				pres = new PresupuestoDTO(rs.getInt(1), rs.getInt(2),presupuestoAceptado(rs.getInt(3)),rs.getDate(4), rs.getInt(5),rs.getString(7));
				list.add(pres);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return list;
	}
	
	public ArrayList<ClienteDTO> recogerClientes(String sql){
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ClienteDTO client = null;
		ArrayList<ClienteDTO> list = new ArrayList<ClienteDTO>();
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				client = new ClienteDTO(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getDate(4),rs.getInt(5),rs.getString(6));
				list.add(client);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return list;
	}
	
	public void AsignarPresupuestoACliente(String sql, int idclient, int idpresupuesto) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1,idclient);
			pst.setInt(2,idpresupuesto);	
			pst.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Alguno de los campos esta vació o no es correcto");
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
	}
	

	public ArrayList<ProductoDTO> recogerProductosPresupuesto(String sqlProducto, int id_pres, boolean conTransporte) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProductoDTO prod = null;
		ArrayList<ProductoDTO> listaProducto = new ArrayList<ProductoDTO>();
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlProducto);
			pst.setInt(1,id_pres);
			pst.setBoolean(2,conTransporte);	
			rs = pst.executeQuery();
			while (rs.next()) {
				prod = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(4));
				if(listaProducto.contains(prod)) {
					
				}else {
					listaProducto.add(prod);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return listaProducto;
	}



	public ArrayList<ProductoDTO> recogerProductos(String sql){
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProductoDTO prod = null;
		ArrayList<ProductoDTO> list = new ArrayList<ProductoDTO>();
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				prod = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(4));
				if(list.contains(prod)) {
					
				}else {
					list.add(prod);
				}			
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return list;
	}
	
	public int contarCliente(String sqlContarCliente) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlContarCliente);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	public void crearCliente(String sql, int id, String nombre, String apellido,Date fecha,int dni,String email) {
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
			try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			
			pst.setInt(1,id);
			pst.setString(2,nombre);	
			pst.setString(3,apellido);
			pst.setDate(4,fecha);
			pst.setInt(5,dni);
			pst.setString(6,email);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Alguno de los campos esta vació o no es correcto");
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
	}

	private boolean presupuestoAceptado(int value) {
		return value == 0;
	}
	
	public PedidoDTO recogerRepuestoPedido(String sqlProductosProveedor, int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PedidoDTO result = null;
		List<ProductoDTO> productos = new ArrayList<ProductoDTO>();
		List<Integer> numProductos = new ArrayList<Integer>();
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlProductosProveedor);
			pst.setInt(1,id);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				productos.add(recogerProductoRep("SELECT * FROM Productos where id_prod = ?", rs.getInt(3)));
				numProductos.add(rs.getInt(4));				
			}
			
			result = new PedidoDTO(id, productos, numProductos, recogerEstadoPedido("SELECT estado FROM Pedido where id_pedido = ?", id), id);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	public List<AlmacenDTO> obtenerAlmacenes(String sql){
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		AlmacenDTO result = null;
		List<AlmacenDTO> almacenes = new ArrayList<AlmacenDTO>();
		
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();	
			while(rs.next()) {
				result = new AlmacenDTO(rs.getString(2),rs.getInt(1),0);
				almacenes.add(result);
			}
			
		} catch (SQLException e) {
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return almacenes;
	}
	
	public ProductoDTO recogerProductoRep(String sql, int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProductoDTO result = null;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				result = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(4));
			}		
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	public int recogerMaxValorIdPedido(String sqlMaximoId) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlMaximoId);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	public void actualizaPedido(String sqlActualizaPedido, int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
			try {
			c = getConnection();
			pst = c.prepareStatement(sqlActualizaPedido);
			
			pst.setInt(1,id);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}
	
	public boolean recogerEstadoPedido(String sqlEstado, int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlEstado);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				if(rs.getInt(1) == 0) {
					result = false;
				}else {
					result = true;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	public List<ProductoDTO> recogerProductosVentaNoTransp(String sqlProductoVenta, int id_venta) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProductoDTO prod = null;
		int transporte;
		int idpres;
		ArrayList<ProductoDTO> listaProducto = new ArrayList<ProductoDTO>();
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlProductoVenta);
			pst.setInt(1,id_venta);	
			rs = pst.executeQuery();
			while (rs.next()) {
				idpres = getIdPresPorIdVenta("SELECT * FROM venta WHERE id_venta = ?", id_venta);
				transporte = productoParaTransportar("SELECT * FROM solicitudes WHERE id_prod = ? and id_pres = ?", rs.getInt(1), idpres);
				if(transporte == 0) {
					prod = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(4));
				}
				if(listaProducto.contains(prod)) {
					
				}else {
					listaProducto.add(prod);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return listaProducto;
	}
	
	public List<ProductoDTO> recogerProductosVentaTransp(String sqlProductoVenta, int id_venta) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProductoDTO prod = null;
		int transporte;
		int idpres;
		ArrayList<ProductoDTO> listaProducto = new ArrayList<ProductoDTO>();
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlProductoVenta);
			pst.setInt(1,id_venta);	
			rs = pst.executeQuery();
			while (rs.next()) {
				idpres = getIdPresPorIdVenta("SELECT * FROM venta WHERE id_venta = ?", id_venta);
				transporte = productoParaTransportar("SELECT * FROM solicitudes WHERE id_prod = ? and id_pres = ?", rs.getInt(1), idpres);
				if(transporte == 1) {
					prod = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(4));
				}
				if(listaProducto.contains(prod)) {
					
				}else {
					listaProducto.add(prod);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return listaProducto;
	}
	
	public List<ProductoDTO> recogerProductosVentaNoMontar(String sqlProductoVenta, int id_venta) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProductoDTO prod = null;
		int transporte;
		int montaje;
		int idpres;
		ArrayList<ProductoDTO> listaProducto = new ArrayList<ProductoDTO>();
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlProductoVenta);
			pst.setInt(1,id_venta);	
			rs = pst.executeQuery();
			while (rs.next()) {
				idpres = getIdPresPorIdVenta("SELECT * FROM venta WHERE id_venta = ?", id_venta);
				transporte = productoParaTransportar("SELECT * FROM solicitudes WHERE id_prod = ? and id_pres = ?", rs.getInt(1), idpres);
				montaje = productoParaMontar("SELECT * FROM solicitudes WHERE id_prod = ? and id_pres = ?", rs.getInt(1), idpres);
				if(montaje == 0 && transporte == 1) {
					prod = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(4));
				}
				if(listaProducto.contains(prod)) {
					
				}else {
					listaProducto.add(prod);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return listaProducto;
	}
	
	public List<ProductoDTO> recogerProductosVentaMontar(String sqlProductoVenta, int id_venta) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProductoDTO prod = null;
		int montaje;
		int transporte;
		int idpres;
		ArrayList<ProductoDTO> listaProducto = new ArrayList<ProductoDTO>();
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlProductoVenta);
			pst.setInt(1,id_venta);	
			rs = pst.executeQuery();
			while (rs.next()) {
				idpres = getIdPresPorIdVenta("SELECT * FROM venta WHERE id_venta = ?", id_venta);
				transporte = productoParaTransportar("SELECT * FROM solicitudes WHERE id_prod = ? and id_pres = ?", rs.getInt(1), idpres);
				montaje = productoParaMontar("SELECT * FROM solicitudes WHERE id_prod = ? and id_pres = ?", rs.getInt(1), idpres);
				if(montaje == 1 && transporte == 1) {
					prod = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(4));
				}
				if(listaProducto.contains(prod)) {
					
				}else {
					listaProducto.add(prod);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return listaProducto;
	}
	
	public List<ProductoDTO> recogerTodosProductosFiltrados(String sql, int value) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProductoDTO prod = null;
		ArrayList<ProductoDTO> list = new ArrayList<ProductoDTO>();
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, value);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				prod = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(4));
				if(list.contains(prod)) {
					
				}else {
					list.add(prod);
				}			
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return list;
	}

	public List<ProductoDTO> recogerProductosFiltrados(String sql, int value, String selectedItem) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProductoDTO prod = null;
		ArrayList<ProductoDTO> list = new ArrayList<ProductoDTO>();
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, value);
			pst.setString(2, selectedItem);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				prod = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(4));
				if(list.contains(prod)) {
					
				}else {
					list.add(prod);
				}			
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return list;
	}
	
	public int precioProducto(String sql, int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(3);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	public void AsignarTransporte(String sql, int bit, int id_prod, int id_venta) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int id_pres = getIdPresPorIdVenta("SELECT * FROM venta WHERE id_venta = ?", id_venta);
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1,bit);
			pst.setInt(2,id_prod);
			pst.setInt(3,id_pres);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}
	
	public int getIdPresPorIdVenta(String sql, int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(5);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	public int productoParaTransportar(String sql, int idprod, int idpres) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, idprod);
			pst.setInt(2, idpres);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt(4);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	public int productoParaMontar(String sql, int idprod, int idpres) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, idprod);
			pst.setInt(2, idpres);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt(5);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	public void CrearVenta(String sql,int id,Date fecha,int precio,int idPresupuesto) {
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1,id);
			pst.setDate(2,fecha);	
			pst.setInt(3,precio);	
			pst.setInt(4,idPresupuesto);	
			pst.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Alguno de los campos esta vació o no es correcto");
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}

	public void cancelPresupuesto(String sql, int idPresupuesto) {
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1,idPresupuesto);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	
	}

	public int contarDatos(String Contar) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(Contar);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
		return result;
	}

	
	public ProductoDTO recogerProductoProv(String sql, int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProductoDTO result = null;

		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				result = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(4));
			}		

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	
	public void asignaTransportistaVenta(String sqlAsignar, int idTransp, int idVenta) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlAsignar);
			pst.setInt(1,idTransp);
			pst.setInt(2,idVenta);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}
	
	public void actualiceTransporteVenta(String sqlAsignar, int idVenta) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlAsignar);
			pst.setInt(1,idVenta);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}
	
	public int buscaIdTranspPorIdVenta(String sql, int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	public void asignaFechaAVenta(String sqlFechas, VentaDTO venta,java.sql.Date dateTime) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
			try {
			c = getConnection();
			pst = c.prepareStatement(sqlFechas);
			
			pst.setDate(1,dateTime);
			pst.setInt(2,venta.getId_venta());	
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
	}
	
	public List<VentaDTO> recogerVentasFecha(String sqlVentasFecha, Date fecha, Date fecha2) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		VentaDTO prod = null;
		ArrayList<VentaDTO> listaProducto = new ArrayList<VentaDTO>();
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlVentasFecha);
			pst.setDate(1,fecha);	
			pst.setDate(2, fecha2);
			rs = pst.executeQuery();
			while (rs.next()) {
				prod = new VentaDTO(rs.getInt(1), rs.getDate(2),rs.getInt(3),rs.getBoolean(4),rs.getInt(5),rs.getInt(6));
				if(listaProducto.contains(prod)) {
					
				}else {
					listaProducto.add(prod);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return listaProducto;
			
	}
	
	public int contarUnidades(String sql, int id_pres, int id_prod) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1,id_pres);
			pst.setInt(2, id_prod);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	public ArrayList<ProductoDTO> recogerProductosPresupuestoTotal(String sqlProducto, int id_pres) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProductoDTO prod = null;
		ArrayList<ProductoDTO> listaProducto = new ArrayList<ProductoDTO>();
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlProducto);
			pst.setInt(1,id_pres);
			rs = pst.executeQuery();
			while (rs.next()) {
				prod = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(4));
				if(listaProducto.contains(prod)) {
					
				}else {
					listaProducto.add(prod);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return listaProducto;
	}
	
	public  List<ProductoDTO> productosPorAlmacen(String sql,int idAlmacen) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProductoDTO prod = null;
		ArrayList<ProductoDTO> listaProducto = new ArrayList<ProductoDTO>();
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1,idAlmacen);
			rs = pst.executeQuery();
			while (rs.next()) {
				prod = new ProductoDTO(rs.getString(1),rs.getInt(2));
				
				if(listaProducto.contains(prod)) {
					
				}else {
					listaProducto.add(prod);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Ha habido algun fallo durante la ejecución");
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return listaProducto;
	}
	
	public  List<ProductoDTO> productos(String sql) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProductoDTO prod = null;
		ArrayList<ProductoDTO> listaProducto = new ArrayList<ProductoDTO>();
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				prod = new ProductoDTO(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
				
				if(listaProducto.contains(prod)) {
					
				}else {
					listaProducto.add(prod);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return listaProducto;
	}
	
	public void creaPresupuesto(String sql, int precio) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
			try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			
			pst.setInt(1,calcularNumPresupuesto() + 1);
			pst.setInt(2,precio);	
			pst.setInt(3,0);
			java.util.Date date = new java.util.Date();
			pst.setDate(4,new Date(date.getTime() + 1296000000));
			pst.setObject(5,null);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}
	
	public void creaSolicitudes(String sql, int idSol, int idPres, ProductoDTO prod) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
			try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1,idSol);
			pst.setInt(2,idPres);	
			pst.setInt(3,prod.getId());
			pst.setInt(4,0);
			pst.setInt(5,0);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}

	public int calcularNumPresupuesto() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			c = getConnection();
			pst = c.prepareStatement("SELECT MAX(id_pres) max_id_pres FROM Presupuestos");
			rs = pst.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	public int calcularNumSolicitud() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			c = getConnection();
			pst = c.prepareStatement("SELECT MAX(id_solic) max_id_solic FROM Solicitudes");
			rs = pst.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	public boolean existeIdProdIdPres(String sql, int idPres, int idProd) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, idPres);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				if(rs.getInt(1) == idProd) {
					result = true;
				}else {
					result = false;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	

	public void crearVendedor(String sqlAñadirVendedor, int id, String nombre, String apellido, String DNI,
			int telefono, String usuario, String contraseña, Time hora_entrada, Time hora_salida, String oficio) {	
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
			try {
			c = getConnection();
			pst = c.prepareStatement(sqlAñadirVendedor);
			
			pst.setInt(1,id);
			pst.setString(2,nombre);	
			pst.setString(3,apellido);
			pst.setString(4,DNI);
			pst.setInt(5,telefono);
			pst.setString(6,usuario);
			pst.setString(7,contraseña);
			pst.setTime(8,hora_entrada);
			pst.setTime(9,hora_salida);
			Date date = new Date(0);
			pst.setDate(10,date);
			pst.setDate(11,date);
			pst.setString(12, oficio);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}
	
	public void crearPersonalAlmacen(String sqlAñadirPersonalAlmacen, int id, String nombre, String apellido,
			String DNI, int telefono, String usuario, String contraseña, Time horaEntrada, Time horaSalida, String oficio) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
			try {
			c = getConnection();
			pst = c.prepareStatement(sqlAñadirPersonalAlmacen);
			
			pst.setInt(1,id);
			pst.setString(2,nombre);	
			pst.setString(3,apellido);
			pst.setString(4,DNI);
			pst.setInt(5,telefono);
			pst.setString(6,usuario);
			pst.setString(7,contraseña);
			pst.setTime(8,horaEntrada);
			pst.setTime(9,horaSalida);
			Date date = new Date(0);
			pst.setDate(10,date);
			pst.setDate(11,date);
			pst.setString(12, oficio);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
	}

	public void añadirAlmacen(String sqlAñadeProductos, ProductoDTO producto, int num) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
			try {
			c = getConnection();
			pst = c.prepareStatement(sqlAñadeProductos);
			
			pst.setInt(1,getCantidadDeProducto(producto.getId()) + num);
			pst.setInt(2,producto.getId());	
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Alguno de los campos esta vació o no es correcto");
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}
	
	private int getCantidadDeProducto(int cant) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			c = getConnection();
			pst = c.prepareStatement("SELECT cantidad FROM Registrado where id_prod = ?");
			pst.setInt(1, cant);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}

	public void añadirTransportista(String sqlAñadirTransp, int id, String nombre, int numero_tel, Time hora_entrada,
			Time hora_salida, String apellido, String DNI, String usuario, String contraseña,String oficio) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
			try {
			c = getConnection();
			pst = c.prepareStatement(sqlAñadirTransp);
			
			pst.setInt(1,id);
			pst.setString(2,nombre);	
			pst.setString(3,apellido);
			pst.setString(4,DNI);
			pst.setInt(5,numero_tel);
			pst.setString(6,usuario);
			pst.setString(7,contraseña);
			pst.setTime(8,hora_entrada);
			pst.setTime(9,hora_salida);
			Date date = new Date(0);
			pst.setDate(10,date);
			pst.setDate(11,date);
			pst.setString(12, oficio);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}
	public AlmacenDTO almacenActivo(String sql) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		AlmacenDTO almacen=null;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				almacen = new AlmacenDTO(rs.getString(2),rs.getInt(1),0);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return almacen;
	}
	public int crearPedido(String sql) {
		String sqlId = "SELECT MAX(Id_Pedido) FROM Pedido";

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int id=0;
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlId);
			rs = pst.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
				id++;
			}
			pst.close();
			
			pst = c.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al consultar la BD");
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
		return id;
	}
	
	public void crearRepuesto(String sql, int idPedidoCreado, int idProducto, int cantidad) {
		String sqlId = "SELECT MAX(Id_Repuesto) FROM Repuesto";

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int id=0;
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlId);
			rs = pst.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
				id++;
			}
			pst.close();
			
			pst = c.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, idPedidoCreado);
			pst.setInt(3, idProducto);
			pst.setInt(4, cantidad);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al consultar la BD");
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}
	public void crearRegistrado(String sql, int idProducto, int idAlmacen, int nUnidades) {
		String sqlId = "SELECT MAX(Id_Reg) FROM Registrado";

		String sqlExiste = "SELECT * FROM Registrado WHERE Id_Prod=? and Id_Almacen=?";
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int id=0;
		try {
			c = getConnection();
			
			pst = c.prepareStatement(sqlExiste);
			pst.setInt(1, idProducto);
			pst.setInt(2, idAlmacen);
			rs = pst.executeQuery();
			if (rs.next()) {
				int cantidad = rs.getInt(4);
				cantidad += nUnidades;
				pst.close();
				
				String sqlUpdate = "UPDATE Registrado SET cantidad = ? WHERE  Id_Prod=? and Id_Almacen=?";
				pst = c.prepareStatement(sqlUpdate);
				pst.setInt(1, cantidad);
				pst.setInt(2,  idProducto);
				pst.setInt(3, idAlmacen);
				pst.executeUpdate();
				pst.close();
			}
			else {
				pst = c.prepareStatement(sqlId);
				rs = pst.executeQuery();
				if (rs.next()) {
					id = rs.getInt(1);
					id++;
				}
				pst.close();
				
				pst = c.prepareStatement(sql);
				pst.setInt(1, id);
				pst.setInt(2, idProducto);
				pst.setInt(3, idAlmacen);
				pst.setInt(4, nUnidades);
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al consultar la BD");
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}
	
	public void asignaFecha(String sql, Date date, int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
			try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setDate(1, date);
			pst.setInt(2,id);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}
	
	public Date inicioVacacional(String sql, int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Date fecha = null;
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				fecha = rs.getDate(1);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return fecha;
	}
	
	public Date getFecha(String sql, int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Date fecha = null;
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				fecha = rs.getDate(1);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return fecha;
	}
	
	public List<VendedorDTO> getVendedores(String query){
		List<VendedorDTO> lista = new ArrayList<VendedorDTO>();
		VendedorDTO dto = null;		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				dto = new VendedorDTO();
				dto.setUsuario(rs.getString(1));
				dto.setContraseña(rs.getString(2));				
				lista.add(dto);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return lista;
	}
	
	public List<PersonalAlmacenDTO> getAlmacenLogin(String query){
		List<PersonalAlmacenDTO> lista = new ArrayList<PersonalAlmacenDTO>();
		PersonalAlmacenDTO dto = null;		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				dto = new PersonalAlmacenDTO();
				dto.setUsuario(rs.getString(1));
				dto.setContraseña(rs.getString(2));				
				lista.add(dto);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return lista;
	}
	
	public List<EmpleadoDTO> getTransportistasLogin(String query){
		List<EmpleadoDTO> lista = new ArrayList<EmpleadoDTO>();
		EmpleadoDTO dto = null;		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = getConnection();
			pst = c.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				dto = new EmpleadoDTO();
				dto.setUsuario(rs.getString(1));
				dto.setContraseña(rs.getString(2));				
				lista.add(dto);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return lista;
	}

	public ClienteDTO getCliente(String sql, int id_pres) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ClienteDTO cliente = null;
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1, id_pres);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				cliente = new ClienteDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),rs.getInt(5), rs.getString(6));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return cliente;

	}
}
