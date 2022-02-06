package view.noteTakingScreen;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import controller.AppController;
import controller.Loader;
import view.noteTakingScreen.ChartingPanel.ChartingPanel;
import view.noteTakingScreen.ChartingPanel.ColumnHeaderPanel;

/*
 * This class holds multiple note taking panels, title panel, pomodoro panel and a functional menubar
 */
public class NoteTakingFrame extends JFrame {

	// Fields
	private BoxingPanel boxingPanel; // Boxing note taking panel
	private CornellPanel cornellPanel; // Cornell note taking panel
	private OutlinePanel outlinePanel; // Outline note taking panel
	private ChartingPanel chartingPanel; // Charting note taking panel
	private ColumnHeaderPanel columnHeaderPanel; // Column header (with charting panel)

	private JButton backButton = new JButton(); // Back button
	private Pomodoro pomdoroPanel = new Pomodoro(); // Pomodoro Panel
	private MenuBar taskMenubar; // Menu bar
	private JTextField titleText = new JTextField("Title"); // Editable title text field

	private int listIndex = -1; // Keeps track of what note is being edited (index)

	// Constructor. Takes layout as a parameter
	public NoteTakingFrame(String layout) {

		// Set the frame
		setLayout(null);
		setTitle("Notivity - Note");
		setSize(1920, 1080);
		getContentPane().setBackground(new Color(254, 250, 224));

		// Setup GUI components
		setupBackButton();
		setupTitleTextArea();
		add(titleText);
		add(backButton);
		add(pomdoroPanel);

		// Setup menubar
		taskMenubar = new MenuBar();
		setJMenuBar(taskMenubar);

		// If the layout selected is outline, load outline panel
		if (layout.equals("outline")) {
			outlinePanel = new OutlinePanel();

			// Outline panel has additional capability of importing external file content
			taskMenubar.getFileMenu().add(taskMenubar.getImportContent());
			add(outlinePanel);
		}

		// If the layout selected is cornell, load cornell panel
		else if (layout.equals("cornell")) {
			cornellPanel = new CornellPanel();
			add(cornellPanel);
		}

		// If the layout selected is charting, load charting panel
		else if (layout.equals("charting")) {
			chartingPanel = new ChartingPanel();
			columnHeaderPanel = new ColumnHeaderPanel();

			// Make the charting panel scrollable
			JScrollPane scrollPanel = new JScrollPane(chartingPanel);

			// Set scrolling policies and bounds
			scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPanel.setBounds(20, 340, 1880, 600);

			// Add all charting related panels to the frame
			add(columnHeaderPanel);
			add(scrollPanel);
		}

		// If the layout selected is boxing, load boxing panel
		else if (layout.equals("boxing")) {
			boxingPanel = new BoxingPanel();
			add(boxingPanel);
		}
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// GETTERS AND SETTERS
	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public BoxingPanel getBoxingPanel() {
		return boxingPanel;
	}

	public void setBoxingPanel(BoxingPanel boxingPanel) {
		this.boxingPanel = boxingPanel;
	}

	public CornellPanel getCornellPanel() {
		return cornellPanel;
	}

	public void setCornellPanel(CornellPanel cornellPanel) {
		this.cornellPanel = cornellPanel;
	}

	public OutlinePanel getOutlinePanel() {
		return outlinePanel;
	}

	public void setOutlinePanel(OutlinePanel outlinePanel) {
		this.outlinePanel = outlinePanel;
	}

	public ChartingPanel getChartingPanel() {
		return chartingPanel;
	}

	public void setChartingPanel(ChartingPanel chartingPanel) {
		this.chartingPanel = chartingPanel;
	}

	public Pomodoro getPomdoroPanel() {
		return pomdoroPanel;
	}

	public void setPomdoroPanel(Pomodoro pomdoroPanel) {
		this.pomdoroPanel = pomdoroPanel;
	}

	public MenuBar getTaskMenubar() {
		return taskMenubar;
	}

	public void setTaskMenubar(MenuBar taskMenubar) {
		this.taskMenubar = taskMenubar;
	}

	public JTextField getTitleText() {
		return titleText;
	}

	public void setTitleText(JTextField titleText) {
		this.titleText = titleText;
	}

	public ColumnHeaderPanel getColumnHeaderPanel() {
		return columnHeaderPanel;
	}

	public void setColumnHeaderPanel(ColumnHeaderPanel columnHeaderPanel) {
		this.columnHeaderPanel = columnHeaderPanel;
	}

	public int getListIndex() {
		return listIndex;
	}

	public void setListIndex(int listIndex) {
		this.listIndex = listIndex;
	}
	
	// Sets up the title text area
	private void setupTitleTextArea() {
		titleText.setBounds(this.getWidth() / 4, 100, 900, 60);
		titleText.setFont(AppController.bigFont);
		titleText.setHorizontalAlignment(JTextField.CENTER);
		titleText.setBackground(new Color(254, 250, 224));
	}

	// Sets up back button
	private void setupBackButton() {
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.setIcon(new ImageIcon(new ImageIcon("images/back.png").getImage().getScaledInstance(80, 50, 0)));
		backButton.setBounds(40, 40, 80, 50);
	}
	
	/*
	 * This method overrides the dispose window method. Before disposing, files are saved
	 */
	@Override
	public void dispose() {
		
		//Object data is written to file
		Loader.writeDataToFile();
		
		//Window is then disposed
	    super.dispose();
	}

}
