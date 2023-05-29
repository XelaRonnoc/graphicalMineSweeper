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
	String[] options = {"Re-try", "New Game", "Exit"};
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

	int diffBtnWidth = 200;
	int diffBtnHeight = 40;
	int diffXOffset = 160;
	int diffYOffset = 170;
	int xTextOffset = 5;
	int yTextOffset = 25;
	int yButtonSpacing = 60;
	
	
	
	private Menu() {
		
	}
	
	public static Menu getMenu() {
		return instance;
	}
	
	public static Menu setMenu(boolean start) {
		if(start) {
			instance.startMenu();
		}else {
			instance.endMenu();
		}
		return instance;
	}
	
	private void startMenu() {
		this.start = true;
		this.background = new Rectangle(backgroundX, backgroundY, backgroundWidth, backgroundHeight);
		this.difficultyButtons = new Rectangle[3];
		for(int i = 0; i < difficultyButtons.length; i ++) {
			difficultyButtons[i] = new Rectangle(backgroundX + diffXOffset, backgroundY + i*yButtonSpacing + diffYOffset, diffBtnWidth, diffBtnHeight);
		}
	}
	
	private void endMenu() {
		this.start = false;
		this.begin = false;
	}
	
	
	public void paint(Graphics g, Point mousePos) {
		if(mousePos == null) {
			mousePos = new Point(-1,-1);
		}
		// Background box
		if(start) {
			g.setColor(Color.BLACK);
		    g.fillRect(this.background.x, this.background.y, this.background.width, this.background.height);
		    g.setColor(Color.WHITE);
		    g.drawRect(this.background.x, this.background.y, this.background.width, this.background.height);
		}

	    
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
			    if(start) {
			    g.drawString(this.difficulties[i], this.difficultyButtons[i].x + xTextOffset, this.difficultyButtons[i].y + yTextOffset);
			    }else {
			    	g.drawString(this.options[i], this.difficultyButtons[i].x + xTextOffset, this.difficultyButtons[i].y + yTextOffset);
			    }
	    
		    }
	    }
	}
	
	public void mouseClicked(int x, int y) {
//		if(mainButton.contains(x,y)) {
//			begin = true;
//		}
		if(this.start) {
			for(int i = 0; i < this.difficultyButtons.length; i++) {
				if(this.difficultyButtons[i].contains(x,y)) {
					this.selected = levelsList[i];
					this.begin = true;
				}
			}
		}else if(!this.start){
			for(int i = 0; i < this.difficultyButtons.length; i++) {
				if(this.difficultyButtons[i].contains(x,y)) {
					switch(i) {
						case 0:
							this.start = true;
							this.begin = true;
							break;
						case 1:
							Menu.setMenu(true);
							break;
						case 2:
							System.out.println("closing program");
							System.exit(0);
							break;
					}
				}	
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
