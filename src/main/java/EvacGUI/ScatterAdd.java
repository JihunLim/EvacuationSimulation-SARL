package EvacGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

//touch from here
public class ScatterAdd extends JDialog {
	private static final String title = "X : Distance Y:Push correlation";
	private static final Random rand = new Random();
	private XYSeries female = new XYSeries("Female");
	private XYSeries male = new XYSeries("Male");
	ChartPanel chartPanel = createDemoPanel(0);

	public ScatterAdd(String s) {
		// super(s);
		Container contentPane;
		contentPane = getContentPane();

		contentPane.add(chartPanel, BorderLayout.CENTER);// adds chart

		JPanel control = new JPanel();// gets new panel for button

		ButtonGroup group = new ButtonGroup();
		JRadioButton weight = new JRadioButton("Weight & Height");
		JRadioButton age = new JRadioButton("Age & Heartrate");
		group.add(weight);
		group.add(age);
		control.add(weight);
		control.add(age);
		weight.setSelected(true);
		weight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				male.clear();
				female.clear();
				chartPanel = createDemoPanel(0);
			}

		});

		age.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				male.clear();
				female.clear();
				chartPanel = createDemoPanel(1);
			}

		});

		this.add(control, BorderLayout.SOUTH);// adds panel
	}

	private ChartPanel createDemoPanel(int mode) {// chart setup
		JFreeChart jfreechart = ChartFactory.createScatterPlot(title, "X", "Y", createSampleData(mode), // create
				// sampledata
				// for
				// male
				PlotOrientation.VERTICAL, true, true, false);// creates chart
																// for male
		XYPlot xyPlot = (XYPlot) jfreechart.getPlot(); // simple
		xyPlot.setDomainCrosshairVisible(true);// simple
		xyPlot.setRangeCrosshairVisible(true);// simple
		XYItemRenderer renderer = xyPlot.getRenderer();
		renderer.setSeriesPaint(0, Color.blue);// simple
		NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
		domain.setVerticalTickLabels(true);
		return new ChartPanel(jfreechart);
	}

	// male dataset
	private XYDataset createSampleData(int mode) {
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();

		// ******************************************
		if (mode == 0) {// weight

			for (int i = 0; i < EvacGUI.canvas.distancearray.size(); i++) {
				// if (Main.st.get(i).gender.equals("M")) {
				male.add(EvacGUI.canvas.distancearray.get(i), EvacGUI.canvas.pushamountarray.get(i));
				// }
				/*
				 * if (Main.st.get(i).gender.equals("F")) {
				 * female.add(Double.parseDouble(Main.st.get(i).weight),
				 * Double.parseDouble(Main.st.get(i).height)); }
				 */
			}

			/*
			 * if (mode == 1) {// age for (int i = 0; i < Main.st.size(); i++) {
			 * if (Main.st.get(i).gender.equals("M")) {
			 * male.add(Double.parseDouble(Main.st.get(i).age),
			 * Double.parseDouble(Main.st.get(i).heartrate));
			 */
		}
		/*
		 * if (Main.st.get(i).gender.equals("F")) {
		 * female.add(Double.parseDouble(Main.st.get(i).age),
		 * Double.parseDouble(Main.st.get(i).heartrate)); }
		 */

		// ******************************************

		// }
		xySeriesCollection.addSeries(male);// male
		xySeriesCollection.addSeries(female);// female
		return xySeriesCollection;

		// some basic stuff you don't need to touch

	}
}