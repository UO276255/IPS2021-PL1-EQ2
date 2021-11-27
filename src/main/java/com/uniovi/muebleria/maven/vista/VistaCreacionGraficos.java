package com.uniovi.muebleria.maven.vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.SwingWrapper;

import com.uniovi.muebleria.maven.controlador.Vendedor.VendedorController;
import com.uniovi.muebleria.maven.modelo.Vendedor.VendedorDTO;
import com.uniovi.muebleria.maven.modelo.Vendedor.VendedorModel;
import com.uniovi.muebleria.maven.util.CreacionGraficos;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
public class VistaCreacionGraficos extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelInical;
	private JButton btnIngresos;
	private JButton btnGastos;
	private JLabel lblTipoGrafico;
	private JPanel panelIngresos;
	private JButton btnMensual;
	private JButton btnPorVendedor;
	private JLabel lblTipoDeIngreso;
	private JPanel panelPorVendedor;
	private JComboBox<String> comboBox;
	private JTextField textField;
	private JLabel lblVendedor;
	private JLabel lblMes;
	private JLabel lblGraficosVendedor;
	private JButton btnMostrar;
	private VendedorController vc = new VendedorController(new VendedorModel());
	/**
	 * Create the frame.
	 */
	public VistaCreacionGraficos() {
		setResizable(false);
		setForeground(Color.BLACK);
		setTitle("Muebleria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPanelInical(), "panelInicio");
		contentPane.add(getPanelIngresos(), "panelIngreso");
		contentPane.add(getPanelPorVendedor(), "panelVendedor");
	}
	private JPanel getPanelInical() {
		if (panelInical == null) {
			panelInical = new JPanel();
			panelInical.setBackground(new Color(255, 239, 213));
			panelInical.setLayout(null);
			panelInical.add(getBtnIngresos());
			panelInical.add(getBtnGastos());
			panelInical.add(getLblTipoGrafico());
		}
		return panelInical;
	}
	private JButton getBtnIngresos() {
		if (btnIngresos == null) {
			btnIngresos = new JButton("Ingresos");
			btnIngresos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CardLayout c = (CardLayout) contentPane.getLayout();
					c.show(contentPane, "panelIngreso");		
				}
			});
			btnIngresos.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnIngresos.setBounds(57, 94, 104, 23);
		}
		return btnIngresos;
	}
	private JButton getBtnGastos() {
		if (btnGastos == null) {
			btnGastos = new JButton("Gastos");
			btnGastos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					graficoGasto();
				}
			});
			btnGastos.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnGastos.setBounds(221, 94, 104, 23);
		}
		return btnGastos;
	}
	private JLabel getLblTipoGrafico() {
		if (lblTipoGrafico == null) {
			lblTipoGrafico = new JLabel("Seleccione el tipo de grafico");
			lblTipoGrafico.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTipoGrafico.setBounds(72, 11, 221, 35);
		}
		return lblTipoGrafico;
	}
	private JPanel getPanelIngresos() {
		if (panelIngresos == null) {
			panelIngresos = new JPanel();
			panelIngresos.setBackground(new Color(255, 239, 213));
			panelIngresos.setLayout(null);
			panelIngresos.add(getBtnMensual());
			panelIngresos.add(getBtnPorVendedor());
			panelIngresos.add(getLblTipoDeIngreso());
		}
		return panelIngresos;
	}
	private JButton getBtnMensual() {
		if (btnMensual == null) {
			btnMensual = new JButton("Mensual");
			btnMensual.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnMensual.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int mes = Integer.parseInt(JOptionPane.showInputDialog("Introduce el mes que desea ver"));
					while(mes < 1 || mes > 12) {
						mes = Integer.parseInt(JOptionPane.showInputDialog("Seleccione un mes valido"));
					}
					graficoMes(mes);
				}
			});
			btnMensual.setBounds(47, 91, 117, 23);
		}
		return btnMensual;
	}
	private JButton getBtnPorVendedor() {
		if (btnPorVendedor == null) {
			btnPorVendedor = new JButton("Por vendedor");
			btnPorVendedor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CardLayout c = (CardLayout) contentPane.getLayout();
					c.show(contentPane, "panelVendedor");
					List<VendedorDTO> vendedores = vc.getVendedores();
					String[] listaModel = new String[vendedores.size()];
					for(int i=0;i<vendedores.size();i++) {
						listaModel[i] = vendedores.get(i).toStringGrafico();
					}
					
					comboBox.setModel(new DefaultComboBoxModel<String>(listaModel));
				}
			});
			btnPorVendedor.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnPorVendedor.setBounds(212, 91, 117, 23);
		}
		return btnPorVendedor;
	}
	private JLabel getLblTipoDeIngreso() {
		if (lblTipoDeIngreso == null) {
			lblTipoDeIngreso = new JLabel("Seleccione el tipo de grafico");
			lblTipoDeIngreso.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTipoDeIngreso.setBounds(75, 21, 225, 23);
		}
		return lblTipoDeIngreso;
	}
	
	protected void graficoGasto() {
		Thread t = new Thread(new Runnable() {
		    @Override
		    public void run() {
				CreacionGraficos exampleChart = new CreacionGraficos();
				CategoryChart chart = exampleChart.graficoGastos();
				JFrame grafico =  new SwingWrapper<CategoryChart>(chart).displayChart();
				grafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
		    }
		   });
		t.start();
	}
	
	protected void graficoMes(int mes) {
		Thread t = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	try {
		    		CreacionGraficos exampleChart = new CreacionGraficos();
					CategoryChart chart = exampleChart.graficoVentasPorMes(mes);
					JFrame grafico =  new SwingWrapper<CategoryChart>(chart).displayChart();
					grafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		    	} catch(Exception e) {
		    		JOptionPane.showMessageDialog(null,"No hay ninguna venta en ese mes");
		    	}
		    }
		   });
		t.start();
	}
	
	protected void graficoVendedorMes(int mes, int id) {
		Thread t = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	try {
		    		CreacionGraficos exampleChart = new CreacionGraficos();
					CategoryChart chart = exampleChart.graficoVentasPorMesYVendedor(mes,id);
					JFrame grafico =  new SwingWrapper<CategoryChart>(chart).displayChart();
					grafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		    	} catch(Exception e) {
		    		JOptionPane.showMessageDialog(null,"No hay ninguna venta en ese mes");
		    	}
		    }
		   });
		t.start();
		
	}
	private JPanel getPanelPorVendedor() {
		if (panelPorVendedor == null) {
			panelPorVendedor = new JPanel();
			panelPorVendedor.setBackground(new Color(255, 239, 213));
			panelPorVendedor.setLayout(null);
			panelPorVendedor.add(getComboBox());
			panelPorVendedor.add(getTextField());
			panelPorVendedor.add(getLblVendedor());
			panelPorVendedor.add(getLblMes());
			panelPorVendedor.add(getLblGraficosVendedor());
			panelPorVendedor.add(getBtnMostrar());
		}
		return panelPorVendedor;
	}
	private JComboBox<String> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.setBounds(93, 105, 257, 22);
		}
		return comboBox;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(264, 74, 86, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	private JLabel getLblVendedor() {
		if (lblVendedor == null) {
			lblVendedor = new JLabel("Vendedor: ");
			lblVendedor.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblVendedor.setBounds(10, 109, 86, 14);
		}
		return lblVendedor;
	}
	private JLabel getLblMes() {
		if (lblMes == null) {
			lblMes = new JLabel("Mes: ");
			lblMes.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblMes.setBounds(10, 77, 86, 14);
		}
		return lblMes;
	}
	private JLabel getLblGraficosVendedor() {
		if (lblGraficosVendedor == null) {
			lblGraficosVendedor = new JLabel("Seleccione el vendedor y el mes");
			lblGraficosVendedor.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblGraficosVendedor.setBounds(49, 23, 301, 22);
		}
		return lblGraficosVendedor;
	}
	private JButton getBtnMostrar() {
		if (btnMostrar == null) {
			btnMostrar = new JButton("Mostrar");
			btnMostrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int mes = Integer.parseInt(getTextField().getText());
						int id = -1;
						String[] nombreCompleto = ((String)getComboBox().getSelectedItem()).split(" ");
						List<VendedorDTO> vendedores = vc.getVendedores();
						for(String s : nombreCompleto) {
							System.out.println(s);
						}
						for(VendedorDTO dto : vendedores) {
							if(nombreCompleto[1].equals(dto.getNombre()) && nombreCompleto[3].equals(dto.getApellido())) {
								id = dto.getIdVendedor();
								System.out.println(id);
							}
						}
						graficoVendedorMes(mes,id);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"Datos no válidos");
					}
				}
			});
			btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnMostrar.setBounds(261, 167, 89, 23);
		}
		return btnMostrar;
	}
}
