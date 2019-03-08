package game.main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import game.main.display.Display;
import game.main.entities.Apple;
import game.main.entities.Snake;
import game.main.input.KeyManager;

public class Game implements Runnable {
	
	private Display display;
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private KeyManager keyManager;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		keyManager = new KeyManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
	}
	
	//Create variables for game loop
	int snakeX = 1024 / 2 - 64;
	int snakeY = 1024 / 2 - 64;
	
	int appleX = 128;
	int appleY = 128;
	
	Snake snake = new Snake(this, snakeX, snakeY);
	Apple apple = new Apple(this, appleX, appleY);
	//End here
	
	private void tick() {
		keyManager.tick();
		
		if(snake.getX() == apple.getX() & snake.getY() == apple.getY()) {
			apple.isEaten();
			snake.grow();
		}
		
		snake.tick();
		
		apple.tick();
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		//Draw Here!
		
		apple.render(g);
		snake.render(g);

		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run() {
		init();
		
		int fps = 30;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1) {
				tick();
				render();
				delta--;
			}
			
			if(timer >= 1000000000) {
				timer = 0;
				
			}
		}
		
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
		
	}
	
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}