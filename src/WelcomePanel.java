import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;


public class WelcomePanel extends JPanel {
	
	InstructionPanel instructions = new InstructionPanel();
	PlayButtonPanel buttonPanel = new PlayButtonPanel();
	
	
	public WelcomePanel() {
		

		buttonPanel.setVisible(true);
		instructions.setVisible(true);
		
		this.setLayout(new BorderLayout());
		this.add(instructions);
		this.add(buttonPanel, BorderLayout.SOUTH);
	
		
	}
	
	

}
