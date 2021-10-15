package com.uniovi.muebleria.maven.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import com.toedter.calendar.JCalendar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JList;

public class VistaDeterminaFecha extends JFrame {

	private JPanel contentPane;
	private JLabel lblFecha;
	private JLabel lblHorario;
	private JCalendar calendar;
	private JComboBox cbHoras;
	private JComboBox cbMinutos;
	private JLabel lbDivisor;
	private JPanel pnAsignarCancelar;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JComboBox cbSeleccionarVenta;
	private JLabel lblProductosConTransporte;
	private JList list;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaDeterminaFecha frame = new VistaDeterminaFecha();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaDeterminaFecha() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblFecha());
		contentPane.add(getLblHorario());
		contentPane.add(getCalendar());
		contentPane.add(getCbHoras());
		contentPane.add(getCbMinutos());
		contentPane.add(getLbDivisor());
		contentPane.add(getPnAsignarCancelar());
		
		cbSeleccionarVenta = new JComboBox();
		cbSeleccionarVenta.setBounds(56, 75, 415, 22);
		contentPane.add(cbSeleccionarVenta);
		
		JLabel lblNewLabel = new JLabel("Seleccionar Venta :");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel.setBounds(56, 48, 151, 22);
		contentPane.add(lblNewLabel);
		contentPane.add(getLblProductosConTransporte());
		contentPane.add(getList());
	}
	public JComboBox getCbSeleccionarVenta() {
		return cbSeleccionarVenta;
	}

	public void setCbSeleccionarVenta(JComboBox cbSeleccionarVenta) {
		this.cbSeleccionarVenta = cbSeleccionarVenta;
	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("FECHA");
			lblFecha.setFont(new Font("Calibri", Font.BOLD, 14));
			lblFecha.setBounds(372, 332, 62, 28);
		}
		return lblFecha;
	}
	private JLabel getLblHorario() {
		if (lblHorario == null) {
			lblHorario = new JLabel("HORARIO");
			lblHorario.setFont(new Font("Calibri", Font.BOLD, 14));
			lblHorario.setBounds(370, 442, 55, 28);
		}
		return lblHorario;
	}

	private JCalendar getCalendar() {
		if (calendar == null) {
			calendar = new JCalendar();
			calendar.setBounds(427, 278, 205, 153);
		}
		return calendar;
	}
	private JComboBox getCbHoras() {
		if (cbHoras == null) {
			cbHoras = new JComboBox();
			cbHoras.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00"}));
			cbHoras.setBounds(427, 443, 44, 22);
		}
		return cbHoras;
	}
	private JComboBox getCbMinutos() {
		if (cbMinutos == null) {
			cbMinutos = new JComboBox();
			cbMinutos.setModel(new DefaultComboBoxModel(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}));
			cbMinutos.setBounds(485, 443, 44, 22);
		}
		return cbMinutos;
	}
	private JLabel getLbDivisor() {
		if (lbDivisor == null) {
			lbDivisor = new JLabel(":");
			lbDivisor.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lbDivisor.setBounds(474, 444, 23, 14);
		}
		return lbDivisor;
	}
	private JPanel getPnAsignarCancelar() {
		if (pnAsignarCancelar == null) {
			pnAsignarCancelar = new JPanel();
			pnAsignarCancelar.setBounds(395, 504, 237, 42);
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

	private JLabel getLblProductosConTransporte() {
		if (lblProductosConTransporte == null) {
			lblProductosConTransporte = new JLabel("Productos con transporte disponibles : ");
			lblProductosConTransporte.setFont(new Font("Calibri", Font.BOLD, 16));
			lblProductosConTransporte.setBounds(56, 171, 269, 22);
		}
		return lblProductosConTransporte;
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
}
