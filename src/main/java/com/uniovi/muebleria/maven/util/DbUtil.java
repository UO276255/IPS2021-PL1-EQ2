package com.uniovi.muebleria.maven.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.uniovi.muebleria.maven.modelo.producto.PresupuestoDTO;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;

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
	
	public ArrayList<TransportistaDTO> recogeTransportistas(String sql){
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		TransportistaDTO tra = null;
		ArrayList<TransportistaDTO> list = new ArrayList<TransportistaDTO>();
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				tra = new TransportistaDTO(rs.getString(2), rs.getInt(3), rs.getDate(4), rs.getDate(5));
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
				pres = new PresupuestoDTO(rs.getInt(1), rs.getInt(2),presupuestoAceptado(rs.getInt(3)),rs.getDate(4), rs.getInt(5));
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

	private boolean presupuestoAceptado(int value) {
		return value == 0;
	}
	
	
}
