package graphicalMineSweeper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.Optional;
import java.util.ArrayList;
import java.awt.Polygon;


public class Cell extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int size = 30;
	
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
	private boolean highlight = false;
	private boolean flagged = false;
	
	
	public Cell(int x, int y, int gridX, int gridY) {
		super(x,y, size, size);
		this.gridLoc = ""+ gridX + "" + gridY;
		this.xLoc = x;
		this.yLoc = y;
		this.label = "";
		this.gridX = gridX;
		this.gridY = gridY;
	}
	
	public void paint(Graphics g, Point mousePos, boolean gameRunning) {

		g.setColor(Color.gray);
	    if(this.contains(mousePos) && gameRunning) {
	    	g.setColor(Color.green);
	    }
	    
	    g.fillRect(xLoc, yLoc, size, size);
	    g.setColor(Color.BLACK);
	    g.drawRect(xLoc, yLoc, size, size);
	    
	    
	    if(this.revealed) {
	    	g.setColor(Color.blue);
	    	g.fillRect(xLoc, yLoc, size, size);
	    	g.setColor(Color.black);
	    	g.setFont(new Font("cellFont", Font.BOLD, 32));
	    	g.drawString(this.label, this.xLoc + size/3 , (this.yLoc -  size/5) + size);
	    } 
	    
	    if(this.highlight && this.hasBomb) {
	    	g.setColor(Color.RED);
	    	g.fillRect(xLoc, yLoc, size, size);
	    }
	    
	    if(flagged) {   	
	        Polygon flag = new Polygon();
	        Point center = new Point(this.xLoc+ size/2, this.yLoc+ size/2);
	        flag.addPoint(center.x + 8, center.y);
	        flag.addPoint(center.x - 8, center.y + 8);
	        flag.addPoint(center.x - 8, center.y - 8);
	        g.setColor(Color.ORANGE);
	        g.fillPolygon(flag);
	        g.setColor(Color.BLACK);
	        g.drawPolygon(flag);
	    }
		
	}
	
	public boolean contains(Point p) { // uses contains method of rect with a null check
		if(p != null) {
		  return super.contains(p);
		} else {
		  return false;
		}
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
	
	public void setHighlight(boolean highlight){
		this.highlight = true;
	}
	
	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

}
