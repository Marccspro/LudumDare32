package fr.veridiangames.main.menu;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

import fr.veridiangames.main.GameState;
import fr.veridiangames.main.GameState.State;
import fr.veridiangames.main.Main;
import fr.veridiangames.main.ViewState;
import fr.veridiangames.main.rendering.Gui;
import fr.veridiangames.main.rendering.Texture;

public class Menu extends ViewState{
	boolean instruction = false;
	boolean option = false;
	boolean about = false;
	
	public Menu() {

	}
	
	public void update() {
		
	}
	
	public void render() {

	}
	
	public void renderGUI() {
		Texture.BG.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); glVertex2f(0, 0);
		glTexCoord2f(1, 0); glVertex2f(Display.getWidth(), 0);
		glTexCoord2f(1, 1); glVertex2f(Display.getWidth(), Display.getHeight());
		glTexCoord2f(0, 1); glVertex2f(0, Display.getHeight());
		glEnd();
		Texture.unbind();
		
		if (instruction) {
			renderInstrcution();
		}else if (option) {
			renderOption();			
		}else if (about) {
			renderAbout();			
		}else {
			renderMenu();			
		}
	}
	
	public void renderMenu() {
		
		Gui.drawString(Main.NAME, Display.getWidth() / 2, Display.getHeight() / 2 - 250, 48, true);
		if (Gui.button("PLAY", Display.getWidth() / 2, Display.getHeight() / 2, 300)) {
			instruction = true;
			about = false;
			option = false;
		}
		if (Gui.button("OPTIONS", Display.getWidth() / 2, Display.getHeight() / 2 + 44, 300)) {
			option = true;
			instruction = false;
			about = false;
		}
		if (Gui.button("ABOUT", Display.getWidth() / 2, Display.getHeight() / 2 + 44 * 2, 300)) {
			option = false;
			instruction = false;
			about = true;
		}
		if (Gui.button("EXIT", Display.getWidth() / 2, Display.getHeight() / 2 + 44 * 3, 300)) {
			Main.exit();
		}
	}
	
	public void renderInstrcution() {
		Gui.drawString("-ZQSD- or -WASD- or Arrows to move", Display.getWidth() / 2, Display.getHeight() / 2 - 300, 32, true);
		Gui.drawString("Press -E- to open inventory", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 64, 32, true);
		Gui.drawString("Press -G- to throw a potato grenade", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 64 * 2, 32, true);
		
		Gui.drawString("What the game is about:", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 64 * 4, 32, true);
		Gui.drawString("You have to collect as many", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 64 * 5, 16, true);
		Gui.drawString("potatoes as you can !", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 58 * 6, 16, true);
		Gui.color(0, 1, 1, 1);
		Gui.drawString("NOTE:  Craft a weapon as soon as you can are you will die ;)", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 55 * 7, 16, true, true);
		Gui.color(1, 0, 0, 1);
		Gui.drawString("disclaimer", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 64 * 8, 32, true, true);
		Gui.drawString("The game may containe bugs or glitches, not that it was made in 48 hours ;)", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 63 * 9, 16, true, true);
		Gui.color(1, 1, 1, 1);
		if (Gui.button("Start", Display.getWidth() - 150, Display.getHeight() - 50, 200)) {
			instruction = false;
			GameState.setState(State.IN_GAME);
		}
		if (Gui.button("Back", 150, Display.getHeight() - 50, 200)) {
			instruction = false;
			about = false;
		}
	}
	
	public void renderOption() {
		Gui.drawString("There is no options :S Let me know", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 64 * 4, 32, true);
		Gui.drawString("if you have one to add ;)", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 64 * 5, 32, true);
		if (Gui.button("Back", 150, Display.getHeight() - 50, 200)) {
			option = false;
		}
	}
	
	public void renderAbout() {
		Gui.drawString("-" + Main.NAME + "- is a game ", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 64, 32, true);
		Gui.drawString("made in 48 hours for the Ludum Dare 32.", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 64 * 2, 32, true);
		Gui.drawString("Game made by Marccspro", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 64 * 5, 32, true);
		Gui.drawString("@Marccspro", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 64 * 6, 32, true);
		Gui.drawString("www.veridiangames.fr", Display.getWidth() / 2, Display.getHeight() / 2 - 300 + 64 * 8, 32, true);
		if (Gui.button("Back", 150, Display.getHeight() - 50, 200)) {
			about = false;
		}
	}
}
