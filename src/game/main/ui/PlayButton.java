package game.main.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.main.Game;
import game.main.gfx.ImageLoader;
import game.main.states.State;

public class PlayButton extends Buttons {
	
	private BufferedImage playButton = ImageLoader.loadImage("/ui/buttons/playButton.png");
	private BufferedImage playButtonHovered = ImageLoader.loadImage("/ui/buttons/playButtonHovered.png");
	
	public PlayButton(Game game, int x, int y) {
		super(game, x, y);
		
	}
	
	private void checkMouse() {
		onMouse = false;
		for(int i = 0; i < xBox.size(); i++) {
			for(int j = 0; j < yBox.size(); j++) {
				if(game.getMouseManager().getMouseX() == xBox.get(i) & game.getMouseManager().getMouseY() == yBox.get(j)) {
					onMouse = true;
					if(game.getMouseManager().isLeftPressed()) {
						State.setState(game.gameState);
					}
				}
			}
		}
	}

	@Override
	public void tick() {
		
		checkMouse();
		
	}

	@Override
	public void render(Graphics g) {
		if(!onMouse) {
			g.drawImage(playButton, x, y, 64 * 2, 32 * 2, null);
		} else {
			g.drawImage(playButtonHovered, x, y, 64 * 2, 32 * 2, null);
		}
	}
	
}