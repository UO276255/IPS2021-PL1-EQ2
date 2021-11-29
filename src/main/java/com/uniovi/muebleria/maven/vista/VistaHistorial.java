package com.uniovi.muebleria.maven.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import com.uniovi.muebleria.maven.controlador.Venta.VentaController;
import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaModel;

public class VistaHistorial extends JFrame {

	private JPanel contentPane;
	JList listVentas;
	JList listaProductos;
	JButton btCargarVentas;
	JDateChooser dateDesde;
	JDateChooser dateHasta;
	private JButton btSeleccionar;
	private JTextField txSumaMontaje;
	private JTextField txSumaTotal;
	private JButton btnGenerarFactura;
	
	/**
	 * Create the frame.
	 */
	public VistaHistorial() {
		setResizable(false);
		setTitle("Muebleria : Historial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 801, 516);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		Panel pnVentas = new Panel();
		pnVentas.setBounds(10, 50, 379, 388);
		contentPane.add(pnVentas);
		pnVentas.setLayout(new BorderLayout(0, 0));
		
		listVentas = new JList();
		listVentas.setBackground(new Color(255, 239, 213));
		listVentas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnVentas.add(listVentas);
		
		JLabel lbHistorial = new JLabel("Historial de Ventas");
		lbHistorial.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lbHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		lbHistorial.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnVentas.add(lbHistorial, BorderLayout.NORTH);
		
		pnVentas.add(getBtSeleccionar(), BorderLayout.SOUTH);
		
		JPanel pnSeleccionarFechas = new JPanel();
		pnSeleccionarFechas.setBounds(108, 11, 572, 33);
		contentPane.add(pnSeleccionarFechas);
		pnSeleccionarFechas.setLayout(new GridLayout(1, 0, 0, 0));
		
		dateDesde = new JDateChooser();
		pnSeleccionarFechas.add(dateDesde);
		
		dateHasta = new JDateChooser();
		pnSeleccionarFechas.add(dateHasta);
		pnSeleccionarFechas.add(getBtCargarVentas());
		
		Panel pnDetalles = new Panel();
		pnDetalles.setBounds(395, 51, 380, 416);
		contentPane.add(pnDetalles);
		pnDetalles.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Detalles de Venta");
		lblNewLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnDetalles.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel pnCentro = new JPanel();
		pnCentro.setBackground(new Color(255, 239, 213));
		pnDetalles.add(pnCentro, BorderLayout.CENTER);
		pnCentro.setLayout(null);
		
		JPanel pnProductos = new JPanel();
		pnProductos.setBounds(10, 11, 360, 317);
		pnCentro.add(pnProductos);
		pnProductos.setLayout(new BorderLayout(0, 0));
		
		JLabel lbProductos = new JLabel("Productos");
		lbProductos.setBackground(new Color(255, 239, 213));
		lbProductos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbProductos.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnProductos.add(lbProductos, BorderLayout.NORTH);
		
		listaProductos = new JList();
		listaProductos.setBackground(new Color(255, 239, 213));
		pnProductos.add(listaProductos, BorderLayout.CENTER);
		
		JLabel lbSumaMontaje = new JLabel("Suma Montaje : ");
		lbSumaMontaje.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbSumaMontaje.setBounds(10, 341, 105, 14);
		pnCentro.add(lbSumaMontaje);
		
		txSumaMontaje = new JTextField();
		txSumaMontaje.setEditable(false);
		txSumaMontaje.setHorizontalAlignment(SwingConstants.CENTER);
		txSumaMontaje.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txSumaMontaje.setBounds(118, 339, 122, 20);
		pnCentro.add(txSumaMontaje);
		txSumaMontaje.setColumns(10);
		
		JLabel lblSumaTotal = new JLabel("Suma Total : ");
		lblSumaTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSumaTotal.setBounds(10, 366, 105, 14);
		pnCentro.add(lblSumaTotal);
		
		txSumaTotal = new JTextField();
		txSumaTotal.setEditable(false);
		txSumaTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txSumaTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txSumaTotal.setColumns(10);
		txSumaTotal.setBounds(118, 364, 122, 20);
		pnCentro.add(txSumaTotal);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(260, 339, 110, 45);
		pnCentro.add(btnNewButton);
		contentPane.add(getBtnGenerarFactura());
	}
	
	
	public JList getListVentas() {
		return listVentas;
	}
	public void setListVentas(JList listVentas) {
		this.listVentas = listVentas;
	}

	public JList getListProductos() {
		return listaProductos;
	}
	public void setListProductos(JList listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	
	
	public JTextField getTxSumaMontaje() {
		return txSumaMontaje;
	}


	public void setTxSumaMontaje(JTextField txSumaMontaje) {
		this.txSumaMontaje = txSumaMontaje;
	}


	public JTextField getTxSumaTotal() {
		return txSumaTotal;
	}


	public void setTxSumaTotal(JTextField txSumaTotal) {
		this.txSumaTotal = txSumaTotal;
	}


	public JButton getBtCargarVentas() {
		btCargarVentas = new JButton("Cargar Ventas");
		btCargarVentas.setFont(new Font("Tahoma", Font.BOLD, 12));
		btCargarVentas.setBackground(new Color(238, 232, 170));
		btCargarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = dateDesde.getDate();
				Date date2 = dateHasta.getDate();
				VentaController controller = new VentaController(new VentaModel(), VistaMuebleria.VIEW_HISTORIAL);
				controller.historialVentas(date,date2);
			}
		});
		return btCargarVentas;
	}
	public void setBtCargarVentas(JButton btCargarVentas) {
		this.btCargarVentas = btCargarVentas;
	}
	
	public JButton getBtSeleccionar() {
		btSeleccionar = new JButton("Seleccionar");
		btSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentaController controller = new VentaController(new VentaModel(), VistaMuebleria.VIEW_HISTORIAL);
				controller.ventaSeleccionada();
			}
		});
		btSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btSeleccionar.setBackground(new Color(173, 255, 47));
		return btSeleccionar;
	}
	private JButton getBtnGenerarFactura() {
		if (btnGenerarFactura == null) {
			btnGenerarFactura = new JButton("Generar factura");
			btnGenerarFactura.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						VentaDTO ventaSeleccionada = (VentaDTO) listVentas.getSelectedValue();
						generarFactura();
						JOptionPane.showMessageDialog(null, "Se ha generado la factura para la venta de id " + ventaSeleccionada.getId_venta());
					} catch (FileNotFoundException | DocumentException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnGenerarFactura.setBackground(new Color(135, 206, 235));
			btnGenerarFactura.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnGenerarFactura.setBounds(10, 444, 379, 23);
		}
		return btnGenerarFactura;
	}
	
	private void generarFactura() throws FileNotFoundException, DocumentException {
		VentaController controller = new VentaController(new VentaModel(), VistaMuebleria.VIEW_HISTORIAL);
		VentaDTO ventaSeleccionada = (VentaDTO) listVentas.getSelectedValue();
		List<ProductoDTO> productos = controller.getListaProductosFiltrado(ventaSeleccionada);
		Document documento = new Document();
		FileOutputStream factura = new FileOutputStream("factura" + ventaSeleccionada.getId_venta() +".pdf");
		PdfWriter.getInstance(documento, factura);
		documento.open();
		Paragraph titulo = new Paragraph("Factura para venta de id: " + ventaSeleccionada.getId_venta() + "\n\n", FontFactory.getFont("arial",22,Font.BOLD,BaseColor.BLACK));
		documento.add(titulo);
		
		PdfPTable tabla = new PdfPTable(3);
		tabla.addCell("PRODUCTO");
		tabla.addCell("CANTIDAD");
		tabla.addCell("PRECIO PRODUCTO");
		
		for(int i=0; i<productos.size(); i++) {
			tabla.addCell("" + productos.get(i).getNombre());
			tabla.addCell("" + controller.getCantidadProducto(ventaSeleccionada.getId_pres(), productos.get(i).getId()));
			tabla.addCell("" + controller.getPrecioProductoVenta(ventaSeleccionada.getId_pres(), productos.get(i).getId()));
		}
		
		documento.add(tabla);
		
		Paragraph precio = new Paragraph("Precio total de la venta: " + ventaSeleccionada.getPrecio(), FontFactory.getFont("arial",16,Font.PLAIN,BaseColor.BLACK));
		documento.add(precio);
		
		String transp = "No";
		if(ventaSeleccionada.isTransporte()) {
			transp = "Sí";
		}
		Paragraph transporte = new Paragraph("Transporte: " + transp, FontFactory.getFont("arial",16,Font.PLAIN,BaseColor.BLACK));
		documento.add(transporte);
		
		String mont = "No";
		if(ventaSeleccionada.isMontaje()) {
			mont = "Sí";
		}
		Paragraph montaje = new Paragraph("Montaje: " + mont, FontFactory.getFont("arial",16,Font.PLAIN,BaseColor.BLACK));
		documento.add(montaje);
		
		documento.close();
	}
}
