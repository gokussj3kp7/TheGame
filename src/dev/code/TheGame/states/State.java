package dev.code.TheGame.states;

import java.awt.Graphics;

import dev.code.TheGame.Game;

public abstract class State {
	
	protected Game game;
	private static State currentState = null;
	
	public State(Game game) {
		this.game = game;
	}
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	//Class related code below
	public abstract void update();
	
	public abstract void render(Graphics g);
	
}
