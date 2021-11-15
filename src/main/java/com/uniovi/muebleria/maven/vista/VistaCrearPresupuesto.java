package com.uniovi.muebleria.maven.vista;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.uniovi.muebleria.maven.controlador.producto.ProductoPresupuestoController;
import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoPresupuestoModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;

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
	private JPanel panelFiltrar;
	private JButton btnFiltrar;
	private JSpinner spinnerPrecio;
	private JLabel lblSeleccionarPrecio;
	private JRadioButton rdbtnPorEncimaDelPrecio;
	private JRadioButton rdbtnPorDebajoDelPrecio;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblCategoria;
	private JComboBox<String> comboBoxCategorias;
	private JButton btnCancelar;
	private JButton btnFiltrarProductos;
	private ArrayList<Integer> uds;
	private JFormattedTextField txUnidades;
	private DefaultListModel<String> listUds = new DefaultListModel<String>();
	private JLabel lbUds;

	
	/**
	 * Create the frame.
	 */
	public VistaCrearPresupuesto() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				inicializar();
				modeloListProductos.clear();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 911, 535);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPanelCrearPresupuesto(), "PanelPresupuestos");
		contentPane.add(getPanelFiltrar(), "PanelFiltrar");
		uds = new ArrayList<Integer>();
	}

	private JPanel getPanelCrearPresupuesto() {
		if (panelCrearPresupuesto == null) {
			panelCrearPresupuesto = new JPanel();
			panelCrearPresupuesto.setBackground(new Color(255, 239, 213));
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
			panelCrearPresupuesto.add(getBtnFiltrar());
			
			JLabel lbUnidades = new JLabel("UNIDADES");
			lbUnidades.setHorizontalAlignment(SwingConstants.CENTER);
			lbUnidades.setFont(new Font("Tahoma", Font.BOLD, 14));
			lbUnidades.setBounds(378, 153, 89, 34);
			panelCrearPresupuesto.add(lbUnidades);
			panelCrearPresupuesto.add(getTxUnidades());
	//		panelCrearPresupuesto.add(getListUds());
			panelCrearPresupuesto.add(getLbUds());
			
			
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
			scrollPanePresupuesto.setBounds(485, 88, 322, 273);
			scrollPanePresupuesto.setViewportView(getListPresupuesto());
		}
		return scrollPanePresupuesto;
	}
	private JButton getBtnAñadirPresupuesto() {
		if (btnAñadirPresupuesto == null) {
			btnAñadirPresupuesto = new JButton("Añadir");
			btnAñadirPresupuesto.setBackground(new Color(173, 255, 47));
			btnAñadirPresupuesto.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnAñadirPresupuesto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProductoPresupuestoController controller = new ProductoPresupuestoController(new ProductoPresupuestoModel(),  VistaMuebleria.VIEW_PRODPRES);
					for (int i=0;i<getListProductos().getSelectedValuesList().size();i++) {
						modeloListPresupuesto.addElement(getListProductos().getSelectedValuesList().get(i));
						uds.add(Integer.valueOf(getTxUnidades().getText()));
						listUds.addElement(Integer.valueOf(getTxUnidades().getText()) + "uds");
						for (int j=0; j<Integer.valueOf(getTxUnidades().getText());j++) {
							sumarPrecio(controller.getPrecioProducto(getListProductos().getSelectedValuesList().get(i).getId()));
						}
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
			btnRetirarPresupuesto.setBackground(new Color(250, 128, 114));
			btnRetirarPresupuesto.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnRetirarPresupuesto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProductoPresupuestoController controller = new ProductoPresupuestoController(new ProductoPresupuestoModel(),  VistaMuebleria.VIEW_PRODPRES);
					for (int i=0;i<getListPresupuesto().getSelectedValuesList().size();i++) {
						if (uds.get(i) <= Integer.valueOf(getTxUnidades().getText())) {
							modeloListPresupuesto.removeElement(getListPresupuesto().getSelectedValuesList().get(i));
							uds.remove(Integer.valueOf(getTxUnidades().getText()));
						}
						else {
							uds.set(i, uds.get(i) - Integer.valueOf(getTxUnidades().getText()));
						}
						
						for (int j=0; j<Integer.valueOf(getTxUnidades().getText());j++) {
							restarPrecio(controller.getPrecioProducto(getListProductos().getSelectedValuesList().get(i).getId()));
						}
					}
				}
			});
			btnRetirarPresupuesto.setBounds(485, 372, 322, 23);
		}
		return btnRetirarPresupuesto;
	}
	private JLabel getLblProductos() {
		if (lblProductos == null) {
			lblProductos = new JLabel("Productos disponibles: ");
			lblProductos.setBounds(30, 63, 199, 14);
		}
		return lblProductos;
	}
	private JLabel getLblPresupuesto() {
		if (lblPresupuesto == null) {
			lblPresupuesto = new JLabel("Productos del presupuesto:");
			lblPresupuesto.setBounds(485, 63, 322, 14);
		}
		return lblPresupuesto;
	}
	private JButton getBtnCreaPresupuesto() {
		if (btnCreaPresupuesto == null) {
			btnCreaPresupuesto = new JButton("Crear presupuesto");
			btnCreaPresupuesto.setBackground(Color.GREEN);
			btnCreaPresupuesto.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnCreaPresupuesto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProductoPresupuestoController controller = new ProductoPresupuestoController(new ProductoPresupuestoModel(),  VistaMuebleria.VIEW_PRODPRES);
					controller.crearPresupuesto(Integer.parseInt(textCoste.getText()));
					ArrayList<ProductoDTO> productos = new ArrayList<ProductoDTO>();
					for(int i=0; i<modeloListPresupuesto.getSize(); i++) {
						productos.add(modeloListPresupuesto.getElementAt(i));
					}
					controller.crearSolicitudes(controller.getIdPres(), toArray(productos));
					JOptionPane.showMessageDialog(null, "Se ha creado el presupuesto de id: " + controller.getIdPres());
					inicializar();
				}
			});
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
			listProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listProductos;
	}
	private JList<ProductoDTO> getListPresupuesto() {
		if (listPresupuesto == null) {
			modeloListPresupuesto = new DefaultListModel<ProductoDTO>();
			listPresupuesto = new JList<ProductoDTO>(modeloListPresupuesto);
			listPresupuesto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
	
	public void restarPrecio(int prod) {
		int actual = Integer.parseInt(textCoste.getText());
		textCoste.setText("" + (actual - prod));
	}
	
	private ProductoDTO[] toArray(List<ProductoDTO> listProductos) {
		ProductoDTO[] arrayProductos = new ProductoDTO[listProductos.size()];
		for(int i=0;i<listProductos.size();i++) {
			arrayProductos[i] = listProductos.get(i);
		}
		return arrayProductos;
	}
	
	private void inicializar() {
		modeloListPresupuesto.clear();
		getTextCoste().setText("0");
	}
	private JPanel getPanelFiltrar() {
		if (panelFiltrar == null) {
			panelFiltrar = new JPanel();
			panelFiltrar.setBackground(new Color(255, 239, 213));
			panelFiltrar.setLayout(null);
			panelFiltrar.add(getSpinnerPrecio());
			panelFiltrar.add(getLblSeleccionarPrecio());
			panelFiltrar.add(getRdbtnPorEncimaDelPrecio());
			panelFiltrar.add(getRdbtnPorDebajoDelPrecio());
			panelFiltrar.add(getLblCategoria());
			panelFiltrar.add(getComboBoxCategorias());
			panelFiltrar.add(getBtnCancelar());
			panelFiltrar.add(getBtnFiltrarProductos());
		}
		return panelFiltrar;
	}
	private JButton getBtnFiltrar() {
		if (btnFiltrar == null) {
			btnFiltrar = new JButton("Filtrar");
			btnFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CardLayout c = (CardLayout) getContentPane().getLayout();
					c.show(getContentPane(), "PanelFiltrar");
					rellenarComboBox();
				}
			});
			btnFiltrar.setBounds(263, 59, 89, 23);
		}
		return btnFiltrar;
	}
	@SuppressWarnings("deprecation")
	private JSpinner getSpinnerPrecio() {
		if (spinnerPrecio == null) {
			spinnerPrecio = new JSpinner();
			spinnerPrecio.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(50)));
			spinnerPrecio.setBounds(163, 115, 55, 20);
		}
		return spinnerPrecio;
	}
	private JLabel getLblSeleccionarPrecio() {
		if (lblSeleccionarPrecio == null) {
			lblSeleccionarPrecio = new JLabel("Selecciona un precio: ");
			lblSeleccionarPrecio.setBounds(38, 118, 153, 14);
		}
		return lblSeleccionarPrecio;
	}
	private JRadioButton getRdbtnPorEncimaDelPrecio() {
		if (rdbtnPorEncimaDelPrecio == null) {
			rdbtnPorEncimaDelPrecio = new JRadioButton("Productos con precio mayor ");
			rdbtnPorEncimaDelPrecio.setBackground(new Color(255, 239, 213));
			rdbtnPorEncimaDelPrecio.setSelected(true);
			buttonGroup.add(rdbtnPorEncimaDelPrecio);
			rdbtnPorEncimaDelPrecio.setBounds(38, 139, 198, 23);
		}
		return rdbtnPorEncimaDelPrecio;
	}
	private JRadioButton getRdbtnPorDebajoDelPrecio() {
		if (rdbtnPorDebajoDelPrecio == null) {
			rdbtnPorDebajoDelPrecio = new JRadioButton("Productos con precio menor");
			rdbtnPorDebajoDelPrecio.setBackground(new Color(255, 239, 213));
			buttonGroup.add(rdbtnPorDebajoDelPrecio);
			rdbtnPorDebajoDelPrecio.setBounds(38, 165, 198, 23);
		}
		return rdbtnPorDebajoDelPrecio;
	}
	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("Selecciona una categoría:");
			lblCategoria.setBounds(38, 263, 198, 14);
		}
		return lblCategoria;
	}
	private JComboBox<String> getComboBoxCategorias() {
		if (comboBoxCategorias == null) {
			comboBoxCategorias = new JComboBox<String>();
			comboBoxCategorias.setBounds(38, 288, 245, 22);
		}
		return comboBoxCategorias;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CardLayout c = (CardLayout) getContentPane().getLayout();
					c.show(getContentPane(), "PanelPresupuestos");
				}
			});
			btnCancelar.setBounds(246, 380, 89, 23);
		}
		return btnCancelar;
	}
	private JButton getBtnFiltrarProductos() {
		if (btnFiltrarProductos == null) {
			btnFiltrarProductos = new JButton("Filtrar");
			btnFiltrarProductos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modeloListProductos.clear();
					ProductoPresupuestoController controller = new ProductoPresupuestoController(new ProductoPresupuestoModel(),  VistaMuebleria.VIEW_PRODPRES);
					boolean productosPrecioSuperior;
					if(getRdbtnPorEncimaDelPrecio().isSelected()) {
						productosPrecioSuperior = true;
					}else {
						productosPrecioSuperior = false;
					}
					controller.setListProductosFiltrados((Integer) getSpinnerPrecio().getValue(), (String) getComboBoxCategorias().getSelectedItem(), productosPrecioSuperior);
					CardLayout c = (CardLayout) getContentPane().getLayout();
					c.show(getContentPane(), "PanelPresupuestos");
				}
			});
			btnFiltrarProductos.setBounds(373, 380, 89, 23);
		}
		return btnFiltrarProductos;
	}
	
	private void rellenarComboBox() {
		String[] categorias = new String[5];
		categorias[0] = "Todas";
		categorias[1] = "silla";
		categorias[2] = "sofa";
		categorias[3] = "mesa";
		categorias[4] = "estanteria";
		getComboBoxCategorias().setModel(new DefaultComboBoxModel<String>(categorias));
	}
	private JFormattedTextField getTxUnidades() {
		if (txUnidades == null) {
			txUnidades = new JFormattedTextField(new Integer(0));
			txUnidades.setFont(new Font("Tahoma", Font.BOLD, 14));
			txUnidades.setHorizontalAlignment(SwingConstants.CENTER);
			txUnidades.setBackground(new Color(250, 235, 215));
			txUnidades.setBounds(378, 189, 89, 29);
		}
		return txUnidades;
	}
	private DefaultListModel<String> getListUds() {

		return listUds;
	}
	private JLabel getLbUds() {
		if (lbUds == null) {
			lbUds = new JLabel("Uds:");
			lbUds.setBounds(819, 63, 46, 14);
		}
		return lbUds;
	}
}
