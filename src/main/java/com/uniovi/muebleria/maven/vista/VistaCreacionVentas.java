package com.uniovi.muebleria.maven.vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uniovi.muebleria.maven.controlador.Presupuestos.PresupuestoController;
import com.uniovi.muebleria.maven.controlador.Venta.VentaController;
import com.uniovi.muebleria.maven.modelo.Cliente.ClienteDTO;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestoVentaDTO;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestosModel;
import com.uniovi.muebleria.maven.modelo.ventas.VentaModel;

import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VistaCreacionVentas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelGeneral;
	private JPanel panelInicio;
	private JLabel lblNTituloInicio;
	private JComboBox<PresupuestoVentaDTO> comboBoxPresupuestoSinAceptar;
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
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.add(getPanelGeneral());
	}

	private JPanel getPanelGeneral() {
		if (panelGeneral == null) {
			panelGeneral = new JPanel();
			panelGeneral.setBackground(new Color(255, 239, 213));
			panelGeneral.setLayout(new CardLayout(0, 0));
			panelGeneral.add(getPanelInicio(), "PanelInicio");
		}
		return panelGeneral;
	}
	private JPanel getPanelInicio() {
		if (panelInicio == null) {
			panelInicio = new JPanel();
			panelInicio.setBackground(new Color(255, 239, 213));
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
	public JComboBox<PresupuestoVentaDTO> getComboBoxPresupuestoSinAceptar() {
		if (comboBoxPresupuestoSinAceptar == null) {
			comboBoxPresupuestoSinAceptar = new JComboBox<PresupuestoVentaDTO>();
			comboBoxPresupuestoSinAceptar.setBounds(56, 134, 459, 22);
		}
		return comboBoxPresupuestoSinAceptar;
	}
	
	public JButton getBtnCrearVenta() {
		if (btnCrearVenta == null) {
			btnCrearVenta = new JButton("Crear Venta");
			btnCrearVenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentaController controller = new VentaController(new VentaModel(), VistaMuebleria.VIEW_VENTAS);
					PresupuestoController controllerP = new PresupuestoController(new PresupuestosModel(),  VistaMuebleria.VIEW_VENTAS);
					PresupuestoVentaDTO dto = (PresupuestoVentaDTO) getComboBoxPresupuestoSinAceptar().getSelectedItem();
					controllerP.removePresupuesto(dto.getIdPresupuesto());
					controller.cearVenta((new Date(System.currentTimeMillis())),dto.getPrecio(),dto.getIdPresupuesto());
					
					JOptionPane.showMessageDialog(null,"La venta se ha creado con exito");
					closeWindow();
				}
			});
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
