package view.homeScreen;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.AppController;
import controller.Loader;

/*
 * This screen has note taking options that the user can select from
 */
public class NoteSettingsFrame extends JFrame implements MouseListener {

	// Fields
	private JButton backButton = new JButton(); // Back button
	private JLabel chooseLayout = new JLabel("Choose Layout"); // Choose layout label
	private JButton outlineButton = new JButton(
			new ImageIcon(new ImageIcon("images/outline.png").getImage().getScaledInstance(280, 250, 0))); // Loads
																											// Outline
																											// method
	private JButton cornellButton = new JButton(
			new ImageIcon(new ImageIcon("images/cornell.png").getImage().getScaledInstance(280, 250, 0))); // Loads
																											// Cornell
																											// method
	private JButton boxingButton = new JButton(
			new ImageIcon(new ImageIcon("images/boxing.png").getImage().getScaledInstance(280, 250, 0))); // Loads
																											// Boxing
																											// method
	private JButton chartingButton = new JButton(
			new ImageIcon(new ImageIcon("images/charting.png").getImage().getScaledInstance(280, 250, 0))); // Loads
																											// charting
																											// method

	// Hover over labels
	private JLabel outlineLabel = new JLabel();
	private JLabel cornellLabel = new JLabel();
	private JLabel chartingLabel = new JLabel();
	private JLabel boxingLabel = new JLabel();

	// Screen background
	private JLabel backgroundImg = new JLabel(new ImageIcon("images/createNote.png"));

	// Constructor
	public NoteSettingsFrame() {

		// Set the frame
		setLayout(null);
		setTitle("Notivity - Create Note");
		setSize(1920, 1080);
		getContentPane().setBackground(Color.WHITE);

		// Setup GUI components
		setupBackButton();
		setupChooseLayout();
		setupLayoutButtons();
		setupLayoutLabels();

		// Add GUI components to screen
		add(backButton);
		add(outlineLabel);
		add(cornellLabel);
		add(boxingLabel);
		add(chartingLabel);
		add(outlineButton);
		add(cornellButton);
		add(boxingButton);
		add(chartingButton);

		// Set the background
		backgroundImg.setBounds(0, 0, 1920, 1080);
		add(backgroundImg);

		// Set the visibility
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// GETTERS AND SETTERS
	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public JLabel getChooseLayout() {
		return chooseLayout;
	}

	public void setChooseLayout(JLabel chooseLayout) {
		this.chooseLayout = chooseLayout;
	}

	public JButton getOutlineButton() {
		return outlineButton;
	}

	public void setOutlineButton(JButton outlineButton) {
		this.outlineButton = outlineButton;
	}

	public JButton getCornellButton() {
		return cornellButton;
	}

	public void setCornellButton(JButton cornellButton) {
		this.cornellButton = cornellButton;
	}

	public JButton getBoxingButton() {
		return boxingButton;
	}

	public void setBoxingButton(JButton boxingButton) {
		this.boxingButton = boxingButton;
	}

	public JButton getChartingButton() {
		return chartingButton;
	}

	public void setChartingButton(JButton chartingButton) {
		this.chartingButton = chartingButton;
	}

	// Sets up layout buttons
	private void setupLayoutButtons() {

		// Set location
		outlineButton.setBounds(chooseLayout.getX(), chooseLayout.getY() - chooseLayout.getHeight() - 100, 280, 250);
		cornellButton.setBounds(chooseLayout.getX() - chooseLayout.getWidth() - 100, chooseLayout.getY(), 280, 250);
		boxingButton.setBounds(chooseLayout.getX() + chooseLayout.getWidth() + 100, chooseLayout.getY(), 280, 250);
		chartingButton.setBounds(chooseLayout.getX(), chooseLayout.getY() + chooseLayout.getHeight() + 100, 280, 250);

		// Add mouse listener
		outlineButton.addMouseListener(this);
		cornellButton.addMouseListener(this);
		boxingButton.addMouseListener(this);
		chartingButton.addMouseListener(this);

	}

	// Sets up hover-over label
	private void setupLayoutLabels() {

		// Set location
		outlineLabel.setBounds(outlineButton.getBounds());
		cornellLabel.setBounds(cornellButton.getBounds());
		boxingLabel.setBounds(boxingButton.getBounds());
		chartingLabel.setBounds(chartingButton.getBounds());

		// Set background and opacity
		outlineLabel.setBackground(new Color(226, 232, 206, 200));
		outlineLabel.setOpaque(true);

		cornellLabel.setBackground(new Color(226, 232, 206, 200));
		cornellLabel.setOpaque(true);

		boxingLabel.setBackground(new Color(226, 232, 206, 200));
		boxingLabel.setOpaque(true);

		chartingLabel.setBackground(new Color(226, 232, 206, 200));
		chartingLabel.setOpaque(true);

		// Set initial visibility to false
		outlineLabel.setVisible(false);
		cornellLabel.setVisible(false);
		chartingLabel.setVisible(false);
		boxingLabel.setVisible(false);
	}

	//Sets up "choose layout" label
	private void setupChooseLayout() {
		chooseLayout.setBounds(810, 390, 350, 250);
		chooseLayout.setFont(AppController.smallFont);
	}

	// Sets up back button
	private void setupBackButton() {
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.setIcon(new ImageIcon(new ImageIcon("images/back.png").getImage().getScaledInstance(80, 50, 0)));
		backButton.setBounds(40, 40, 80, 50);
	}

	// If mouse hovers over the button. Add a label on top of the button. Set the label visibility to true
	@Override
	public void mouseEntered(MouseEvent event) {

		if (event.getSource() == outlineButton)
			outlineLabel.setVisible(true);

		if (event.getSource() == cornellButton)
			cornellLabel.setVisible(true);

		if (event.getSource() == chartingButton)
			chartingLabel.setVisible(true);

		if (event.getSource() == boxingButton)
			boxingLabel.setVisible(true);

	}

	// If mouse doesn't hover over the button. Remove label
	@Override
	public void mouseExited(MouseEvent event) {

		if (event.getSource() == outlineButton)
			outlineLabel.setVisible(false);

		if (event.getSource() == cornellButton)
			cornellLabel.setVisible(false);

		if (event.getSource() == chartingButton)
			chartingLabel.setVisible(false);

		if (event.getSource() == boxingButton)
			boxingLabel.setVisible(false);

	}

	@Override
	public void mousePressed(MouseEvent event) {

	}

	@Override
	public void mouseReleased(MouseEvent event) {

	}

	@Override
	public void mouseClicked(MouseEvent event) {

	}

	/*
	 * This method overrides the dispose window method. Before disposing, files are
	 * saved
	 */
	@Override
	public void dispose() {

		// Object data is written to file
		Loader.writeDataToFile();

		// Window is then disposed
		super.dispose();
	}

}
