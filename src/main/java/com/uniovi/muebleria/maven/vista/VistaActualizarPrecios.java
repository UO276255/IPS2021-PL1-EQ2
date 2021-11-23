package com.uniovi.muebleria.maven.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;

import java.awt.GridLayout;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class VistaActualizarPrecios extends JFrame {

	private JPanel contentPane;
	private JPanel pnProductos;
	private JLabel lbListaProds;
	private JButton btSeleccionar;
	private JList listProductos;
	private JLabel lbProducto;
	private JTextField txProducto;
	private JLabel lblNuevoPrecio;
	private JButton btActualizar;
	private JPanel pnBotones;
	private JButton btCancelar;
	private JButton btFinalizar;
	private JFormattedTextField txPrecio;
	private JLabel lbEuro;
	/**
	 * Create the frame.
	 */
	public VistaActualizarPrecios() {
		setTitle("Ventana ActualizarPrecios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 641, 556);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPnProductos());
		contentPane.add(getLbProducto());
		contentPane.add(getTxProducto());
		contentPane.add(getLblNuevoPrecio());
		contentPane.add(getBtActualizar());
		contentPane.add(getPnBotones());
		contentPane.add(getTxPrecio());
		contentPane.add(getLbEuro());
	}
	private JPanel getPnProductos() {
		if (pnProductos == null) {
			pnProductos = new JPanel();
			pnProductos.setBounds(53, 30, 523, 280);
			pnProductos.setLayout(new BorderLayout(0, 0));
			pnProductos.add(getLbListaProds(), BorderLayout.NORTH);
			pnProductos.add(getBtSeleccionar(), BorderLayout.SOUTH);
			pnProductos.add(getListProductos(), BorderLayout.CENTER);
		}
		return pnProductos;
	}
	private JLabel getLbListaProds() {
		if (lbListaProds == null) {
			lbListaProds = new JLabel("Lista de Productos");
			lbListaProds.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			lbListaProds.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return lbListaProds;
	}
	private JButton getBtSeleccionar() {
		if (btSeleccionar == null) {
			btSeleccionar = new JButton("SELECCIONAR");
			btSeleccionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getTxProducto().setText((getListProductos().getSelectedValue().toStringActualizarPrecios()));
				}
			});
			btSeleccionar.setBackground(Color.GREEN);
			btSeleccionar.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return btSeleccionar;
	}
	public JList<ProductoDTO> getListProductos() {
		if (listProductos == null) {
			listProductos = new JList();
			listProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listProductos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			listProductos.setBackground(new Color(255, 255, 224));
		}
		return listProductos;
	}
	private JLabel getLbProducto() {
		if (lbProducto == null) {
			lbProducto = new JLabel("Producto Seleccionado:");
			lbProducto.setFont(new Font("Tahoma", Font.BOLD, 12));
			lbProducto.setBounds(57, 350, 161, 15);
		}
		return lbProducto;
	}
	private JTextField getTxProducto() {
		if (txProducto == null) {
			txProducto = new JTextField();
			txProducto.setFont(new Font("Tahoma", Font.ITALIC, 12));
			txProducto.setEditable(false);
			txProducto.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			txProducto.setBackground(new Color(250, 240, 230));
			txProducto.setBounds(223, 338, 214, 37);
			txProducto.setColumns(10);
		}
		return txProducto;
	}
	private JLabel getLblNuevoPrecio() {
		if (lblNuevoPrecio == null) {
			lblNuevoPrecio = new JLabel("Nuevo Precio:");
			lblNuevoPrecio.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNuevoPrecio.setBounds(57, 411, 161, 15);
		}
		return lblNuevoPrecio;
	}
	private JButton getBtActualizar() {
		if (btActualizar == null) {
			btActualizar = new JButton("ACTUALIZAR");
			btActualizar.setBorder(new LineBorder(new Color(0, 0, 0)));
			btActualizar.setBackground(new Color(255, 255, 0));
			btActualizar.setFont(new Font("Tahoma", Font.BOLD, 13));
			btActualizar.setBounds(469, 400, 113, 37);
		}
		return btActualizar;
	}
	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setBounds(317, 465, 265, 41);
			pnBotones.setLayout(new GridLayout(1, 0, 0, 0));
			pnBotones.add(getBtFinalizar());
			pnBotones.add(getBtCancelar());
		}
		return pnBotones;
	}
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("CANCELAR");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int option = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar? Los cambios se descartarán y no se actualizarán los precios", "Cancelar", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION)
						dispose();
	
				}
			});
			btCancelar.setBackground(Color.RED);
			btCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return btCancelar;
	}
	private JButton getBtFinalizar() {
		if (btFinalizar == null) {
			btFinalizar = new JButton("FINALIZAR");
			btFinalizar.setFont(new Font("Tahoma", Font.BOLD, 13));
			btFinalizar.setBackground(Color.GREEN);
		}
		return btFinalizar;
	}
	private JFormattedTextField getTxPrecio() {
		if (txPrecio == null) {
			txPrecio = new JFormattedTextField(new Integer(0));
			txPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txPrecio.setBackground(new Color(250, 240, 230));
			txPrecio.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			txPrecio.setBounds(223, 400, 190, 37);
		}
		return txPrecio;
	}
	private JLabel getLbEuro() {
		if (lbEuro == null) {
			lbEuro = new JLabel("€");
			lbEuro.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lbEuro.setBounds(423, 400, 25, 37);
		}
		return lbEuro;
	}
}
