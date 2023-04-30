package graphicalMineSweeper;
import java.awt.Graphics;
import java.awt.Point;


public class Grid {
	
	public static Cell curCell;
	
	private Cell[][] cells = new Cell[10][10];
	private int numberOfBombs;
	private int unassignedBombs;
	private int gridSize;
	private int safeSpaces;
	private int safeSpacesLeft;
	
	public Grid(int bombs, int gridSize) {
		if(bombs <=0) {
			bombs = 1;
		}
		
		if(gridSize < 2) {
			gridSize = 2;
		}
		this.gridSize = gridSize<= 20 ? gridSize : 10;
		this.cells = new Cell[this.gridSize][this.gridSize];
	    for(int i=0; i<cells.length; i++) {
	        for(int j=0; j<cells[i].length; j++) {
	        	if(j == cells[i].length-1) {
	        		cells[i][j] = new Cell(10+Cell.size*j, 10+Cell.size*i, j, i);
	        	}else {
	        		cells[i][j] = new Cell(10+Cell.size*j, 10+Cell.size*i, j, i);
	        	}
	        	
	        	
	        }
	    }
	    
	    this.numberOfBombs = (int) ( bombs >= Math.pow(this.gridSize, 2) ? Math.pow(this.gridSize, 2)-1 : bombs);
	    this.unassignedBombs = this.numberOfBombs;
	    this.safeSpaces = (int) Math.pow(this.gridSize, 2) - this.numberOfBombs;
	    this.safeSpacesLeft = safeSpaces;
	    
	    initialiseBombs();
	    
	}
	
	
	public void initialiseBombs() {
		while(this.unassignedBombs > 0) {
			int xLoc = (int) Math.floor(Math.random()*this.gridSize);
			int yLoc = (int) Math.floor(Math.random()*this.gridSize);
			if(cells[yLoc][xLoc].getBomb()) {
				continue;
			}
			cells[yLoc][xLoc].setBomb();
			unassignedBombs--;
		}
	}
	
	public void paint(Graphics g, Point mousePos) {
		// replace with stream?
	    for(int i=0; i<cells.length; i++) {
	        for(int j=0; j<cells[i].length; j++) {
	          cells[i][j].paint(g, mousePos);
	        }
	      }
	}
	
//	public Cell getCell(int input) {
//		int xLoc = (input/10);
//		int yLoc = (input%10);
//		Cell selected = cells[yLoc][xLoc];
//		return selected;
//	}
	
	public static void setCurCell(Cell c) {
		curCell = c;
	}
	
	public void showBombs(Cell selected) {
		selected.setRevealed();
		decrementSafeSpacesLeft();
		int curX = selected.getGridXLoc();
		int curY = selected.getGridYLoc();
		int bombCount = 0;
		for(int i = curY-1; i <= curY+1; i++) {
			for(int j = curX-1; j <= curX+1; j++) {
				
				if(i > -1 && i < this.gridSize && j > -1 && j < this.gridSize) {
					if(!cells[i][j].getRevealed()) {				
						if(cells[i][j].getBomb()) {
							bombCount++;
						}
					}
				}
			}
		}
		selected.setLabel(bombCount);
		if(bombCount == 0) {
			for(int i = curY-1; i <= curY+1; i++) {
				for(int j = curX-1; j <= curX+1; j++) {
					if(i > -1 && i < this.gridSize && j > -1 && j < this.gridSize) {
						if(!cells[i][j].getRevealed()) {
							showBombs(cells[i][j]);
						}
					}
				}
			}
		}		
	}
	
	public int getSafeSpacesLeft() {
		return this.safeSpacesLeft;
	}
	
	public void decrementSafeSpacesLeft() {
		this.safeSpacesLeft--;
	}
	
	public void mouseClicked(int x, int y) {
		Cell clicked = curCell;
		
//		 for(int i=0; i<cells.length; i++) {
//		    for(int j=0; j<cells[i].length; j++) {
//		        if(cells[i][j].contains(x,y)) {
//		        	clicked = cells[i][j];
//		        }
//		    }
//		 }
		 
		 if(clicked != null) {
			boolean bomb = clicked.getBomb();
			if(bomb) {
				System.out.println("BOOOM!");
					
			}else {
				this.showBombs(clicked);
			}
				
			if(this.getSafeSpacesLeft() == 0) {
				System.out.println("You Won!!!");
			}
		}
	}
}
