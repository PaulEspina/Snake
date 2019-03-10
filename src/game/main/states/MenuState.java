package game.main.states;

import java.awt.Graphics;

import game.main.Game;
import game.main.ui.PlayButton;
import game.main.ui.QuitButton;

public class MenuState extends State {
	
	@SuppressWarnings("unused")
	private Game game;
	
	private PlayButton playButton;
	private QuitButton quitButton;
	
	public MenuState(Game game) {
		this.game = game;
		playButton = new PlayButton(game, game.width / 2 - 32 * 2, game.height / 2);
		quitButton = new QuitButton(game, game.width / 2 - 32 * 2, game.height /2 + 96);
	}
	
	@Override
	public void tick() {
		
		playButton.tick();
		quitButton.tick();
		
	}

	@Override
	public void render(Graphics g) {
		
		playButton.render(g);
		quitButton.render(g);
		
	}

}
