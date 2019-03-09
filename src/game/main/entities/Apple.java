package game.main.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import game.main.Game;
import game.main.gfx.ImageLoader;

public class Apple {
	
	protected Game game;
	
	protected int x, y, size;
	protected BufferedImage apple = ImageLoader.loadImage("/textures/apple32.png");
	
	public Apple(Game game) {
		this.game = game;
		x = createRandomCoord();
		y = createRandomCoord();
		size = 32;
		
	}
	
	private int createRandomCoord() {
		Random rand = new Random();
		int n = rand.nextInt(512) / 32;
		int x = Math.round(n) * 32;
		
		return x;
	}
	
	public void isEaten() {
		x = createRandomCoord();
		y = createRandomCoord();
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.drawImage(apple, x, y, null);
		
	}
	
	public  int getX() {
		return x;
	}
	
	public  int getY() {
		return y;
	}

}
