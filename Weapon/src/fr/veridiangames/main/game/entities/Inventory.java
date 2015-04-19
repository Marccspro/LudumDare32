package fr.veridiangames.main.game.entities;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import fr.veridiangames.main.game.Game;
import fr.veridiangames.main.game.entities.weapons.ChikenWeapon;
import fr.veridiangames.main.game.entities.weapons.PingPongWeapon;
import fr.veridiangames.main.game.entities.weapons.PlayerWeapon;
import fr.veridiangames.main.game.entities.weapons.RawSteakWeapon;
import fr.veridiangames.main.game.entities.weapons.SteakWeapon;
import fr.veridiangames.main.game.entities.weapons.WaterBottleWeapon;
import fr.veridiangames.main.game.entities.weapons.Weapon;
import fr.veridiangames.main.game.level.tiles.items.PotionItem;
import fr.veridiangames.main.rendering.Gui;
import fr.veridiangames.main.rendering.Texture;

public class Inventory {
	
	public List<PlayerWeapon> playerWeapons;
	public List<Weapon> weapons;
	public List<PotionItem> potions;
	
	public List<ChikenWeapon> chikens;
	public List<PingPongWeapon> pingpongs;
	public List<RawSteakWeapon> rawSteaks;
	public List<SteakWeapon> steaks;
	public List<WaterBottleWeapon> bottles;
	
	int slot = 0;
	
	int x, y;
	
	public static boolean showInv = false;
	
	public List<Weapon> craft;
	
	public Inventory() {
		playerWeapons = new ArrayList<PlayerWeapon>();
		weapons = new ArrayList<Weapon>();
		potions = new ArrayList<PotionItem>();
		
		chikens = new ArrayList<ChikenWeapon>();
		pingpongs = new ArrayList<PingPongWeapon>();
		rawSteaks = new ArrayList<RawSteakWeapon>();
		steaks = new ArrayList<SteakWeapon>();
		bottles = new ArrayList<WaterBottleWeapon>();
		
		craft = new ArrayList<Weapon>();
	}
	
