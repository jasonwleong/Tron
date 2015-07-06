import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class TronContentPane extends JPanel {
	public static TronContentPanel contentPanel = new TronContentPanel();
	public static JPanel panel = new JPanel();
	public static Interface userInterface = new Interface();
	public static WelcomePanel welcome = new WelcomePanel();

	public TronContentPane() {
		panel.setBackground(Color.BLUE);
		this.setLayout(new BorderLayout());
		this.add(welcome);
		welcome.buttonPanel.play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				welcome.setVisible(false);
				add(panel, BorderLayout.NORTH);
				add(contentPanel);
				add(userInterface, BorderLayout.SOUTH);
				setVisible(true);
				TronContentPane.contentPanel.runThread.suspend();
				TronContentPane.contentPanel.newGame();
				TronContentPane.contentPanel.runThread.resume();
			}
		});
	}

}
