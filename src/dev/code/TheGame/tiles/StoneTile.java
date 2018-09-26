package dev.code.TheGame.tiles;

import java.awt.image.BufferedImage;

import dev.code.TheGame.gfx.Assets;

public class StoneTile extends Tile{

	public StoneTile(int id) {
		super(Assets.stone, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
	
}
