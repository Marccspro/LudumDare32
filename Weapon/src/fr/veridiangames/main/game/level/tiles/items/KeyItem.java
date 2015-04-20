package fr.veridiangames.main.game.level.tiles.items;

import fr.veridiangames.main.Main;
import fr.veridiangames.main.audio.Audio;
import fr.veridiangames.main.game.Game;
import fr.veridiangames.main.game.entities.Player;
import fr.veridiangames.main.rendering.Renderer;

public class KeyItem extends Item {

	int code;
	
	public KeyItem(float x, float y, int code) {
		super(x, y);
		
		this.code = (code & 0xff0000) >> 16;
		tex = 0;
	}
	
	public void update() {
		if (!removed) {
			Player p = Game.getGame().getPlayer();
			
			if (p.x > x - 0.5f && p.y > y - 0.5f && p.x < x + 1.5f && p.y < y + 1.5f) {
				p.addKey(code);
				Main.getMain().playSound(Audio.KEY_PICKUP);
				removed = true;
			}
		}
	}
	
	public void render() {
		Renderer.renderItem(x, h, y+1, 8 * 2 + 2);
	}
	
	public void renderGUI() {

	}
	
	public static boolean keyColor(int color) {
		if (color == 0xff0000ff) return true;
		if (color == 0xff1000ff) return true;
		if (color == 0xff2000ff) return true;
		if (color == 0xff3000ff) return true;
		if (color == 0xff4000ff) return true;
		if (color == 0xff5000ff) return true;
		if (color == 0xff6000ff) return true;
		if (color == 0xff7000ff) return true;
		if (color == 0xff8000ff) return true;
		if (color == 0xff9000ff) return true;
		
		return false;
	}
}