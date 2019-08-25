package main;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.Layer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;



public class G_Video {
	String nomeGrafico, eixoX, eixoY;
	int id;
	List<Float> valorX = new ArrayList<Float>();
	List<Float> valorY = new ArrayList<Float>();
	JFreeChart chart;
	Marker m;
	
	public String getNomeGrafico() {
		return nomeGrafico;
	}

	public String getEixoX() {
		return eixoX;
	}

	public String getEixoY() {
		return eixoY;
	}

	public void setNomeGrafico(String nomeGrafico) {
		this.nomeGrafico = nomeGrafico;
	}

	public void setEixoX(String eixoX) {
		this.eixoX = eixoX;
	}

	public void setEixoY(String eixoY) {
		this.eixoY = eixoY;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void adicionaMarcacaoIntervalo(float inicio, String texto1, int escalaTempo) {
		final XYPlot plot = chart.getXYPlot();
		plot.removeDomainMarker(m);
		m = null;
        m = new ValueMarker(inicio);
        m.setStroke(new BasicStroke(1));
        m.setPaint(Color.blue);
        m.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        m.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);
        m.setLabel(texto1);
        m.setLabelFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 10));
        m.setLabelPaint(Color.black);
        plot.addDomainMarker(m);
        plot.zoom(5);
        chart.fireChartChanged();
        final NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        if(escalaTempo == 0) {
        	domainAxis.setRange(inicio-10, inicio+10); 
        }else {
        	domainAxis.setRange(inicio-10000, inicio+10000); 
        }
	}
	

	
	
	public void setValores(List<Float> list, List<Float> list2) {
		for (int i = 0; i < list.size(); i++) {
		//	System.out.println("CARREGOU ESSA PUTARIA?????????????????????????"+list.get(i));
			valorX.add(list.get(i));
			valorY.add(list2.get(i));
		}
	}
	
	public ChartPanel getPainelVideo() {
		
		// Create a simple XY chart
				XYSeries series = new XYSeries(nomeGrafico);
				for (int i = 0; i < valorX.size(); i++) {
					series.add(valorX.get(i), valorY.get(i));
				}
				valorX.clear();
				valorY.clear();
				// Add the series to your data set
				XYSeriesCollection dataset = new XYSeriesCollection();
				dataset.addSeries(series);
				// Generate the graph
				chart = ChartFactory.createXYLineChart(
						nomeGrafico, // Title
						eixoX, // x-axis Label
						eixoY, // y-axis Label
						dataset, // Dataset
						PlotOrientation.VERTICAL, // Plot Orientation
						false, // Show Legend
						false, // Use tooltips
						false // Configure chart to generate URLs?
				);
				
				chart.setBorderVisible(false);
				
				
				ChartPanel chPanel = new ChartPanel(chart); //creating the chart panel, which extends JPanel
				chPanel.setPreferredSize(new Dimension(785, 440)); //size according to my window
				chPanel.setMouseWheelEnabled(true);
				chPanel.setMaximumDrawHeight(1080);		//To colocando isso pra não deformar o gráfico quando muda o tamanho da janela
				chPanel.setMaximumDrawWidth(1900);
				chPanel.setMinimumDrawHeight(40);		//To colocando isso pra não deformar o gráfico quando muda o tamanho da janela
				chPanel.setMinimumDrawWidth(40);
				XYPlot plot = (XYPlot) chart.getPlot();
			//	plot.setBackgroundPaint(new Color(235, 235, 235));
				plot.setBackgroundPaint(new Color(255, 255, 255));

				plot.setRangeGridlinePaint(Color.black);
				plot.setDomainGridlinesVisible(false);
				
				return chPanel;
	}

	
	
}
