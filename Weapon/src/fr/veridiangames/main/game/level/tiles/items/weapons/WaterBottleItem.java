package fr.veridiangames.main.game.level.tiles.items.weapons;

import fr.veridiangames.main.game.entities.weapons.WaterBottleWeapon;
import fr.veridiangames.main.rendering.Renderer;

public class WaterBottleItem extends WeaponItem {

	public WaterBottleItem(float x, float y) {
		super(x, y);
		tex = 1;
		weapon = new WaterBottleWeapon();
	}
	
	public void render() {
		Renderer.renderItem(x, h, y+1, 4);
	}
	
	public void renderGUI() {
		
	}
}
