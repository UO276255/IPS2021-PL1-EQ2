package com.uniovi.muebleria.maven.vista;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.codec.digest.DigestUtils;

import com.uniovi.muebleria.maven.controlador.PersonalAlmacen.PersonalAlmacenController;
import com.uniovi.muebleria.maven.controlador.Vendedor.VendedorController;
import com.uniovi.muebleria.maven.controlador.empleado.TransportistaController;
import com.uniovi.muebleria.maven.modelo.PersonalAlmacen.PersonalAlmacenModel;
import com.uniovi.muebleria.maven.modelo.Vendedor.VendedorModel;
import com.uniovi.muebleria.maven.modelo.empleado.TransportistaModel;

public class VistaLogin extends JFrame {
	private static final long serialVersionUID = -5572975645091268493L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private VistaMuebleria vm = new VistaMuebleria();
	private JLabel lblLogIn;
	private JLabel lblUsuario;
	private JTextField textFieldUsuario;
	private JLabel lblContraseña;
	private JPasswordField passwordFieldContraseña;
	private JButton btnAdministrador;

	/**
	 * Create the frame.
	 */
	public VistaLogin() {
		setTitle("Mueblería");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 484, 301);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNewButton());
		contentPane.add(getLblLogIn());
		contentPane.add(getLblUsuario());
		contentPane.add(getTextFieldUsuario());
		contentPane.add(getLblContraseña());
		contentPane.add(getPasswordFieldContraseña());
		contentPane.add(getBtnAdministrador());
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Login");
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnNewButton.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					switch(tipoEmpleado(getPasswordFieldContraseña().getText())) {
					case 1:	vm.setVisible(true);vm.setLocationRelativeTo(null);vm.bloquearVendedor();closeWindow(); break;
					case 2:	vm.setVisible(true);vm.setLocationRelativeTo(null);vm.bloquearAlmacen();closeWindow(); break;
					case 3:	vm.setVisible(true);vm.setLocationRelativeTo(null);vm.bloquearTransportista();closeWindow(); break;
					case 0:JOptionPane.showMessageDialog(null,"Credenciales incorrectas");break;
					}
				}
			});
			btnNewButton.setBounds(369, 228, 89, 23);
		}
		return btnNewButton;
	}
	private JLabel getLblLogIn() {
		if (lblLogIn == null) {
			lblLogIn = new JLabel("Introduzca su usuario y su contraseña para acceder");
			lblLogIn.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblLogIn.setBounds(66, 26, 355, 15);
		}
		return lblLogIn;
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("Usuario: ");
			lblUsuario.setBounds(66, 94, 74, 14);
		}
		return lblUsuario;
	}
	private JTextField getTextFieldUsuario() {
		if (textFieldUsuario == null) {
			textFieldUsuario = new JTextField();
			textFieldUsuario.setBounds(149, 92, 191, 17);
			textFieldUsuario.setColumns(10);
		}
		return textFieldUsuario;
	}
	private JLabel getLblContraseña() {
		if (lblContraseña == null) {
			lblContraseña = new JLabel("Contraseña: ");
			lblContraseña.setBounds(66, 130, 89, 14);
		}
		return lblContraseña;
	}
	private JPasswordField getPasswordFieldContraseña() {
		if (passwordFieldContraseña == null) {
			passwordFieldContraseña = new JPasswordField();
			passwordFieldContraseña.setBounds(149, 127, 191, 17);
		}
		return passwordFieldContraseña;
	}
	private JButton getBtnAdministrador() {
		if (btnAdministrador == null) {
			btnAdministrador = new JButton("Administrador");
			btnAdministrador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showInputDialog("Introduce la contraseña del aministrador").equals("123456")) {
						vm.setVisible(true);
						vm.ventanaAdministrador();
						closeWindow();
					};
				}
			});
			btnAdministrador.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnAdministrador.setBounds(10, 229, 130, 23);
		}
		return btnAdministrador;
	}
	
	private void closeWindow() {
		this.dispose();
	}
	
	@SuppressWarnings("deprecation")
	protected int tipoEmpleado(String text) {
		PersonalAlmacenController controllerAlmacen = new PersonalAlmacenController(new PersonalAlmacenModel(), null);
		TransportistaController controllerTransp = new TransportistaController(new TransportistaModel(), null);
		VendedorController controllerVendedor = new VendedorController(new VendedorModel(), null);
		if(controllerVendedor.LoginDeVendedor(getTextFieldUsuario().getText(), DigestUtils.md5Hex(getPasswordFieldContraseña().getText()))) {
			return 1;
		} else if(controllerAlmacen.LoginDeAlmacen(getTextFieldUsuario().getText(), DigestUtils.md5Hex(getPasswordFieldContraseña().getText()))) {
			return 2;
		} else if(controllerTransp.LoginDeTransportista(getTextFieldUsuario().getText(), DigestUtils.md5Hex(getPasswordFieldContraseña().getText()))) {
			return 3;
		}
		return 0;
	}
}
