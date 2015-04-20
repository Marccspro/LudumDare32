package fr.veridiangames.main.game;

import static org.lwjgl.opengl.GL11.*;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import fr.veridiangames.main.GameState;
import fr.veridiangames.main.Input;
import fr.veridiangames.main.Main;
import fr.veridiangames.main.GameState.State;
import fr.veridiangames.main.ViewState;
import fr.veridiangames.main.audio.Audio;
import fr.veridiangames.main.game.entities.Player;
import fr.veridiangames.main.game.level.Level;
import fr.veridiangames.main.menu.Menu;
import fr.veridiangames.main.rendering.Gui;
import fr.veridiangames.main.rendering.Texture;

public class Game extends ViewState{
	
	public static int difficulty = 2;
	
	public HashMap<String, Level> levelCache = new HashMap<String, Level>();
	private static Game game;
	
	public static boolean paused = false;
	public static boolean finished = false;
	public boolean changingLevel = false;
	public boolean levelChange = false;
	
	Level level;
	Player player;
	
	public Game() {
		game = this;
		player = new Player(0, 0);
		loadLevel("lvl1");
	}
	int time = 0;
	public void update() {
		if (!player.dead && !paused && !finished) {
			time++;
			level.update();
		}
	}
	
	public void render() {
		if (level.level == 1) {
			glFogf(GL_FOG_END, 25);			
		}else {
			glFogf(GL_FOG_END, 10);						
		}
		level.render();
	}
	
	public void renderGUI() {
		if (Input.r.getKeyDown(Keyboard.KEY_ESCAPE)) {
			paused = !paused;
		}
		
		Texture.SILOUHETTE.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); glVertex2f(0, 0);
		glTexCoord2f(1, 0); glVertex2f(Display.getWidth(), 0);
		glTexCoord2f(1, 1); glVertex2f(Display.getWidth(), Display.getHeight());
		glTexCoord2f(0, 1); glVertex2f(0, Display.getHeight());
		glEnd();
		Texture.unbind();
		
		level.renderGUI();
		if (player.dead) {
			Gui.drawString("You are dead !", Display.getWidth() / 2, Display.getHeight() / 2 - 200, 48, true);
			if (Gui.button("Restart", Display.getWidth() / 2, Display.getHeight() / 2 + 80, 300)) {
				restart();
			}
			if (Gui.button("Back to menu", Display.getWidth() / 2, Display.getHeight() / 2 + 40, 300)) {
				restart();
				GameState.setState(State.IN_MENU);
			}
		}
		if (paused) {
			Gui.color(0, 0, 0, 0.8f);
			Texture.unbind();
			Gui.drawQuad(0, 0, Display.getWidth(), Display.getHeight());
			Gui.color(1, 1, 1, 1);
			Gui.drawString("Game paused !", Display.getWidth() / 2, Display.getHeight() / 2 - 200, 48, true);
			if (Gui.button("Restart", Display.getWidth() / 2, Display.getHeight() / 2 + 80, 300)) {
				paused = false;
				restart();
			}
			if (Gui.button("Back to menu", Display.getWidth() / 2, Display.getHeight() / 2 + 40, 300)) {
				restart();
				GameState.setState(State.IN_MENU);
			}
			if (Gui.button("Resume", Display.getWidth() / 2, Display.getHeight() / 2, 300)) {
				paused = false;
			}
			if (Gui.button("Options", Display.getWidth() / 2, Display.getHeight() / 2 + 80 * 2, 300)) {
				paused = true;
				GameState.setState(State.IN_MENU);
				Menu.option = true;
			}
			if (Gui.button("Instructions", Display.getWidth() / 2, Display.getHeight() / 2 + 80 * 3, 300)) {
				paused = true;
				GameState.setState(State.IN_MENU);
				Menu.instruction = true;
			}
		}
		if (finished) {
			Gui.color(0, 0, 0, 0.8f);
			Texture.unbind();
			Gui.drawQuad(0, 0, Display.getWidth(), Display.getHeight());
			Gui.color(1, 1, 1, 1);
			Gui.drawString("You finished the game !", Display.getWidth() / 2, Display.getHeight() / 2 - 200, 48, true);
			if (Gui.button("New Game", Display.getWidth() / 2, Display.getHeight() / 2 + 150, 300)) {
				paused = false;
				finished = false;
				restart();
			}
			if (Gui.button("Back to menu", Display.getWidth() / 2, Display.getHeight() / 2 + 200, 300)) {
				restart();
				GameState.setState(State.IN_MENU);
			}
			Gui.drawString("You finished the game in ", Display.getWidth()/2 - 600, Display.getHeight() / 2 - 120, 32, false);
			Gui.color(0, 1, 1, 1);
			Gui.drawString(parseTime(time) + " minuts !", Display.getWidth()/2 + 100, Display.getHeight() / 2 - 120, 32, false, true);
			Gui.drawString("Potatoes: " + player.getPotatoes().size(), Display.getWidth()/2, Display.getHeight() / 2 - 70, 32, true);
		}
		
	}
	
	public String parseTime(int time) {
		int sec = time % 60;
		int min = time / 3600;
		
		return min + ":" + sec;
	}
	
	public void loadLevel(String level) {
		if (!changingLevel) {
			changingLevel = true;
			levelChange = true;
			Main.getMain().playSound(Audio.LEVEL_CHANGE);
			if (levelCache.containsKey(level)) {
				this.level = levelCache.get(level);
			}else {
				Level lvl = new Level(level, player);
				levelCache.put(level, lvl);
				
				this.level = lvl;
			}
		}
	}
	
	public void restart() {
		player = new Player(0, 0);
		level = new Level("lvl1", player);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public static Game getGame() {
		return game;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void exit() {
		levelCache.clear();
	}
}
