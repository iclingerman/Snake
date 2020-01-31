package snake;

import java.awt.Graphics;

public class Cell {
	private int x;
	private int y;
	private int width;
	
	public Cell(int x, int y, int width) {
		this.x = x;
		this.y = y; 
		this.width = width;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", width=" + width + "]";
	}
	
	public void draw(Graphics g) {
		g.drawRect(x, y, width, width);
	}
}
