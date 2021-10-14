package com.uniovi.muebleria.maven.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestoDTO;

import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaCreacionVentas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelGeneral;
	private JPanel panelInicio;
	private JLabel lblNTituloInicio;
	private JComboBox<String> comboBoxPresupuestoSinAceptar;
	private JButton btnCrearVenta;
	private JButton btnCancelarVenta;
	/**
	 * Create the frame.
	 */
	public VistaCreacionVentas() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.add(getPanelGeneral());
	}

	private JPanel getPanelGeneral() {
		if (panelGeneral == null) {
			panelGeneral = new JPanel();
			panelGeneral.setLayout(new CardLayout(0, 0));
			panelGeneral.add(getPanelInicio(), "name_22560730068600");
		}
		return panelGeneral;
	}
	private JPanel getPanelInicio() {
		if (panelInicio == null) {
			panelInicio = new JPanel();
			panelInicio.setLayout(null);
			panelInicio.add(getLblNTituloInicio());
			panelInicio.add(getComboBoxPresupuestoSinAceptar());
			panelInicio.add(getBtnCrearVenta());
			panelInicio.add(getBtnCancelarVenta());
		}
		return panelInicio;
	}
	private JLabel getLblNTituloInicio() {
		if (lblNTituloInicio == null) {
			lblNTituloInicio = new JLabel("Seleccione un presupuesto para aceptarlo");
			lblNTituloInicio.setBounds(91, 11, 424, 25);
			lblNTituloInicio.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		return lblNTituloInicio;
	}
	public JComboBox<String> getComboBoxPresupuestoSinAceptar() {
		if (comboBoxPresupuestoSinAceptar == null) {
			comboBoxPresupuestoSinAceptar = new JComboBox<String>();
			comboBoxPresupuestoSinAceptar.setBounds(56, 134, 459, 22);
		}
		return comboBoxPresupuestoSinAceptar;
	}
	private JButton getBtnCrearVenta() {
		if (btnCrearVenta == null) {
			btnCrearVenta = new JButton("Crear Venta");
			btnCrearVenta.setBounds(544, 134, 111, 23);
		}
		return btnCrearVenta;
	}
	private JButton getBtnCancelarVenta() {
		if (btnCancelarVenta == null) {
			btnCancelarVenta = new JButton("Cancelar");
			btnCancelarVenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					closeWindow();
				}
			});
			btnCancelarVenta.setBounds(544, 300, 111, 23);
		}
		return btnCancelarVenta;
	}
	
	private void closeWindow() {
		this.dispose();
	}
}
