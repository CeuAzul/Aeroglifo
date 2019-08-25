package main;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.XYItemEntity;
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



public class G_Utilidades_Corte {
	String nomeGrafico, eixoX, eixoY;
	int id;
	List<Float> valorX = new ArrayList<Float>();
	List<Float> valorY = new ArrayList<Float>();
	JFreeChart chart;
	double ultimoXSelecionado;
	
	Marker mInicio, mFim, mMouse;
	
	public double getUltimoXSelecionado() {
		return ultimoXSelecionado;
	}
	
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


	public void setInicio(float inicio, String texto1) {
		final XYPlot plot = chart.getXYPlot();

		plot.removeDomainMarker(mInicio);

        mInicio = new ValueMarker(inicio);
        mInicio.setStroke(new BasicStroke(1));
        mInicio.setPaint(Color.blue);
        mInicio.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        mInicio.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);
        mInicio.setLabel(texto1);
        mInicio.setLabelFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 10));
        mInicio.setLabelPaint(Color.black);
        plot.addDomainMarker(mInicio);
	}
	
	public void setFim(float fim, String texto1) {
		final XYPlot plot = chart.getXYPlot();

		plot.removeDomainMarker(mFim);

        mFim = new ValueMarker(fim);
        mFim.setStroke(new BasicStroke(1));
        mFim.setPaint(Color.blue);
        mFim.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        mFim.setLabelTextAnchor(TextAnchor.BASELINE_LEFT);
        mFim.setLabel(texto1);
        mFim.setLabelFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 10));
        mFim.setLabelPaint(Color.black);
        plot.addDomainMarker(mFim);
	}
	
	public void adicionaMarcacaoIntervalo(float inicio, float fim, String texto1, String texto2) {
		final XYPlot plot = chart.getXYPlot();
		
        Marker target = new IntervalMarker(inicio,fim);
        target.setPaint(new Color(0,0,255,255));
        target.setAlpha(0.025f);


        plot.addDomainMarker(target);
        
        Marker m = new ValueMarker(inicio);
        m.setStroke(new BasicStroke(1));
        m.setPaint(Color.lightGray);

        plot.addDomainMarker(m);

        
        Marker m2 = new ValueMarker(fim);
        m2.setStroke(new BasicStroke(1));
        m2.setPaint(Color.lightGray);

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
						true, // Show Legend
						true, // Use tooltips
						true // Configure chart to generate URLs?
				);
				
				ChartPanel chPanel = new ChartPanel(chart); //creating the chart panel, which extends JPanel
				chPanel.setPreferredSize(new Dimension(800, 440)); //size according to my window
				chPanel.setMouseWheelEnabled(true);
				chPanel.setMaximumDrawHeight(1080);		//To colocando isso pra não deformar o gráfico quando muda o tamanho da janela
				chPanel.setMaximumDrawWidth(1900);
				XYPlot plot = (XYPlot) chart.getPlot();
			//	plot.setBackgroundPaint(new Color(235, 235, 235));
				plot.setBackgroundPaint(new Color(255, 255, 255));

				plot.setRangeGridlinePaint(Color.black);
				plot.setDomainGridlinesVisible(false);
				chPanel.addChartMouseListener(new ClickedIndexMouseListener());
				return chPanel;
	}
	
	final class ClickedIndexMouseListener implements ChartMouseListener {

	    @Override
	    public void chartMouseMoved(ChartMouseEvent event) {
	    }

	    @Override
	    public void chartMouseClicked(ChartMouseEvent event) {
	        try {
	            XYItemEntity ce = (XYItemEntity) event.getEntity();
	            System.out.println("Item    " + ce.getItem());
	            System.out.println("Series  " + ce.getSeriesIndex());
	            System.out.println("X Value " + ce.getDataset().getX(ce.getSeriesIndex(),  ce.getItem()));
	            System.out.println("Y Value " + ce.getDataset().getY(ce.getSeriesIndex(),  ce.getItem()));
	            
	    		final XYPlot plot = chart.getXYPlot();

	    		plot.removeDomainMarker(mMouse);
	            ultimoXSelecionado = Double.parseDouble(ce.getDataset().getX(ce.getSeriesIndex(),  ce.getItem())+"");
	    		mMouse = new ValueMarker(ultimoXSelecionado);
	    		mMouse.setStroke(new BasicStroke(1));
	    		mMouse.setPaint(Color.GRAY);
	    		mMouse.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
	    		mMouse.setLabelTextAnchor(TextAnchor.TOP_LEFT);
	    		mMouse.setLabel("("+ ce.getDataset().getX(ce.getSeriesIndex(),  ce.getItem())+", "+ce.getDataset().getY(ce.getSeriesIndex(),  ce.getItem())+")");
	    		mMouse.setLabelFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 9));
	    		mMouse.setLabelPaint(Color.black);
	            plot.addDomainMarker(mMouse);

	            
	        } catch (Exception e) {}
	    }
	}
	
	
}
