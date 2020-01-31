package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Random;

import javax.swing.Timer;
import javax.swing.JComponent;

/**
 * 
 * @author Ian Clingerman
 * 
 *         This class is the canvas for the game. This class keeps track of the
 *         snake's movement and position, spawning the food, and keeping track
 *         of whether the game is over or not
 *
 */
public class Canvas extends JComponent implements KeyListener {

	protected int gridWidth; // number of cells in the width
	protected int gridHeight; // number of cells in the height
	protected int gridSize; // size of the boxes in the grid
	protected boolean gameOver;
	protected Cell[][] grid;

	protected Snake snake;

	protected Food food;

	protected Timer up, down, left, right;
	protected int timerStep;

	protected Random r;

	protected int score;

	public Canvas() {
		gridWidth = 20;
		gridHeight = 20;
		gridSize = 30;
		this.setPreferredSize(new Dimension(gridWidth * gridSize + 1, gridHeight * gridSize + 1));
		grid = new Cell[gridWidth][gridHeight];
		for (int i = 0; i < gridWidth; i++) {
			for (int j = 0; j < gridHeight; j++) {
				grid[i][j] = new Cell(i * gridSize, j * gridSize, gridSize);
			}
		}
		addKeyListener(this);
		setFocusable(true);
		timerStep = 175;
		up = new Timer(timerStep, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				snake.moveBody();
				checkGame();
				repaint();
			}
		});

		down = new Timer(timerStep, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				snake.moveBody();
				checkGame();
				repaint();
			}
		});

		left = new Timer(timerStep, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				snake.moveBody();
				checkGame();
				repaint();
			}
		});

		right = new Timer(timerStep, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				snake.moveBody();
				checkGame();
				repaint();
			}
		});

		r = new Random();
		snake = new Snake(5 * gridSize, (int) (gridWidth / 2) * gridSize, gridSize);
		food = new Food(r.nextInt(gridWidth) * gridSize, r.nextInt(gridHeight) * gridSize, gridSize);
		gameOver = false;
		score = 0;

	}

	public void paintComponent(Graphics g) {
		if (!gameOver) {
			for (int i = 0; i < gridWidth; i++) {
				for (int j = 0; j < gridHeight; j++) {
					grid[i][j].draw(g);
				}
			}
			g.setColor(Color.BLACK);
			snake.draw(g);
			food.draw(g);
		} else if (gameOver) {
			g.setFont(new Font("Arial", Font.BOLD, gridSize));
			g.drawString("Game Over", 7 * gridSize, 6 * gridSize - 5);
			for (int i = 0; i < gridWidth; i++) {
				for (int j = 0; j < gridHeight; j++) {
					grid[i][j].draw(g);
				}
			}
			g.drawString("Press Enter to Restart", 5 * gridSize, 7 * gridSize - 5);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'w') {
			snake.setDirection('U');
			snake.moveBody();
			checkGame();
			up.restart();
			down.stop();
			left.stop();
			right.stop();
		} else if (e.getKeyChar() == 's') {
			snake.setDirection('D');
			snake.moveBody();
			checkGame();
			down.restart();
			up.stop();
			left.stop();
			right.stop();
		} else if (e.getKeyChar() == 'a') {
			snake.setDirection('L');
			snake.moveBody();
			checkGame();
			left.restart();
			right.stop();
			up.stop();
			down.stop();
		} else if (e.getKeyChar() == 'd') {
			snake.setDirection('R');
			snake.moveBody();
			checkGame();
			right.restart();
			left.stop();
			up.stop();
			down.stop();
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void checkGame() {
		if (snake.getX() == food.getX() && snake.getY() == food.getY()) {
			snake.eat();
			food = spawnFood();
			score++;
		}

		if (snake.getX() > this.getWidth() - this.gridSize || snake.getX() < 0
				|| snake.getY() > this.getHeight() - this.gridSize || snake.getY() < 0 || snake.check()) {
			up.stop();
			down.stop();
			left.stop();
			right.stop();
			this.gameOver = true;
		}
	}

	// This function ensures that the food does not spawn on one of the pieces of
	// the snake by recursively calling the function and checking the randomly
	// generated x and y coordinates of the food with those of the snake body pieces
	public Food spawnFood() {
		Food newFood = new Food(r.nextInt(gridWidth) * gridSize, r.nextInt(gridHeight) * gridSize, gridSize);
		boolean unique = false;
		while (!unique) {
			for (int i = 0; i < snake.getBody().size(); i++) {
				if (snake.getBody().get(i).getX() == newFood.getX()
						&& snake.getBody().get(i).getY() == newFood.getY()) {
					unique = false;
					newFood = spawnFood();
					break;
				}
			}
			unique = true;
		}

		return newFood;
	}

	public int getScore() {
		return score;
	}

	public boolean isGameOver() {
		if (gameOver) {
			snake = new Snake(5 * gridSize, (int) (gridWidth / 2) * gridSize, gridSize);
			food = new Food(r.nextInt(gridWidth) * gridSize, r.nextInt(gridHeight) * gridSize, gridSize);
			gameOver = false;
			score = 0;
			repaint();
			return true;
		} else
			return false;
	}
}
