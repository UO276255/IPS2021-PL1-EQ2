package com.uniovi.muebleria.maven.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uniovi.muebleria.maven.controlador.Presupuestos.PresupuestoController;
import com.uniovi.muebleria.maven.controlador.producto.ProductoPresupuestoController;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestoDTO;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestosModel;
import com.uniovi.muebleria.maven.modelo.producto.ProductoPresupuestoModel;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class VistaVisualizarPresupuestos extends JFrame {

	private JPanel contentPane;
	private JComboBox cbPresupuestos;
	private JButton btSeleccionar;
	private JPanel pnProductos;
	private JList listProductos;
	private JButton btActualizar;
	private JButton btCancelar;

	/**
	 * Create the frame.
	 */
	public VistaVisualizarPresupuestos() {
		setResizable(false);
		setBackground(new Color(250, 235, 215));
		setTitle("Visualizar Presupuestos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 544);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCbPresupuestos());
		contentPane.add(getBtSeleccionar());
		contentPane.add(getPnProductos());
		contentPane.add(getBtCancelar());
		setLocationRelativeTo(null);
	}
	public JComboBox getCbPresupuestos() {
		if (cbPresupuestos == null) {
			cbPresupuestos = new JComboBox();
			cbPresupuestos.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbPresupuestos.setBounds(49, 46, 387, 28);
		}
		return cbPresupuestos;
	}
	private JButton getBtSeleccionar() {
		if (btSeleccionar == null) {
			btSeleccionar = new JButton("Seleccionar");
			btSeleccionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PresupuestoController controller = new PresupuestoController(new PresupuestosModel(),  VistaMuebleria.VIEW_VISUALIZAR_PRESUPUESTOS,false);
					controller.cargarProductos();
				}
			});
			btSeleccionar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			btSeleccionar.setBackground(new Color(0, 255, 0));
			btSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 12));
			btSeleccionar.setBounds(454, 46, 163, 28);
		}
		return btSeleccionar;
	}
	private JPanel getPnProductos() {
		if (pnProductos == null) {
			pnProductos = new JPanel();
			pnProductos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			pnProductos.setBounds(49, 98, 568, 330);
			pnProductos.setLayout(new BorderLayout(0, 0));
			pnProductos.add(getListProductos(), BorderLayout.CENTER);
			pnProductos.add(getBtActualizar(), BorderLayout.SOUTH);
		}
		return pnProductos;
	}
	public JList getListProductos() {
		if (listProductos == null) {
			listProductos = new JList();
		}
		return listProductos;
	}
	private JButton getBtActualizar() {
		if (btActualizar == null) {
			btActualizar = new JButton("Actualizar Presupuesto");
			btActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PresupuestoController controller = new PresupuestoController(new PresupuestosModel(),  VistaMuebleria.VIEW_VISUALIZAR_PRESUPUESTOS,false);
					controller.actualizarPresupuesto();
					JOptionPane.showMessageDialog(null, "El presupuesto fue actualizado con exito");
				}
			});
			btActualizar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			btActualizar.setBackground(new Color(255, 215, 0));
			btActualizar.setFont(new Font("Tahoma", Font.BOLD, 12));
		}
		return btActualizar;
	}
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btCancelar.setBackground(new Color(255, 0, 0));
			btCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
			btCancelar.setBounds(470, 468, 147, 28);
		}
		return btCancelar;
	}
}
