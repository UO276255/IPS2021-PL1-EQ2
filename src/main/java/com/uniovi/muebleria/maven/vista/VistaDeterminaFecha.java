package com.uniovi.muebleria.maven.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import com.toedter.calendar.JCalendar;
import com.uniovi.muebleria.maven.controlador.Venta.VentaController;
import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaModel;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaDeterminaFecha extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCalendar calendar;
	private JComboBox cbHoras;
	private JComboBox cbMinutos;
	private JLabel lbDivisor;
	private JPanel pnAsignarCancelar;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JList list;
	private List listaProductos;
	private JComboBox cbSeleccionarVenta;
	private VentaDTO venta;
	 

	/**
	 * Create the frame.
	 */
	public VistaDeterminaFecha() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCalendar());
		contentPane.add(getCbHoras());
		contentPane.add(getCbMinutos());
		contentPane.add(getLbDivisor());
		contentPane.add(getPnAsignarCancelar());
		contentPane.add(getList());
		
		JPanel panelRecoger = new JPanel();
		panelRecoger.setBounds(27, 96, 344, 319);
		contentPane.add(panelRecoger);
		panelRecoger.setLayout(new BorderLayout(0, 0));
		
		JLabel lbProductos = new JLabel("Productos con transporte disponible :");
		lbProductos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbProductos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelRecoger.add(lbProductos, BorderLayout.NORTH);
		
		listaProductos = new List();
		listaProductos.setEnabled(false);
		panelRecoger.add(listaProductos, BorderLayout.CENTER);
		
		JPanel panelTransporte = new JPanel();
		panelTransporte.setBounds(386, 96, 344, 319);
		contentPane.add(panelTransporte);
		panelTransporte.setLayout(new BorderLayout(0, 0));
		
		JLabel lbTransportista = new JLabel("Transportista :");
		lbTransportista.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTransportista.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTransporte.add(lbTransportista, BorderLayout.NORTH);
		
		List listaTransportista = new List();
		panelTransporte.add(listaTransportista, BorderLayout.CENTER);
		contentPane.add(getCbSeleccionarVenta());
		
		JButton btnNewButton_2 = new JButton("Seleccionar Venta");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionaVenta();
			}
		});
		btnNewButton_2.setBackground(new Color(0, 250, 154));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBounds(27, 62, 151, 23);
		contentPane.add(btnNewButton_2);
	}
	@SuppressWarnings("deprecation")
	private void seleccionaVenta() {
		venta=(VentaDTO) cbSeleccionarVenta.getSelectedItem();
		int idx = cbSeleccionarVenta.getSelectedIndex();
		VentaController controller = new VentaController(new VentaModel(), VistaMuebleria.VIEW_VENTA);
		ProductoDTO[] productos = controller.getListaProductos(venta, true);
		getCbSeleccionarVenta().setSelectedIndex(idx);
		listaProductos.clear();
		for(int i=0; i< productos.length; i++)
			listaProductos.add(productos[i].toString());
			
	}

	public void setCbSeleccionarVenta(JComboBox cbSeleccionarVenta) {
		this.cbSeleccionarVenta = cbSeleccionarVenta;
	}

	private JCalendar getCalendar() {
		if (calendar == null) {
			calendar = new JCalendar();
			calendar.setBounds(27, 457, 205, 153);
		}
		return calendar;
	}
	private JComboBox getCbHoras() {
		if (cbHoras == null) {
			cbHoras = new JComboBox();
			cbHoras.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00"}));
			cbHoras.setBounds(242, 520, 44, 22);
		}
		return cbHoras;
	}
	private JComboBox getCbMinutos() {
		if (cbMinutos == null) {
			cbMinutos = new JComboBox();
			cbMinutos.setModel(new DefaultComboBoxModel(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}));
			cbMinutos.setBounds(296, 520, 44, 22);
		}
		return cbMinutos;
	}
	private JLabel getLbDivisor() {
		if (lbDivisor == null) {
			lbDivisor = new JLabel(":");
			lbDivisor.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lbDivisor.setBounds(288, 521, 23, 14);
		}
		return lbDivisor;
	}
	private JPanel getPnAsignarCancelar() {
		if (pnAsignarCancelar == null) {
			pnAsignarCancelar = new JPanel();
			pnAsignarCancelar.setBounds(453, 531, 277, 58);
			pnAsignarCancelar.setLayout(new GridLayout(1, 0, 0, 0));
			pnAsignarCancelar.add(getBtnNewButton());
			pnAsignarCancelar.add(getBtnNewButton_1());
		}
		return pnAsignarCancelar;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Asignar");
			btnNewButton.setBackground(Color.GREEN);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Cancelar");
			btnNewButton_1.setBackground(Color.RED);
		}
		return btnNewButton_1;
	}
	private JList getList() {
		if (list == null) {
			list = new JList();
			list.setBackground(Color.WHITE);
			list.setForeground(Color.BLACK);
			list.setBounds(68, 488, 269, -277);
		}
		return list;
	}
	public JComboBox getCbSeleccionarVenta() {
		if (cbSeleccionarVenta == null) {
			cbSeleccionarVenta = new JComboBox();
			cbSeleccionarVenta.setBounds(27, 29, 703, 22);
		}
		return cbSeleccionarVenta;
	}
}
