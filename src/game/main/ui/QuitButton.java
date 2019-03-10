package game.main.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.main.Game;
import game.main.gfx.ImageLoader;

public class QuitButton extends Buttons {
	
	private BufferedImage quitButton = ImageLoader.loadImage("/ui/buttons/quitButton.png");
	private BufferedImage quitButtonHovered = ImageLoader.loadImage("/ui/buttons/quitButtonHovered.png");
	int coveredAreaX, coveredAreaY;
	
	public QuitButton(Game game, int x, int y) {
		super(game, x, y);

	}
	
	private void checkMouse() {
		onMouse = false;
		for(int i = 0; i < xBox.size(); i++) {
			for(int j = 0; j < yBox.size(); j++) {
				if(game.getMouseManager().getMouseX() == xBox.get(i) & game.getMouseManager().getMouseY() == yBox.get(j)) {
					onMouse = true;
					
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
			g.drawImage(quitButton, x, y, 64 * 2, 32 * 2, null);
		} else {
			g.drawImage(quitButtonHovered, x, y, 64 * 2, 32 * 2, null);
		}
		
	}

}