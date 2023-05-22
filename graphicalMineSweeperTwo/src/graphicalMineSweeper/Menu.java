package graphicalMineSweeper;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Optional;

public class Menu {
	private static Menu instance = new Menu();
	
	Rectangle background;
	Rectangle mainButton;
	Rectangle[] difficultyButtons;
	boolean start;
	
	private Menu() {
		
	}
	
	public static Menu getMenu() {
		return instance;
	}
	
	public void startMenu(int xPos, int yPos, int width, int height, int buttonWidth, int buttonHeight) {
		this.start = true;
		this.background = new Rectangle(xPos, yPos, width, height);
		this.mainButton = new Rectangle(xPos, yPos, buttonWidth, buttonHeight);
		Rectangle[] difficultyButtons = new Rectangle[3];
		for(int i = 0; i < difficultyButtons.length; i ++) {
			difficultyButtons[i] = new Rectangle(xPos, yPos + i*10, 50, 25);
		}
	}
	
	public void endMenu(int xPos, int yPos, int width, int height, int buttonWidth, int buttonHeight) {
		this.start = false;
		this.background = new Rectangle(xPos, yPos, width, height);
		this.mainButton = new Rectangle(xPos, yPos, buttonWidth, buttonHeight);
		difficultyButtons = null;
	}
	
	
	public void paint(Graphics g, Point mousePos) {
		// Background box
		g.setColor(Color.BLACK);
	    g.fillRect(this.background.x, this.background.y, this.background.width, this.background.height);
	    g.setColor(Color.WHITE);
	    g.drawRect(this.background.x, this.background.y, this.background.width, this.background.height);
	    
	    // Main Button Box
		g.setColor(Color.BLACK);
	    g.fillRect(this.mainButton.x, this.mainButton.y, this.mainButton.width, this.mainButton.height);
	    g.setColor(Color.WHITE);
	    g.drawRect(this.mainButton.x, this.mainButton.y, this.mainButton.width, this.mainButton.height);
	    
	    // difficulty buttons
	    for(Rectangle r : difficultyButtons) {
			g.setColor(Color.BLACK);
		    g.fillRect(r.x, r.y, r.width, r.height);
		    g.setColor(Color.WHITE);
		    g.drawRect(r.x, r.y, r.width, r.height);
	    }
	}
	
	public void mouseClicked(int x, int y) {
		
	}
}
