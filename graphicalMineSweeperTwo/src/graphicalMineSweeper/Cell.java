package graphicalMineSweeper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.Optional;
import java.util.ArrayList;


public class Cell extends Rectangle {
//	private GridSingleton gridRef = GridSingleton.getGrid();
	
	public static int size = 35;
	
	private int xLoc;
	private int yLoc;
	private boolean hasBomb = false;
	private String label;
	private boolean revealed = false;
	private int gridX; // where it is stored in grid
	private int gridY;
	private String gridLoc;
	private ArrayList<Cell> neighbors = new ArrayList<Cell>();
	private boolean canCascade = true;
	private int bombNear = 0;
	
	
	
	public Cell(int x, int y, int gridX, int gridY) {
		super(x,y, size, size);
		this.gridLoc = ""+ gridX + "" + gridY;
		this.xLoc = x;
		this.yLoc = y;
		this.label = "";
		this.gridX = gridX;
		this.gridY = gridY;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public void setLabel() {
		this.label = "" + this.bombNear;
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
		g.setColor(Color.gray);
	    if(this.contains(mousePos)) {
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
	    	g.drawString(this.label, this.xLoc + size/3 , (this.yLoc -  size/5) + size);

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

	public String getGridLoc() {
		return this.gridLoc;
	}
	
	public void addNeighbor(Cell neighbor) {
		this.neighbors.add(neighbor);
	}
	
	public ArrayList<Cell> getNeighbors(){
		return this.neighbors;
	}
	
	public void setCanCascade(boolean cascadable) {
		this.canCascade = cascadable;
	}

	public boolean canCascade() {
		return this.canCascade;
	}
	
	public int getNear() {
		return this.bombNear;
	}
	
	public void incrementBombNear() {
		this.bombNear++;
	}
	
	// ONLY USE FOR TESTING
	public void setNear(int bombs) {
		this.bombNear = bombs;
	}	
	
	public String toString() {
		return "gridX: " + this.gridX + " gridY: " + this.gridY;
	}

}
