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
import com.uniovi.muebleria.maven.controlador.pedidos.PedidoController;
import com.uniovi.muebleria.maven.controlador.producto.ProductoController;
import com.uniovi.muebleria.maven.controlador.producto.ProductoPresupuestoController;
import com.uniovi.muebleria.maven.controlador.transportista.TransportistaController;
import com.uniovi.muebleria.maven.modelo.Almacen.AlmacenModel;
import com.uniovi.muebleria.maven.modelo.Presupuesto.PresupuestosModel;
import com.uniovi.muebleria.maven.modelo.pedidos.PedidoModel;
import com.uniovi.muebleria.maven.modelo.producto.ProductoModel;
import com.uniovi.muebleria.maven.modelo.producto.ProductoPresupuestoModel;
import com.uniovi.muebleria.maven.modelo.transportista.TransportistaModel;
import com.uniovi.muebleria.maven.modelo.ventas.VentaModel;
import com.uniovi.muebleria.maven.util.Database;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
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

	protected static final VistaCrearPedido VIEW_PEDIDO = new VistaCrearPedido();

	private Database db=null;
	
	/**
	 * Create the frame.
	 * @param list 
	 */
	public VistaMuebleria() {
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 647, 442);
		PanelInicio = new JPanel();
		PanelInicio.setBackground(new Color(255, 239, 213));
		PanelInicio.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelInicio);
		PanelInicio.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 239, 213));
		PanelInicio.add(panel, BorderLayout.CENTER);
		
		db=new Database();
		
		JButton btnCargarBaseDatos = new JButton("Crear la base de datos");
		btnCargarBaseDatos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnCargarBaseDatos.setBounds(117, 28, 170, 23);
		btnCargarBaseDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.createDatabase(true);
				//db.loadDatabase();
			}
		});
		
		JButton btnLoadDB = new JButton("Cargar la base de datos");
		btnLoadDB.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnLoadDB.setBounds(325, 28, 196, 23);
		btnLoadDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.loadDatabase();
			}
		});
		panel.setLayout(null);
		panel.add(btnCargarBaseDatos);
		panel.add(btnLoadDB);
		
		JPanel PanelBotones = new JPanel();
		PanelBotones.setBackground(new Color(255, 239, 213));
		PanelBotones.setBounds(10, 101, 601, 281);
		panel.add(PanelBotones);
		PanelBotones.setLayout(new GridLayout(8, 1, 0, 0));
		
		JButton btnAsignarPresupuesto = new JButton("Asignar Presupuesto");
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
		
		JButton btnAsignarTransporte = new JButton("Asignar Transporte");
		btnAsignarTransporte.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAsignarTransporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransportistaController controllerT = new TransportistaController(new TransportistaModel(), VIEW_TRANSPORTE);
				controllerT.initView();
			}
		});
		PanelBotones.add(btnAsignarTransporte);
		
		JButton btnFechaEntrega = new JButton("Determinar fecha de entrega");
		btnFechaEntrega.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnFechaEntrega.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				VentaController controllerF = new VentaController(new VentaModel(), VIEW_VENTA);
			}

		});

		PanelBotones.add(btnFechaEntrega);
		
		JButton btnCrearVenta = new JButton("Crear una venta");
		btnCrearVenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCrearVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PresupuestoController controller = new PresupuestoController(new PresupuestosModel(), VIEW_VENTAS);
				controller.initViewVentas();
				
				if(VIEW_VENTAS.getComboBoxPresupuestoSinAceptar().getItemCount() == 0) {		
					JOptionPane.showMessageDialog(null, "No hay ningun presupuesto sin aceptar, lo siento");
				} else {
					
				}

				
			}
		});
		PanelBotones.add(btnCrearVenta);
		
		JButton btnVisualizarHistorial = new JButton("Visualizar historial de ventas");
		btnVisualizarHistorial.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVisualizarHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentaController controller = new VentaController(new VentaModel(), VIEW_HISTORIAL);
				controller.initViewHistorial();
			}
		});
		
		JButton btnCreaPresupuesto = new JButton("Crear un presupuesto");
		btnCreaPresupuesto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCreaPresupuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductoPresupuestoController controller = new ProductoPresupuestoController(new ProductoPresupuestoModel(), VIEW_PRODPRES);
				controller.initViewProdPres();
			}
		});
		PanelBotones.add(btnCreaPresupuesto);
		PanelBotones.add(btnVisualizarHistorial);
		
		JButton btnSeguimientoPedido = new JButton("Seguimiento de pedidos");
		btnSeguimientoPedido.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSeguimientoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoController controller = new PedidoController(new PedidoModel(), VIEW_SEGUIMIENTO);
				controller.initView();
				VIEW_SEGUIMIENTO.getSpinnerIdProd().setModel(new SpinnerNumberModel(1, 1, VIEW_SEGUIMIENTO.getMaxIdProv(), 1));
			}
		});
		PanelBotones.add(btnSeguimientoPedido);
		
		JButton btnVerAlmacenes = new JButton("Visualizar Almacenes");
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
		
		JButton btCrearPedido = new JButton("Crear Pedido");
		btCrearPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductoController controller = new ProductoController(new ProductoModel(), VIEW_PEDIDO);
			}
		});
		btCrearPedido.setFont(new Font("Tahoma", Font.BOLD, 12));
		PanelBotones.add(btCrearPedido);
		
		JButton btnAñadirEmpleado = new JButton("Añadir nuevo empleado");
		btnAñadirEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VIEW_CREAR_EMPLEADO.setVisible(true);
			}
		});
		btnAñadirEmpleado.setFont(new Font("Tahoma", Font.BOLD, 12));
		PanelBotones.add(btnAñadirEmpleado);
	}

	public JFrame getFrame() { return this.getFrame(); }


}
