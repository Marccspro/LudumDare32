package fr.veridiangames.main.game.level.tiles.items.weapons;

import fr.veridiangames.main.game.entities.weapons.RawSteakWeapon;
import fr.veridiangames.main.rendering.Renderer;

public class RawSteakItem extends WeaponItem {

	public RawSteakItem(float x, float y) {
		super(x, y);
		tex = 1;
		weapon = new RawSteakWeapon();
	}

	public void render() {
		Renderer.renderItem(x, h, y+1, 0);
	}
	
	public void renderGUI() {
		
	}
}
