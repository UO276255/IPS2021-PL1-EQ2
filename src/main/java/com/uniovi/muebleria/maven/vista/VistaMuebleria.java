package com.uniovi.muebleria.maven.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uniovi.muebleria.maven.controlador.Presupuestos.PresupuestoController;
import com.uniovi.muebleria.maven.controlador.transportista.TransportistaController;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestosModel;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaModel;
import com.uniovi.muebleria.maven.util.Database;


public class VistaMuebleria extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel PanelInicio;
	public static  VistaAsignaTransporte VIEW_TRANSPORTE = new VistaAsignaTransporte();
	public static  VistaAsignarPresupuesto VIEW_PRESUPUESTO = new VistaAsignarPresupuesto();

	/**
	 * Create the frame.
	 * @param list 
	 */
	public VistaMuebleria() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 515);
		PanelInicio = new JPanel();
		PanelInicio.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelInicio);
		PanelInicio.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		PanelInicio.add(panel, BorderLayout.CENTER);
		
		JButton btnCargarBaseDatos = new JButton("Cargar la base de datos");
		btnCargarBaseDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
				db.loadDatabase();
			}
		});
		panel.add(btnCargarBaseDatos);
		
		JPanel PanelBotones = new JPanel();
		PanelInicio.add(PanelBotones, BorderLayout.EAST);
		PanelBotones.setLayout(new GridLayout(6, 1, 0, 0));
		
		JButton btnAsignarPresupuesto = new JButton("Asignar Presupuesto");
		btnAsignarPresupuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PresupuestoController controller = new PresupuestoController(new PresupuestosModel(),  VIEW_PRESUPUESTO);
				controller.initView();
				if(VIEW_PRESUPUESTO.getComboBoxPresupuestos().getItemCount() == 0) {
					VIEW_PRESUPUESTO.getBtnAsignarNuevo().setEnabled(false);
					VIEW_PRESUPUESTO.getBtnAsignarExistente().setEnabled(false);
					JOptionPane.showMessageDialog(null, "No hay ningun presupuesto sin asignar, lo siento");
				} else {
					VIEW_PRESUPUESTO.getBtnAsignarNuevo().setEnabled(true);
					VIEW_PRESUPUESTO.getBtnAsignarExistente().setEnabled(true);
				}
			}
		});
		PanelBotones.add(btnAsignarPresupuesto);
		
		JButton btnAsignarTransporte = new JButton("Asignar Transporte");
		btnAsignarTransporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransportistaController controller = new TransportistaController(new TransportistaModel(), VIEW_TRANSPORTE);
				controller.initView();
			}
		});
		PanelBotones.add(btnAsignarTransporte);
		
		JButton btnFechaEntrega = new JButton("Determinar fecha de entrega");
		btnFechaEntrega.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				abrirOpcionFecha();
			}

		});

		PanelBotones.add(btnFechaEntrega);
		
		JButton btnVisualiarPresupuestos = new JButton("Visualizar Presupuestos");
		PanelBotones.add(btnVisualiarPresupuestos);
		
		JButton btnVisualizarHistorial = new JButton("Visualizar historial de ventas");
		PanelBotones.add(btnVisualizarHistorial);
		
		JButton btnSeguimientoPedido = new JButton("Seguimiento de pedidos");
		PanelBotones.add(btnSeguimientoPedido);
	}

	public JFrame getFrame() { return this.getFrame(); }

	private void abrirOpcionFecha() {
		// TODO Auto-generated method stub
		
	}

}
