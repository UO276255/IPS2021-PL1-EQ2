package com.uniovi.muebleria.maven.util;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler.LegendPosition;

import com.uniovi.muebleria.maven.modelo.pedidos.PedidoDTO;
import com.uniovi.muebleria.maven.modelo.pedidos.PedidoModel;
import com.uniovi.muebleria.maven.modelo.ventas.VentaDTO;
import com.uniovi.muebleria.maven.modelo.ventas.VentaModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;



public class CreacionGraficos{
//
//	public static void main(String[] args) {
//
//		CreacionGraficos exampleChart = new CreacionGraficos();
//		CategoryChart chart = exampleChart.graficoGastos();
//		JFrame grafico =  new SwingWrapper<CategoryChart>(chart).displayChart();
//		grafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		
//	} 

	public CategoryChart graficoGastos(int mes) {
		PedidoModel pd = new PedidoModel();
		List<PedidoDTO> pedidos = pd.PedidosPorMes(mes);
		List<Integer> precios = new ArrayList<Integer>();
		List<Integer> IdProductos = new ArrayList<Integer>();
		for(int i= 0; i<pedidos.size();i++) {
			IdProductos.add(pedidos.get(i).getId());
			precios.add(pd.getPrecioPedido(pedidos.get(i).getId()));
		}
		// Create Chart
		CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Gastos por pedido").xAxisTitle("Id del pedido").yAxisTitle("Precio en €").build();
		chart.getStyler().setLegendPosition(LegendPosition.InsideNW);

		// Series
		chart.addSeries("Gasto en €",IdProductos,precios);

		return chart;
	}

	public CategoryChart graficoVentasPorMes(int mes) {
		VentaModel mv = new VentaModel();
		List<VentaDTO> ventas = mv.getListaVentasPorMes(mes);
		List<Integer> numeroVentas = new ArrayList<Integer>();
		List<Integer> valor = new ArrayList<Integer>();
		for(int i= 0; i<ventas.size();i++) {
			numeroVentas.add(i+1);
			valor.add(ventas.get(i).getPrecio());
		}

		CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Ingresos mensuales").xAxisTitle("Id de la venta").yAxisTitle("Ganancia en €").build();
		chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		chart.addSeries("Beneficio en €",numeroVentas,valor);		 
		return chart;
	}

	public CategoryChart graficoVentasPorMesYVendedor(int mes,int idVendedor) {
		VentaModel mv = new VentaModel();
		List<VentaDTO> ventas = mv.getListaVentasPorMesyVendedor(mes,idVendedor);

		List<Integer> numeroVentas = new ArrayList<Integer>();
		List<Integer> valor = new ArrayList<Integer>();
		for(int i= 0; i<ventas.size();i++) {
			numeroVentas.add(i+1);
			valor.add(ventas.get(i).getPrecio());
		}

		CategoryChart chart = new CategoryChartBuilder().
				width(800).height(600).title("Ingresos mensuales del vendedor indicado").xAxisTitle("Id de las ventas").yAxisTitle("Ventas en €").build();
		chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		chart.addSeries("Beneficio en €",numeroVentas,valor);		 
		return chart;
	}

}
