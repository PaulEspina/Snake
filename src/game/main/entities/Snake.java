package game.main.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.main.Game;
import game.main.gfx.ImageLoader;

public class Snake {
	
	protected Game game;
	
	protected int velX, velY, speed, ticks, length, x, y, size;
	protected boolean isMovingX, isMovingY, isMoving;
	protected ArrayList<Integer> xCoords = new ArrayList<Integer>();
	protected ArrayList<Integer> yCoords = new ArrayList<Integer>();
	
	protected BufferedImage snakeHead = ImageLoader.loadImage("/textures/snakeHead.png");
	protected BufferedImage snakeBody = ImageLoader.loadImage("/textures/snakeBody.png");
	protected BufferedImage snakeTail = ImageLoader.loadImage("/textures/snakeTail.png");
	
	public Snake(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
		velX = 0;
		velY = 0;
		speed = 64;
		size = 64;
		ticks = 0;
		length = 3;
		isMoving = false;
		xCoords.add(x);
		xCoords.add(x);
		xCoords.add(x);
		yCoords.add(y + size * 2);
		yCoords.add(y + size);
		yCoords.add(y);
		
	}

	protected void move() {	
		x += velX;
		y += velY;
		xCoords.add(x);
		yCoords.add(y);
		if(length < xCoords.size()) {
			xCoords.remove(0);
			yCoords.remove(0);
		}
//		xCoords.remove(0);
//		yCoords.remove(0);
		
	}
	
	public void grow() {
		length++;
	}
	
	public void tick() {
		//This makes key presses responsive and makes the snake move
		//every 5 frames.
		if(ticks < 5) {
			getInput();
			ticks++;
		}
		else {
			if(isMoving) {
				move();
			}
			ticks = 0;
		}
		
		
	}
	
	protected void getInput() {
		if(game.getKeyManager().up & !isMovingY) {
			velY = -speed;
			velX = 0;
			isMovingY = true;
			isMovingX = false;
			isMoving = true;
		}
		if(game.getKeyManager().down & !isMovingY) {
			velY = +speed;
			velX = 0;
			isMovingY = true;
			isMovingX = false;
			isMoving = true;
		}
		if(game.getKeyManager().left & !isMovingX) {
			velX = -speed;
			velY = 0;
			isMovingX = true;
			isMovingY = false;
			isMoving = true;
		}
		if(game.getKeyManager().right & !isMovingX) {
			velX = +speed;
			velY = 0;
			isMovingX = true;
			isMovingY = false;
			isMoving = true;
		}
		
	}

	public void render(Graphics g) {
		for(int i = 0; i < xCoords.size(); i++) {
			if(i == xCoords.size() - 1) {
				g.drawImage(snakeHead, xCoords.get(i), yCoords.get(i), null);
			} else if(i == 0) {
				g.drawImage(snakeTail, xCoords.get(i), yCoords.get(i), null);
			} else {
				g.drawImage(snakeBody, xCoords.get(i), yCoords.get(i), null);
			}
		}
		
	}
	
	public  int getX() {
		return x;
	}
	
	public  int getY() {
		return y;
	}
	
}
