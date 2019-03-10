package game.main.states;

import java.awt.Graphics;
import java.util.ArrayList;

import game.main.Game;
import game.main.entities.Apple;
import game.main.entities.Snake;

public class GameState extends State {
	
	private Snake snake;
	private Apple apple;
	
	private ArrayList<Integer> xCoords, yCoords;
	
	public GameState(Game game) {
		snake = new Snake(game);
		apple = new Apple();
		
	}
	
	@Override
	public void tick() {
		
		xCoords = snake.getXCoords();
		yCoords = snake.getYCoords();
		
		//This prevents apple from spawning on snake's body
		for(int i = 0; i < xCoords.size() - 1; i++) {
			if(xCoords.get(i) == apple.getX() & yCoords.get(i) == apple.getY()) {
				apple.isEaten();
				
			}
		}
		
		
		//The eating mechanism
		if(snake.getX() == apple.getX() & snake.getY() == apple.getY()) {
			apple.isEaten();
			snake.grow();
			
		}
		
		snake.tick();
		
		apple.tick();
		
	}

	@Override
	public void render(Graphics g) {
		
		apple.render(g);
		snake.render(g);
		
		
	}

}
