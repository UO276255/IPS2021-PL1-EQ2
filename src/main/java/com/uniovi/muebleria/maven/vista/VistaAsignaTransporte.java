package com.uniovi.muebleria.maven.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaAsignaTransporte extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<TransportistaDTO> lstTransportistas = new ArrayList<TransportistaDTO>();
	private TransportistaDTO transpElegido;
	
	private JLabel lblListaTransportistas;
	private JComboBox<String> comboBoxListaTransportistas;
	private JButton btnAceptaTransp;
	
	public VistaAsignaTransporte() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 515);
		getContentPane().setLayout(null);
		getContentPane().add(getLblListaTransportistas());
		getContentPane().add(getComboBoxListaTransportistas());
		getContentPane().add(getBtnAceptaTransp());
	}
	private JLabel getLblListaTransportistas() {
		if (lblListaTransportistas == null) {
			lblListaTransportistas = new JLabel("Lista de transportistas para su envío: ");
			lblListaTransportistas.setBounds(89, 125, 240, 21);
		}
		return lblListaTransportistas;
	}
	private JComboBox<String> getComboBoxListaTransportistas() {
		if (comboBoxListaTransportistas == null) {
			comboBoxListaTransportistas = new JComboBox<String>();
			comboBoxListaTransportistas.setBounds(89, 146, 443, 22);
			for (TransportistaDTO tran : lstTransportistas) {
				comboBoxListaTransportistas.addItem(tran.getNombre() + "," + tran.getNumTelefono() + "," + tran.getHorarioIn() + "," + tran.getHorarioIn());
			}
		}
		return comboBoxListaTransportistas;
	}
	private JButton getBtnAceptaTransp() {
		if (btnAceptaTransp == null) {
			btnAceptaTransp = new JButton("Aceptar transportista");
			btnAceptaTransp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String[] tr;
					tr = getComboBoxListaTransportistas().getSelectedItem().toString().split(",");
					for (TransportistaDTO tran : lstTransportistas) {
						if(tran.getNumTelefono() == Integer.parseInt(tr[1])) {
							tran = transpElegido;
						}
					}
				}
			});
			btnAceptaTransp.setBounds(89, 399, 200, 23);
		}
		return btnAceptaTransp;
	}
}
