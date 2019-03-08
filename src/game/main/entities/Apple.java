package game.main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import game.main.Game;

public class Apple {
	
	protected Game game;
	
	protected int x, y, size;
	
	public Apple(Game game) {
		this.game = game;
		x = createRandomCoord();
		y = createRandomCoord();
		size = 64;
		
	}
	
	private int createRandomCoord() {
		Random rand = new Random();
		int n = rand.nextInt(1024) / 64;
		int x = Math.round(n) * 64;
		
		return x;
	}
	
	public void isEaten() {
		x = createRandomCoord();
		y = createRandomCoord();
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, size, size);
		
	}
	
	public  int getX() {
		return x;
	}
	
	public  int getY() {
		return y;
	}

}
