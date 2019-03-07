package game.main.entities;

import java.awt.Color;
import java.awt.Graphics;

import game.main.Game;

public class Snake extends Entity {
	
	protected Game game;
	
	private int velX, velY, speed, ticks;
	
	public Snake(Game game, int x, int y) {
		super(x, y);
		this.game = game;
		velX = 0;
		velY = 0;
		speed = 50;
		ticks = 0;

	}
	
	protected void move() {
		x += velX;
		y += velY;
	}
	
	@Override
	public void tick() {
		
		if(ticks < 15) {
			getInput();
			ticks++;
		}
		else {
			move();
			ticks = 0;
		}
		
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
		g.fillRect(x, y, sizeX, sizeY);
		
	}
	
}
