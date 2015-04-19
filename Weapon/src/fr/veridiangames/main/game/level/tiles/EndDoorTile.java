package fr.veridiangames.main.game.level.tiles;

import fr.veridiangames.main.game.Game;
import fr.veridiangames.main.game.entities.Player;

public class EndDoorTile extends UpTile{
	
	int lvl;
	
	public EndDoorTile(float x, float y, int lvl) {
		super(x, y);
		
		this.lvl = ((lvl & 0xff0000) >> 16) / 16;
		
		solid = false;
		tex = 1;
		uptex = 1;
	}
	
	public void update() {
		Player p = Game.getGame().getPlayer();
		
		if (p.x > x && p.y > y && p.x < x + 1 && p.y < y + 1) {
			Game.getGame().loadLevel("lvl" + lvl);
			return;
		}
	}
}