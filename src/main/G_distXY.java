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
import org.jfree.data.time.Millisecond;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.Layer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;



public class G_distXY {
	String nomeGrafico, eixoX, eixoY;
	int idx, idy;
	JFreeChart chart;
	XYSeries series;
	float xAntigo, yAntigo;
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
	
	public int getIdx() {
		return idx;
	}
	
	public int getIdy() {
		return idy;
	}

	public void setId(int idx, int idy) {
		this.idx = idx;
		this.idy = idy;
	}
	
	public void addValores(float x, float y) {
		if((xAntigo == x) && (yAntigo == y)) {
		}else {
			series.add(x, y);
			xAntigo = x;
			yAntigo = y;
		}
	}
	

	
	public ChartPanel getPainelVideo() {
		
		// Create a simple XY chart
		series = new XYSeries(nomeGrafico, false);
				// Add the series to your data set
				XYSeriesCollection dataset = new XYSeriesCollection();
				
				dataset.addSeries(series);
				// Generate the graph
				
				chart = ChartFactory.createXYLineChart(
						nomeGrafico, // Title
						"", // x-axis Label
						"", // y-axis Label
						dataset, // Dataset
						PlotOrientation.VERTICAL, // Plot Orientation
						false, // Show Legend
						false, // Use tooltips
						false // Configure chart to generate URLs?
				);
				chart.setTitle("");
				Font font3 = new Font("Dialog", Font.PLAIN, 9); 
				
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
				plot.setDomainGridlinePaint(Color.black);
				plot.setDomainGridlinesVisible(true);
				plot.getDomainAxis().setLabelFont(font3);
				plot.getRangeAxis().setLabelFont(font3);
				plot.getDomainAxis().setTickLabelFont(font3);
				plot.getRangeAxis().setTickLabelFont(font3);
				plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.blue);
				return chPanel;
	}

	public void limpaGrafico() {
		series.clear();
		
	}

	
	
}
