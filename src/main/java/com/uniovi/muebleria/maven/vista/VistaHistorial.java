package com.uniovi.muebleria.maven.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.List;

import com.toedter.calendar.JDateChooser;
import com.uniovi.muebleria.maven.controlador.Venta.VentaController;
import com.uniovi.muebleria.maven.modelo.ventas.VentaDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaModel;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

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
	
	/**
	 * Create the frame.
	 */
	public VistaHistorial() {
		setTitle("Muebleria : Historial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel pnVentas = new Panel();
		pnVentas.setBounds(10, 50, 379, 417);
		contentPane.add(pnVentas);
		pnVentas.setLayout(new BorderLayout(0, 0));
		
		listVentas = new JList();
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
		pnDetalles.add(pnCentro, BorderLayout.CENTER);
		pnCentro.setLayout(null);
		
		JPanel pnProductos = new JPanel();
		pnProductos.setBounds(10, 11, 360, 317);
		pnCentro.add(pnProductos);
		pnProductos.setLayout(new BorderLayout(0, 0));
		
		JLabel lbProductos = new JLabel("Productos");
		lbProductos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbProductos.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnProductos.add(lbProductos, BorderLayout.NORTH);
		
		listaProductos = new JList();
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
}
