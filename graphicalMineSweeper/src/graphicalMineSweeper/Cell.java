package graphicalMineSweeper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;
import java.awt.Rectangle;


public class Cell extends Rectangle {
	
	public static int size = 35;
	
	private int xLoc;
	private int yLoc;
	private boolean hasBomb = false;
	private String label;
	private boolean revealed = false;
	private int gridX;
	private int gridY;
	
	
	
	public Cell(int x, int y, int gridX, int gridY) {
		super(x,y, size, size);
		this.xLoc = x;
		this.yLoc = y;
		this.label = "";
		this.gridX = gridX;
		this.gridY = gridY;
	}
	
	
	public String getLabel() {
		return this.label;
	}
	
	public void setLabel(int numBombs) {
		this.label = "" + numBombs;
		
	}
	
	public int getXLoc() {
		return this.xLoc;
	}
	public int getYLoc() {
		return this.yLoc;
	}
	
	public int getGridXLoc() {
		return this.gridX;
	}
	public int getGridYLoc() {
		return this.gridY;
	}
	
	public void paint(Graphics g, Point mousePos) {
		if(hasBomb) {
	    g.setColor(Color.red);
		}else {
			g.setColor(Color.gray);
		}
	    if(this.contains(mousePos)) {
	    	Grid.setCurCell(this);
	    	g.setColor(Color.green);
	    }
	    
	    g.fillRect(x, y, size, size);
	    g.setColor(Color.BLACK);
	    g.drawRect(x, y, size, size);
	    if(this.revealed) {
	    	g.setColor(Color.blue);
	    	g.fillRect(x, y, size, size);
	    	g.setColor(Color.black);
	    	g.setFont(new Font("cellFont", Font.BOLD, 32));
	    	g.drawString(this.label, x + size/4, y + size - size/5);

	    }  
		
	}
	
	public boolean contains(Point p) { // uses contains method of rect with a null check
		if(p != null) {
		  return super.contains(p);
		} else {
		  return false;
		}
	}
	
	public void setBomb() {
		this.hasBomb = true;
	}
	
	public boolean getBomb() {
		return this.hasBomb;
	}
	
	public void setRevealed() {
		this.revealed = true;
	}
	
	public boolean getRevealed() {
		return this.revealed;
	}
	
	

	

}
