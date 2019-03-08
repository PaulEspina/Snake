package game.main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import game.main.Game;

public class Apple extends Entity {
	
	protected Game game;

	public Apple(Game game, int x, int y) {
		super(x, y);
		this.game = game;
		
	}
	
	public int newX() {
		Random rand = new Random();
		int nx = rand.nextInt(1024) / 64;
		int newX = Math.round(nx) * 64;
		
		return newX;
	}
	
	public int newY() {
		Random rand = new Random();
		int ny = rand.nextInt(1024) / 64;
		int newY = Math.round(ny) * 64;
		
		return newY;
	}
	
	public void isEaten() {
		x = newX();
		y = newY();
	}

	@Override
	public void tick() {

	}

	@Override
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
