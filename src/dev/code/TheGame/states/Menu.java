package dev.code.TheGame.states;

import java.awt.Graphics;

import dev.code.TheGame.Game;
import dev.code.TheGame.gfx.Assets;

public class Menu extends State{
	
	public Menu(Game game){
		super(game);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.grass, 0, 0, null);
	}

}
