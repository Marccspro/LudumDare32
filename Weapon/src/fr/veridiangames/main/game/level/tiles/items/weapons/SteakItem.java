package fr.veridiangames.main.game.level.tiles.items.weapons;

import fr.veridiangames.main.game.entities.weapons.SteakWeapon;
import fr.veridiangames.main.rendering.Renderer;

public class SteakItem extends WeaponItem {

	public SteakItem(float x, float y) {
		super(x, y);
		tex = 1;
		weapon = new SteakWeapon();
	}

	public void render() {
		Renderer.renderItem(x, h, y+1, 1);
	}
	
	public void renderGUI() {
		
	}
}
