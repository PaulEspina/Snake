package game.main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import game.main.Game;

public class Snake extends Entity {
	
	protected Game game;
	
	protected int velX, velY, speed, ticks, length;
	protected ArrayList<Integer> xCoords = new ArrayList<Integer>();
	protected ArrayList<Integer> yCoords = new ArrayList<Integer>();
	
	public Snake(Game game, int x, int y) {
		super(x, y);
		this.game = game;
		velX = 0;
		velY = 0;
		speed = 50;
		ticks = 0;
		length = 1;
		xCoords.add(x);
		yCoords.add(y);

	}

	protected void move() {
		x += velX;
		y += velY;
		xCoords.add(x);
		yCoords.add(y);
	}
	
	public void grow() {
		length++;
	}
	
	@Override
	public void tick() {
		
		
		
		if(length < xCoords.size()) {
			xCoords.remove(0);
			yCoords.remove(0);
		}
		
		//This makes key presses responsive and makes the snake move
		//every 5 frames.
		if(ticks < 5) {
			getInput();
			ticks++;
		}
		else {
			move();
			ticks = 0;
		}
		//
	}
	
	protected void getInput() {
		if(game.getKeyManager().up) {
			velY = -speed;
			velX = 0;
		}
		if(game.getKeyManager().down) {
			velY = +speed;
			velX = 0;
		}
		if(game.getKeyManager().left) {
			velX = -speed;
			velY = 0;
		}
		if(game.getKeyManager().right) {
			velX = +speed;
			velY = 0;
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		//g.fillRect(x, y, size, size);
		for(int i = 0; i < length; i++) {
			g.fillRect(xCoords.get(i), yCoords.get(i), size, size);
		}
	}
	
	public  int getX() {
		return x;
	}
	
	public  int getY() {
		return y;
	}
	
}
