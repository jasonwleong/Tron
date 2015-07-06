import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tron {

	public static void main(String[] args) {

		TronContentPane contentPane = new TronContentPane();
		JFrame window = new JFrame("Tron");
		window.setSize(1010, 695);
		window.setResizable(false);
		window.setLocation(0, 10);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.add(contentPane);

	}
}
