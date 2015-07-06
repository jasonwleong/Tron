import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Interface extends JPanel {
	
	public static JSlider speedSlider;
	public static JButton newGame = new JButton("New Game");

	public static JLabel p1;
	public static JLabel p2;
	public static JCheckBox grid;
	
	JLabel currentSpeed;
	JLabel speedLabel;
	
	
	public Interface() {
		grid = new JCheckBox("Grid");
		p1 = new JLabel("Player 1: " + TronContentPane.contentPanel.p1Score);
		p2 = new JLabel("Player 1: " + TronContentPane.contentPanel.p1Score);
		p1.setFont(new Font("Comic Sans", Font.BOLD, 40));
		p2.setFont(new Font("Comic Sans", Font.BOLD, 40));
		
		currentSpeed = new JLabel("Normal");
		currentSpeed.setForeground(Color.WHITE);
		speedLabel = new JLabel("Speed: ");
		speedLabel.setForeground(Color.WHITE);
		ButtonListener buttonListener = new ButtonListener();
		speedSlider = new JSlider(0, 2, 1);
		speedSlider.addChangeListener(new SliderListener());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.GREEN);
		buttonPanel.add(newGame);
		buttonPanel.setVisible(true);
		
		newGame.addActionListener(buttonListener);
		newGame.setVisible(true);
		
		p1.setForeground(Color.WHITE);
		p2.setForeground(Color.WHITE);
	
		this.setBackground(Color.BLUE);
		this.add(p1);
		this.add(buttonPanel);
		this.add(speedLabel);
		this.add(currentSpeed);
		this.add(speedSlider);
		this.add(p2);
		this.setVisible(true);
		
	}

	public class SliderListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			int value = speedSlider.getValue();
			if (value == 0) {
				TronContentPanel.sleepTime = 75;
				currentSpeed.setText("Potato");
			}
			else if (value == 1) {
				TronContentPanel.sleepTime = 50;
				currentSpeed.setText("Normal");
			}
			else if (value == 2) {
				TronContentPanel.sleepTime = 25;
				currentSpeed.setText("God Tier");
			}
			
		}
		
	}
}
