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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class VistaCrearPedido extends JFrame {

	private JPanel contentPane;
	private JTextField txAlmacen;

	/**
	 * Create the frame.
	 */
	public VistaCrearPedido() {
		setTitle("Muebleria : Crear Pedido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnListaProductos = new JPanel();
		pnListaProductos.setBounds(35, 35, 259, 286);
		contentPane.add(pnListaProductos);
		pnListaProductos.setLayout(new BorderLayout(0, 0));
		
		JList listProducts = new JList();
		pnListaProductos.add(listProducts, BorderLayout.CENTER);
		
		JLabel lbListaProd = new JLabel("Lista Productos");
		lbListaProd.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lbListaProd.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnListaProductos.add(lbListaProd, BorderLayout.NORTH);
		
		JButton btSeleccionar = new JButton("SELECCIONAR");
		btSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btSeleccionar.setBackground(Color.GREEN);
		pnListaProductos.add(btSeleccionar, BorderLayout.SOUTH);
		
		JPanel pnProductosAñadidos = new JPanel();
		pnProductosAñadidos.setBounds(403, 35, 259, 286);
		contentPane.add(pnProductosAñadidos);
		pnProductosAñadidos.setLayout(new BorderLayout(0, 0));
		
		JList listProductosAñadidos = new JList();
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
		
		JComboBox cbUnidades = new JComboBox();
		cbUnidades.setBounds(315, 151, 78, 22);
		contentPane.add(cbUnidades);
		
		JPanel pnBotones = new JPanel();
		pnBotones.setBounds(403, 415, 287, 50);
		contentPane.add(pnBotones);
		pnBotones.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btCancelar = new JButton("CANCELAR");
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
		txAlmacen.setBounds(129, 353, 241, 54);
		contentPane.add(txAlmacen);
		txAlmacen.setColumns(10);
	}
}
