package com.uniovi.muebleria.maven.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;
import javax.swing.JScrollPane;

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
	private JPanel panelProductos;
	private JPanel panelMontados;
	private JLabel lblProductos;
	private JLabel lblMontados;
	private JList<ProductoDTO> listProductos;
	private JList<ProductoDTO> listMontados;
	private JButton btnTransportados;
	private JButton btnMontados;
	private JScrollPane scrollPane;
	
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
			panelPrncpl.add(getPanelProductos());
			panelPrncpl.add(getPanelMontados());
		}
		return panelPrncpl;
	}
	private JLabel getLblListaTransportistas() {
		if (lblListaTransportistas == null) {
			lblListaTransportistas = new JLabel("Lista de transportistas para su envío: ");
			lblListaTransportistas.setBounds(44, 36, 240, 21);
		}
		return lblListaTransportistas;
	}
	public JComboBox<TransportistaDTO> getComboBoxListaTransportistas() {
		if (comboBoxListaTransportistas == null) {
			comboBoxListaTransportistas = new JComboBox<TransportistaDTO>();
			comboBoxListaTransportistas.setBounds(44, 60, 647, 22);
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
	private JPanel getPanelProductos() {
		if (panelProductos == null) {
			panelProductos = new JPanel();
			panelProductos.setBounds(44, 119, 317, 295);
			panelProductos.setLayout(null);
			panelProductos.add(getLblProductos());
			panelProductos.add(getBtnTransportados());
			panelProductos.add(getScrollPane());
		}
		return panelProductos;
	}
	private JPanel getPanelMontados() {
		if (panelMontados == null) {
			panelMontados = new JPanel();
			panelMontados.setBounds(371, 119, 317, 295);
			panelMontados.setLayout(null);
			panelMontados.add(getLblMontados());
			panelMontados.add(getListMontados());
			panelMontados.add(getBtnMontados());
		}
		return panelMontados;
	}
	private JLabel getLblProductos() {
		if (lblProductos == null) {
			lblProductos = new JLabel("Productos transportados");
			lblProductos.setBounds(10, 0, 307, 20);
		}
		return lblProductos;
	}
	private JLabel getLblMontados() {
		if (lblMontados == null) {
			lblMontados = new JLabel("Productos montados");
			lblMontados.setBounds(10, 0, 307, 21);
		}
		return lblMontados;
	}
	public JList<ProductoDTO> getListProductos() {
		if (listProductos == null) {
			listProductos = new JList<ProductoDTO>();
			listProductos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return listProductos;
	}
	public void setListProductos(DefaultListModel<ProductoDTO> modeloListProductos) {
		listProductos = new JList<ProductoDTO>(modeloListProductos);
	}
	public JList<ProductoDTO> getListMontados() {
		if (listMontados == null) {
			listMontados = new JList<ProductoDTO>();
			listMontados.setBounds(0, 22, 317, 248);
		}
		return listMontados;
	}
	public void setListMontados(DefaultListModel<ProductoDTO> modeloListMontados) {
		listMontados = new JList<ProductoDTO>(modeloListMontados);
	}
	private JButton getBtnTransportados() {
		if (btnTransportados == null) {
			btnTransportados = new JButton("Añadir a productos para montar");
			btnTransportados.setBounds(0, 272, 317, 23);
		}
		return btnTransportados;
	}
	private JButton getBtnMontados() {
		if (btnMontados == null) {
			btnMontados = new JButton("Quitar de productos para montar");
			btnMontados.setBounds(0, 272, 317, 23);
		}
		return btnMontados;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(84, 87, 179, 115);
			scrollPane.setViewportView(getListProductos());
		}
		return scrollPane;
	}
}
