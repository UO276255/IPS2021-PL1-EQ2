package com.uniovi.muebleria.maven.vista;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;
import com.uniovi.muebleria.maven.controlador.empleado.VacacionesController;
import com.uniovi.muebleria.maven.modelo.empleado.EmpleadoDTO;
import com.uniovi.muebleria.maven.modelo.empleado.VacacionesModel;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

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
		setTitle("Muebleria");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				modeloListEmpleados.clear();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 746, 590);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
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
		setLocationRelativeTo(null);
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
			btnAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Date dateInicio = (Date) getCalendarFechaInicio().getDate();
					Date dateFinal = (Date) getCalendarFechaFin().getDate();
					Date actual = (Date) Calendar.getInstance().getTime();
					VacacionesController controller = new VacacionesController(new VacacionesModel(),  VistaMuebleria.VIEW_GESTIONA_VACACIONES);
					if(listEmpleados.getSelectedValue() == null) {
						JOptionPane.showMessageDialog(null, "No hay un empleado asignado");
					}else if (dateInicio.compareTo(actual)<0) {
						JOptionPane.showMessageDialog(null, "La fecha asignada debe ser posterior a la actual");
					} else if (dateFinal.compareTo(dateInicio)<0){
						JOptionPane.showMessageDialog(null, "La fecha final debe ser posterior a la fecha inicial");
					} else {
						controller.asignaFechaEmpleados(listEmpleados.getSelectedValue().getId(), dateInicio, dateFinal);
						JOptionPane.showMessageDialog(null, "Vacaciones asignadas a " + listEmpleados.getSelectedValue().getNombre() + " " + listEmpleados.getSelectedValue().getApellido() + " correctamente");
					}
				}
			});
			btnAsignar.setBounds(338, 496, 89, 23);
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
			btnCancelar.setBounds(437, 496, 89, 23);
		}
		return btnCancelar;
	}
	
	private void closeWindow() {
		this.dispose();
	}
	
	public void iniciar() {
		VacacionesController controller = new VacacionesController(new VacacionesModel(),  VistaMuebleria.VIEW_GESTIONA_VACACIONES);
		EmpleadoDTO[] empleados = controller.getListaEmpleados();
		for (int i=0;i<empleados.length;i++) {
			modeloListEmpleados.addElement(empleados[i]);						
		}
	}
}
