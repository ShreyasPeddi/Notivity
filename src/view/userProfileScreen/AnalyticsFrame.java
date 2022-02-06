package view.userProfileScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

//This class displays user analytics - time spent on each note. Time spent on top 4 notes(by most time) is provided
public class AnalyticsFrame extends JFrame implements ActionListener {

	// Panel which consists the bar graph
	private ChartPanel chartPanel;

	// Drop down panel
	private JPanel dropDownPanel = new JPanel();

	// Drop down options
	private String[] optionsToChoose = { "Bar Chart", "Pie Chart", "3D Pie Chart", "3D Bar Chart" };

	// Drop down menu
	private JComboBox<String> dropDown = new JComboBox<>(optionsToChoose);

	// Select option
	private JButton selectOption = new JButton("Select Option");

	//Stores time spent for each note
	private HashMap<String, Integer> analyticsData;

	//Title of the chart
	private String chartTitle;

	// Constructor - Takes in application title, chart title and data in hashmap
	public AnalyticsFrame(String applicationTitle, String chartTitle, HashMap<String, Integer> analyticsData) {

		// Set the frame
		setTitle(applicationTitle);
		setLayout(new BorderLayout(0, 5));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Setup drop down menu option
		setupDropDownPanel();
		add(dropDownPanel, BorderLayout.PAGE_END);

		this.analyticsData = analyticsData;
		this.chartTitle = chartTitle;

		// Setup the initial graph panel
		createChart();
		add(chartPanel, BorderLayout.CENTER);

		// Set the size
		setBounds(0, 0, 1000, 800);

		setVisible(true);
	}

	// This method sets up GUI for drop down
	private void setupDropDownPanel() {
		
		//Select Button - background, foreground
		selectOption.setBackground(Color.BLUE);
		selectOption.setForeground(Color.white);
		
		//Implement mouse listener to change color
		selectOption.addMouseListener(new MouseAdapter() {
			
			//Change color to cyan
			public void mouseEntered(java.awt.event.MouseEvent event) {
				selectOption.setBackground(Color.cyan);
				selectOption.setForeground(Color.black);
		    }

			//Change back the color to blue
		    public void mouseExited(java.awt.event.MouseEvent event) {
		    	selectOption.setBackground(Color.blue);
		    	selectOption.setForeground(Color.white);
		    }
			
		});
		
		//Add GUI components to the panel
		dropDownPanel.add(dropDown);
		dropDownPanel.add(selectOption);
		
		dropDownPanel.setBackground(Color.WHITE);
		//Add action listener to selection button
		selectOption.addActionListener(this);
	}

	/*
	 * This method creates a chart based on data provided. It uses the jfree chart
	 * library (external)
	 */
	private void createChart() {

		// https://stackoverflow.com/questions/5522575/how-can-i-update-a-jfreecharts-appearance-after-its-been-made-visible
		JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Notes", "Time Spent (in seconds)",
				createCategoryDataset(), PlotOrientation.VERTICAL, true, true, false);

