package com.uniovi.muebleria.maven.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.codec.digest.DigestUtils;

import com.uniovi.muebleria.maven.controlador.PersonalAlmacen.PersonalAlmacenController;
import com.uniovi.muebleria.maven.controlador.Vendedor.VendedorController;
import com.uniovi.muebleria.maven.controlador.empleado.TransportistaController;
import com.uniovi.muebleria.maven.modelo.PersonalAlmacen.PersonalAlmacenModel;
import com.uniovi.muebleria.maven.modelo.Vendedor.VendedorModel;
import com.uniovi.muebleria.maven.modelo.empleado.EmpleadoModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.management.RuntimeErrorException;
import javax.swing.BoxLayout;

public class VistaCrearEmpleado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelDatos;
	private JLabel lblAñadirTrabajador;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblTelefono;
	private JLabel lblDNI;
	private JLabel lblUsuario;
	private JLabel lblContraseña;
	private JLabel lblPerfil;
	private JComboBox<String> comboBoxPerfil;
	private JTextField textFieldContraseña;
	private JTextField textFieldUsuario;
	private JTextField textFieldTelefono;
	private JTextField textFieldDNI;
	private JTextField textFieldApellido;
	private JTextField textFieldNombre;
	private JButton btnAñadir;
	private JLabel lblHoraEntrada;
	private JLabel lblHorarioSalida;
	private JSpinner spinnerEntrada;
	private JSpinner spinnerSalida;
	private JButton btnCancelar;


	/**
	 * Create the frame.
	 */
	public VistaCrearEmpleado() {
		setResizable(false);
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 722, 415);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.add(getPanelDatos());
		String[] perfiles = {"VENDEDOR" , "TRANSPORTISTA","PERSONAL ALMACEN"};
		getComboBoxPerfil().setModel(new DefaultComboBoxModel<String>(perfiles));
	}
	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			panelDatos = new JPanel();
			panelDatos.setBackground(new Color(255, 239, 213));
			panelDatos.setLayout(null);
			panelDatos.add(getLblAñadirTrabajador());
			panelDatos.add(getLblNombre());
			panelDatos.add(getLblApellido());
			panelDatos.add(getLblTelefono());
			panelDatos.add(getLblDNI());
			panelDatos.add(getLblUsuario());
			panelDatos.add(getLblContraseña());
			panelDatos.add(getLblPerfil());
			panelDatos.add(getComboBoxPerfil());
			panelDatos.add(getTextFieldContraseña());
			panelDatos.add(getTextFieldUsuario());
			panelDatos.add(getTextFieldTelefono());
			panelDatos.add(getTextFieldDNI());
			panelDatos.add(getTextFieldApellido());
			panelDatos.add(getTextFieldNombre());
			panelDatos.add(getSpinnerEntrada());
			panelDatos.add(getSpinnerSalida());
			panelDatos.add(getLblHoraEntrada());
			panelDatos.add(getLblHorarioSalida());
			panelDatos.add(getBtnAñadir());
			panelDatos.add(getBtnCancelar());
		}
		return panelDatos;
	}
	private JLabel getLblAñadirTrabajador() {
		if (lblAñadirTrabajador == null) {
			lblAñadirTrabajador = new JLabel("Añadir nuevo trabajador");
			lblAñadirTrabajador.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblAñadirTrabajador.setBounds(205, 55, 328, 25);
		}
		return lblAñadirTrabajador;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNombre.setBounds(64, 143, 93, 14);
		}
		return lblNombre;
	}
	private JLabel getLblApellido() {
		if (lblApellido == null) {
			lblApellido = new JLabel("Apellido: ");
			lblApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblApellido.setBounds(64, 168, 93, 14);
		}
		return lblApellido;
	}
	private JLabel getLblTelefono() {
		if (lblTelefono == null) {
			lblTelefono = new JLabel("Telefono: ");
			lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblTelefono.setBounds(64, 218, 93, 14);
		}
		return lblTelefono;
	}
	private JLabel getLblDNI() {
		if (lblDNI == null) {
			lblDNI = new JLabel("DNI: ");
			lblDNI.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblDNI.setBounds(64, 193, 93, 14);
		}
		return lblDNI;
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("Usuario: ");
			lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblUsuario.setBounds(64, 243, 93, 14);
		}
		return lblUsuario;
	}
	private JLabel getLblContraseña() {
		if (lblContraseña == null) {
			lblContraseña = new JLabel("Contraseña: ");
			lblContraseña.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblContraseña.setBounds(64, 268, 93, 14);
		}
		return lblContraseña;
	}
	private JLabel getLblPerfil() {
		if (lblPerfil == null) {
			lblPerfil = new JLabel("Perfil Laboral: ");
			lblPerfil.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblPerfil.setBounds(64, 293, 93, 14);
		}
		return lblPerfil;
	}
	private JComboBox<String> getComboBoxPerfil() {
		if (comboBoxPerfil == null) {
			comboBoxPerfil = new JComboBox<String>();
			comboBoxPerfil.setBackground(new Color(255, 239, 213));
			comboBoxPerfil.setBounds(205, 290, 153, 22);
		}
		return comboBoxPerfil;
	}
	private JTextField getTextFieldContraseña() {
		if (textFieldContraseña == null) {
			textFieldContraseña = new JTextField();
			textFieldContraseña.setBackground(new Color(255, 239, 213));
			textFieldContraseña.setBounds(205, 266, 153, 20);
			textFieldContraseña.setColumns(10);
		}
		return textFieldContraseña;
	}
	private JTextField getTextFieldUsuario() {
		if (textFieldUsuario == null) {
			textFieldUsuario = new JTextField();
			textFieldUsuario.setBackground(new Color(255, 239, 213));
			textFieldUsuario.setBounds(205, 241, 153, 20);
			textFieldUsuario.setColumns(10);
		}
		return textFieldUsuario;
	}
	private JTextField getTextFieldTelefono() {
		if (textFieldTelefono == null) {
			textFieldTelefono = new JTextField();
			textFieldTelefono.setBackground(new Color(255, 239, 213));
			textFieldTelefono.setBounds(205, 216, 153, 20);
			textFieldTelefono.setColumns(10);
		}
		return textFieldTelefono;
	}
	private JTextField getTextFieldDNI() {
		if (textFieldDNI == null) {
			textFieldDNI = new JTextField();
			textFieldDNI.setBackground(new Color(255, 239, 213));
			textFieldDNI.setBounds(205, 191, 153, 20);
			textFieldDNI.setColumns(10);
		}
		return textFieldDNI;
	}
	private JTextField getTextFieldApellido() {
		if (textFieldApellido == null) {
			textFieldApellido = new JTextField();
			textFieldApellido.setBackground(new Color(255, 239, 213));
			textFieldApellido.setBounds(205, 166, 153, 20);
			textFieldApellido.setColumns(10);
		}
		return textFieldApellido;
	}
	private JTextField getTextFieldNombre() {
		if (textFieldNombre == null) {
			textFieldNombre = new JTextField();
			textFieldNombre.setBackground(new Color(255, 239, 213));
			textFieldNombre.setBounds(205, 141, 153, 20);
			textFieldNombre.setColumns(10);
		}
		return textFieldNombre;
	}
	private JButton getBtnAñadir() {
		if (btnAñadir == null) {
			btnAñadir = new JButton("Añadir");
			btnAñadir.setBounds(422, 335, 89, 23);
			btnAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//try {
						añadirEmpleado();
						JOptionPane.showMessageDialog(null,"Empleado añadido con exito");
						closeWindow();
						inicializar();
//					}catch (Exception ex) {
//						JOptionPane.showMessageDialog(null,"Alguno de los campos esta vació o no es correcto");
//					}				
				}
			});
			btnAñadir.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return btnAñadir;
	}
	
	private JLabel getLblHoraEntrada() {
		if (lblHoraEntrada == null) {
			lblHoraEntrada = new JLabel("Horario Entrada");
			lblHoraEntrada.setBounds(422, 138, 138, 25);
			lblHoraEntrada.setFont(new Font("Tahoma", Font.BOLD, 12));
		}
		return lblHoraEntrada;
	}
	private JLabel getLblHorarioSalida() {
		if (lblHorarioSalida == null) {
			lblHorarioSalida = new JLabel("Horario Salida");
			lblHorarioSalida.setBounds(422, 207, 138, 25);
			lblHorarioSalida.setFont(new Font("Tahoma", Font.BOLD, 12));
		}
		return lblHorarioSalida;
	}
	@SuppressWarnings("deprecation")
	private JSpinner getSpinnerEntrada() {
		if (spinnerEntrada == null) {
			spinnerEntrada = new JSpinner(new SpinnerDateModel());
			spinnerEntrada.setBackground(new Color(255, 239, 213));
			spinnerEntrada.setBounds(422, 163, 131, 25);
			spinnerEntrada.setFont(new Font("Tahoma", Font.BOLD, 13));
			JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinnerEntrada, "HH:mm");
			spinnerEntrada.setEditor(timeEditor);
			spinnerEntrada.setValue(new Time(0,0,0));
		}
		return spinnerEntrada;
	}
	
	@SuppressWarnings("deprecation")
	private JSpinner getSpinnerSalida() {
		if (spinnerSalida == null) {
			spinnerSalida = new JSpinner(new SpinnerDateModel());
			spinnerSalida.setBackground(new Color(255, 239, 213));
			spinnerSalida.setBounds(422, 238, 131, 25);
			spinnerSalida.setFont(new Font("Tahoma", Font.BOLD, 13));
			JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinnerSalida, "HH:mm");
			spinnerSalida.setEditor(timeEditor);
			spinnerSalida.setValue(new Time(0,0,0));
		}
		return spinnerSalida;
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
			btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnCancelar.setBounds(527, 335, 103, 23);
		}
		return btnCancelar;
	}
	
	private void closeWindow() {
		this.dispose();
	}
	
	@SuppressWarnings("deprecation")
	private void inicializar() {
		getTextFieldApellido().setText("");
		getTextFieldNombre().setText("");
		getTextFieldContraseña().setText("");
		getTextFieldDNI().setText("");
		getTextFieldUsuario().setText("");
		getTextFieldTelefono().setText("");
		getSpinnerSalida().setValue(new Time(0,0,0));
		getSpinnerEntrada().setValue(new Time(0,0,0));
		
		
	}
