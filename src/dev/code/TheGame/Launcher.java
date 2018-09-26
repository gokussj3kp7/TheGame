package dev.code.TheGame;

import dev.code.TheGame.display.Display;

public class Launcher {
	public static void main(String[] args) {
		Game game = new Game("The Game !!!",500,500);
		game.start();
	}
}
