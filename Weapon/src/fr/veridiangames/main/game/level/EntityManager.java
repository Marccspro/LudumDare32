package fr.veridiangames.main.game.level;

import java.util.ArrayList;
import java.util.List;

import fr.veridiangames.main.game.Game;
import fr.veridiangames.main.game.entities.Entity;
import fr.veridiangames.main.game.entities.Inventory;
import fr.veridiangames.main.game.entities.Player;
import fr.veridiangames.main.game.entities.mobs.BossMob;
import fr.veridiangames.main.game.entities.mobs.MillipedeMob;
import fr.veridiangames.main.game.entities.mobs.Weird1Mob;
import fr.veridiangames.main.game.entities.mobs.WormMob;
import fr.veridiangames.main.game.level.tiles.items.KeyItem;
import fr.veridiangames.main.game.level.tiles.items.weapons.ChikenItem;
import fr.veridiangames.main.game.level.tiles.items.weapons.RawSteakItem;
import fr.veridiangames.main.game.level.tiles.items.weapons.SteakItem;
import fr.veridiangames.main.rendering.Texture;

public class EntityManager {
	List<Entity> entities = new ArrayList<Entity>();
	
	public void add(Entity e) {
		entities.add(e);
	}
	
	public void updateEntities(Level level) {
		entities.get(0).update();
		
		if (Inventory.showInv) return;
		for (int i = 0; i < entities.size(); i++) {
			if (i == 0) continue;
			Entity e = entities.get(i);
			
			if (e.dead) {
				e.removed = true;
			}
			
			if (!(e instanceof Player)) {
				if (e.removed) {
					if (e instanceof  WormMob) {
						if (Math.random() > 0.5f)
							Game.getGame().getLevel().items.add(new RawSteakItem(e.x - 0.5f, e.y - 0.5f));
					} else if (e instanceof  MillipedeMob) {
						if (Math.random() > 0.5f)
							Game.getGame().getLevel().items.add(new SteakItem(e.x - 0.5f, e.y - 0.5f));
					}else if (e instanceof  Weird1Mob) {
						if (Math.random() > 0.5f)
							Game.getGame().getLevel().items.add(new ChikenItem(e.x - 0.5f, e.y - 0.5f));
					}else if (e instanceof BossMob) {
						Game.getGame().getLevel().items.add(new KeyItem(e.x - 0.5f, e.y - 0.5f, 0xff500000));
					}
					
					entities.remove(e);
					continue;
				}
			}
			
			e.update();
		}
	}
	
	public void renderEntities() {
		Texture.MOBS.bind();
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render();
		}
	}
	
	public void renderGUIEntities() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			
			e.renderGUI();
		}
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
}