//	String texto="Saludos desde Apuntesdejava.com";
//  String encriptMD5=DigestUtils.md5Hex(texto);
//  System.out.println("md5:"+encriptMD5);
	
	private void añadirEmpleado() {
		if(getComboBoxPerfil().getSelectedItem().equals("VENDEDOR")) {
		VendedorController controllerV = new VendedorController(new VendedorModel(), VistaMuebleria.VIEW_CREAR_EMPLEADO);
		String contraseñaencriptada = DigestUtils.md5Hex(getTextFieldContraseña().getText());
		controllerV.crearTrabajador(getTextFieldNombre().getText(),getTextFieldApellido().getText(),getTextFieldDNI().getText(),
				Integer.parseInt(getTextFieldTelefono().getText()),getTextFieldUsuario().getText(),contraseñaencriptada,
				(Date)getSpinnerEntrada().getValue(),(Date)getSpinnerSalida().getValue(),"v");
		} else if(getComboBoxPerfil().getSelectedItem().equals("PERSONAL ALMACEN")) {
			PersonalAlmacenController controllerM = new PersonalAlmacenController(new PersonalAlmacenModel(), VistaMuebleria.VIEW_CREAR_EMPLEADO);
			String contraseñaencriptada = DigestUtils.md5Hex(getTextFieldContraseña().getText());
			controllerM.crearTrabajador(getTextFieldNombre().getText(),getTextFieldApellido().getText(),getTextFieldDNI().getText(),
					Integer.parseInt(getTextFieldTelefono().getText()),getTextFieldUsuario().getText(),contraseñaencriptada,
					(Date)getSpinnerEntrada().getValue(),(Date)getSpinnerSalida().getValue(),"pa");
		} else {
			TransportistaController controllerT = new TransportistaController(new EmpleadoModel());
			String contraseñaencriptada = DigestUtils.md5Hex(getTextFieldContraseña().getText());
			controllerT.crearTrabajador(getTextFieldNombre().getText(),Integer.parseInt(getTextFieldTelefono().getText()),
					(Date)getSpinnerEntrada().getValue(),(Date)getSpinnerSalida().getValue(),getTextFieldApellido().getText(),getTextFieldDNI().getText(),
					getTextFieldUsuario().getText(),contraseñaencriptada,"t");
		}
		
	}
}
