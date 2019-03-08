package game.main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.main.Game;
import game.main.gfx.ImageLoader;

public class Snake extends Entity {
	
	protected Game game;
	
	protected int velX, velY, speed, ticks, length;
	protected boolean isMovingX, isMovingY, isMoving;
	protected ArrayList<Integer> xCoords = new ArrayList<Integer>();
	protected ArrayList<Integer> yCoords = new ArrayList<Integer>();
	
	protected BufferedImage snakeHead = ImageLoader.loadImage("/textures/snakeHead.png");
	protected BufferedImage snakeBody = ImageLoader.loadImage("/textures/snakeBody.png");
	protected BufferedImage snakeTail = ImageLoader.loadImage("/textures/snakeTail.png");
	
	public Snake(Game game, int x, int y) {
		super(x, y);
		this.game = game;
		velX = 0;
		velY = 0;
		speed = 64;
		ticks = 0;
		length = 3;
		isMoving = false;
		xCoords.add(x);
		xCoords.add(x);
		xCoords.add(x);
		yCoords.add(y);
		yCoords.add(y - size);
		yCoords.add(y - size * 2);
		
	}

	protected void move() {
		x += velX;
		y += velY;
		xCoords.add(x);
		yCoords.add(y);
	}
	
	public void grow() {
		length++;
	}
	
	@Override
	public void tick() {
		
		if(length < xCoords.size()) {
			xCoords.remove(0);
			yCoords.remove(0);
		}
		
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

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		//g.fillRect(x, y, size, size);
		for(int i = 0; i < length; i++) {
			if(i == xCoords.size() - 1) {
				g.drawImage(snakeHead, xCoords.get(i), yCoords.get(i), null);
			} else if(i == 0) {
				g.drawImage(snakeTail, xCoords.get(i), yCoords.get(i), null);
			}else {
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
