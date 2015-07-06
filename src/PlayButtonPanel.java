import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;


public class PlayButtonPanel extends JPanel {
	
	static JButton play = new JButton("PLAY");

	public PlayButtonPanel() {
		
		this.setBackground(Color.GREEN);
		this.setVisible(true);
		this.add(play);
		
		play.setVisible(true);

		
	}
}
