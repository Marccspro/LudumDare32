package fr.veridiangames.main.game.level.tiles.items.weapons;

import fr.veridiangames.main.game.entities.weapons.ChikenWeapon;
import fr.veridiangames.main.rendering.Renderer;

public class ChikenItem extends WeaponItem {

	public ChikenItem(float x, float y) {
		super(x, y);
		tex = 1;
		weapon = new ChikenWeapon();
	}

	public void render() {
		Renderer.renderItem(x, h, y+1, 2);
	}
	
	public void renderGUI() {
		
	}
}
