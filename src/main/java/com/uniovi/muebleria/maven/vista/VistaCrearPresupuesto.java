package com.uniovi.muebleria.maven.vista;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.uniovi.muebleria.maven.controlador.producto.ProductoPresupuestoController;
import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoModel;
import com.uniovi.muebleria.maven.modelo.producto.ProductoPresupuestoModel;

public class VistaCrearPresupuesto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCrearPresupuesto;
	private JScrollPane scrollPaneProductos;
	private JScrollPane scrollPanePresupuesto;
	private JButton btnAñadirPresupuesto;
	private JButton btnRetirarPresupuesto;
	private JLabel lblProductos;
	private JLabel lblPresupuesto;
	private JButton btnCreaPresupuesto;
	private JTextField textCoste;
	private JLabel lblCoste;
	private JList<ProductoDTO> listProductos;
	private JList<ProductoDTO> listPresupuesto;
	private DefaultListModel<ProductoDTO> modeloListProductos = new DefaultListModel<ProductoDTO>();
	private DefaultListModel<ProductoDTO> modeloListPresupuesto = new DefaultListModel<ProductoDTO>();

	/**
	 * Create the frame.
	 */
	public VistaCrearPresupuesto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 759, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPanelCrearPresupuesto(), "name_1478865512748300");
	}

	private JPanel getPanelCrearPresupuesto() {
		if (panelCrearPresupuesto == null) {
			panelCrearPresupuesto = new JPanel();
			panelCrearPresupuesto.setLayout(null);
			panelCrearPresupuesto.add(getScrollPaneProductos());
			panelCrearPresupuesto.add(getScrollPanePresupuesto());
			panelCrearPresupuesto.add(getBtnAñadirPresupuesto());
			panelCrearPresupuesto.add(getBtnRetirarPresupuesto());
			panelCrearPresupuesto.add(getLblProductos());
			panelCrearPresupuesto.add(getLblPresupuesto());
			panelCrearPresupuesto.add(getBtnCreaPresupuesto());
			panelCrearPresupuesto.add(getTextCoste());
			panelCrearPresupuesto.add(getLblCoste());
		}
		return panelCrearPresupuesto;
	}
	private JScrollPane getScrollPaneProductos() {
		if (scrollPaneProductos == null) {
			scrollPaneProductos = new JScrollPane();
			scrollPaneProductos.setBounds(30, 88, 322, 273);
			scrollPaneProductos.setViewportView(getListProductos());
		}
		return scrollPaneProductos;
	}
	private JScrollPane getScrollPanePresupuesto() {
		if (scrollPanePresupuesto == null) {
			scrollPanePresupuesto = new JScrollPane();
			scrollPanePresupuesto.setBounds(375, 88, 322, 273);
			scrollPanePresupuesto.setViewportView(getListPresupuesto());
		}
		return scrollPanePresupuesto;
	}
	private JButton getBtnAñadirPresupuesto() {
		if (btnAñadirPresupuesto == null) {
			btnAñadirPresupuesto = new JButton("Añadir");
			btnAñadirPresupuesto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProductoPresupuestoController controller = new ProductoPresupuestoController(new ProductoPresupuestoModel(),  VistaMuebleria.VIEW_PRODPRES);
					for (int i=0;i<getListProductos().getSelectedValuesList().size();i++) {
						modeloListPresupuesto.addElement(getListProductos().getSelectedValuesList().get(i));
						sumarPrecio(controller.getPrecioProducto(getListProductos().getSelectedValuesList().get(i).getId()));
					}
				}
			});
			btnAñadirPresupuesto.setBounds(30, 372, 322, 23);
		}
		return btnAñadirPresupuesto;
	}
	private JButton getBtnRetirarPresupuesto() {
		if (btnRetirarPresupuesto == null) {
			btnRetirarPresupuesto = new JButton("Retirar");
			btnRetirarPresupuesto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int i=0;i<getListPresupuesto().getSelectedValuesList().size();i++) {
						modeloListPresupuesto.removeElement(getListPresupuesto().getSelectedValuesList().get(i));
					}
				}
			});
			btnRetirarPresupuesto.setBounds(375, 372, 322, 23);
		}
		return btnRetirarPresupuesto;
	}
	private JLabel getLblProductos() {
		if (lblProductos == null) {
			lblProductos = new JLabel("Productos disponibles: ");
			lblProductos.setBounds(30, 63, 322, 14);
		}
		return lblProductos;
	}
	private JLabel getLblPresupuesto() {
		if (lblPresupuesto == null) {
			lblPresupuesto = new JLabel("Productos del presupuesto:");
			lblPresupuesto.setBounds(375, 63, 322, 14);
		}
		return lblPresupuesto;
	}
	private JButton getBtnCreaPresupuesto() {
		if (btnCreaPresupuesto == null) {
			btnCreaPresupuesto = new JButton("Crear presupuesto");
			btnCreaPresupuesto.setBounds(276, 432, 173, 23);
		}
		return btnCreaPresupuesto;
	}
	private JTextField getTextCoste() {
		if (textCoste == null) {
			textCoste = new JTextField();
			textCoste.setText("0");
			textCoste.setEditable(false);
			textCoste.setColumns(10);
			textCoste.setBounds(649, 433, 48, 20);
		}
		return textCoste;
	}
	private JLabel getLblCoste() {
		if (lblCoste == null) {
			lblCoste = new JLabel("Coste presupuesto:");
			lblCoste.setBounds(533, 436, 136, 14);
		}
		return lblCoste;
	}
	private JList<ProductoDTO> getListProductos() {
		if (listProductos == null) {
			modeloListProductos = new DefaultListModel<ProductoDTO>();
			listProductos = new JList<ProductoDTO>(modeloListProductos);
		}
		return listProductos;
	}
	private JList<ProductoDTO> getListPresupuesto() {
		if (listPresupuesto == null) {
			modeloListPresupuesto = new DefaultListModel<ProductoDTO>();
			listPresupuesto = new JList<ProductoDTO>(modeloListPresupuesto);
		}
		return listPresupuesto;
	}
	public void addModeloListProductos(ProductoDTO prod){
		modeloListProductos.addElement(prod);
	}
	
	public void sumarPrecio(int prod) {
		int actual = Integer.parseInt(textCoste.getText());
		textCoste.setText("" + (actual + prod));
	}
}
