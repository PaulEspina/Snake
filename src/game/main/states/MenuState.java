package game.main.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.main.Game;
import game.main.gfx.ImageLoader;
import game.main.ui.PlayButton;
import game.main.ui.QuitButton;

public class MenuState extends State {
	
	@SuppressWarnings("unused")
	private Game game;
	
	private PlayButton playButton;
	private QuitButton quitButton;
	
	private BufferedImage menuSnake = ImageLoader.loadImage("/ui/menuSnake.png");
	
	public MenuState(Game game) {
		this.game = game;
		playButton = new PlayButton(game, game.width / 2 - 32 * 2, 300);
		quitButton = new QuitButton(game, game.width / 2 - 32 * 2, 375);
	}
	
	@Override
	public void tick() {
		
		playButton.tick();
		quitButton.tick();
		
	}

	@Override
	public void render(Graphics g) {
		Color myGreen = new Color(100, 150, 100);
		g.setColor(myGreen);
		g.fillRect(0, 0, game.width, game.height);
		g.drawImage(menuSnake, game.width/2 - 175, 20, 350, 275, null);
		
		playButton.render(g);
		quitButton.render(g);
		
	}

}
