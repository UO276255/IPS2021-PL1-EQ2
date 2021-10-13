package com.uniovi.muebleria.maven.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;

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
	private JList listMontados;
	private JButton btnTransportados;
	private JButton btnMontados;
	
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
			panelProductos.add(getListProductos());
			panelProductos.add(getBtnTransportados());
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
	private JList getListProductos() {
		if (listProductos == null) {
			listProductos = new JList();
			listProductos.setBounds(0, 21, 317, 248);
		}
		return listProductos;
	}
	private JList getListMontados() {
		if (listMontados == null) {
			listMontados = new JList();
			listMontados.setBounds(0, 22, 317, 248);
		}
		return listMontados;
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
}
