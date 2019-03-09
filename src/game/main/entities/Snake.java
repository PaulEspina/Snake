package game.main.entities;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.main.Game;
import game.main.gfx.ImageLoader;

public class Snake {
	
	protected Game game;
	
	protected int velX, velY, speed, ticks, length, x, y, size;
	protected boolean isMovingX, isMovingY, isMoving;
	protected String direction;
	
	protected ArrayList<Integer> xCoords = new ArrayList<Integer>();
	protected ArrayList<Integer> yCoords = new ArrayList<Integer>();
	
	protected BufferedImage snakeHead = ImageLoader.loadImage("/textures/snakeHead32.png");
	protected BufferedImage newSnakeHead = snakeHead;
	
	protected BufferedImage snakeBody = ImageLoader.loadImage("/textures/snakeBody32.png");

	protected BufferedImage snakeTail = ImageLoader.loadImage("/textures/snakeTail32.png");
	protected BufferedImage newSnakeTail = snakeTail;

	
	public Snake(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
		velX = 0;
		velY = 0;
		speed = 32;
		size = 32;
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
	}
	
	protected void rotations() {
		//Head
		if(direction == "up") {
			newSnakeHead = rotateTexture(0, snakeHead);
		}
		if(direction == "down") {
			newSnakeHead = rotateTexture(180, snakeHead);
		}
		if(direction == "left") {
			newSnakeHead = rotateTexture(-90, snakeHead);
		}
		if(direction == "right") {
			newSnakeHead = rotateTexture(90, snakeHead);
		}
		
		//Tail
		if(xCoords.get(1) > xCoords.get(0)) {
			newSnakeTail = rotateTexture(90, snakeTail);
		}
		if(xCoords.get(1) < xCoords.get(0)) {
			newSnakeTail = rotateTexture(-90, snakeTail);
		}
		if(yCoords.get(1) > yCoords.get(0)) {
			newSnakeTail = rotateTexture(180, snakeTail);
		}
		if(yCoords.get(1) < yCoords.get(0)) {
			newSnakeTail = rotateTexture(0, snakeTail);
		}
	}
	
	public void grow() {
		length++;
	}
	
	private BufferedImage rotateTexture(int angle, BufferedImage image) {
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(angle), image.getWidth()/2, image.getHeight()/2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(image, null);
	}
	
	public void tick() {
		if(ticks < 5) {
			getInput();
			ticks++;
		}
		else {
			if(isMoving) {
				move();
				rotations();
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
			direction = "up";
			
		}
		if(game.getKeyManager().down & !isMovingY) {
			velY = +speed;
			velX = 0;
			isMovingY = true;
			isMovingX = false;
			isMoving = true;
			direction = "down";
			
		}
		if(game.getKeyManager().left & !isMovingX) {
			velX = -speed;
			velY = 0;
			isMovingX = true;
			isMovingY = false;
			isMoving = true;
			direction = "left";
		}
		if(game.getKeyManager().right & !isMovingX) {
			velX = +speed;
			velY = 0;
			isMovingX = true;
			isMovingY = false;
			isMoving = true;
			direction = "right";
		}
		
	}

	public void render(Graphics g) {
		g.drawImage(newSnakeHead, xCoords.get(xCoords.size() - 1), yCoords.get(xCoords.size() - 1), null);
		for(int i = 1; i < (xCoords.size() - 1); i++) {
			g.drawImage(snakeBody, xCoords.get(i), yCoords.get(i), null);
		}
		g.drawImage(newSnakeTail, xCoords.get(0), yCoords.get(0), null);

	}
	
	public  int getX() {
		return x;
	}
	
	public  int getY() {
		return y;
	}
	
}
