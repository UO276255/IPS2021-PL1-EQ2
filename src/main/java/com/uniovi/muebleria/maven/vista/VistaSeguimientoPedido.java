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
	private JSpinner spinnerIdProd;
	private JLabel lblSeleccionarProveedor;
	private JButton btnBuscar;
	private JTextPane textPanePedido;
	private JScrollPane scrollPanePedido;
	private JButton btnCancelar;
	private JButton btnMarcarRecibido;

	/**
	 * Create the frame.
	 */
	public VistaSeguimientoPedido() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				inicializar();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 662, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getSpinnerIdProd());
		contentPane.add(getLblSeleccionarProveedor());
		contentPane.add(getBtnBuscar());
		contentPane.add(getScrollPanePedido());
		contentPane.add(getBtnCancelar());
		contentPane.add(getBtnMarcarRecibido());
	}
	public JSpinner getSpinnerIdProd() {
		if (spinnerIdProd == null) {
			spinnerIdProd = new JSpinner();
			spinnerIdProd.setModel(new SpinnerNumberModel(0, 0, 0, 1));
			spinnerIdProd.setBounds(436, 69, 30, 20);
		}
		return spinnerIdProd;
	}
	private JLabel getLblSeleccionarProveedor() {
		if (lblSeleccionarProveedor == null) {
			lblSeleccionarProveedor = new JLabel("Selecciona la id del producto para buscar el pedido:");
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
					PedidoDTO pedido = controller.getPedido((Integer) getSpinnerIdProd().getValue());
					textPanePedido.setText(pedido.toString());
				}
			});
			btnBuscar.setBounds(279, 100, 89, 23);
		}
		return btnBuscar;
	}
	private JTextPane getTextPanePedido() {
		if (textPanePedido == null) {
			textPanePedido = new JTextPane();
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
					int valor = (Integer) getSpinnerIdProd().getValue();
					PedidoController controller = new PedidoController(new PedidoModel(), VistaMuebleria.VIEW_SEGUIMIENTO);
					PedidoDTO pedido = controller.getPedido(valor);
					controller.setPedidoRegistrado(valor);
					textPanePedido.setVisible(true);
					scrollPanePedido.setVisible(true);
					pedido = controller.getPedido(valor);
					textPanePedido.setText(pedido.toString());
					JOptionPane.showMessageDialog(null, "Pedido con id : " + pedido.getId()  +  " marcado correctamente como recibido");
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
		spinnerIdProd.setValue(0);
		scrollPanePedido.setVisible(false);
		textPanePedido.setVisible(false);
	}
	
	public int getMaxIdProv() {
		PedidoController controller = new PedidoController(new PedidoModel(), VistaMuebleria.VIEW_SEGUIMIENTO);
		return controller.getMaxId();
	}
}
