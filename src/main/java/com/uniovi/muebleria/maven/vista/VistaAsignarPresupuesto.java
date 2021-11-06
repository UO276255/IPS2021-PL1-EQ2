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
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

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
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldNacimiento;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblFechaNacimiento;
	private JLabel lblTitulo;
	private JLabel lblSeleccioneUnPresupuesto;
	private JLabel lblSeleccioneUnCliente;
	private JButton btnAsignar;
	private JButton btnCancelar;
	private JButton btnCancelarNuevoCliente;
	private JButton btnCancelarClienteExistente;
	private JTextField textFieldDNI;
	private JTextField textFieldEmail;

	/**
	 * Create the frame.
	 */
	public VistaAsignarPresupuesto() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 635, 328);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.add(getPanelGeneral());
		this.dispose();
	}
	public JPanel getPanelGeneral() {
		if (panelGeneral == null) {
			panelGeneral = new JPanel();
			panelGeneral.setBackground(new Color(255, 239, 213));
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
			panelInicial.setBackground(new Color(255, 239, 213));
			panelInicial.setLayout(null);
			panelInicial.add(getBtnAsignarExistente());
			panelInicial.add(getBtnAsignarNuevo());
			panelInicial.add(getComboBoxPresupuestos());
			panelInicial.add(getLblSeleccioneUnPresupuesto());
			panelInicial.add(getBtnCancelar());
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
			btnAsignarNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CardLayout c = (CardLayout) getPanelGeneral().getLayout();
					c.show(getPanelGeneral(), "PanelNuevoCliente");										
				}
			});
			btnAsignarNuevo.setBounds(58, 181, 221, 34);
		}
		return btnAsignarNuevo;
	}
	public JComboBox<PresupuestoDTO> getComboBoxPresupuestos() {
		if (comboBoxPresupuestos == null) {
			comboBoxPresupuestos = new JComboBox<PresupuestoDTO>();
			comboBoxPresupuestos.setBounds(40, 80, 535, 22);
		}
		return comboBoxPresupuestos;
	}
	private JPanel getPanelNuevoCliente() {
		if (panelNuevoCliente == null) {
			panelNuevoCliente = new JPanel();
			panelNuevoCliente.setBackground(new Color(255, 239, 213));
			panelNuevoCliente.setLayout(null);
			panelNuevoCliente.add(getTextFieldNombre());
			panelNuevoCliente.add(getTextFieldApellido());
			panelNuevoCliente.add(getTextFieldNacimiento());
			panelNuevoCliente.add(getLblNombre());
			panelNuevoCliente.add(getLblApellido());
			panelNuevoCliente.add(getLblFechaNacimiento());
			panelNuevoCliente.add(getLblTitulo());
			panelNuevoCliente.add(getBtnAsignar());
			panelNuevoCliente.add(getBtnCancelarNuevoCliente());
			
			JLabel lblDni = new JLabel("DNI: ");
			lblDni.setBounds(30, 204, 127, 14);
			panelNuevoCliente.add(lblDni);
			
			textFieldDNI = new JTextField();
			textFieldDNI.setColumns(10);
			textFieldDNI.setBounds(239, 201, 166, 20);
			panelNuevoCliente.add(textFieldDNI);
			
			JLabel lblEmail = new JLabel("Email: ");
			lblEmail.setBounds(30, 235, 127, 14);
			panelNuevoCliente.add(lblEmail);
			
			textFieldEmail = new JTextField();
			textFieldEmail.setColumns(10);
			textFieldEmail.setBounds(239, 232, 166, 20);
			panelNuevoCliente.add(textFieldEmail);
		}
		return panelNuevoCliente;
	}
	private JPanel getPanelClienteExistente() {
		if (panelClienteExistente == null) {
			panelClienteExistente = new JPanel();
			panelClienteExistente.setBackground(new Color(255, 239, 213));
			panelClienteExistente.setLayout(null);
			panelClienteExistente.add(getComboBoxClientesExistentes());
			panelClienteExistente.add(getBtnAsignarCliente());
			panelClienteExistente.add(getLblSeleccioneUnCliente());
			panelClienteExistente.add(getBtnCancelarClienteExistente());
		}
		return panelClienteExistente;
	}
	public JComboBox<ClienteDTO> getComboBoxClientesExistentes() {
		if (comboBoxClientesExistentes == null) {
			comboBoxClientesExistentes = new JComboBox<ClienteDTO>();
			comboBoxClientesExistentes.setBounds(10, 105, 437, 22);
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
			btnAsignarCliente.setBounds(456, 105, 143, 23);
		}
		return btnAsignarCliente;
	}
	
	private void closeWindow() {
		this.dispose();
	}
	
	private JTextField getTextFieldNombre() {
		if (textFieldNombre == null) {
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(239, 111, 166, 20);
			textFieldNombre.setColumns(10);
		}
		return textFieldNombre;
	}
	private JTextField getTextFieldApellido() {
		if (textFieldApellido == null) {
			textFieldApellido = new JTextField();
			textFieldApellido.setBounds(239, 142, 166, 20);
			textFieldApellido.setColumns(10);
		}
		return textFieldApellido;
	}
	private JTextField getTextFieldNacimiento() {
		if (textFieldNacimiento == null) {
			textFieldNacimiento = new JTextField();
			textFieldNacimiento.setBounds(239, 173, 166, 20);
			textFieldNacimiento.setColumns(10);
		}
		return textFieldNacimiento;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre: ");
			lblNombre.setBounds(30, 114, 76, 14);
		}
		return lblNombre;
	}
	private JLabel getLblApellido() {
		if (lblApellido == null) {
			lblApellido = new JLabel("Apellido: ");
			lblApellido.setBounds(30, 145, 76, 14);
		}
		return lblApellido;
	}
	private JLabel getLblFechaNacimiento() {
		if (lblFechaNacimiento == null) {
			lblFechaNacimiento = new JLabel("Fecha de nacimiento (yyyy-mm-dd): ");
			lblFechaNacimiento.setBounds(30, 176, 227, 14);
		}
		return lblFechaNacimiento;
	}
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Inserte los datos del nuevo cliente");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 27));
			lblTitulo.setBounds(30, 29, 459, 26);
		}
		return lblTitulo;
	}
	private JLabel getLblSeleccioneUnPresupuesto() {
		if (lblSeleccioneUnPresupuesto == null) {
			lblSeleccioneUnPresupuesto = new JLabel("Seleccione un presupuesto");
			lblSeleccioneUnPresupuesto.setFont(new Font("Tahoma", Font.PLAIN, 27));
			lblSeleccioneUnPresupuesto.setBounds(58, 17, 466, 34);
		}
		return lblSeleccioneUnPresupuesto;
	}
	private JLabel getLblSeleccioneUnCliente() {
		if (lblSeleccioneUnCliente == null) {
			lblSeleccioneUnCliente = new JLabel("Seleccione un cliente");
			lblSeleccioneUnCliente.setFont(new Font("Tahoma", Font.PLAIN, 27));
			lblSeleccioneUnCliente.setBounds(43, 26, 466, 34);
		}
		return lblSeleccioneUnCliente;
	}
	private JButton getBtnAsignar() {
		if (btnAsignar == null) {
			btnAsignar = new JButton("Asignar");
			btnAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					ClienteController controllerC = new ClienteController(new ClienteModel(), VistaMuebleria.VIEW_PRESUPUESTO);
					controllerC.crearNuevoCliente(getTextFieldNombre().getText(),getTextFieldApellido().getText(),getTextFieldNacimiento().getText(),Integer.parseInt(textFieldDNI.getText()),textFieldEmail.getText());
					
					PresupuestoController controllerP = new PresupuestoController(new PresupuestosModel(),  VistaMuebleria.VIEW_PRESUPUESTO);
					controllerP.asignarClienteAPresupuesto(controllerC.contarClientes(),((PresupuestoDTO) getComboBoxPresupuestos().getSelectedItem()).getIdPresupuesto());

					CardLayout c = (CardLayout) getPanelGeneral().getLayout();
					c.show(getPanelGeneral(), "PanelInicial");
					
					JOptionPane.showMessageDialog(null, "presupuesto con id : " + ((PresupuestoDTO) getComboBoxPresupuestos().getSelectedItem()).getIdPresupuesto()  + 
							" asociado con exito al cliente con id:  " + (controllerC.contarClientes()));
					closeWindow();
				}
			});
			btnAsignar.setBounds(456, 141, 100, 23);
		}
		return btnAsignar;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					closeWindow();
				}
			});
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCancelar.setBounds(258, 245, 97, 23);
		}
		return btnCancelar;
	}
	private JButton getBtnCancelarNuevoCliente() {
		if (btnCancelarNuevoCliente == null) {
			btnCancelarNuevoCliente = new JButton("Cancelar");
			btnCancelarNuevoCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CardLayout c = (CardLayout) getPanelGeneral().getLayout();
					c.show(getPanelGeneral(), "PanelInicial");	
				}
			});
			btnCancelarNuevoCliente.setBounds(456, 245, 100, 23);
		}
		return btnCancelarNuevoCliente;
	}
	private JButton getBtnCancelarClienteExistente() {
		if (btnCancelarClienteExistente == null) {
			btnCancelarClienteExistente = new JButton("Cancelar");
			btnCancelarClienteExistente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CardLayout c = (CardLayout) getPanelGeneral().getLayout();
					c.show(getPanelGeneral(), "PanelInicial");	
				}
			});
			btnCancelarClienteExistente.setBounds(487, 245, 89, 23);
		}
		return btnCancelarClienteExistente;
	}
}
