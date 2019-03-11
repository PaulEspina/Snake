package game.main.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.main.Game;
import game.main.gfx.ImageLoader;

public class GameOver extends State {
	
	private Game game;
	
	private BufferedImage gameOver = ImageLoader.loadImage("/ui/gameOver.png");
	
	private int tick;
	
	public GameOver(Game game) {
		this.game = game;
		tick = 0;
	}
	
	@Override
	public void tick() {
		tick++;
		if(tick == 90) {
			State.setState(game.menuState);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(gameOver, 0, 0, null);
		
	}

}
