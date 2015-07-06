import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		char command = e.getActionCommand().charAt(0);
		if (command == 'N') {
			TronContentPane.contentPanel.runThread.suspend();
			TronContentPane.contentPanel.newGame();
			TronContentPane.contentPanel.runThread.resume();
		}
	}

}