		// Set up the chart panel
		chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		chartPanel.setMouseWheelEnabled(true);
		chartPanel.setHorizontalAxisTrace(true);
		chartPanel.setVerticalAxisTrace(true);

	}

	/*
	 * This method creates a dataset by converting hash map data to data points in a
	 * bar graph
	 */
	private CategoryDataset createCategoryDataset() {

		// Title on the x axis
		String xTitle = "Notes";

		// https://stackoverflow.com/questions/5522575/how-can-i-update-a-jfreecharts-appearance-after-its-been-made-visible
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// Iterate through the map and add the value to the data set
		for (Map.Entry map : analyticsData.entrySet()) {
			dataset.addValue((int) map.getValue(), xTitle, (String) map.getKey());
		}

		// Return the data set
		return dataset;
	}

	/*
	 * This method creates a pie dataset by converting hash map data to data points in a
	 * pie chart
	 */
	private PieDataset createPieDataSet() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		// Iterate through the map and add the value to the data set
		for (Map.Entry map : analyticsData.entrySet()) {
			dataset.setValue((String) map.getKey(), new Integer((int) map.getValue()));
		}

		return dataset;
	}

	// Implements functionality for changing graphs
	@Override
	public void actionPerformed(ActionEvent event) {

		// Select option
		if (event.getSource() == selectOption) {

			// Get selected option
			String option = (String) dropDown.getSelectedItem();

			// Bar Graph
			if (option.equals(optionsToChoose[0])) {

				// Remove current panel
				this.remove(chartPanel);

				// Create new bar chart
				JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Notes", "Time Spent (in seconds)",
						createCategoryDataset(), PlotOrientation.VERTICAL, true, true, false);

				// Set up the chart panel
				chartPanel = new ChartPanel(barChart);

				setupChart();

				// Add the panel back
				this.add(chartPanel, BorderLayout.CENTER);
				this.revalidate();
				this.repaint();

			}

			// Pie Chart
			else if (option.equals(optionsToChoose[1])) {

				// Remove current panel
				this.remove(chartPanel);

				// Create new pie chart
				JFreeChart pieChart = ChartFactory.createPieChart(this.chartTitle, createPieDataSet(), true, true,
						false);

				// Set up the chart panel
				chartPanel = new ChartPanel(pieChart);

				//Setup chart panel
				setupChart();

				// Add the panel back
				this.add(chartPanel, BorderLayout.CENTER);
				this.revalidate();
				this.repaint();

			}

			// 3D Pie Chart
			else if (option.equals(optionsToChoose[2])) {

				// Remove current panel
				this.remove(chartPanel);

				// Create new pie chart
				JFreeChart pieChart = ChartFactory.createPieChart3D(this.chartTitle, createPieDataSet(), true, true,
						false);

				PiePlot3D plot = (PiePlot3D) pieChart.getPlot();
				plot.setStartAngle(270);
				plot.setForegroundAlpha(0.60f);
				plot.setInteriorGap(0.02);
				
				// Set up the chart panel
				chartPanel = new ChartPanel(pieChart);
				
				setupChart();

				// Add the panel back
				this.add(chartPanel, BorderLayout.CENTER);
				this.revalidate();
				this.repaint();

			}

			//3D Bar Graph
			else if (option.equals(optionsToChoose[3])) {

				// Remove current panel
				this.remove(chartPanel);

				// Create new bar chart
				JFreeChart barChart = ChartFactory.createBarChart3D(chartTitle, "Notes", "Time Spent (in seconds)",
						createCategoryDataset(), PlotOrientation.VERTICAL, true, true, false);

				// Set up the chart panel
				chartPanel = new ChartPanel(barChart);

				setupChart();

				// Add the panel back
				this.add(chartPanel, BorderLayout.CENTER);
				this.revalidate();
				this.repaint();
			}
		}
	}

	// This method sets up the chart panel - size, tracing, mouse wheel
	private void setupChart() {
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		chartPanel.setMouseWheelEnabled(true);
		chartPanel.setHorizontalAxisTrace(true);
		chartPanel.setVerticalAxisTrace(true);
	}

	// GETTERS AND SETTERS
	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}

	public JPanel getDropDownPanel() {
		return dropDownPanel;
	}

	public void setDropDownPanel(JPanel dropDownPanel) {
		this.dropDownPanel = dropDownPanel;
	}

	public String[] getOptionsToChoose() {
		return optionsToChoose;
	}

	public void setOptionsToChoose(String[] optionsToChoose) {
		this.optionsToChoose = optionsToChoose;
	}

	public JComboBox<String> getDropDown() {
		return dropDown;
	}

	public void setDropDown(JComboBox<String> dropDown) {
		this.dropDown = dropDown;
	}

	public JButton getSelectOption() {
		return selectOption;
	}

	public void setSelectOption(JButton selectOption) {
		this.selectOption = selectOption;
	}

	public HashMap<String, Integer> getAnalyticsData() {
		return analyticsData;
	}

	public void setAnalyticsData(HashMap<String, Integer> analyticsData) {
		this.analyticsData = analyticsData;
	}

	public String getChartTitle() {
		return chartTitle;
	}

	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}

}