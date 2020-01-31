package snake;

import java.awt.Color;
import java.awt.Graphics;

public class Food {
	protected int x, y, size;
	
	public Food(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "Food [x=" + x + ", y=" + y + ", size=" + size + "]";
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, size, size);
		g.setColor(Color.BLACK);
	}
}
