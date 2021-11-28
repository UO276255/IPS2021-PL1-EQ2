package com.uniovi.muebleria.maven.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import com.uniovi.muebleria.maven.controlador.pedidos.PedidoController;
import com.uniovi.muebleria.maven.modelo.pedidos.PedidoDTO;
import com.uniovi.muebleria.maven.modelo.pedidos.PedidoModel;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaSeguimientoPedido extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JSpinner spinnerIdPed;
	private JLabel lblSeleccionarProveedor;
	private JButton btnBuscar;
	private JTextPane textPanePedido;
	private JScrollPane scrollPanePedido;
	private JButton btnCancelar;
	private JButton btnMarcarRecibido;
	private int pedidoSeleccionado;

	/**
	 * Create the frame.
	 */
	public VistaSeguimientoPedido() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				inicializar();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 662, 410);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getSpinnerIdPed());
		contentPane.add(getLblSeleccionarProveedor());
		contentPane.add(getBtnBuscar());
		contentPane.add(getScrollPanePedido());
		contentPane.add(getBtnCancelar());
		contentPane.add(getBtnMarcarRecibido());
	}
	public JSpinner getSpinnerIdPed() {
		if (spinnerIdPed == null) {
			spinnerIdPed = new JSpinner();
			spinnerIdPed.setModel(new SpinnerNumberModel(0, 0, 0, 1));
			spinnerIdPed.setBounds(425, 69, 41, 20);
		}
		return spinnerIdPed;
	}
	private JLabel getLblSeleccionarProveedor() {
		if (lblSeleccionarProveedor == null) {
			lblSeleccionarProveedor = new JLabel("Selecciona la id del pedido para buscarlo:");
			lblSeleccionarProveedor.setBounds(139, 72, 313, 14);
		}
		return lblSeleccionarProveedor;
	}
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textPanePedido.setVisible(true);
					scrollPanePedido.setVisible(true);
					PedidoController controller = new PedidoController(new PedidoModel(), VistaMuebleria.VIEW_SEGUIMIENTO);
					PedidoDTO pedido = controller.getPedido((Integer) getSpinnerIdPed().getValue());
					textPanePedido.setText(pedido.toString());
					pedidoSeleccionado = (Integer) getSpinnerIdPed().getValue();
				}
			});
			btnBuscar.setBounds(279, 100, 89, 23);
		}
		return btnBuscar;
	}
	private JTextPane getTextPanePedido() {
		if (textPanePedido == null) {
			textPanePedido = new JTextPane();
			textPanePedido.setBackground(new Color(255, 239, 213));
			textPanePedido.setDisabledTextColor(Color.BLACK);
			textPanePedido.setVisible(false);
			textPanePedido.setForeground(Color.BLACK);
			textPanePedido.setEnabled(false);
		}
		return textPanePedido;
	}
	private JScrollPane getScrollPanePedido() {
		if (scrollPanePedido == null) {
			scrollPanePedido = new JScrollPane();
			scrollPanePedido.setVisible(false);
			scrollPanePedido.setBounds(10, 142, 626, 179);
			scrollPanePedido.setViewportView(getTextPanePedido());
		}
		return scrollPanePedido;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					closeWindow();
					inicializar();
				}
			});
			btnCancelar.setBounds(10, 337, 89, 23);
		}
		return btnCancelar;
	}
	private JButton getBtnMarcarRecibido() {
		if (btnMarcarRecibido == null) {
			btnMarcarRecibido = new JButton("Marcar pedido como recibido");
			btnMarcarRecibido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PedidoController controller = new PedidoController(new PedidoModel(), VistaMuebleria.VIEW_SEGUIMIENTO);
					int valor = pedidoSeleccionado;
					PedidoDTO pedido = controller.getPedido(valor);
					if(!pedido.isEstado()) {
						controller.añadirAlmacen(pedido.getProductos(), pedido.getNumProductos());
						controller.setPedidoRegistrado(valor);
						textPanePedido.setVisible(true);
						scrollPanePedido.setVisible(true);
						pedido = controller.getPedido(valor);
						textPanePedido.setText(pedido.toString());
						JOptionPane.showMessageDialog(null, "Pedido con id : " + pedido.getId()  +  " marcado correctamente como recibido");
					}else {
						JOptionPane.showMessageDialog(null, "Este pedido ya estaba marcado como recibido");
					}
				}
			});
			btnMarcarRecibido.setBounds(216, 337, 226, 23);
		}
		return btnMarcarRecibido;
	}
	
	private void closeWindow() {
		this.dispose();
	}
	
	private void inicializar() {
		textPanePedido.setText("");
		spinnerIdPed.setValue(0);
		scrollPanePedido.setVisible(false);
		textPanePedido.setVisible(false);
	}
	
	public int getMaxIdPedido() {
		PedidoController controller = new PedidoController(new PedidoModel(), VistaMuebleria.VIEW_SEGUIMIENTO);
		return controller.getMaxIdPedido();
	}
}
