package com.sero.bot.impl;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.sero.bot.interfaces.Parameter;
import com.sero.bot.interfaces.UI;

public class UIImpl extends JFrame implements UI{	
    private XYSeriesCollection dataset;
    private XYSeries trades;
	
	public UIImpl() {
	}
	
	@Override
	public void init() {
	    dataset = new XYSeriesCollection();
	    trades = new XYSeries("Trades");		
	    
	    dataset.addSeries(trades);
	    
	 // Create chart
	    JFreeChart chart = ChartFactory.createScatterPlot("BTC Value", "Trades", "Price", dataset);
	    XYItemRenderer renderer = chart.getXYPlot().getRenderer();
	    Shape shape = new Rectangle2D.Double(-1, -1, 1, 1);
	    renderer.setSeriesShape(0, shape);

	    
	 // Create Panel
	    ChartPanel panel = new ChartPanel(chart);
	    setContentPane(panel);
	    
	    //setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    setSize(800, 400);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setVisible(true);
	}

	@Override
	public void run(Parameter p) {
	    trades.add(p.getInstance().getL(), p.getInstance().getP());
	}
}
