package main;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.Layer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;



public class G_Padrao {
	String nomeGrafico, eixoX, eixoY;
	int id;
	List<Float> valorX = new ArrayList<Float>();
	List<Float> valorY = new ArrayList<Float>();
	JFreeChart chart;
	int indice=0;
    private List<XYSeriesCollection> seriesArrayList = new ArrayList<XYSeriesCollection>();

	
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
	
	public void adicionaDadoGrafico(List<Float> lX, List<Float> lY, String nomeDado) {
    	final XYPlot plot = chart.getXYPlot();
    	XYSeries series = new XYSeries(nomeDado);
		for (int i = 0; i < lX.size(); i++) {
			series.add(lX.get(i), lY.get(i));
		}
        seriesArrayList.add(new XYSeriesCollection(series));
        plot.setDataset(indice, seriesArrayList.get(indice));
        plot.setRenderer(indice, new StandardXYItemRenderer());
        indice++;
	}


	public void adicionaMarcacaoIntervalo(float inicio, float fim, String texto1, String texto2) {
		final XYPlot plot = chart.getXYPlot();
		
        Marker target = new IntervalMarker(inicio,fim);
        target.setPaint(new Color(0,0,255,255));
        target.setAlpha(0.025f);


        plot.addDomainMarker(target);
        
        Marker m = new ValueMarker(inicio);
        m.setStroke(new BasicStroke(1));
        m.setPaint(Color.blue);
        m.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        m.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);
        m.setLabel(texto1);
        m.setLabelFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 10));
        m.setLabelPaint(Color.black);
        plot.addDomainMarker(m);

        
        Marker m2 = new ValueMarker(fim);
        m2.setStroke(new BasicStroke(1));
        m2.setPaint(Color.blue);
        m2.setLabelAnchor(RectangleAnchor.BOTTOM_LEFT);
        m2.setLabelTextAnchor(TextAnchor.BASELINE_RIGHT);
        m2.setLabel(texto2);
        m2.setLabelFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 10));
        m2.setLabelPaint(Color.black);
        plot.addDomainMarker(m2);

	}

	
	
	public void setValores(List<Float> list, List<Float> list2) {
		for (int i = 0; i < list.size(); i++) {
		//	System.out.println("CARREGOU ESSA PUTARIA?????????????????????????"+list.get(i));
			valorX.add(list.get(i));
			valorY.add(list2.get(i));
		}
	}
	
	public ChartPanel getPainelPadrao() {
		
		// Create a simple XY chart
				XYSeries series = new XYSeries(nomeGrafico);

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
						true, // Show Legend
						true, // Use tooltips
						true // Configure chart to generate URLs?
				);
				
				ChartPanel chPanel = new ChartPanel(chart); //creating the chart panel, which extends JPanel
				chPanel.setPreferredSize(new Dimension(785, 440)); //size according to my window
				chPanel.setMouseWheelEnabled(true);
				chPanel.setMaximumDrawHeight(1080);		//To colocando isso pra não deformar o gráfico quando muda o tamanho da janela
				chPanel.setMaximumDrawWidth(1900);
				XYPlot plot = (XYPlot) chart.getPlot();
			//	plot.setBackgroundPaint(new Color(235, 235, 235));
				plot.setBackgroundPaint(new Color(255, 255, 255));

				plot.setRangeGridlinePaint(Color.black);
				plot.setDomainGridlinesVisible(false);
				
				
				adicionaDadoGrafico(valorX, valorY, nomeGrafico);

				valorX.clear();
				valorY.clear();
				return chPanel;
	}
	
}
