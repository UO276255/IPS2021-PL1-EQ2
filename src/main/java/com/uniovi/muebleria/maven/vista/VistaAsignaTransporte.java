package com.uniovi.muebleria.maven.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import com.uniovi.muebleria.maven.controlador.producto.ProductoController;
import com.uniovi.muebleria.maven.controlador.transportista.TransportistaController;
import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoModel;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaDTO;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaModel;
import com.uniovi.muebleria.maven.modelo.ventas.VentaDTO;
import java.awt.Cursor;
import java.awt.Color;

public class VistaAsignaTransporte extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TransportistaDTO transpElegido;
	private JPanel panelGeneral;
	private DefaultListModel<ProductoDTO> modeloListRecoger = new DefaultListModel<ProductoDTO>();
	private DefaultListModel<ProductoDTO> modeloListTransp = new DefaultListModel<ProductoDTO>();
	private DefaultListModel<ProductoDTO> modeloListMontar = new DefaultListModel<ProductoDTO>();
	private DefaultListModel<ProductoDTO> modeloListNoMontar = new DefaultListModel<ProductoDTO>();
	private JPanel panelInicial;
	private JPanel panelTransportados;
	private JComboBox<TransportistaDTO> comboBoxListaTransportistas;
	private JButton btnAceptaTransp;
	private JLabel lblRecoger;
	private JButton btnParaTransportar;
	private JScrollPane scrollPaneRecoger;
	private JLabel lblTransportados;
	private JButton btnParaRecoger;
	private JScrollPane scrollPaneTransportar;
	private JPanel panelRecoger;
	private JPanel panelTransporte;
	private JLabel lblLista;
	private JList<ProductoDTO> listRecoger;
	private JList<ProductoDTO> listTransportar;
	private JPanel panelMontados;
	private JPanel panelNoMontados;
	private JPanel panelMontar;
	private JLabel lblProdMontar;
	private JLabel lblNoMontar;
	private JLabel lblMontar;
	private JButton btnParaMontar;
	private JButton btnParaNoMontar;
	private JList<ProductoDTO> listNoMontar;
	private JList<ProductoDTO> listMontar;
	private JScrollPane scrollPaneNoMontar;
	private JScrollPane scrollPaneMontar;
	private JButton btnCancelar;
	private JButton btnFinalizar;
	private JButton btnMenu;
	private JButton btnBuscar;
	private JLabel lblVenta;
	private JComboBox<VentaDTO> comboBoxListaVentas;
	
	public VistaAsignaTransporte() {
		getContentPane().setBackground(new Color(255, 239, 213));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				inicializar();
				CardLayout c = (CardLayout) getPanelGeneral().getLayout();
				c.show(getPanelGeneral(), "PanelTransporte");
			}
		});
		setTitle("Muebleria");
		inicializar();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 759, 515);
		getContentPane().setLayout(null);
		getContentPane().add(getPanelGeneral());
	}
	private JPanel getPanelGeneral() {
		if (panelGeneral == null) {
			panelGeneral = new JPanel();
			panelGeneral.setBackground(new Color(255, 239, 213));
			panelGeneral.setBounds(0, 0, 745, 478);
			panelGeneral.setLayout(new CardLayout(0, 0));
			panelGeneral.add(getPanelTransportados(), "PanelTransporte");
			panelGeneral.add(getPanelMontados(), "PanelMontados");
		}
		return panelGeneral;
	}
	private JPanel getPanelTransportados() {
		if (panelTransportados == null) {
			panelTransportados = new JPanel();
			panelTransportados.setBackground(new Color(255, 239, 213));
			panelTransportados.setLayout(null);
			panelTransportados.add(getComboBoxListaTransportistas());
			panelTransportados.add(getBtnAceptaTransp());
			panelTransportados.add(getPanelRecoger());
			panelTransportados.add(getPanelTransporte());
			panelTransportados.add(getLblLista());
			panelTransportados.add(getBtnMenu());
			panelTransportados.add(getBtnBuscar());
			panelTransportados.add(getLblVenta());
			panelTransportados.add(getComboBoxListaVentas());
		}
		return panelTransportados;
	}
	public JComboBox<TransportistaDTO> getComboBoxListaTransportistas() {
		if (comboBoxListaTransportistas == null) {
			comboBoxListaTransportistas = new JComboBox<TransportistaDTO>();
			comboBoxListaTransportistas.setBounds(20, 91, 703, 22);
		}
		return comboBoxListaTransportistas;
	}
	private JButton getBtnAceptaTransp() {
		if (btnAceptaTransp == null) {
			btnAceptaTransp = new JButton("Aceptar transportista");
			btnAceptaTransp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
					ProductoController controller = new ProductoController(new ProductoModel(),  VistaMuebleria.VIEW_TRANSPORTE);
					ProductoDTO[] productos = controller.getListaProductosVentaNoMontar(((VentaDTO) getComboBoxListaVentas().getSelectedItem()).getId_venta());
					for (int i=0;i<productos.length;i++) {
						addModeloListProdNoMontar(productos[i]);						
					}
					ProductoDTO[] productosMon = controller.getListaProductosVentaMontar(((VentaDTO) getComboBoxListaVentas().getSelectedItem()).getId_venta());
					for (int i=0;i<productosMon.length;i++) {
						addModeloListProdMontar(productosMon[i]);						
					}
					setTransportista((TransportistaDTO) getComboBoxListaTransportistas().getSelectedItem());
					CardLayout c = (CardLayout) getPanelGeneral().getLayout();
					c.show(getPanelGeneral(), "PanelMontados");
				}
			});
			btnAceptaTransp.setBounds(283,444, 180, 23);
		}
		return btnAceptaTransp;
	}
	private JLabel getLblRecoger() {
		if (lblRecoger == null) {
			lblRecoger = new JLabel("Productos para recoger");
		}
		return lblRecoger;
	}
	private JButton getBtnParaTransportar() {
		if (btnParaTransportar == null) {
			btnParaTransportar = new JButton("Añadir a productos para transportar");
			btnParaTransportar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProductoController controller = new ProductoController(new ProductoModel(),  VistaMuebleria.VIEW_TRANSPORTE);
					controller.actualizarTransporte(1, getListRecoger().getSelectedValue().getId(), ((VentaDTO) getComboBoxListaVentas().getSelectedItem()).getId_venta());
					for (int i=0;i<getListRecoger().getSelectedValuesList().size();i++) {
						modeloListTransp.addElement(getListRecoger().getSelectedValuesList().get(i));
						modeloListRecoger.removeElement(getListRecoger().getSelectedValuesList().get(i));
					}
				}
			});
		}
		return btnParaTransportar;
	}
	private JScrollPane getScrollPaneRecoger() {
		if (scrollPaneRecoger == null) {
			scrollPaneRecoger = new JScrollPane();
			scrollPaneRecoger.setViewportView(getListRecoger());
		}
		return scrollPaneRecoger;
	}
	private JLabel getLblTransportados() {
		if (lblTransportados == null) {
			lblTransportados = new JLabel("Productos para transportar");
		}
		return lblTransportados;
	}
	private JButton getBtnParaRecoger() {
		if (btnParaRecoger == null) {
			btnParaRecoger = new JButton("Quitar de productos para recoger");
			btnParaRecoger.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProductoController controller = new ProductoController(new ProductoModel(),  VistaMuebleria.VIEW_TRANSPORTE);
					controller.actualizarTransporte(0, getListTransportar().getSelectedValue().getId(), ((VentaDTO) getComboBoxListaVentas().getSelectedItem()).getId_venta());
					for (int i=0;i<getListTransportar().getSelectedValuesList().size();i++) {
						modeloListRecoger.addElement(getListTransportar().getSelectedValuesList().get(i));
						modeloListTransp.removeElement(getListTransportar().getSelectedValuesList().get(i));
					}
				}
			});
		}
		return btnParaRecoger;
	}
	private JScrollPane getScrollPaneTransportar() {
		if (scrollPaneTransportar == null) {
			scrollPaneTransportar = new JScrollPane();
			scrollPaneTransportar.setViewportView(getListTransportar());
		}
		return scrollPaneTransportar;
	}
	private JPanel getPanelRecoger() {
		if (panelRecoger == null) {
			panelRecoger = new JPanel();
			panelRecoger.setBounds(20, 124, 344, 274);
			panelRecoger.setLayout(new BorderLayout(0, 0));
			panelRecoger.add(getLblRecoger(), BorderLayout.NORTH);
			panelRecoger.add(getBtnParaTransportar(), BorderLayout.SOUTH);
			panelRecoger.add(getScrollPaneRecoger(), BorderLayout.CENTER);
		}
		return panelRecoger;
	}
	private JPanel getPanelTransporte() {
		if (panelTransporte == null) {
			panelTransporte = new JPanel();
			panelTransporte.setBounds(379, 124, 344, 274);
			panelTransporte.setLayout(new BorderLayout(0, 0));
			panelTransporte.add(getLblTransportados(), BorderLayout.NORTH);
			panelTransporte.add(getBtnParaRecoger(), BorderLayout.SOUTH);
			panelTransporte.add(getScrollPaneTransportar(), BorderLayout.CENTER);
		}
		return panelTransporte;
	}
	private JLabel getLblLista() {
		if (lblLista == null) {
			lblLista = new JLabel("Lista de transportistas disponibles:");
			lblLista.setBounds(20, 78, 344, 14);
		}
		return lblLista;
	}
	private JList<ProductoDTO> getListRecoger() {
		if (listRecoger == null) {
			modeloListRecoger = new DefaultListModel<ProductoDTO>();
			listRecoger = new JList<ProductoDTO>(modeloListRecoger);
		}
		return listRecoger;
	}
	private JList<ProductoDTO> getListTransportar() {
		if (listTransportar == null) {
			modeloListTransp = new DefaultListModel<ProductoDTO>();
			listTransportar = new JList<ProductoDTO>(modeloListTransp);
		}
		return listTransportar;
	}
	public void addModeloListProdNoTransp(ProductoDTO prod){
		modeloListRecoger.addElement(prod);
	}
	public void addModeloListProdTransp(ProductoDTO prod){
		modeloListTransp.addElement(prod);
	}
	private JPanel getPanelMontados() {
		if (panelMontados == null) {
			panelMontados = new JPanel();
			panelMontados.setLayout(null);
			panelMontados.add(getPanelNoMontados());
			panelMontados.add(getPanelMontar());
			panelMontados.add(getLblProdMontar());
			panelMontados.add(getBtnCancelar());
			panelMontados.add(getBtnFinalizar());
		}
		return panelMontados;
	}
	private JPanel getPanelNoMontados() {
		if (panelNoMontados == null) {
			panelNoMontados = new JPanel();
			panelNoMontados.setBounds(20, 124, 344, 274);
			panelNoMontados.setLayout(new BorderLayout(0, 0));
			panelNoMontados.add(getLblNoMontar(), BorderLayout.NORTH);
			panelNoMontados.add(getBtnParaMontar(), BorderLayout.SOUTH);
			panelNoMontados.add(getScrollPaneNoMontar(), BorderLayout.CENTER);
		}
		return panelNoMontados;
	}
	private JPanel getPanelMontar() {
		if (panelMontar == null) {
			panelMontar = new JPanel();
			panelMontar.setBounds(379, 124, 344, 274);
			panelMontar.setLayout(new BorderLayout(0, 0));
			panelMontar.add(getLblMontar(), BorderLayout.NORTH);
			panelMontar.add(getBtnParaNoMontar(), BorderLayout.SOUTH);
			panelMontar.add(getScrollPaneMontar(), BorderLayout.CENTER);
		}
		return panelMontar;
	}
	private JLabel getLblProdMontar() {
		if (lblProdMontar == null) {
			lblProdMontar = new JLabel("Selecciona los productos para montar: ");
			lblProdMontar.setBounds(20, 99, 273, 16);
		}
		return lblProdMontar;
	}
	private JLabel getLblNoMontar() {
		if (lblNoMontar == null) {
			lblNoMontar = new JLabel("Productos sin montar:");
		}
		return lblNoMontar;
	}
	private JLabel getLblMontar() {
		if (lblMontar == null) {
			lblMontar = new JLabel("Productos para montar:");
		}
		return lblMontar;
	}
	private JButton getBtnParaMontar() {
		if (btnParaMontar == null) {
			btnParaMontar = new JButton("Añadir para montar");
			btnParaMontar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProductoController controller = new ProductoController(new ProductoModel(),  VistaMuebleria.VIEW_TRANSPORTE);
					controller.actualizarMontaje(1, getListNoMontar().getSelectedValue().getId(), ((VentaDTO) getComboBoxListaVentas().getSelectedItem()).getId_venta());
					for (int i=0;i<getListNoMontar().getSelectedValuesList().size();i++) {
						modeloListMontar.addElement(getListNoMontar().getSelectedValuesList().get(i));
						modeloListNoMontar.removeElement(getListNoMontar().getSelectedValuesList().get(i));
					}
				}
			});
		}
		return btnParaMontar;
	}
	private JButton getBtnParaNoMontar() {
		if (btnParaNoMontar == null) {
			btnParaNoMontar = new JButton("Quitar para montar");
			btnParaNoMontar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProductoController controller = new ProductoController(new ProductoModel(),  VistaMuebleria.VIEW_TRANSPORTE);
					controller.actualizarMontaje(0, getListMontar().getSelectedValue().getId(), ((VentaDTO) getComboBoxListaVentas().getSelectedItem()).getId_venta());
					for (int i=0;i<getListMontar().getSelectedValuesList().size();i++) {
						modeloListNoMontar.addElement(getListMontar().getSelectedValuesList().get(i));
						modeloListMontar.removeElement(getListMontar().getSelectedValuesList().get(i));
					}
				}
			});
		}
		return btnParaNoMontar;
	}
	private JList<ProductoDTO> getListNoMontar() {
		if (listNoMontar == null) {
			modeloListNoMontar = new DefaultListModel<ProductoDTO>();
			listNoMontar = new JList<ProductoDTO>(modeloListNoMontar);
		}
		return listNoMontar;
	}
	private JList<ProductoDTO> getListMontar() {
		if (listMontar == null) {
			modeloListMontar = new DefaultListModel<ProductoDTO>();
			listMontar = new JList<ProductoDTO>(modeloListMontar);
		}
		return listMontar;
	}public void addModeloListProdNoMontar(ProductoDTO prod){
		modeloListNoMontar.addElement(prod);
	}
	public void addModeloListProdMontar(ProductoDTO prod){
		modeloListMontar.addElement(prod);
	}
	private JScrollPane getScrollPaneNoMontar() {
		if (scrollPaneNoMontar == null) {
			scrollPaneNoMontar = new JScrollPane();
			scrollPaneNoMontar.setViewportView(getListNoMontar());
		}
		return scrollPaneNoMontar;
	}
	private JScrollPane getScrollPaneMontar() {
		if (scrollPaneMontar == null) {
			scrollPaneMontar = new JScrollPane();
			scrollPaneMontar.setViewportView(getListMontar());
		}
		return scrollPaneMontar;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CardLayout c = (CardLayout) getPanelGeneral().getLayout();
					c.show(getPanelGeneral(), "PanelTransporte");
				}
			});
			btnCancelar.setBounds(20, 444, 131, 23);
		}
		return btnCancelar;
	}
	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TransportistaController controller = new TransportistaController(new TransportistaModel(),  VistaMuebleria.VIEW_TRANSPORTE);
					controller.asignaTransportista(getTransportista().getIdTransp());
					JOptionPane.showMessageDialog(null, "Se ha seleccionado al transportista: " 
								+ transpElegido.getNombre()  +  " para la venta de id: "
								+ ((VentaDTO) getComboBoxListaVentas().getSelectedItem()).getId_venta());
					closeWindow();
					inicializar();
					CardLayout c = (CardLayout) getPanelGeneral().getLayout();
					c.show(getPanelGeneral(), "PanelTransporte");
				}
			});
			btnFinalizar.setBounds(321, 444, 105, 23);
		}
		return btnFinalizar;
	}
	private JButton getBtnMenu() {
		if (btnMenu == null) {
			btnMenu = new JButton("Cancelar");
			btnMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					closeWindow();
					inicializar();
				}
			});
			btnMenu.setBounds(10, 444, 100, 23);
		}
		return btnMenu;
	}
	
	private void closeWindow() {
		this.dispose();
	}
	
	private void inicializar() {
		modeloListTransp.clear();
		modeloListRecoger.clear();
		modeloListMontar.clear();
		modeloListNoMontar.clear();
	}
	
	public void clearListaMontaje() {
		modeloListMontar.clear();
	}
	
	public void clearListaNoMontaje() {
		modeloListNoMontar.clear();
	}
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
//					TransportistaController transpController = new TransportistaController(new TransportistaModel(), VistaMuebleria.VIEW_TRANSPORTE);
//					int idTransp = transpController.getTransportistaPorVenta(((VentaDTO) getComboBoxListaVentas().getSelectedItem()).getId_venta());
//					if(idTransp != 0) {
//						getComboBoxListaTransportistas().setSelectedIndex(idTransp-1);
//					}
					ProductoController controller = new ProductoController(new ProductoModel(),  VistaMuebleria.VIEW_TRANSPORTE);
					ProductoDTO[] productos = controller.getListaProductosVentaNoTransp(((VentaDTO) getComboBoxListaVentas().getSelectedItem()).getId_venta());
					for (int i=0;i<productos.length;i++) {
						addModeloListProdNoTransp(productos[i]);						
					}
					ProductoDTO[] productosTr = controller.getListaProductosVentaTransp(((VentaDTO) getComboBoxListaVentas().getSelectedItem()).getId_venta());
					for (int i=0;i<productosTr.length;i++) {
						addModeloListProdTransp(productosTr[i]);						
					}
				}
			});
			btnBuscar.setBounds(634, 45, 89, 23);
		}
		return btnBuscar;
	}
	
	public ProductoDTO[] toArray(List<ProductoDTO> listProductos) {
		ProductoDTO[] arrayProductos = new ProductoDTO[listProductos.size()];
		for(int i=0;i<listProductos.size();i++) {
			arrayProductos[i] = listProductos.get(i);
		}
		return arrayProductos;
	}
	
	public int getMaxIdVenta() {
		ProductoController controller = new ProductoController(new ProductoModel(), VistaMuebleria.VIEW_TRANSPORTE);
		return controller.getMaxId();
	}
	
	public TransportistaDTO getTransportista() {
		return transpElegido;
	}
	
	public void setTransportista(TransportistaDTO transpElegido) {
		this.transpElegido = transpElegido;
	}
	private JLabel getLblVenta() {
		if (lblVenta == null) {
			lblVenta = new JLabel("Selecciona la venta:");
			lblVenta.setBounds(20, 26, 163, 14);
		}
		return lblVenta;
	}
	public JComboBox<VentaDTO> getComboBoxListaVentas() {
		if (comboBoxListaVentas == null) {
			comboBoxListaVentas = new JComboBox<VentaDTO>();
			comboBoxListaVentas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			comboBoxListaVentas.setBounds(20, 45, 604, 22);
		}
		return comboBoxListaVentas;
	}
}