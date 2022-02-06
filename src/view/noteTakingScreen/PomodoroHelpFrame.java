package view.noteTakingScreen;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

//Displays an image with instructions on what is pomodoro technique
public class PomodoroHelpFrame extends JFrame {

	// Constructor
	public PomodoroHelpFrame() {

		// Set the frame
		setTitle("Notivity - Pomodoro Information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Create a new panel
		JPanel pomodoroPanel = new JPanel();

		//Label to hold the image
		JLabel helpImage = new JLabel(new ImageIcon(new ImageIcon("images/whatIsPomodoro.png").getImage()));

		//Set the bounds of the image
		helpImage.setBounds(0, 10, 709, 1024);
		pomodoroPanel.add(helpImage);

		//Set panel layout
		pomodoroPanel.setLayout(new BoxLayout(pomodoroPanel, BoxLayout.Y_AXIS));
		
		//Add the panel to a scrollable area
		add(new JScrollPane(pomodoroPanel));

		//Set size, layout and visibility
		setSize(730, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(null);
	}
}
