package game.main;

import game.main.Game;

public class Launcher {
	
	public static void main(String[] args) {
		
		Game game = new Game("Snake", 1024, 1024);
		game.start();
		
	}

}
