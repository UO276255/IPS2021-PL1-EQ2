package com.uniovi.muebleria.maven.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;


import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VistaAsignaTransporte extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TransportistaDTO transpElegido;
	private JPanel panelPrincipal;
	private JLabel lblListaTransportistas;
	private JComboBox<TransportistaDTO> comboBoxListaTransportistas;
	private JButton btnAceptaTransp;
	
	public VistaAsignaTransporte() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 515);
		getContentPane().setLayout(null);
		getContentPane().add(getPanelPrincipal());
	}
	private JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();
			panelPrincipal.setBounds(0, 0, 743, 476);
			panelPrincipal.setLayout(null);
			panelPrincipal.add(getLblListaTransportistas());
			panelPrincipal.add(getComboBoxListaTransportistas());
			panelPrincipal.add(getBtnAceptaTransp());
		}
		return panelPrincipal;
	}
	private JLabel getLblListaTransportistas() {
		if (lblListaTransportistas == null) {
			lblListaTransportistas = new JLabel("Lista de transportistas para su envío: ");
			lblListaTransportistas.setBounds(88, 120, 240, 21);
		}
		return lblListaTransportistas;
	}
	public JComboBox<TransportistaDTO> getComboBoxListaTransportistas() {
		if (comboBoxListaTransportistas == null) {
			comboBoxListaTransportistas = new JComboBox<TransportistaDTO>();
			comboBoxListaTransportistas.setBounds(87, 141, 443, 22);
		}
		return comboBoxListaTransportistas;
	}
	private JButton getBtnAceptaTransp() {
		if (btnAceptaTransp == null) {
			btnAceptaTransp = new JButton("Aceptar transportista");
			btnAceptaTransp.setBounds(88, 427, 200, 23);
		}
		return btnAceptaTransp;
	}
}
