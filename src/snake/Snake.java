package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {
	protected int x, y, size, length;
	/*
	 * x - x coordinate, y - y coordinate, size - size of the squares of the snake,
	 * length - length of the snake body (not including the head)
	 */

	protected char direction;

	protected ArrayList<SnakeBody> body;

	public Snake(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.length = 2;
		this.direction = 'R';

		this.body = new ArrayList<SnakeBody>();

		for (int i = 1; i <= this.length; i++) {
			body.add(new SnakeBody(this.x - (i * this.size), this.y, this.size));
		}
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void eat() {
		this.length++;
	}

	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}

	
	public ArrayList<SnakeBody> getBody() {
		return body;
	}

	public void setBody(ArrayList<SnakeBody> body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Snake [x=" + x + ", y=" + y + ", size=" + size + ", length=" + length + "]";
	}

	public void moveBody() {
		if(this.length > this.body.size()-1) {
			this.body.add(new SnakeBody(body.get(body.size()-1).getX(), body.get(body.size()-1).getY(), this.size));
		}
		for (int i = this.body.size() - 1; i > 0; i--) {
			this.body.get(i).setX(this.body.get(i-1).getX());
			this.body.get(i).setY(this.body.get(i-1).getY());
		}
		switch(this.direction) {
		case 'U':
			this.body.get(0).setY(this.y);
			this.body.get(0).setX(this.x);
			this.y = this.y - this.size;
			break;
		case 'D':
			this.body.get(0).setY(this.y);
			this.body.get(0).setX(this.x);
			this.y = this.y + this.size;
			break;
		case 'L':
			this.body.get(0).setX(this.x);
			this.body.get(0).setY(this.y);
			this.x = this.x - this.size;
			break;
		case 'R':
			this.body.get(0).setX(this.x);
			this.body.get(0).setY(this.y);
			this.x = this.x + this.size;
			break;
		}
	}

	public void draw(Graphics g) {
		for (int i = 0; i < this.body.size(); i++) {
			g.setColor(Color.BLACK);
			this.body.get(i).draw(g);
		}
		g.setColor(Color.BLUE);
		g.fillRect(x, y, size, size);
		g.setColor(Color.BLACK);
	}
	
	public boolean check() {
		if(length > 400) {
			return true;
		}
		
		for(int i = 0; i < body.size(); i++) {
			if(this.x == body.get(i).getX() && this.y == body.get(i).getY()) {
				return true;
			}
		}
		
		return false;
	}
}
