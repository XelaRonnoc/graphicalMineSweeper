package graphicalMineSweeper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.Optional;
import java.util.ArrayList;


public class Cell extends Rectangle {
	private GridSingleton gridRef = GridSingleton.getGrid();
	
	public static int size = 35;
	
	private int xLoc;
	private int yLoc;
	private boolean hasBomb = false;
	private String label;
	private boolean revealed = false;
	private int gridX; // where it is stored in grid
	private int gridY;
	private int loc;
	private ArrayList<Cell> neighbors = new ArrayList<Cell>();
	private boolean canCascade = true;
	private int bombNear = 0;
	
	
	
	public Cell(int x, int y, int gridX, int gridY) {
		super(x,y, size, size);
		this.loc = Integer.parseInt(""+ x + "" + y);
		this.xLoc = x;
		this.yLoc = y;
		this.label = "";
		this.gridX = gridX;
		this.gridY = gridY;
	}
	
	public Cell(int x, int y) {
		super(x,y, size, size);
		this.loc = Integer.parseInt(""+ x + "" + y);
		this.xLoc = x;
		this.yLoc = y;
		this.label = "";
	}
	
	public void newInitialiseNeighbors() {
		for(int i = this.yLoc-1; i <= this.yLoc+1; i++) {
			for(int j = this.xLoc-1; j <= xLoc+1; j++) {
				if(i != this.yLoc || j != this.xLoc) {
					if(i < 0 || j < 0 || i >= this.gridRef.getGridSize() || j >= this.gridRef.getGridSize()) {
						continue;
					}
					int cellLoc =  Integer.parseInt("" + j +"" + i);
					Optional<Cell> neighbor = gridRef.getCell(cellLoc);
					if(neighbor.isPresent()) {
						if(neighbor.get().getBomb()) {
							this.canCascade = false;
							this.bombNear++;
						}
						this.neighbors.add(neighbor.get());
					}
				}
			}
		}
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

	public int getLoc() {
		return this.loc;
	}
	
	
	public ArrayList<Cell> getNeighbors(){
		return this.neighbors;
	}

	public boolean canCascade() {
		
		return this.canCascade;
	}
	
	public int getNear() {
		return this.bombNear;
	}
	
	// ONLY USE FOR TESTING
	public void setNear(int bombs) {
		this.bombNear = bombs;
	}
	
	

	

}
