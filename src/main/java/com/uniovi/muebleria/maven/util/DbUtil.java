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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.uniovi.muebleria.maven.modelo.Cliente.ClienteDTO;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestoDTO;
import com.uniovi.muebleria.maven.modelo.pedidos.PedidoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;
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
	
	public ArrayList<TransportistaDTO> recogerTransportistas(String sql){
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		TransportistaDTO tra = null;
		ArrayList<TransportistaDTO> list = new ArrayList<TransportistaDTO>();
		
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				tra = new TransportistaDTO(rs.getString(2), rs.getInt(3),rs.getDate(4), rs.getDate(5));
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
				tra = new VentaDTO(rs.getInt(1), rs.getDate(2),rs.getInt(3), rs.getBoolean(4),rs.getInt(5));
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
				client = new ClienteDTO(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getDate(4));
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
	
	public void AsignarTransporte(String sql, int bit, int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = getConnection();
			pst = c.prepareStatement(sql);
			pst.setInt(1,bit);
			pst.setInt(2,id);	
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
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
			throw new RuntimeException(e);
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
				prod = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(6));
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
				prod = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(6));
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
	
	public void crearCliente(String sql, int id, String nombre, String apellido,Date fecha) {
		
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
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
				result = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(6));
			}		
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		return result;
	}
	
	public int recogerMaxValorIdProv(String sqlMaximoId) {
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
	
	public List<ProductoDTO> recogerProductosVenta(String sqlProductoVenta, int id_pres) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ProductoDTO prod = null;
		ArrayList<ProductoDTO> listaProducto = new ArrayList<ProductoDTO>();
		try {
			c = getConnection();
			pst = c.prepareStatement(sqlProductoVenta);
			pst.setInt(1,id_pres);	
			rs = pst.executeQuery();
			while (rs.next()) {
				prod = new ProductoDTO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(6));
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
}