	public void renderInv() {
		if (!showInv) return;
		x = 0;
		y = 0;
		Gui.drawString("Inventory", x + Display.getWidth() / 2, y + Display.getHeight() / 2 - 200, 32, true);
		Gui.color(0f, 0f, 0f, 0.5f);
		Gui.drawQuad(x + Display.getWidth() / 2 - 300, y + Display.getHeight() / 2 - 150, 600, 300);
		
		
		int potionX = x + Display.getWidth() / 2 - 250;
		int potionY = y + Display.getHeight() / 2 - 130;
		
		Gui.drawString("Potions: " + potions.size(), potionX + 30, potionY + 20, 24, false);
		
		if (Gui.button("Use", potionX + 400, potionY + 32, 100)) {
			if (potions.size() > 0) {
				potions.remove(0);
				Game.getGame().getPlayer().addLife(5);
			}			
		}
		
		int craftX = x + Display.getWidth() / 2;
		int craftY = y + Display.getHeight() / 2;
		
		
		Gui.drawString("" + rawSteaks.size(), craftX - 80 * 3 + 24, craftY - 55, 24);
		Gui.drawString("" + steaks.size(), craftX - 80 * 2 + 24, craftY - 55, 24);
		Gui.drawString("" + chikens.size(), craftX - 80 * 1 + 24, craftY - 55, 24);
		Gui.drawString("" + pingpongs.size(), craftX - 80 * 0 + 24, craftY - 55, 24);
		Gui.drawString("" + bottles.size(), craftX + 80 * 1 + 24, craftY - 55, 24);
		Gui.drawString("" + 0, craftX + 80 * 2 + 24, craftY - 55, 24);
		
		if (Gui.button("add", craftX - 80 * 3 + 35, craftY + 55, 70)) {
			if (rawSteaks.size() > 0 && craft.size() < 6) {
				craft.add(rawSteaks.get(0));
				rawSteaks.remove(0);
			}
		}
		if (Gui.button("add", craftX - 80 * 2 + 35, craftY + 55, 70)) {
			if (steaks.size() > 0 && craft.size() < 6) {
				craft.add(steaks.get(0));
				steaks.remove(0);
			}
		}
		if (Gui.button("add", craftX - 80 * 1 + 35, craftY + 55, 70)) {
			if (chikens.size() > 0 && craft.size() < 6) {
				craft.add(chikens.get(0));
				chikens.remove(0);
			}
		}
		if (Gui.button("add", craftX - 80 * 0 + 35, craftY + 55, 70)) {
			if (pingpongs.size() > 0 && craft.size() < 6) {
				craft.add(pingpongs.get(0));
				pingpongs.remove(0);
			}
		}
		if (Gui.button("add", craftX + 80 * 1 + 35, craftY + 55, 70)) {
			if (bottles.size() > 0 && craft.size() < 6) {
				craft.add(bottles.get(0));
				bottles.remove(0);
			}
		}
		if (Gui.button("add", craftX + 80 * 2 + 35, craftY + 55, 70)) {

		}
		
		
		if (Gui.button("Reset", craftX + 185, craftY + 93, 200, true)) {
			for (int i = 0; i < craft.size(); i++) {
				add(craft.get(i));
			}
			craft.clear();
		}
		if (Gui.button("Craft!", craftX + 185, craftY + 130, 200, true)) {
			if (!craft.isEmpty()) {
				PlayerWeapon w = new PlayerWeapon();
				
				for (int i = 0; i < craft.size(); i++) {
					w.add(craft.get(i));
				}
				
				craft.clear();
				w.maxLife = w.life;
				playerWeapons.add(w);
			}
		}
		
		Texture.WEAPONS.bind();
		
		float[][] powerCoords = getCoords(0, 8, 8);
		
		for (int i = 0; i < craft.size(); i++) {
			Weapon w = craft.get(i);
			powerCoords = getCoords(w.tex, 8, 8);
			Gui.drawTexturedQuad(craftX - 64 * 4 - 44 + i * 64, craftY + 80, 64, 64, powerCoords);
		}
		
		powerCoords = getCoords(0, 8, 8);
		Gui.drawTexturedQuad(craftX - 80 * 3, craftY - 32, 64, 64, powerCoords);
		
		powerCoords = getCoords(1, 8, 8);
		Gui.drawTexturedQuad(craftX - 80 * 2, craftY - 32, 64, 64, powerCoords);
		
		powerCoords = getCoords(2, 8, 8);
		Gui.drawTexturedQuad(craftX - 80 * 1, craftY - 32, 64, 64, powerCoords);
		
		powerCoords = getCoords(3, 8, 8);
		Gui.drawTexturedQuad(craftX + 80 * 0, craftY - 32, 64, 64, powerCoords);
		
		powerCoords = getCoords(4, 8, 8);
		Gui.drawTexturedQuad(craftX + 80 * 1, craftY - 32, 64, 64, powerCoords);
		
		powerCoords = getCoords(5, 8, 8);
		Gui.drawTexturedQuad(craftX + 80 * 2, craftY - 32, 64, 64, powerCoords);
		
		Texture.unbind();
		
	}
	
	
	public void renderBottom() {
		Gui.color(0f, 0f, 0f, 0.5f);
		Gui.drawQuad(Display.getWidth() / 2 - 200, Display.getHeight() - 50, 400, 200);
		
		Gui.color(0, 0, 0, 1);
		Gui.drawQuad(Display.getWidth() / 2 - 3 * 44 - 22 + 2, Display.getHeight() - 44, 40, 40);
		Gui.drawQuad(Display.getWidth() / 2 - 2 * 44 - 22 + 2, Display.getHeight() - 44, 40, 40);
		Gui.drawQuad(Display.getWidth() / 2 - 1 * 44 - 22 + 2, Display.getHeight() - 44, 40, 40);
		
		Gui.drawQuad(Display.getWidth() / 2 + 1 * 44 - 22 + 2, Display.getHeight() - 44, 40, 40);
		Gui.drawQuad(Display.getWidth() / 2 + 2 * 44 - 22 + 2, Display.getHeight() - 44, 40, 40);
		Gui.drawQuad(Display.getWidth() / 2 + 3 * 44 - 22 + 2, Display.getHeight() - 44, 40, 40);
		
		Gui.color(1, 1, 1, 1);
		Gui.drawQuad(Display.getWidth() / 2 - 3 * 44 + 2 - 22 + 2, Display.getHeight() - 44 + 2, 36, 36);
		Gui.drawQuad(Display.getWidth() / 2 - 2 * 44 + 2 - 22 + 2, Display.getHeight() - 44 + 2, 36, 36);
		Gui.drawQuad(Display.getWidth() / 2 - 1 * 44 + 2 - 22 + 2, Display.getHeight() - 44 + 2, 36, 36);
		
		Gui.drawQuad(Display.getWidth() / 2 + 1 * 44 + 2 - 22 + 2, Display.getHeight() - 44 + 2, 36, 36);
		Gui.drawQuad(Display.getWidth() / 2 + 2 * 44 + 2 - 22 + 2, Display.getHeight() - 44 + 2, 36, 36);
		Gui.drawQuad(Display.getWidth() / 2 + 3 * 44 + 2 - 22 + 2, Display.getHeight() - 44 + 2, 36, 36);
		
		Gui.color(0, 0, 0, 0.5f);
		
			if (slot <= 2) {
				Gui.drawQuad(Display.getWidth() / 2 + (slot - 3) * 44 + 2 - 22 + 2, Display.getHeight() - 44 + 2, 36, 36);
			}else {
				Gui.drawQuad(Display.getWidth() / 2 + (slot - 2) * 44 + 2 - 22 + 2, Display.getHeight() - 44 + 2, 36, 36);				
			}
		
		Gui.color(1, 1, 1, 1);
		
		for (int i = 0; i < playerWeapons.size(); i++) {
			PlayerWeapon w = playerWeapons.get(i);
			if (i <= 2) {
				w.renderGUI(Display.getWidth() / 2 - 155 + (i) * 44, Display.getHeight() - 50 + 6);
			}else {
				w.renderGUI(Display.getWidth() / 2 - 155 + (i + 1) * 44, Display.getHeight() - 50 + 6);				
			}
		}
		
		if (Gui.button("Inventory", Display.getWidth() / 2, 20, 300)) {
			showInv = !showInv;
		}
		
		while(Keyboard.next()) {
			if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
				showInv = !showInv;
			}
		}
	}
	
	public void add(Weapon w) {
		if (w instanceof ChikenWeapon) {
			chikens.add((ChikenWeapon) w);
			
		}else if (w instanceof PingPongWeapon) {
			pingpongs.add((PingPongWeapon) w);
			
		}else if (w instanceof RawSteakWeapon) {
			rawSteaks.add((RawSteakWeapon) w);
			
		}else if (w instanceof SteakWeapon) {
			steaks.add((SteakWeapon) w);
			
		}else if (w instanceof WaterBottleWeapon) {
			bottles.add((WaterBottleWeapon) w);
		}else {
			weapons.add(w);
		}
	}
	
	public void add(PotionItem p) {
		potions.add(p);
	}
	
	public float[][] getCoords(int tex, int w, int h) {
		float[][] r = new float[4][2];
		
		int xo = tex % w;
		int yo = tex / w;
		
		r[0][0] = (0 + xo) / (float)w;		r[0][1] = (0 + yo) / (float)h;
		r[1][0] = (1 + xo) / (float)w;		r[1][1] = (0 + yo) / (float)h;
		r[2][0] = (1 + xo) / (float)w;		r[2][1] = (1 + yo) / (float)h;
		r[3][0] = (0 + xo) / (float)w;		r[3][1] = (1 + yo) / (float)h;
		
		return r;
	}
	
	public void update() {
		
		int d = (int) (Mouse.getDWheel() * 0.0095f);
		slot += (int) (d);
		if (slot > 5) slot = 0;
		if (slot < 0) slot = 5;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
			slot = 1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
			slot = 2;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
			slot = 3;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
			slot = 4;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_5)) {
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_6)) {
			slot = 0;
		}

		if (playerWeapons.size() > slot) {
			if (playerWeapons.get(slot).removed) {
				playerWeapons.remove(playerWeapons.get(slot));
			}
		}
	}
	
	public void render(float x, float y, int dir, boolean attack) {
		if (playerWeapons.size() > slot) {
			playerWeapons.get(slot).render(x, y, dir, attack);
		}
	}
	
	public PlayerWeapon getWeapon() {
		if (playerWeapons.size() > slot) {
			return playerWeapons.get(slot);			
		}
		return null;
	}
}
