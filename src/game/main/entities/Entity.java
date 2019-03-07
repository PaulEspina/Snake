package game.main.entities;

import java.awt.Graphics;

public abstract class Entity {
	
	protected int x, y, sizeX, sizeY;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		sizeX = 50;
		sizeY = 50;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
