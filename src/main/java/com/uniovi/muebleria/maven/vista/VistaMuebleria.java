package com.uniovi.muebleria.maven.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import com.uniovi.muebleria.maven.controlador.Almacen.AlmacenController;
import com.uniovi.muebleria.maven.controlador.Presupuestos.PresupuestoController;
import com.uniovi.muebleria.maven.controlador.Venta.VentaController;
import com.uniovi.muebleria.maven.controlador.empleado.TransportistaController;
import com.uniovi.muebleria.maven.controlador.pedidos.PedidoController;
import com.uniovi.muebleria.maven.controlador.producto.ProductoController;
import com.uniovi.muebleria.maven.controlador.producto.ProductoPresupuestoController;
import com.uniovi.muebleria.maven.modelo.Almacen.AlmacenModel;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestosModel;
import com.uniovi.muebleria.maven.modelo.empleado.EmpleadoModel;
import com.uniovi.muebleria.maven.modelo.pedidos.PedidoModel;
import com.uniovi.muebleria.maven.modelo.producto.ProductoModel;
import com.uniovi.muebleria.maven.modelo.producto.ProductoPresupuestoModel;
import com.uniovi.muebleria.maven.modelo.ventas.VentaModel;
import com.uniovi.muebleria.maven.util.Database;
import java.awt.Color;
import java.awt.Font;


