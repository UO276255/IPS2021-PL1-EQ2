package com.uniovi.muebleria.maven.vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uniovi.muebleria.maven.modelo.producto.PresupuestoDTO;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.CardLayout;

public class VistaAsignarPresupuesto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelInicial;
	private JButton btnAsignarExistente;
	private JButton btnAsignarNuevo;
	private JComboBox<PresupuestoDTO> comboBoxPresupuestos;
	private JPanel panelNuevoCliente;
	private JPanel panelClienteExistente;

	/**
	 * Create the frame.
	 */
	public VistaAsignarPresupuesto() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.add(getPanel());
	}
	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new CardLayout(0, 0));
			panel.add(getPanelInicial(), "name_8415916233899");
			panel.add(getPanelNuevoCliente(), "name_8615751187300");
			panel.add(getPanelClienteExistente(), "name_8628403314900");
		}
		return panel;
	}
	public JPanel getPanelInicial() {
		if (panelInicial == null) {
			panelInicial = new JPanel();
			panelInicial.setLayout(null);
			panelInicial.add(getBtnAsignarExistente());
			panelInicial.add(getBtnAsignarNuevo());
			panelInicial.add(getComboBoxPresupuestos());
		}
		return panelInicial;
	}
	public JButton getBtnAsignarExistente() {
		if (btnAsignarExistente == null) {
			btnAsignarExistente = new JButton("Asignar a un cliente existente");
			btnAsignarExistente.setBounds(400, 181, 175, 34);
		}
		return btnAsignarExistente;
	}
	public JButton getBtnAsignarNuevo() {
		if (btnAsignarNuevo == null) {
			btnAsignarNuevo = new JButton("Asignar a un cliente nuevo");
			btnAsignarNuevo.setBounds(40, 181, 175, 34);
		}
		return btnAsignarNuevo;
	}
	public JComboBox<PresupuestoDTO> getComboBoxPresupuestos() {
		if (comboBoxPresupuestos == null) {
			comboBoxPresupuestos = new JComboBox();
			comboBoxPresupuestos.setBounds(40, 90, 535, 22);
		}
		return comboBoxPresupuestos;
	}
	private JPanel getPanelNuevoCliente() {
		if (panelNuevoCliente == null) {
			panelNuevoCliente = new JPanel();
		}
		return panelNuevoCliente;
	}
	private JPanel getPanelClienteExistente() {
		if (panelClienteExistente == null) {
			panelClienteExistente = new JPanel();
		}
		return panelClienteExistente;
	}
}
