package fr.veridiangames.main.game.entities.weapons;

import fr.veridiangames.main.game.Game;

public class PingPongWeapon extends Weapon {

	public PingPongWeapon() {
		super(3, 8 / Game.difficulty);
		life = 8;
	}

	public void update() {
		
	}

}
