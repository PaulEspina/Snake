package game.main.entities;

import java.awt.Graphics;

public abstract class Entity {
	
	protected  int x;
	protected  int y;
	protected int size;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		size = 50;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
