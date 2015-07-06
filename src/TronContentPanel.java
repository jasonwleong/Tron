import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class TronContentPanel extends JPanel implements KeyListener, Runnable,
		ActionListener {
	
	Color player1Color, player2Color;
	Color player1HeadColor, player2HeadColor;

	boolean upPressed, downPressed, leftPressed, rightPressed;

	// directions for player1
	public static final int noDirection = 0;
	public static final int up = 1;
	public static final int down = 2;
	public static final int left = 3;
	public static final int right = 4;

	// directions for player2
	public static final int notMoving = 5;
	public static final int north = 6;
	public static final int south = 7;
	public static final int west = 8;
	public static final int east = 9;

	int direction, direction2;

	static LinkedList<Point> player1;
	static LinkedList<Point> player2;

	Point player1Head;
	Point player2Head;

	// final values of grid and its units
	final int unitHeight = 10;
	final int unitWidth = 10;
	final int gridHeight = 100;
	final int gridWidth = 100;

	// variables created to make calculations easier
	int width, height;

	private boolean threadStopped;
	public static int sleepTime = 50;

	public static Thread runThread;

	Point player1NewPoint;
	Point player2NewPoint;

	// score for each player
	public int p1Score = 0;
	public int p2Score = 0;

	private boolean player1Wins;
	private boolean player2Wins;
	private boolean tieGame;

	Timer timer = new Timer(50, this);

	public TronContentPanel() {

		// colors of player1 and player2
		player1Color = Color.CYAN;
		player2Color = Color.YELLOW;

		// colors of player1 and player2 heads
		player1HeadColor = Color.blue;
		player2HeadColor = Color.orange;

		// change properties of this panel
		this.setBackground(Color.BLACK);
		int direction = noDirection;
		int direction2 = notMoving;

		// add listeners
		this.addKeyListener(this);

		// initialize players
		player1 = new LinkedList<Point>();
		player2 = new LinkedList<Point>();

		// establish points of head
		player1Head = new Point(9, 30);
		player2Head = new Point(90, 30);

		// run thread
		runThread = new Thread(this);
		runThread.start();
		timer.start();

		// set booleans to begin as false
		threadStopped = false;
		player1Wins = false;
		player2Wins = false;
		tieGame = false;

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.requestFocus();
		drawGrid(g);
		drawPlayers(g);
		// if (player1Wins || player2Wins || tieGame) {
		// drawGameOver(g);
		// repaint();
		// }

	}

	public void newGame() {
		player1.clear();
		player2.clear();
		player1 = new LinkedList<Point>();
		player2 = new LinkedList<Point>();
		player1Head = new Point(9, 30);
		player2Head = new Point(90, 30);
		direction = right;
		direction2 = west;
		move();
	}

	public void drawGrid(Graphics g) {
		width = gridWidth * unitWidth;
		height = gridHeight * unitHeight;

		// draw grid in background
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(0, 0, width, height);
		for (int verticalLines = unitWidth; verticalLines < width; verticalLines += unitWidth) {
			g.drawLine(verticalLines, 0, verticalLines, width);
		}
		for (int horizontalLines = unitHeight; horizontalLines < height; horizontalLines += unitHeight) {
			g.drawLine(0, horizontalLines, height, horizontalLines);
		}
	}

	public void drawPlayers(Graphics g) {
		// draw player1
		g.setColor(player1Color);
		for (Point p1 : player1) {
			g.fillRect(p1.x * unitWidth, p1.y * unitHeight, unitWidth,
					unitHeight);

		}

		// draw player2
		g.setColor(player2Color);
		for (Point p2 : player2) {
			g.fillRect(p2.x * unitWidth, p2.y * unitHeight, unitWidth,
					unitHeight);
		}

	}

	public void drawGameOver(Graphics g) {
		if (tieGame) {
			g.setFont(new Font("Arial", Font.ITALIC, 100));
			g.setColor(Color.MAGENTA);
			g.drawString("Tie Game!", 275, 325);
		}
		if (player1Wins) {
			g.setFont(new Font("Arial", Font.BOLD, 100));
			g.setColor(Color.MAGENTA);
			g.drawString("Player 1 Wins!", 150, 175);
		}
		if (player2Wins) {
			g.setFont(new Font("Arial", Font.BOLD, 100));
			g.setColor(Color.MAGENTA);
			g.drawString("Player 2 Wins!", 150, 475);
		}
	}

	public void move() {

		// moving player1 with WASD
		switch (direction) {
		case up:
			player1NewPoint = new Point(player1Head.x, player1Head.y - 1);
			break;
		case down:
			player1NewPoint = new Point(player1Head.x, player1Head.y + 1);
			break;
		case left:
			player1NewPoint = new Point(player1Head.x - 1, player1Head.y);
			break;
		case right:
			player1NewPoint = new Point(player1Head.x + 1, player1Head.y);
			break;
		default:
			direction = right;
		}

		// moving player2 with arrow keys
		switch (direction2) {
		case north:
			player2NewPoint = new Point(player2Head.x, player2Head.y - 1);
			break;
		case south:
			player2NewPoint = new Point(player2Head.x, player2Head.y + 1);
			break;
		case west:
			player2NewPoint = new Point(player2Head.x - 1, player2Head.y);
			break;
		case east:
			player2NewPoint = new Point(player2Head.x + 1, player2Head.y);
			break;
		default:
			direction2 = west;

		}
		// check if game is over, if so, stop thread
		if (shouldStopThread()) {
			threadStopped = true;
			runThread.suspend();
		}

		if (player1NewPoint != player1Head && player1NewPoint != null) {
			player1.add(player1NewPoint);
		}
		if (player2NewPoint != player2Head && player2NewPoint != null) {
			player2.add(player2NewPoint);
		}
		if (player1NewPoint != null && player1NewPoint != player1Head)
			player1Head = player1NewPoint;
		if (player2NewPoint != null && player2NewPoint != player2Head)
			player2Head = player2NewPoint;

	}

	public boolean shouldStopThread() {
		
		if (player1NewPoint == null || player2NewPoint == null)
			return false;
		else if ((player1.contains(player2NewPoint))
				&& (player2.contains(player1NewPoint))) {
			tieGame = true;
			return true;
		} else if ((player1NewPoint.x < 0 || player1NewPoint.x >= gridWidth)
				|| (player1NewPoint.y < 0 || player1NewPoint.y >= 61)
				|| (player1.contains(player1NewPoint) && player1NewPoint != player1Head)
				|| (player2.contains(player1NewPoint))) {
			p2Score++;
			player2Wins = true;
			Interface.p2.setText("Player 2: " + p2Score);
			return true;

		} else if ((player2NewPoint.x < 0 || player2NewPoint.x >= gridWidth)
				|| (player2NewPoint.y < 0 || player2NewPoint.y >= 61)
				|| (player2.contains(player2NewPoint) && player2NewPoint != player2Head) 
				|| (player1.contains(player2NewPoint))) {
			p1Score++;
			Interface.p1.setText("Player 1: " + p1Score);
			player1Wins = true;
			return true;
		}
		return false;

	}

	@Override
	public void keyPressed(KeyEvent evt) {
		
		switch (evt.getKeyCode()) {
		// player1
		case KeyEvent.VK_W:
			if (direction != down)
				direction = up;
			break;
		case KeyEvent.VK_S:
			if (direction != up)
				direction = down;
			break;
		case KeyEvent.VK_A:
			if (direction != right)
				direction = left;
			break;
		case KeyEvent.VK_D:
			if (direction != left)
				direction = right;
			break;
			
		// player2
		case KeyEvent.VK_UP:
			if (direction2 != south)
				direction2 = north;
			break;
		case KeyEvent.VK_DOWN:
			if (direction2 != north)
				direction2 = south;
			break;
		case KeyEvent.VK_LEFT:
			if (direction2 != east)
				direction2 = west;
			break;
		case KeyEvent.VK_RIGHT:
			if (direction2 != west)
				direction2 = east;
			break;
		case KeyEvent.VK_SPACE:
			this.runThread.suspend();
			newGame();
			this.runThread.resume();
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent evt) {

		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while (true) {
			move();
			repaint();
			try {
				Thread.currentThread();
				Thread.sleep(sleepTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.requestFocus();

	}

}
