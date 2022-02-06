package view.noteTakingScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.AppController;

/*
 * This class creates the optional pomodoro panel which is a running timer alternating between work and break periods
 */
public class Pomodoro extends JPanel {

	// Fields
	private JLabel circleBg = new JLabel(); // Background around the timer
	private Font font = AppController.bigFont; // Font of the timer
	private DecimalFormat dFormat = new DecimalFormat("00"); // Decimal format used to format timer:
																// https://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html#:~:text=DecimalFormat%20is%20a%20concrete%20subclass,%2C%20Arabic%2C%20and%20Indic%20digits.
	private Timer workTimer; // Keeps track of tasks and updates timer label every second
	private int seconds = 0; // Keeps track of seconds
	private int minutes = 25; // Keeps track of minutes
	private String dSeconds, dMinutes; // Decimal format
	private JLabel counterLabel = new JLabel("25:00"); // Label to display timer
	private boolean breakTime = false; // Keeps track of whether it is break time or work time

	// Constructor
	public Pomodoro() {

		// Set the panel
		setLayout(null);
		setBounds(1600, 0, 320, 250);
		this.setBackground(new Color(254, 250, 224));

		// Setup labels and add them to screen
		setupCircleBg();
		setupCounterLabel();
		add(circleBg);
		add(counterLabel);

		// Initial visibility is false since this feature is optional
		setVisible(false);
	}

	// GETTERS AND SETTERS
	public JLabel getCircleBg() {
		return circleBg;
	}

	public void setCircleBg(JLabel circleBg) {
		this.circleBg = circleBg;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public DecimalFormat getdFormat() {
		return dFormat;
	}

	public void setdFormat(DecimalFormat dFormat) {
		this.dFormat = dFormat;
	}

	public Timer getWorkTimer() {
		return workTimer;
	}

	public void setWorkTimer(Timer workTimer) {
		this.workTimer = workTimer;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public String getdSeconds() {
		return dSeconds;
	}

	public void setdSeconds(String dSeconds) {
		this.dSeconds = dSeconds;
	}

	public String getdMinutes() {
		return dMinutes;
	}

	public void setdMinutes(String dMinutes) {
		this.dMinutes = dMinutes;
	}

	public JLabel getCounterLabel() {
		return counterLabel;
	}

	public void setCounterLabel(JLabel counterLabel) {
		this.counterLabel = counterLabel;
	}

	public boolean isBreakTime() {
		return breakTime;
	}

	public void setBreakTime(boolean breakTime) {
		this.breakTime = breakTime;
	}

	// UTILITY METHODS

	// Sets up timer background label
	private void setupCircleBg() {
		circleBg.setBounds(80, 20, 180, 180);
		circleBg.setIcon(
				new ImageIcon(new ImageIcon("images/circlePomodoro.png").getImage().getScaledInstance(175, 175, 0)));
	}

	// Sets up timer label
	private void setupCounterLabel() {
		counterLabel.setBounds(circleBg.getX() + 26, circleBg.getY() + 10, 150, 150);
		counterLabel.setFont(font);
	}

	/*
	 * This method starts the pomodoro timer by creating a new Java timer which updates every 1 second
	 */
	public void startPomodoro() {

		//Make the panel visible
		setVisible(true);
		
		//Create a new timer, updates every 1 second
		workTimer = new Timer(1000, new ActionListener() {

			//For every second, do the following
			@Override
			public void actionPerformed(ActionEvent event) {
				
				//Decrement the seconds
				seconds--;

				// Source: https://www.youtube.com/watch?v=zWw72j-EbqI
				
				//Convert the seconds and minutes to decimal format
				dSeconds = dFormat.format(seconds);
				dMinutes = dFormat.format(minutes);
				
				//Update the label
				counterLabel.setText(dMinutes + ":" + dSeconds);

				//If 60 seconds are complete, reset seconds and decrement minutes
				if (seconds == -1) {
					seconds = 59;
					minutes--;
					dSeconds = dFormat.format(seconds);
					dMinutes = dFormat.format(minutes);
					
					//Update the label
					counterLabel.setText(dMinutes + ":" + dSeconds);

				}

				//If 25 minutes are complete, start a break time timer
				if (minutes == 0 && seconds == 0 && !breakTime) {

					//Starts a break time timer
					takeBreak();
					breakTime = true;
				}

				//If break time is complete, start work timer
				if (minutes == 0 && seconds == 0 && breakTime) {

					//Start a work timer
					startWork();
					breakTime = false;
				}

			}

			//This method resets work timer by starting with 25 minutes
			private void startWork() {
				minutes = 25;
				seconds = 0;
				
				//Decimal format
				dSeconds = dFormat.format(seconds);
				dMinutes = dFormat.format(minutes);
				
				//Update the label
				counterLabel.setText(dMinutes + ":" + dSeconds);
				counterLabel.setForeground(Color.black);

			}

			//This method starts a break timer
			private void takeBreak() {
				
				//Display a message to inform user it's break time
				JOptionPane.showMessageDialog(null, "Break for 5 minutes");
				
				//5 minutes break
				minutes = 5;
				seconds = 0;
				
				//Decimal format
				dSeconds = dFormat.format(seconds);
				dMinutes = dFormat.format(minutes);
				
				//Update the label
				counterLabel.setText(dMinutes + ":" + dSeconds);
				counterLabel.setForeground(Color.red);
			}

		});

		//Start the timer
		workTimer.start();
	}

	//This method removes pomdoro timer
	public void stopPomodoro() {
		
		//Pause pomodoro timer
		pausePomodoro();
		
		//Reset minutes and seconds
		minutes = 25;
		seconds = 0;
		counterLabel.setText("25:00");
		
		//Set the visibility to false
		setVisible(false);
		repaint();
	}
	
	//This method pauses pomodoro
	public void pausePomodoro() {
		workTimer.stop();
	}

}
