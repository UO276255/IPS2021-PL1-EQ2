package com.uniovi.muebleria.maven.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import javax.swing.ListSelectionModel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaEntregarPedido extends JFrame {

	private JPanel contentPane;
	private JPanel pnVentas;
	private JLabel lbVentas;
	private JList listVentas;
	private JPanel pnBotones;
	private JButton btEntregar;
	private JButton btCancelar;

	/**
	 * Create the frame.
	 */
	public VistaEntregarPedido() {
		setTitle("Ventana EntregarPedido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 632, 542);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPnVentas());
		contentPane.add(getPnBotones());
	}

	private JPanel getPnVentas() {
		if (pnVentas == null) {
			pnVentas = new JPanel();
			pnVentas.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
			pnVentas.setBounds(29, 46, 550, 366);
			pnVentas.setLayout(new BorderLayout(0, 0));
			pnVentas.add(getLbVentas(), BorderLayout.NORTH);
			pnVentas.add(getListVentas(), BorderLayout.CENTER);
		}
		return pnVentas;
	}
	private JLabel getLbVentas() {
		if (lbVentas == null) {
			lbVentas = new JLabel("Ventas con transporte asignado");
			lbVentas.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return lbVentas;
	}
	public JList getListVentas() {
		if (listVentas == null) {
			listVentas = new JList();
			listVentas.setBackground(new Color(250, 240, 230));
			listVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listVentas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return listVentas;
	}
	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setBounds(299, 442, 280, 50);
			pnBotones.setLayout(new GridLayout(1, 0, 0, 0));
			pnBotones.add(getBtEntregar());
			pnBotones.add(getBtCancelar());
		}
		return pnBotones;
	}
	private JButton getBtEntregar() {
		if (btEntregar == null) {
			btEntregar = new JButton("ENTREGAR");
			btEntregar.setFont(new Font("Tahoma", Font.BOLD, 13));
			btEntregar.setBackground(Color.GREEN);
		}
		return btEntregar;
	}
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("CANCELAR");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
			btCancelar.setBackground(Color.RED);
		}
		return btCancelar;
	}
}
