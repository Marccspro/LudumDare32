package fr.veridiangames.main.game.level.tiles;

public abstract class BlockTile extends Tile{
	
	public int wallTex = 0;
	
	public BlockTile(float x, float y) {
		super(x, y);
		block = true;
	}
}
