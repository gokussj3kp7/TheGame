package dev.code.TheGame.states;

import java.awt.Graphics;

import dev.code.TheGame.Game;
import dev.code.TheGame.entities.creatures.Player;
import dev.code.TheGame.gfx.Assets;
import dev.code.TheGame.tiles.Tile;

public class GameState extends State{
	private Player player;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game,200,200);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		player.update();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		player.render(g);
		Tile.tiles[0].render(g,0,0);
		Tile.tiles[1].render(g,64,64);
	}

}
