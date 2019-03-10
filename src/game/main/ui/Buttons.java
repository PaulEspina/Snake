package game.main.ui;

import java.awt.Graphics;
import java.util.ArrayList;

import game.main.Game;

public abstract class Buttons {	
	
	protected Game game;
	
	protected int x, y;
	
	protected boolean onMouse;
	
	protected ArrayList<Integer> xBox = new ArrayList<Integer>();
	protected ArrayList<Integer> yBox = new ArrayList<Integer>();
	
	public Buttons(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
		
		for(int i = x; i <= x + 64 * 2; i++) {
			xBox.add(i);
		}
		
		for(int i = y; i <= y + 32 * 2; i++) {
			yBox.add(i);
		}
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
