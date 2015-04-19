package fr.veridiangames.main.game.level.tiles.items.weapons;

import fr.veridiangames.main.game.entities.weapons.PingPongWeapon;
import fr.veridiangames.main.rendering.Renderer;

public class PingPongItem extends WeaponItem {

	public PingPongItem(float x, float y) {
		super(x, y);
		tex = 1;
		weapon = new PingPongWeapon();
	}

	public void render() {
		Renderer.renderItem(x, h, y-1, 3);
	}
	
	public void renderGUI() {
		
	}
}
