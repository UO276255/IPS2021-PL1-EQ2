package com.uniovi.muebleria.maven.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uniovi.muebleria.maven.util.Database;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MuebleriaVisual extends JFrame {

	private JPanel PanelPrincipal;
	private JPanel panelBotones;
	private JPanel panelBaseDatos;
	private JButton btnAsignarPresupuesto;
	private JButton btnVisualizarPresupuestos;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnIniciarBaseDatos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MuebleriaVisual frame = new MuebleriaVisual();
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
	public MuebleriaVisual() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 525);
		PanelPrincipal = new JPanel();
		PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelPrincipal);
		PanelPrincipal.setLayout(new BorderLayout(0, 0));
		PanelPrincipal.add(getPanelBotones(), BorderLayout.EAST);
		PanelPrincipal.add(getPanelBaseDatos(), BorderLayout.CENTER);
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new GridLayout(6, 1, 0, 0));
			panelBotones.add(getButtonAsignarPresupuesto());
			panelBotones.add(getButtonVisualizarPresupuestos());
			panelBotones.add(getButton_3());
			panelBotones.add(getButton_4());
			panelBotones.add(getButton_5());
			panelBotones.add(getButton_6());
		}
		return panelBotones;
	}
	private JPanel getPanelBaseDatos() {
		if (panelBaseDatos == null) {
			panelBaseDatos = new JPanel();
			panelBaseDatos.add(getButtonBaseDatos());
		}
		return panelBaseDatos;
	}
	private JButton getButtonAsignarPresupuesto() {
		if (btnAsignarPresupuesto == null) {
			btnAsignarPresupuesto = new JButton("Asignar Presupuesto");
		}
		return btnAsignarPresupuesto;
	}
	private JButton getButtonVisualizarPresupuestos() {
		if (btnVisualizarPresupuestos == null) {
			btnVisualizarPresupuestos = new JButton("Visualizar Presupuesto");
		}
		return btnVisualizarPresupuestos;
	}
	private JButton getButton_3() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("New button");
		}
		return btnNewButton_2;
	}
	private JButton getButton_4() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("New button");
		}
		return btnNewButton_3;
	}
	private JButton getButton_5() {
		if (btnNewButton_4 == null) {
			btnNewButton_4 = new JButton("New button");
		}
		return btnNewButton_4;
	}
	private JButton getButton_6() {
		if (btnNewButton_5 == null) {
			btnNewButton_5 = new JButton("New button");
		}
		return btnNewButton_5;
	}
	private JButton getButtonBaseDatos() {
		if (btnIniciarBaseDatos == null) {
			btnIniciarBaseDatos = new JButton("Iniciar Base de Datos");
			btnIniciarBaseDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Database db=new Database();
					db.createDatabase(false);
					db.loadDatabase();
					getButtonBaseDatos().setEnabled(false);					
				}
			});
		}
		return btnIniciarBaseDatos;
	}
}
