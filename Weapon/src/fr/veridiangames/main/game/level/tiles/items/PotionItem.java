package fr.veridiangames.main.game.level.tiles.items;

import org.lwjgl.opengl.Display;

import fr.veridiangames.main.Main;
import fr.veridiangames.main.audio.Audio;
import fr.veridiangames.main.game.Game;
import fr.veridiangames.main.game.entities.Player;
import fr.veridiangames.main.rendering.Gui;
import fr.veridiangames.main.rendering.Renderer;

public class PotionItem extends Item {
	
	boolean used = false;
	boolean tryToUse = false;
	boolean canUse = false;
	
	public PotionItem(float x, float y) {
		super(x, y);
		tex = 0;
	}
	
	public void update() {
		if (!removed) {
			Player p = Game.getGame().getPlayer();
			
			if (p.x > x - 0.5f && p.y > y - 0.5f && p.x < x + 1.5f && p.y < y + 1.5f) {
				p.addPotion(this);
				Main.getMain().playSound(Audio.KEY_PICKUP);
				removed = true;
			}
		}
	}
	
	public void use(Player p) {
		if (!used) {
			tryToUse = true;
			if (canUse) {
				p.addLife(5);
				canUse = false;
				used = true;				
			}
		}
	}
	
	public void render() {
		Renderer.renderItem(x, h, y+1, 8 * 2 + 1);
	}
	
	public void renderGUI() {
		if (tryToUse) {
			if (Game.getGame().getPlayer().life >= 10) {
				Gui.drawString("Life full !", Display.getWidth() / 2, Display.getHeight() / 2, 48, true);
			}else {
				canUse = true;
			}
		}
	}

}