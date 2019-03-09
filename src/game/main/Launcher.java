package game.main;

import game.main.Game;

public class Launcher {
	
	public static void main(String[] args) {
		
		Game game = new Game("Snake", 512, 512);
		game.start();
		
	}

}
