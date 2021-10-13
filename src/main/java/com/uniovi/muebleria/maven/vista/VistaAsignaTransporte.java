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
	private JPanel panelPrncpl;
	private JLabel lblListaTransportistas;
	private JComboBox<TransportistaDTO> comboBoxListaTransportistas;
	private JButton btnAceptaTransp;
	
	public VistaAsignaTransporte() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 515);
		getContentPane().setLayout(null);
		getContentPane().add(getPanelPrncpl());
	}
	private JPanel getPanelPrncpl() {
		if (panelPrncpl == null) {
			panelPrncpl = new JPanel();
			panelPrncpl.setBounds(0, 0, 745, 478);
			panelPrncpl.setLayout(null);
			panelPrncpl.add(getLblListaTransportistas());
			panelPrncpl.add(getComboBoxListaTransportistas());
			panelPrncpl.add(getBtnAceptaTransp());
		}
		return panelPrncpl;
	}
	private JLabel getLblListaTransportistas() {
		if (lblListaTransportistas == null) {
			lblListaTransportistas = new JLabel("Lista de transportistas para su envío: ");
			lblListaTransportistas.setBounds(44, 116, 240, 21);
		}
		return lblListaTransportistas;
	}
	public JComboBox<TransportistaDTO> getComboBoxListaTransportistas() {
		if (comboBoxListaTransportistas == null) {
			comboBoxListaTransportistas = new JComboBox<TransportistaDTO>();
			comboBoxListaTransportistas.setBounds(44, 140, 647, 22);
		}
		return comboBoxListaTransportistas;
	}
	private JButton getBtnAceptaTransp() {
		if (btnAceptaTransp == null) {
			btnAceptaTransp = new JButton("Aceptar transportista");
			btnAceptaTransp.setBounds(44, 445, 200, 23);
		}
		return btnAceptaTransp;
	}
}
