package com.uniovi.muebleria.maven.vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uniovi.muebleria.maven.controlador.producto.ProductoController;
import com.uniovi.muebleria.maven.modelo.Almacen.AlmacenDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoModel;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.Color;

public class VistaAlmacenes extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTitulo;
	private JPanel panelSalir;
	private JButton btnSalir;
	private JLabel lblListaAlmacenes;
	private JPanel panelLista;
	private DefaultListModel<AlmacenDTO> modeloListAlmacen = new DefaultListModel<AlmacenDTO>();
	private JPanel panelMostrarProductos;
	private JPanel panelElegirAlmacen;
	private JPanel panelSeleccion;
	private JComboBox<AlmacenDTO> comboBoxAlmacenes;
	private JButton btnElegirAlmacen;
	private JLabel lblAlmacen;
	private JScrollPane scrollPane;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public VistaAlmacenes() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 742, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelTitulo(), BorderLayout.NORTH);
		contentPane.add(getPanelSalir(), BorderLayout.SOUTH);
		contentPane.add(getPanelLista(), BorderLayout.CENTER);
	}

	private JPanel getPanelTitulo() {
		if (panelTitulo == null) {
			panelTitulo = new JPanel();
			panelTitulo.setBackground(new Color(255, 239, 213));
			panelTitulo.add(getLblListaAlmacenes());
		}
		return panelTitulo;
	}
	private JPanel getPanelSalir() {
		if (panelSalir == null) {
			panelSalir = new JPanel();
			panelSalir.setBackground(new Color(255, 239, 213));
			panelSalir.add(getBtnSalir());
		}
		return panelSalir;
	}
	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					closeWindow();
				}
			});
			btnSalir.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return btnSalir;
	}
	private JLabel getLblListaAlmacenes() {
		if (lblListaAlmacenes == null) {
			lblListaAlmacenes = new JLabel("Listado de Almacenes");
			lblListaAlmacenes.setFont(new Font("Tahoma", Font.BOLD, 17));
		}
		return lblListaAlmacenes;
	}
	
	private void closeWindow() {
		this.dispose();
	}
	public JPanel getPanelLista() {
		if (panelLista == null) {
			panelLista = new JPanel();
			panelLista.setBackground(new Color(255, 239, 213));
			panelLista.setLayout(new CardLayout(0, 0));
			panelLista.add(getPanelMostrarProductos(), "panelProductos");
			panelLista.add(getPanelElegirAlmacen(), "panelElegirAlmacen");
		}
		return panelLista;
	}
	
	public void addModeloAlmacen(AlmacenDTO alm){
		modeloListAlmacen.addElement(alm);
	}

	public void clearModelos() {
		modeloListAlmacen.clear();
	}
	private JPanel getPanelMostrarProductos() {
		if (panelMostrarProductos == null) {
			panelMostrarProductos = new JPanel();
			panelMostrarProductos.setLayout(new BorderLayout(0, 0));
			panelMostrarProductos.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panelMostrarProductos;
	}
	private JPanel getPanelElegirAlmacen() {
		if (panelElegirAlmacen == null) {
			panelElegirAlmacen = new JPanel();
			panelElegirAlmacen.setBackground(new Color(255, 239, 213));
			panelElegirAlmacen.setLayout(new BorderLayout(0, 0));
			panelElegirAlmacen.add(getPanelSeleccion(), BorderLayout.CENTER);
		}
		return panelElegirAlmacen;
	}
	private JPanel getPanelSeleccion() {
		if (panelSeleccion == null) {
			panelSeleccion = new JPanel();
			panelSeleccion.setBackground(new Color(255, 239, 213));
			panelSeleccion.setLayout(null);
			panelSeleccion.add(getComboBoxAlmacenes());
			panelSeleccion.add(getBtnElegirAlmacen());
			panelSeleccion.add(getLblAlmacen());
		}
		return panelSeleccion;
	}
	
	public JComboBox<AlmacenDTO> getComboBoxAlmacenes() {
		if (comboBoxAlmacenes == null) {
			comboBoxAlmacenes = new JComboBox<AlmacenDTO>();
			comboBoxAlmacenes.setBackground(new Color(255, 239, 213));
			comboBoxAlmacenes.setFont(new Font("Tahoma", Font.BOLD, 12));
			comboBoxAlmacenes.setBounds(75, 193, 413, 22);
		}
		return comboBoxAlmacenes;
	}
	private JButton getBtnElegirAlmacen() {
		if (btnElegirAlmacen == null) {
			btnElegirAlmacen = new JButton("Seleccionar");
			btnElegirAlmacen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProductoController controller = new ProductoController(new ProductoModel(),VistaMuebleria.VIEW_ALMACEN);
					AlmacenDTO almacen = (AlmacenDTO) getComboBoxAlmacenes().getSelectedItem();
					controller.añadirAlmacenes(almacen.getIdAlmacen());
					CardLayout c = (CardLayout) getPanelLista().getLayout();
					c.show(getPanelLista(), "panelProductos");	
				}
			});
			btnElegirAlmacen.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnElegirAlmacen.setBounds(532, 194, 104, 23);
		}
		return btnElegirAlmacen;
	}
	private JLabel getLblAlmacen() {
		if (lblAlmacen == null) {
			lblAlmacen = new JLabel("Seleccione el almacen a visualizar");
			lblAlmacen.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblAlmacen.setBounds(75, 82, 281, 47);
		}
		return lblAlmacen;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	public JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setBackground(new Color(255, 239, 213));
			table.setRowSelectionAllowed(false);
			table.setFont(new Font("Tahoma", Font.PLAIN, 15));
			table.setFillsViewportHeight(true);
			table.setEnabled(false);
		}
		return table;
	}
}
