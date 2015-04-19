package fr.veridiangames.main.game.level.tiles.items;

import fr.veridiangames.main.Main;
import fr.veridiangames.main.audio.Audio;
import fr.veridiangames.main.game.Game;
import fr.veridiangames.main.game.entities.Player;
import fr.veridiangames.main.rendering.Renderer;

public class PotatoItem extends Item {
	
	boolean used = false;
	boolean tryToUse = false;
	boolean canUse = false;
	
	public PotatoItem(float x, float y) {
		super(x, y);
		tex = 0;
	}
	
	public void update() {
		if (!removed) {
			Player p = Game.getGame().getPlayer();
			
			if (p.x > x && p.y > y && p.x < x + 1 && p.y < y + 1) {
				p.addPotato(this);
				Main.getMain().playSound(Audio.POTATO_PICKUP);
				removed = true;
			}
		}
	}
	
	public void render() {
		Renderer.renderItem(x, h, y+1, 8 * 2);
	}
	
	public void renderGUI() {
		
	}
}
