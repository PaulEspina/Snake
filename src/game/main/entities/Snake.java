package game.main.entities;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.main.Game;
import game.main.gfx.ImageLoader;

public class Snake {
	
	private Game game;
	
	private int velX, velY, speed, ticks, length, x, y, size;
	private boolean isMovingX, isMovingY, isMoving, xLock, yLock, xLock1, yLock1;
	private String direction;
	
	private ArrayList<Integer> xCoords = new ArrayList<Integer>();
	private ArrayList<Integer> yCoords = new ArrayList<Integer>();
	
	private BufferedImage snakeHead = ImageLoader.loadImage("/textures/snakeHead32.png");
	private BufferedImage newSnakeHead = snakeHead;
	
	private BufferedImage snakeBody = ImageLoader.loadImage("/textures/snakeBody32.png");

	private BufferedImage snakeTail = ImageLoader.loadImage("/textures/snakeTail32.png");
	private BufferedImage newSnakeTail = snakeTail;
	
	public Snake(Game game) {
		this.game = game;
		x = 512 / 2;
		y = 512 / 2;
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
		xLock1 = false;
		yLock1 = false;
		
	}
	
	private void gameOverCheck() {
		if(collisionCheck()) {
			game.setRunning(false);
		}
	}
	
	private boolean collisionCheck() {
		for(int i = 0; i < length - 1; i++)
			if(x == xCoords.get(i)) {
				if(y == yCoords.get(i) ) {
					return true;
				}
			}
			if(x >= game.getWidth() | x < 0) {
				return true;
			}
			if(y >= game.getHeight() | y < 0) {
				return true;
			}
			return false;
	}

	private void move() {	
		x += velX;
		y += velY;
		xCoords.add(x);
		yCoords.add(y);
		if(length < xCoords.size()) {
			xCoords.remove(0);
			yCoords.remove(0);
		}
		
	}
	
	private void rotations() {
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
				gameOverCheck();
				
				yLock = false;
				xLock = false;
				
			}
			ticks = 0;
		}	
	}
	
	private void getInput() {
		if(!yLock & !yLock1) {
			if(game.getKeyManager().up & !isMovingY) {
				velY = -speed;
				velX = 0;
				isMovingY = true;
				isMovingX = false;
				isMoving = true;
				direction = "up";
				xLock = true;
				yLock1 = true;
				xLock1 = false;
			}
			if(game.getKeyManager().down & !isMovingY) {
				velY = +speed;
				velX = 0;
				isMovingY = true;
				isMovingX = false;
				isMoving = true;
				direction = "down";
				xLock = true;
				yLock1 = true;
				xLock1 = false;
			}
		}
		
		if(!xLock & !xLock1) {
			if(game.getKeyManager().left & !isMovingX & !xLock & !xLock1) {
				velX = -speed;
				velY = 0;
				isMovingX = true;
				isMovingY = false;
				isMoving = true;
				direction = "left";
				yLock = true;
				xLock1 = true;
				yLock1 = false;
			}
			if(game.getKeyManager().right & !isMovingX & !xLock & !xLock1) {
				velX = +speed;
				velY = 0;
				isMovingX = true;
				isMovingY = false;
				isMoving = true;
				direction = "right";
				yLock = true;
				xLock1 = true;
				yLock1 = false;
			}
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
	
	public ArrayList<Integer> getXCoords() {
		return xCoords;
	}
	public ArrayList<Integer> getYCoords() {
		return yCoords;
	}
	
}
