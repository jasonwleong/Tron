import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InstructionPanel extends JPanel {

	JPanel buttonPanel = new JPanel();

	public InstructionPanel() {
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(Color.WHITE);
		//purpose of game
		g.drawString(
				"This is a 2 player game. The goal of the game is to isolate"
						+ " and block available paths and force them to to run into a solid object" , 50, 120);
		g.drawString("Thus, a point will be gained for the opposing player if any player runs into" 
				+ " a wall, itself, or another player.", 50, 140);
		g.drawString("In the event that both players butt heads, a tie occurs and no points will be awarded that game.", 50, 160);
		
		//player one
		g.drawString("- Moves with WASD Keys", 70, 230);
		g.drawString("- colored CYAN", 70, 250);
		g.drawString("- player BEGINS ON LEFT SIDE of the screen", 70, 270);
		g.drawString("- begins every game MOVING RIGHT, towards the center of the screen", 70, 290);
		
		//player two
		g.drawString("- Moves with ARROW Keys", 70, 370);
		g.drawString("- colored YELLOW", 70, 390);
		g.drawString("- player BEGINS ON RIGHT SIDE of the screen", 70, 410);
		g.drawString("- begins every game MOVING LEFT, towards the center of the screen", 70, 430);
		
		// other general instructions
		g.drawString("Players may choose to start a new game by either pressing the 'New Game' button on the bottom or by pressing SPACE BAR.", 50, 480);
		g.drawString("There is a slider with three major ticks to adjust the speed of both players: POTATO (slow), NORMAL, and GOD TIER (fast).", 50 , 500);
		g.drawString("There is no finite end to the game, players are left to decide how long a match of Tron will be as the score will simply increase accordingly.", 50, 520);
		g.drawString("Scores are recorded on the bottom of the screen. This panel will change the game itself will begin precisely when the 'PLAY' button is pressed.", 50, 540);
		g.drawString("HAVE FUN!", 300, 580);
		
		// large TNR font, player one and two titles
		g.setFont(new Font("Times New Roman", Font.HANGING_BASELINE, 35));
		g.setColor(Color.CYAN);
		g.drawString("Player One", 50 , 205);
		g.setColor(Color.YELLOW);
		g.drawString("Player Two", 50 , 340);
		
		// welcome line
		g.setFont(new Font("Comic Sans", Font.BOLD, 60));
		g.setColor(Color.GREEN);
		g.drawString("Welcome to Tron!", 50, 85);
		
	}
}
