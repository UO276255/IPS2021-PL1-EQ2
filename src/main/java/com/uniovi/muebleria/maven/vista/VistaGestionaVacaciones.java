package com.uniovi.muebleria.maven.vista;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;
import com.uniovi.muebleria.maven.controlador.empleado.VacacionesController;
import com.uniovi.muebleria.maven.modelo.empleado.EmpleadoDTO;
import com.uniovi.muebleria.maven.modelo.empleado.VacacionesModel;

public class VistaGestionaVacaciones extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPaneEmpleados;
	private JList<EmpleadoDTO> listEmpleados;
	private DefaultListModel<EmpleadoDTO> modeloListEmpleados = new DefaultListModel<EmpleadoDTO>();
	private JLabel lblEmpleados;
	private JLabel lblFechaInicio;
	private JCalendar calendarFechaInicio;
	private JLabel lblFechaFin;
	private JCalendar calendarFechaFin;
	private JButton btnAsignar;
	private JButton btnCancelar;

	/**
	 * Create the frame.
	 */
	public VistaGestionaVacaciones() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 746, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPaneEmpleados());
		contentPane.add(getLblEmpleados());
		contentPane.add(getLblFechaInicio());
		contentPane.add(getCalendarFechaInicio());
		contentPane.add(getLblFechaFin());
		contentPane.add(getCalendarFechaFin());
		contentPane.add(getBtnAsignar());
		contentPane.add(getBtnCancelar());
		iniciar();
	}
	private JScrollPane getScrollPaneEmpleados() {
		if (scrollPaneEmpleados == null) {
			scrollPaneEmpleados = new JScrollPane();
			scrollPaneEmpleados.setBounds(28, 87, 300, 432);
			scrollPaneEmpleados.setViewportView(getListEmpleados());
		}
		return scrollPaneEmpleados;
	}
	private JList<EmpleadoDTO> getListEmpleados() {
		if (listEmpleados == null) {
			modeloListEmpleados = new DefaultListModel<EmpleadoDTO>();
			listEmpleados = new JList<EmpleadoDTO>(modeloListEmpleados);
		}
		return listEmpleados;
	}
	private JLabel getLblEmpleados() {
		if (lblEmpleados == null) {
			lblEmpleados = new JLabel("Empleados:");
			lblEmpleados.setBounds(28, 62, 124, 14);
		}
		return lblEmpleados;
	}
	private JLabel getLblFechaInicio() {
		if (lblFechaInicio == null) {
			lblFechaInicio = new JLabel("Asignar fecha de inicio:");
			lblFechaInicio.setBounds(338, 89, 210, 14);
		}
		return lblFechaInicio;
	}
	private JCalendar getCalendarFechaInicio() {
		if (calendarFechaInicio == null) {
			calendarFechaInicio = new JCalendar();
			calendarFechaInicio.setBounds(338, 114, 364, 166);
		}
		return calendarFechaInicio;
	}
	private JLabel getLblFechaFin() {
		if (lblFechaFin == null) {
			lblFechaFin = new JLabel("Asignar fecha final:");
			lblFechaFin.setBounds(338, 294, 210, 14);
		}
		return lblFechaFin;
	}
	private JCalendar getCalendarFechaFin() {
		if (calendarFechaFin == null) {
			calendarFechaFin = new JCalendar();
			calendarFechaFin.setBounds(338, 319, 364, 166);
		}
		return calendarFechaFin;
	}
	private JButton getBtnAsignar() {
		if (btnAsignar == null) {
			btnAsignar = new JButton("Asignar");
			btnAsignar.setBounds(338, 496, 89, 23);
		}
		return btnAsignar;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(437, 496, 89, 23);
		}
		return btnCancelar;
	}
	
	private void iniciar() {
		VacacionesController controller = new VacacionesController(new VacacionesModel(),  VistaMuebleria.VIEW_GESTIONA_VACACIONES);
		EmpleadoDTO[] empleados = controller.getListaEmpleados();
		for (int i=0;i<empleados.length;i++) {
			modeloListEmpleados.addElement(empleados[i]);						
		}
	}
}
