package com.uniovi.muebleria.maven.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;

import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.uniovi.muebleria.maven.controlador.producto.ProductoController;
import com.uniovi.muebleria.maven.modelo.producto.ProductoModel;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class VistaCrearPedido extends JFrame {

	private JPanel contentPane;
	private JTextField txAlmacen;
	private List listProducts;
	private List listProductosAñadidos;


	/**
	 * Create the frame.
	 */
	public VistaCrearPedido() {
		setTitle("Muebleria : Crear Pedido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 716, 515);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnListaProductos = new JPanel();
		pnListaProductos.setBounds(35, 35, 259, 286);
		contentPane.add(pnListaProductos);
		pnListaProductos.setLayout(new BorderLayout(0, 0));
		
		listProducts = new List();
		listProducts.setBackground(new Color(255, 255, 224));
		pnListaProductos.add(listProducts, BorderLayout.CENTER);
		
		JLabel lbListaProd = new JLabel("Lista Productos");
		lbListaProd.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lbListaProd.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnListaProductos.add(lbListaProd, BorderLayout.NORTH);
		listProductosAñadidos = new List();
		listProductosAñadidos.setBackground(new Color(255, 255, 224));
		JButton btSeleccionar = new JButton("AÑADIR");
		btSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ProductoController controller = new ProductoController(new ProductoModel(),  VistaMuebleria.VIEW_PEDIDO);
				listProductosAñadidos.add(listProducts.getSelectedItem());
			}
		});
		btSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btSeleccionar.setBackground(Color.GREEN);
		pnListaProductos.add(btSeleccionar, BorderLayout.SOUTH);
		
		JPanel pnProductosAñadidos = new JPanel();
		pnProductosAñadidos.setBounds(411, 35, 259, 286);
		contentPane.add(pnProductosAñadidos);
		pnProductosAñadidos.setLayout(new BorderLayout(0, 0));
		
		
		pnProductosAñadidos.add(listProductosAñadidos, BorderLayout.CENTER);
		
		JLabel lbProdAñadidos = new JLabel("Productos Añadidos");
		lbProdAñadidos.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lbProdAñadidos.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnProductosAñadidos.add(lbProdAñadidos, BorderLayout.NORTH);
		
		JButton btEliminar = new JButton("ELIMINAR");
		btEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btEliminar.setBackground(Color.RED);
		pnProductosAñadidos.add(btEliminar, BorderLayout.SOUTH);
		
		JLabel lbUnidades = new JLabel("UNIDADES");
		lbUnidades.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbUnidades.setBounds(315, 120, 76, 34);
		contentPane.add(lbUnidades);
		
		JPanel pnBotones = new JPanel();
		pnBotones.setBounds(403, 415, 287, 50);
		contentPane.add(pnBotones);
		pnBotones.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btCancelar = new JButton("CANCELAR");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btCancelar.setBackground(Color.RED);
		pnBotones.add(btCancelar);
		
		JButton btCrear = new JButton("CREAR");
		btCrear.setFont(new Font("Tahoma", Font.BOLD, 12));
		btCrear.setBackground(Color.GREEN);
		pnBotones.add(btCrear);
		
		JLabel lbAlmacen = new JLabel("ALMACEN :");
		lbAlmacen.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbAlmacen.setBounds(42, 361, 107, 34);
		contentPane.add(lbAlmacen);
		
		txAlmacen = new JTextField();
		txAlmacen.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txAlmacen.setBackground(new Color(255, 255, 224));
		txAlmacen.setEditable(false);
		txAlmacen.setEnabled(false);
		txAlmacen.setBounds(129, 353, 241, 54);
		contentPane.add(txAlmacen);
		txAlmacen.setColumns(10);
		
		JFormattedTextField txUnidades = new JFormattedTextField(new Integer(0));
		txUnidades.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txUnidades.setBackground(new Color(255, 255, 224));
		txUnidades.setFont(new Font("Tahoma", Font.BOLD, 14));
		txUnidades.setHorizontalAlignment(SwingConstants.CENTER);
		txUnidades.setBounds(315, 148, 76, 28);
		contentPane.add(txUnidades);
		
	}
	

	public List getListProducts() {
		return listProducts;
	}

	public void setListProducts(List listProducts) {
		this.listProducts = listProducts;
	}
}
