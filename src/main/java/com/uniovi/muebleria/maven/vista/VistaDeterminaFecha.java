package com.uniovi.muebleria.maven.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VistaDeterminaFecha extends JFrame {

	private JPanel contentPane;
	private JLabel lblFecha;
	private JLabel lblHorario;
	private JCalendar calendar;
	private JComboBox cbHoras;
	private JComboBox cbMinutos;
	private JLabel lbDivisor;


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
		setBounds(100, 100, 378, 334);
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
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("FECHA");
			lblFecha.setFont(new Font("Calibri", Font.BOLD, 14));
			lblFecha.setBounds(43, 90, 82, 28);
		}
		return lblFecha;
	}
	private JLabel getLblHorario() {
		if (lblHorario == null) {
			lblHorario = new JLabel("HORARIO");
			lblHorario.setFont(new Font("Calibri", Font.BOLD, 14));
			lblHorario.setBounds(43, 230, 82, 28);
		}
		return lblHorario;
	}

	private JCalendar getCalendar() {
		if (calendar == null) {
			calendar = new JCalendar();
			calendar.setBounds(118, 36, 205, 153);
		}
		return calendar;
	}
	private JComboBox getCbHoras() {
		if (cbHoras == null) {
			cbHoras = new JComboBox();
			cbHoras.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00"}));
			cbHoras.setBounds(117, 231, 44, 22);
		}
		return cbHoras;
	}
	private JComboBox getCbMinutos() {
		if (cbMinutos == null) {
			cbMinutos = new JComboBox();
			cbMinutos.setModel(new DefaultComboBoxModel(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}));
			cbMinutos.setBounds(171, 231, 44, 22);
		}
		return cbMinutos;
	}
	private JLabel getLbDivisor() {
		if (lbDivisor == null) {
			lbDivisor = new JLabel(":");
			lbDivisor.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lbDivisor.setBounds(163, 232, 23, 14);
		}
		return lbDivisor;
	}
}
