package com.uniovi.muebleria.maven.vista;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uniovi.muebleria.maven.controlador.Cliente.ClienteController;
import com.uniovi.muebleria.maven.controlador.Presupuestos.PresupuestoController;
import com.uniovi.muebleria.maven.modelo.Cliente.ClienteDTO;
import com.uniovi.muebleria.maven.modelo.Cliente.ClienteModel;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestoDTO;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestosModel;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaAsignarPresupuesto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelGeneral;
	private JPanel panelInicial;
	private JButton btnAsignarExistente;
	private JButton btnAsignarNuevo;
	private JComboBox<PresupuestoDTO> comboBoxPresupuestos;
	private JPanel panelNuevoCliente;
	private JPanel panelClienteExistente;
	private JComboBox<ClienteDTO> comboBoxClientesExistentes;
	private JButton btnAsignarCliente;

	/**
	 * Create the frame.
	 */
	public VistaAsignarPresupuesto() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 635, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.add(getPanelGeneral());
		this.dispose();
	}
	public JPanel getPanelGeneral() {
		if (panelGeneral == null) {
			panelGeneral = new JPanel();
			panelGeneral.setLayout(new CardLayout(0, 0));
			panelGeneral.add(getPanelInicial(), "PanelInicial");
			panelGeneral.add(getPanelNuevoCliente(), "PanelNuevoCliente");
			panelGeneral.add(getPanelClienteExistente(), "PanelClienteExistente");
		}
		return panelGeneral;
	}
	public JPanel getPanelInicial() {
		if (panelInicial == null) {
			panelInicial = new JPanel();
			panelInicial.setLayout(null);
			panelInicial.add(getBtnAsignarExistente());
			panelInicial.add(getBtnAsignarNuevo());
			panelInicial.add(getComboBoxPresupuestos());
		}
		return panelInicial;
	}
	public JButton getBtnAsignarExistente() {
		if (btnAsignarExistente == null) {
			btnAsignarExistente = new JButton("Asignar a un cliente existente");
			btnAsignarExistente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {		
					CardLayout c = (CardLayout) getPanelGeneral().getLayout();
					c.show(getPanelGeneral(), "PanelClienteExistente");	
					ClienteController controller = new ClienteController(new ClienteModel(), VistaMuebleria.VIEW_PRESUPUESTO);
					controller.setListaClientes();
				}
			});
			btnAsignarExistente.setBounds(354, 181, 221, 34);
		}
		return btnAsignarExistente;
	}
	public JButton getBtnAsignarNuevo() {
		if (btnAsignarNuevo == null) {
			btnAsignarNuevo = new JButton("Asignar a un cliente nuevo");
			btnAsignarNuevo.setBounds(40, 181, 221, 34);
		}
		return btnAsignarNuevo;
	}
	public JComboBox<PresupuestoDTO> getComboBoxPresupuestos() {
		if (comboBoxPresupuestos == null) {
			comboBoxPresupuestos = new JComboBox<PresupuestoDTO>();
			comboBoxPresupuestos.setBounds(40, 54, 535, 22);
		}
		return comboBoxPresupuestos;
	}
	private JPanel getPanelNuevoCliente() {
		if (panelNuevoCliente == null) {
			panelNuevoCliente = new JPanel();
		}
		return panelNuevoCliente;
	}
	private JPanel getPanelClienteExistente() {
		if (panelClienteExistente == null) {
			panelClienteExistente = new JPanel();
			panelClienteExistente.setLayout(null);
			panelClienteExistente.add(getComboBoxClientesExistentes());
			panelClienteExistente.add(getBtnAsignarCliente());
		}
		return panelClienteExistente;
	}
	public JComboBox<ClienteDTO> getComboBoxClientesExistentes() {
		if (comboBoxClientesExistentes == null) {
			comboBoxClientesExistentes = new JComboBox<ClienteDTO>();
			comboBoxClientesExistentes.setBounds(10, 88, 535, 22);
		}
		return comboBoxClientesExistentes;
	}
	private JButton getBtnAsignarCliente() {
		if (btnAsignarCliente == null) {
			btnAsignarCliente = new JButton("Asignar Cliente");
			btnAsignarCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						PresupuestoController controller = new PresupuestoController(new PresupuestosModel(),  VistaMuebleria.VIEW_PRESUPUESTO);
						controller.asignarClienteAPresupuesto(((ClienteDTO) getComboBoxClientesExistentes().getSelectedItem()).getIdCliente(),
							((PresupuestoDTO) getComboBoxPresupuestos().getSelectedItem()).getIdPresupuesto());
						CardLayout c = (CardLayout) getPanelGeneral().getLayout();
						c.show(getPanelGeneral(), "PanelInicial");	
						JOptionPane.showMessageDialog(null, "presupuesto con id : " + ((PresupuestoDTO) getComboBoxPresupuestos().getSelectedItem()).getIdPresupuesto()  + 
								" asociado con exito al cliente con id:  " + ((ClienteDTO) getComboBoxClientesExistentes().getSelectedItem()).getIdCliente());
						closeWindow();
					}
			});
			btnAsignarCliente.setBounds(402, 224, 143, 23);
		}
		return btnAsignarCliente;
	}
	private void closeWindow() {
		this.dispose();
	}
}
