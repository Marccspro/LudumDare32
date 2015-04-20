package fr.veridiangames.main.game.entities.weapons;

import fr.veridiangames.main.game.Game;

public class WaterBottleWeapon extends Weapon {

	public WaterBottleWeapon() {
		super(4, 5 / Game.difficulty);
		life = 10;
	}

	public void update() {
		
	}

}
