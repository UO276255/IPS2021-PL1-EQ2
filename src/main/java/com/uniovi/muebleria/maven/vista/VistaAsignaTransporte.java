package com.uniovi.muebleria.maven.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.uniovi.muebleria.maven.transportista.TransportistaDTO;

import java.util.ArrayList;

import javax.swing.JComboBox;

public class VistaAsignaTransporte extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<TransportistaDTO> transportistas;
	
	private JLabel lblListaTransportistas;
	private JComboBox<?> comboBoxListaTransportistas;
	
	public VistaAsignaTransporte() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 515);
		getContentPane().setLayout(null);
		getContentPane().add(getLblListaTransportistas());
		getContentPane().add(getComboBoxListaTransportistas());
	}
	private JLabel getLblListaTransportistas() {
		if (lblListaTransportistas == null) {
			lblListaTransportistas = new JLabel("Lista de transportistas para su envío: ");
			lblListaTransportistas.setBounds(89, 125, 188, 21);
		}
		return lblListaTransportistas;
	}
	private JComboBox<?> getComboBoxListaTransportistas() {
		if (comboBoxListaTransportistas == null) {
			comboBoxListaTransportistas = new JComboBox();
			comboBoxListaTransportistas.setBounds(89, 146, 443, 22);
		}
		return comboBoxListaTransportistas;
	}
}
