package graphicalMineSweeper;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Menu {

	private static Menu instance = new Menu();
	
	Rectangle background;
	Rectangle mainButton;
	Rectangle[] difficultyButtons;
	boolean start = true;
	boolean begin = false;
	
	String[] difficulties = {"Easy", "Medium", "Hard"};
	public enum Level {
		EASY,
		MEDIUM,
		HARD
	}
	Level selected = Level.EASY;
	private Level[] levelsList = Level.values();
	
	int backgroundHeight = 520;
	int backgroundWidth = 520;
	int backgroundX = 100;
	int backgroundY = 100;
	
	int mainBtnWidth = 200;
	int mainBtnHeight = 50;
	int mainXOffset = 160;
	int mainYOffset = 200;
	

	int diffBtnWidth = 100;
	int diffBtnHeight = 20;
	int diffXOffset = 210;
	int diffYOffset = 270;
	
	
	
	private Menu() {
		
	}
	
	public static Menu getMenu() {
		return instance;
	}
	
	public static Menu setMenu(boolean start) {
		if(start) {
			instance.startMenu();
		}else {
//			instance.endMenu(xPos, yPos, width, height, buttonWidth, buttonHeight);
		}
		return instance;
	}
	
	private void startMenu() {
		this.start = true;
		this.background = new Rectangle(backgroundX, backgroundY, backgroundWidth, backgroundHeight);
		this.mainButton = new Rectangle(backgroundX + mainXOffset, backgroundY + mainYOffset, mainBtnWidth, mainBtnHeight);
		this.difficultyButtons = new Rectangle[3];
		for(int i = 0; i < difficultyButtons.length; i ++) {
			difficultyButtons[i] = new Rectangle(backgroundX + diffXOffset, backgroundY + i*30 + diffYOffset, diffBtnWidth, diffBtnHeight);
		}
	}
	
//	private void endMenu(int xPos, int yPos, int width, int height, int buttonWidth, int buttonHeight) {
//		this.start = false;
//		this.background = new Rectangle(xPos, yPos, width, height);
//		this.mainButton = new Rectangle(xPos, yPos, buttonWidth, buttonHeight);
//		this.difficultyButtons = null;
//	}
	
	
	public void paint(Graphics g, Point mousePos) {
		if(mousePos == null) {
			mousePos = new Point(-1,-1);
		}
		// Background box
		g.setColor(Color.BLACK);
	    g.fillRect(this.background.x, this.background.y, this.background.width, this.background.height);
	    g.setColor(Color.WHITE);
	    g.drawRect(this.background.x, this.background.y, this.background.width, this.background.height);

	    // Main Button Box
	    if(this.mainButton.contains(mousePos)) {
	    	g.setColor(Color.GRAY);
	    }else {
	    	g.setColor(Color.BLACK);
	    }
	    g.fillRect(this.mainButton.x, this.mainButton.y, this.mainButton.width, this.mainButton.height);
	    g.setColor(Color.WHITE);
	    g.drawRect(this.mainButton.x, this.mainButton.y, this.mainButton.width, this.mainButton.height);
    	g.setFont(new Font("menuFont", Font.BOLD, 32));
    	g.drawString("Start!", mainButton.x + 60, mainButton.y + 35);
	    
	    // difficulty buttons
    	g.setFont(new Font("menuFont", Font.BOLD, 15));
	    if(this.difficultyButtons != null) {
		    for(int i = 0; i < this.difficultyButtons.length; i++) {
			    if(this.difficultyButtons[i].contains(mousePos)) {
			    	g.setColor(Color.GRAY);
			    }else {
			    	g.setColor(Color.BLACK);
			    }
			    g.fillRect(this.difficultyButtons[i].x, this.difficultyButtons[i].y, this.difficultyButtons[i].width, this.difficultyButtons[i].height);
			    g.setColor(Color.WHITE);
			    g.drawRect(this.difficultyButtons[i].x, this.difficultyButtons[i].y, this.difficultyButtons[i].width, this.difficultyButtons[i].height);
			    g.drawString(this.difficulties[i], this.difficultyButtons[i].x + 5, this.difficultyButtons[i].y + 15);
		    }
	    }
	}
	
	public void mouseClicked(int x, int y) {
		if(mainButton.contains(x,y)) {
			begin = true;
		}
		
		for(int i = 0; i < this.difficultyButtons.length; i++) {
			if(this.difficultyButtons[i].contains(x,y)) {
				this.selected = levelsList[i];
			}
		}
		
	}
	
	public boolean hasBegun() {
		return begin;
	}
	
	public boolean getStart() {
		return start;
	}
	
	public Level getLevel() {
		return selected;
	}
	
}