public class VistaMuebleria extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel PanelInicio;
	
	public static VistaAsignaTransporte VIEW_TRANSPORTE = new VistaAsignaTransporte();
	public static VistaCreacionVentas VIEW_VENTAS = new VistaCreacionVentas();
	public static VistaAsignarPresupuesto VIEW_PRESUPUESTO = new VistaAsignarPresupuesto();
	public static VistaDeterminaFecha VIEW_VENTA = new VistaDeterminaFecha();
	public static VistaSeguimientoPedido VIEW_SEGUIMIENTO = new VistaSeguimientoPedido();
	public static final VistaHistorial VIEW_HISTORIAL = new VistaHistorial();
	public static final VistaAlmacenes VIEW_ALMACEN = new VistaAlmacenes();
	public static final VistaCrearPresupuesto VIEW_PRODPRES = new VistaCrearPresupuesto();
	public static final VistaCrearEmpleado VIEW_CREAR_EMPLEADO = new VistaCrearEmpleado();
	public static final VistaEntregarPedido VIEW_ENTREGAR_PEDIDO = new VistaEntregarPedido();
	public static final VistaCrearPedido VIEW_PEDIDO = new VistaCrearPedido();
	public static final VistaGestionaVacaciones VIEW_GESTIONA_VACACIONES = new VistaGestionaVacaciones();
	public static final VistaActualizarPrecios VIEW_ACTUALIZAR_PRECIOS = new VistaActualizarPrecios();
	public static final VistaVisualizarPresupuestos VIEW_VISUALIZAR_PRESUPUESTOS = new VistaVisualizarPresupuestos();
	public static VistaCreacionGraficos VIEW_GRAFICOS = new VistaCreacionGraficos();

	private Database db=null;
	private JButton btnGestionaVacaciones;
	private JButton btnBorrarBaseDatos;
	private JButton btnCargarBaseDatos;
	private JButton btnLoadDB;
	private JButton btnAsignarPresupuesto;
	private JButton btnAsignarTransporte;
	private JButton btnFechaEntrega;
	private JButton btnCrearVenta;
	private JButton btnVisualizarHistorial;
	private JButton btnSeguimientoPedido;
	private JButton btnVerAlmacenes;
	private JButton btCrearPedido;
	private JButton btnNewButton;
	private JButton btnAñadirEmpleado;
	private JButton btnCreaPresupuesto;
	private JPanel PanelBotones;
	private JPanel  panel;
	private JButton btActualizarPrecios;
	private JButton btnGraficosRentabilidad;
	private JButton btnNewButton_1;
	
	/**
	 * Create the frame.
	 * @param list 
	 */
	public VistaMuebleria() {
		setTitle("Muebleria");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 647, 442);
		PanelInicio = new JPanel();
		PanelInicio.setBackground(new Color(255, 239, 213));
		PanelInicio.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelInicio);
		PanelInicio.setLayout(new BorderLayout(0, 0));
		
		 panel = new JPanel();
		panel.setBackground(new Color(255, 239, 213));
		PanelInicio.add(panel, BorderLayout.CENTER);
		
		db=new Database();
		
		btnBorrarBaseDatos = new JButton("Borrar la base de datos");
		btnBorrarBaseDatos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnBorrarBaseDatos.setBounds(10, 28, 185, 23);
		btnBorrarBaseDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i=0; i<3;i++)
					db.removeDatabase();
			}
		});
		
		btnCargarBaseDatos = new JButton("Crear la base de datos");
		btnCargarBaseDatos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnCargarBaseDatos.setBounds(219, 39, 170, 23);
		btnCargarBaseDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.createDatabase(true);
			}
		});
		
		btnLoadDB = new JButton("Cargar la base de datos");
		btnLoadDB.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnLoadDB.setBounds(415, 28, 196, 23);
		btnLoadDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.loadDatabase();
			}
		});
		panel.setLayout(null);
		panel.add(btnBorrarBaseDatos);
		panel.add(btnCargarBaseDatos);
		panel.add(btnLoadDB);
		
		PanelBotones = new JPanel();
		PanelBotones.setBackground(new Color(255, 239, 213));
		PanelBotones.setBounds(10, 101, 601, 281);
		panel.add(PanelBotones);
		PanelBotones.setLayout(new GridLayout(8, 1, 0, 0));
		
		btnAsignarPresupuesto = new JButton("Asignar Presupuesto");
		btnAsignarPresupuesto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAsignarPresupuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PresupuestoController controller = new PresupuestoController(new PresupuestosModel(),  VIEW_PRESUPUESTO);
				controller.initViewPresupuesto();
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
		
		btnAsignarTransporte = new JButton("Asignar Transporte");
		btnAsignarTransporte.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAsignarTransporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransportistaController controllerT = new TransportistaController(new EmpleadoModel(), VIEW_TRANSPORTE);
				controllerT.initView();
			}
		});
		PanelBotones.add(btnAsignarTransporte);
		
		btnFechaEntrega = new JButton("Determinar fecha de entrega");
		btnFechaEntrega.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnFechaEntrega.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				VentaController controllerF = new VentaController(new VentaModel(), VIEW_VENTA);
			}
		});

		PanelBotones.add(btnFechaEntrega);
		
		btnCrearVenta = new JButton("Crear una venta");
		btnCrearVenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCrearVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PresupuestoController controller = new PresupuestoController(new PresupuestosModel(), VIEW_VENTAS);
				//controller.initViewVentas();
				
				if(VIEW_VENTAS.getComboBoxPresupuestoSinAceptar().getItemCount() == 0) {		
					JOptionPane.showMessageDialog(null, "No hay ningun presupuesto sin aceptar, lo siento");
				} else {
					
				}

				
			}
		});
		PanelBotones.add(btnCrearVenta);
		
		btnVisualizarHistorial = new JButton("Visualizar historial de ventas");
		btnVisualizarHistorial.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVisualizarHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentaController controller = new VentaController(new VentaModel(), VIEW_HISTORIAL);
				controller.initViewHistorial();
			}
		});
		
		btnCreaPresupuesto = new JButton("Crear un presupuesto");
		btnCreaPresupuesto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCreaPresupuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductoPresupuestoController controller = new ProductoPresupuestoController(new ProductoPresupuestoModel(), VIEW_PRODPRES);
				controller.initViewProdPres();
			}
		});
		PanelBotones.add(btnCreaPresupuesto);
		PanelBotones.add(btnVisualizarHistorial);
		
		btnSeguimientoPedido = new JButton("Seguimiento de pedidos");
		btnSeguimientoPedido.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSeguimientoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoController controller = new PedidoController(new PedidoModel(), VIEW_SEGUIMIENTO);
				controller.initView();
				VIEW_SEGUIMIENTO.getSpinnerIdPed().setModel(new SpinnerNumberModel(1, 1, VIEW_SEGUIMIENTO.getMaxIdPedido(), 1));
			}
		});
		PanelBotones.add(btnSeguimientoPedido);
		
		btnVerAlmacenes = new JButton("Visualizar Almacenes");
		btnVerAlmacenes.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVerAlmacenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlmacenController controller = new AlmacenController(new AlmacenModel(),VIEW_ALMACEN);
				CardLayout c = (CardLayout) VIEW_ALMACEN.getPanelLista().getLayout();
				c.show(VIEW_ALMACEN.getPanelLista(), "panelElegirAlmacen");		
				controller.initView();
					
			}
		});
		PanelBotones.add(btnVerAlmacenes);
		
		btCrearPedido = new JButton("Crear Pedido");
		btCrearPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductoController controller = new ProductoController(new ProductoModel(), VIEW_PEDIDO);
			}
		});
		
		btnNewButton = new JButton("Entregar Pedido");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentaController controller = new VentaController(new VentaModel(), VIEW_ENTREGAR_PEDIDO);
				controller.initViewEntregaPedido();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		PanelBotones.add(btnNewButton);
		btCrearPedido.setFont(new Font("Tahoma", Font.BOLD, 12));
		PanelBotones.add(btCrearPedido);
		
		btnAñadirEmpleado = new JButton("Añadir nuevo empleado");
		btnAñadirEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VIEW_CREAR_EMPLEADO.setVisible(true);
			}
		});
		
		btActualizarPrecios = new JButton("Actualizar Precios");
		btActualizarPrecios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductoController controller = new ProductoController(new ProductoModel(), VIEW_ACTUALIZAR_PRECIOS);
				controller.initViewActualizaPrecios();
			}
		});
		btActualizarPrecios.setFont(new Font("Tahoma", Font.BOLD, 12));
		PanelBotones.add(btActualizarPrecios);
		btnAñadirEmpleado.setFont(new Font("Tahoma", Font.BOLD, 12));
		PanelBotones.add(btnAñadirEmpleado);
		PanelBotones.add(getBtnGestionaVacaciones());
		
		btnGraficosRentabilidad = new JButton("Graficos de rentabilidad");
		btnGraficosRentabilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VIEW_GRAFICOS = new VistaCreacionGraficos();
				VIEW_GRAFICOS.setVisible(true);
			}
		});
		
		btnNewButton_1 = new JButton("Visualizar Presupuesto");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PresupuestoController controller = new PresupuestoController(new PresupuestosModel(), VIEW_VISUALIZAR_PRESUPUESTOS,true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		PanelBotones.add(btnNewButton_1);
		btnGraficosRentabilidad.setFont(new Font("Tahoma", Font.BOLD, 12));
		PanelBotones.add(btnGraficosRentabilidad);
	}

	public JFrame getFrame() { return this.getFrame(); }
	private JButton getBtnGestionaVacaciones() {
		if (btnGestionaVacaciones == null) {
			btnGestionaVacaciones = new JButton("Gestionar Vacaciones");
			btnGestionaVacaciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VIEW_GESTIONA_VACACIONES.setVisible(true);
					VIEW_GESTIONA_VACACIONES.iniciar();
				}
			});
			btnGestionaVacaciones.setFont(new Font("Tahoma", Font.BOLD, 12));
		}
		return btnGestionaVacaciones;
	}

	public void bloquearVendedor() {
		PanelBotones.remove(btnAsignarTransporte);
		PanelBotones.remove(btnFechaEntrega);
		PanelBotones.remove(btnAñadirEmpleado);
		PanelBotones.remove(btnGestionaVacaciones);
		PanelBotones.remove(btnVerAlmacenes);
		panel.remove(btnBorrarBaseDatos);
		panel.remove(btnCargarBaseDatos);
		panel.remove(btnLoadDB);
		PanelBotones.remove(btnGraficosRentabilidad);
	}
	
	public void bloquearAlmacen() {
		PanelBotones.remove(btnAsignarTransporte);
		PanelBotones.remove(btnFechaEntrega);
		PanelBotones.remove(btnAñadirEmpleado);
		PanelBotones.remove(btnGestionaVacaciones);
		PanelBotones.remove(btCrearPedido);
		PanelBotones.remove(btnVisualizarHistorial);
		PanelBotones.remove(btnCreaPresupuesto);
		PanelBotones.remove(btnCrearVenta);
		PanelBotones.remove(btnAsignarPresupuesto);
		PanelBotones.remove(btnSeguimientoPedido);
		PanelBotones.remove(btnNewButton);
		panel.remove(btnBorrarBaseDatos);
		panel.remove(btnCargarBaseDatos);
		panel.remove(btnLoadDB);
		PanelBotones.remove(btnGraficosRentabilidad);
		PanelBotones.remove(btActualizarPrecios);
	}
	
	public void bloquearTransportista() {
		PanelBotones.remove(btnAsignarTransporte);
		PanelBotones.remove(btnFechaEntrega);
		PanelBotones.remove(btnAñadirEmpleado);
		PanelBotones.remove(btnGestionaVacaciones);
		PanelBotones.remove(btnVerAlmacenes);
		PanelBotones.remove(btCrearPedido);
		PanelBotones.remove(btnVisualizarHistorial);
		PanelBotones.remove(btnVerAlmacenes);
		PanelBotones.remove(btnCreaPresupuesto);
		PanelBotones.remove(btnCrearVenta);
		PanelBotones.remove(btnAsignarPresupuesto);
		PanelBotones.remove(btnSeguimientoPedido);
		PanelBotones.remove(btnGraficosRentabilidad);
		panel.remove(btnBorrarBaseDatos);
		panel.remove(btnCargarBaseDatos);
		panel.remove(btnLoadDB);
		PanelBotones.remove(btActualizarPrecios);
	}

	public void ventanaAdministrador() {

	}
}
