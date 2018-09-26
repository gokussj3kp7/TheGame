package dev.code.TheGame;

import dev.code.TheGame.display.Display;
import dev.code.TheGame.gfx.Assets;
import dev.code.TheGame.gfx.ImageLoader;
import dev.code.TheGame.gfx.SpriteSheet;
import dev.code.TheGame.inputs.KeyManager;
import dev.code.TheGame.states.GameState;
import dev.code.TheGame.states.Menu;
import dev.code.TheGame.states.State;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


//implementing runnable to make the Game class run on a thread
public class Game implements Runnable{
	private Display display;
	
	public int width,height;
	public String title;
	
	private boolean running=false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private State gameState;
	private State menu;
	
	private KeyManager keyManager;
	
	public Game(String title,int width,int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	private void init() {
		display = new Display(title,width,height);//displaying window from Game class
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		gameState = new GameState(this);
		menu = new Menu(this);
		State.setState(gameState);
	}
	
	private void update() {
		keyManager.update();
		if(State.getState() != null)
			State.getState().update();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//clear screen
		g.clearRect(0, 0, width, height);
		//start drawing
		
		if(State.getState() != null)
			State.getState().render(g);
		
		//g.fillRect(0, 0, width, height);//commented cause no longer needed
		
		bs.show();
		g.dispose();
		
		//end drawing
	}
	
	public void run() {
		init();
		
		int fps = 60;
		double timePerUpdate = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta = delta + (now - lastTime)/timePerUpdate;
			timer +=  now - lastTime;
			lastTime = now;
			
			
			if(delta >= 1) {
				update();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000) {
				System.out.println("Fps : " + ticks);
				ticks = 0;
				timer = 0;
			}
			
		}
		
		
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);//using 'this' inside thread because we want Game as thread
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
