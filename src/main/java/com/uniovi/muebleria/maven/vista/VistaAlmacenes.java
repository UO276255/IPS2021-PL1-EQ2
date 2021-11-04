package com.uniovi.muebleria.maven.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uniovi.muebleria.maven.modelo.Almacen.AlmacenDTO;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class VistaAlmacenes extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelTitulo;
	private JPanel panelSalit;
	private JButton btnSalir;
	private JLabel lblListaAlmacenes;
	private JPanel panelLista;
	private JScrollPane scrollPaneLista;
	private JList<AlmacenDTO> listAlmacenes;
	private DefaultListModel<AlmacenDTO> modeloListAlmacen = new DefaultListModel<AlmacenDTO>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAlmacenes frame = new VistaAlmacenes();
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
	public VistaAlmacenes() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 742, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelTitulo(), BorderLayout.NORTH);
		contentPane.add(getPanelSalit(), BorderLayout.SOUTH);
		contentPane.add(getPanelLista(), BorderLayout.CENTER);
	}

	private JPanel getPanelTitulo() {
		if (panelTitulo == null) {
			panelTitulo = new JPanel();
			panelTitulo.add(getLblListaAlmacenes());
		}
		return panelTitulo;
	}
	private JPanel getPanelSalit() {
		if (panelSalit == null) {
			panelSalit = new JPanel();
			panelSalit.add(getBtnSalir());
		}
		return panelSalit;
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
	private JPanel getPanelLista() {
		if (panelLista == null) {
			panelLista = new JPanel();
			panelLista.setLayout(new BorderLayout(0, 0));
			panelLista.add(getScrollPaneLista(), BorderLayout.CENTER);
		}
		return panelLista;
	}
	private JScrollPane getScrollPaneLista() {
		if (scrollPaneLista == null) {
			scrollPaneLista = new JScrollPane();
			scrollPaneLista.setViewportView(getListAlmacenes());
		}
		return scrollPaneLista;
	}
	public JList<AlmacenDTO> getListAlmacenes() {
		if (listAlmacenes == null) {
			modeloListAlmacen = new DefaultListModel<AlmacenDTO>();
			listAlmacenes = new JList<AlmacenDTO>(modeloListAlmacen);
			listAlmacenes.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return listAlmacenes;
	}
	
	public void addModeloAlmacen(AlmacenDTO alm){
		modeloListAlmacen.addElement(alm);
	}

	public void clearModelos() {
		modeloListAlmacen.clear();
	}
}
