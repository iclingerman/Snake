package snake;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author Ian Clingerman
 * 
 *         This class is the frame for the game canvas and tracks key presses
 *         for movement and restarting the game
 *
 */
public class Frame extends JFrame implements KeyListener {
	protected Canvas canvas;

	protected JLabel currentScore;
	protected int score;
	protected JLabel currentHighScore;
	protected int highScore;
	protected Timer timer;

	protected boolean started = false;
	protected int prevKey;

	// Class constructor
	public Frame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setResizable(false);

		addKeyListener(this);
		setFocusable(true);

		// Panels to provide the border for the game window
		JPanel north = new JPanel();
		this.add(north, BorderLayout.NORTH);

		JPanel west = new JPanel();
		this.add(west, BorderLayout.WEST);

		JPanel east = new JPanel();
		this.add(east, BorderLayout.EAST);

		JPanel south = new JPanel();
		this.add(south, BorderLayout.SOUTH);

		// Labels for the current and high scores
		currentScore = new JLabel("Score: " + score + "    ");
		north.add(currentScore);

		currentHighScore = new JLabel("High Score: " + highScore);
		north.add(currentHighScore);

		// The game canvas in the center of the screen
		canvas = new Canvas();
		this.add(canvas, BorderLayout.CENTER);

		pack();

		// timer to check the score from the canvas class consistently
		timer = new Timer(1, new ActionListener() {
			/**
			 * This method gets the score from the canvas class and updates the score label
			 * as well as the high score label if needed
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				score = canvas.getScore();
				currentScore.setText("Score: " + score + "    ");
				if (score > highScore) {
					highScore = score;
					currentHighScore.setText("High Score: " + highScore);
				}
			}
		});
		timer.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * This method keeps track of key presses for the movement of the snake. It does
	 * not allow the user to press the same key twice in a row and to press the
	 * inverse key of the last pressed key
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (!started && e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S
				|| e.getKeyCode() == KeyEvent.VK_D) {
			started = true;
		}
		if (e.getKeyCode() != prevKey && started) {
			if (e.getKeyCode() == KeyEvent.VK_W && prevKey != KeyEvent.VK_S) {
				canvas.keyPressed(e);
			} else if (e.getKeyCode() == KeyEvent.VK_S && prevKey != KeyEvent.VK_W) {
				canvas.keyPressed(e);
			} else if (e.getKeyCode() == KeyEvent.VK_A && prevKey != KeyEvent.VK_D) {
				canvas.keyPressed(e);
			} else if (e.getKeyCode() == KeyEvent.VK_D && prevKey != KeyEvent.VK_A) {
				canvas.keyPressed(e);
			}
			prevKey = e.getKeyCode();
		}

	}

	/**
	 * This method is used in restarting the game after the game over screen. If the
	 * key entered is enter and the canvas method isGameOver returns true, then the
	 * started variable for key presses is set to false to prepared to start the new
	 * game
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && canvas.isGameOver()) {
			started = false;
		}
	}

	// Main method for the game
	public static void main(String[] args) {
		new Frame().setVisible(true);
	}
}
