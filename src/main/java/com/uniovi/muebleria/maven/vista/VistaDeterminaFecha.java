package com.uniovi.muebleria.maven.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JCalendar;
import com.uniovi.muebleria.maven.controlador.Venta.VentaController;
import com.uniovi.muebleria.maven.modelo.empleado.EmpleadoDTO;
import com.uniovi.muebleria.maven.modelo.producto.ProductoDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaModel;

public class VistaDeterminaFecha extends JFrame {

	private JPanel contentPane;
	private JCalendar calendar;
	private JPanel pnAsignarCancelar;
	private JButton btAsignar;
	private JButton btCancelar;
	private JList list;
	private List listaProductos;
	private JComboBox cbSeleccionarVenta;
	private VentaDTO venta;
	private JSpinner spTimer;
	List listaTransportista;
	private EmpleadoDTO transportistaSeleccionado;

	/**
	 * Create the frame.
	 */
	public VistaDeterminaFecha() {
		setResizable(false);
		
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 773, 620);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCalendar());
		contentPane.add(getPnAsignarCancelar());
		contentPane.add(getList());
		
		JPanel panelRecoger = new JPanel();
		panelRecoger.setBounds(27, 96, 289, 335);
		contentPane.add(panelRecoger);
		panelRecoger.setLayout(new BorderLayout(0, 0));
		
		JLabel lbProductos = new JLabel("Productos con transporte disponible :");
		lbProductos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbProductos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelRecoger.add(lbProductos, BorderLayout.NORTH);
		
		listaProductos = new List();
		listaProductos.setBackground(new Color(255, 239, 213));
		panelRecoger.add(listaProductos, BorderLayout.CENTER);
		
		JPanel panelTransporte = new JPanel();
		panelTransporte.setBounds(326, 96, 404, 58);
		contentPane.add(panelTransporte);
		panelTransporte.setLayout(new BorderLayout(0, 0));
		
		JLabel lbTransportista = new JLabel("Transportista :");
		lbTransportista.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTransportista.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTransporte.add(lbTransportista, BorderLayout.NORTH);
		
		 listaTransportista = new List();
		 listaTransportista.setBackground(new Color(255, 239, 213));
		panelTransporte.add(listaTransportista, BorderLayout.CENTER);
		contentPane.add(getCbSeleccionarVenta());
		
		JButton btnNewButton_2 = new JButton("Seleccionar Venta");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionaVenta();
			}
		});
		btnNewButton_2.setBackground(new Color(0, 250, 154));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBounds(27, 62, 151, 23);
		contentPane.add(btnNewButton_2);
		
		spTimer = new JSpinner(new SpinnerDateModel());
		spTimer.setBackground(new Color(255, 239, 213));
		spTimer.setFont(new Font("Tahoma", Font.BOLD, 14));
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spTimer, "HH:mm");
		spTimer.setEditor(timeEditor);
		spTimer.setValue(new Date(0,0,0,0,0));
		spTimer.setBounds(469, 400, 123, 31);
		contentPane.add(spTimer);
	}
	@SuppressWarnings("deprecation")
	private void seleccionaVenta() {
		venta=(VentaDTO) cbSeleccionarVenta.getSelectedItem();
		int idx = cbSeleccionarVenta.getSelectedIndex();
		VentaController controller = new VentaController(new VentaModel(), VistaMuebleria.VIEW_VENTA);
		ProductoDTO[] productos = controller.getListaProductos(venta, true);
		getCbSeleccionarVenta().setSelectedIndex(idx);
		listaProductos.clear();
		listaTransportista.clear();
		for(int i=0; i< productos.length; i++)
			listaProductos.add(productos[i].toString());
			
		EmpleadoDTO transportista = controller.getTransportista(venta);
		if (transportista==null) {
			JOptionPane.showMessageDialog(null, "La venta no tiene transportista asignado");
		}
		else
			transportistaSeleccionado = transportista;
			listaTransportista.add(transportista.toString());
	}

	public void setCbSeleccionarVenta(JComboBox cbSeleccionarVenta) {
		this.cbSeleccionarVenta = cbSeleccionarVenta;
	}

	private JCalendar getCalendar() {
		if (calendar == null) {
			calendar = new JCalendar();
			calendar.setBounds(326, 165, 404, 205);
		}
		return calendar;
	}
	private JPanel getPnAsignarCancelar() {
		if (pnAsignarCancelar == null) {
			pnAsignarCancelar = new JPanel();
			pnAsignarCancelar.setBackground(new Color(255, 239, 213));
			pnAsignarCancelar.setBounds(252, 488, 277, 58);
			pnAsignarCancelar.setLayout(new GridLayout(1, 0, 0, 0));
			pnAsignarCancelar.add(getBtAsignar());
			pnAsignarCancelar.add(getBtCancelar());
		}
		return pnAsignarCancelar;
	}
	private JButton getBtAsignar() {
		if (btAsignar == null) {
			btAsignar = new JButton("Asignar");
			btAsignar.setFont(new Font("Tahoma", Font.BOLD, 12));
			btAsignar.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					VentaController controller = new VentaController(new VentaModel(), VistaMuebleria.VIEW_VENTA);
					Date time = (Date) spTimer.getValue();
					Date date = getCalendar().getDate();
					Date actual = Calendar.getInstance().getTime();
					ArrayList<Date> dateInicioActual = controller.getDiaInicioVacaciones(transportistaSeleccionado.getId());
					ArrayList<Date> dateFinaloActual = controller.getDiaFinalVacaciones(transportistaSeleccionado.getId());
					if (date.getDay() == 0) {
							JOptionPane.showMessageDialog(null, "Debe asignarse una fecha en días laborables");
					} else if (date.compareTo(actual)<0) {
						JOptionPane.showMessageDialog(null, "La fecha asignada debe ser posterior a la actual");
					} else if (date.compareTo(actual)==0 && time.getHours() < actual.getHours()) {
						JOptionPane.showMessageDialog(null, "La fecha asignada debe ser posterior a la actual");
					} else if (date.compareTo(actual)==0 && time.getHours() == actual.getHours() 
							&& time.getMinutes() < actual.getMinutes()) {
						JOptionPane.showMessageDialog(null, "La fecha asignada debe ser posterior a la actual");
					} else if(!dateInicioActual.isEmpty()) {
						for(int i=0; i<dateInicioActual.size(); i++) {
							if(dateInicioActual.get(i).compareTo(date)<0 && date.compareTo(dateFinaloActual.get(i))<0) {
								JOptionPane.showMessageDialog(null, "Este empleado estará en este momento en periodo vacacional.");
							}
						}
					} else {
						if (controller.asignaFechaVenta(venta,date,time)) {
							JOptionPane.showMessageDialog(null, "Se asignó la fecha correctamente");
						} else {
							JOptionPane.showMessageDialog(null, "No está dentro del horario del transportista");
						}
					}
				}
			});
			btAsignar.setBackground(Color.GREEN);
		}
		return btAsignar;
	}
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btCancelar.setBackground(Color.RED);
		}
		return btCancelar;
	}
	private JList getList() {
		if (list == null) {
			list = new JList();
			list.setBackground(Color.WHITE);
			list.setForeground(Color.BLACK);
			list.setBounds(68, 488, 269, -277);
		}
		return list;
	}
	public JComboBox getCbSeleccionarVenta() {
		if (cbSeleccionarVenta == null) {
			cbSeleccionarVenta = new JComboBox();
			cbSeleccionarVenta.setBounds(27, 29, 703, 22);
		}
		return cbSeleccionarVenta;
	}
}
