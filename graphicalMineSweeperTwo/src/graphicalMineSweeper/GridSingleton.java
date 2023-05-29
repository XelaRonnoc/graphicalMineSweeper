package graphicalMineSweeper;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Optional;


public class GridSingleton {
	
	private static GridSingleton instance = new GridSingleton();
	
	private Cell curCell;
	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private int numberOfBombs = 0;
	private int unassignedBombs = 0;
	private int gridSize = 2;
	private int gridArea = 4;
	private int safeSpacesLeft;
	private int screenWidth = 720;
	private int borderWidth = 10;
	private boolean gameRunning = false;
	private boolean victory = false;
	
	private GridSingleton() {
		
	}
	
	public static GridSingleton getGrid() {
		return instance;
	}
	
	public void setupGrid(int bombs, int size) {
		this.gridSize = size >= 2 ? size : 2; 
		this.gridArea = (int) Math.pow(this.gridSize, 2);
		this.setBorderWidth();
		this.setUpCells();
		this.setupBombs(bombs);
		this.initialliseBombs();
		this.setUpNeighbors();
		this.safeSpacesLeft = this.gridArea-this.numberOfBombs;
		this.setGameRunning(true);

	}
	
	private void setUpNeighbors() {
		for(Cell cell: cells) {
			initCellNeighbors(cell); 
		}
		
	}
	
	private void initCellNeighbors(Cell cell) {
		for(int i = cell.getGridYLoc()-1; i <= cell.getGridYLoc()+1; i++) {
			for(int j = cell.getGridXLoc()-1; j <= cell.getGridXLoc()+1; j++) {
				if(i != cell.getGridYLoc() || j != cell.getGridXLoc()) {
					if(i < 0 || j < 0 || i >= this.getGridSize() || j >= this.getGridSize()) {
						continue;
					}
					Optional<Cell> neighbor = this.getCell(j,i);
					if(neighbor.isPresent()) {
						if(neighbor.get().getBomb()) {
							cell.setCanCascade(false);
							cell.incrementBombNear();
						}
						cell.addNeighbor(neighbor.get());
					}
				}
			}
		}
	}

	private void initialliseBombs() {
		while(this.unassignedBombs > 0) {
			int cellLoc = (int) Math.floor(Math.random()*this.gridArea);
			if(cells.get(cellLoc).getBomb()) {
				continue;
			}
			this.cells.get(cellLoc).setBomb();
			unassignedBombs--;
		}
		
	}

	private void setupBombs(int bombs) {
		if(bombs >= this.gridArea){
			this.numberOfBombs = this.gridArea - 1;
		}else if(bombs < 0) {
			this.numberOfBombs = 0;
		}else {
			this.numberOfBombs = bombs;
		}
		this.unassignedBombs = this.numberOfBombs;
		
	}

	private void setUpCells() {
		this.cells = new ArrayList<Cell>(this.gridArea);
		int yLoc = 0;
		int xLoc = 0;
		while(xLoc < this.gridSize && yLoc < this.gridSize) {
			if(xLoc == this.gridSize-1) { // if end of row
				cells.add(new Cell(this.borderWidth+Cell.size*xLoc,this.borderWidth+Cell.size*yLoc, xLoc, yLoc));
				xLoc = yLoc != this.gridSize-1 ? 0 : xLoc++; // if not end of last row set to 0
				yLoc++;
			}else {
				cells.add(new Cell(this.borderWidth+Cell.size*xLoc,this.borderWidth+Cell.size*yLoc, xLoc, yLoc));
				xLoc++;
			}
		}

	}
	
	public Optional<Cell> getCell(String input) {
		Optional<Cell> selected = this.cells.stream().filter(s -> s.getGridLoc().equals(input)).findFirst();
		if(selected.isEmpty()) {
			System.out.println("no cell found");
		}
		return selected;
	}
	
	public Optional<Cell> getCell(int x, int y) {
		Optional<Cell> selected = this.cells.stream().filter(s -> s.getGridXLoc() == x && s.getGridYLoc() == y).findFirst();
		if(selected.isEmpty()) {
			System.out.println("no cell found");
			return Optional.empty();
		}
		return selected;
	}
	
	public void showBombs(Cell selected) {
		if(selected.getBomb()) {
			return;
		}
		selected.setRevealed();
		this.decrementSafeSpacesLeft();
		for(Cell neighbor : selected.getNeighbors()) {
			if(!neighbor.getRevealed()) {
				if(selected.canCascade()) {
					showBombs(neighbor);
				}	
			}
		}
		selected.setLabel();
	}
	
	
	public int getSafeSpacesLeft() {
		return this.safeSpacesLeft;
	}
	
	public void decrementSafeSpacesLeft() {
		this.safeSpacesLeft--;
	}
	
	public int getNumberOfBombs() {
		return this.numberOfBombs;
	}
	
	public int getGridSize() {
		return this.gridSize;
	}
	
	public int getGridArea() {
		return this.gridArea;
	}
	
	public Cell getCurCell() {
		return this.curCell;
	}
	
	public boolean getGameRunning() {
		return this.gameRunning;
	}
	
	public void setGameRunning(boolean running) {
		this.gameRunning = running;
	}
	
	public void mouseClicked(int x, int y) {
		if(!this.gameRunning) {
			return;
		}
		Optional<Cell> clicked = Optional.empty();
		for(Cell cell: cells) {
			if(cell.contains(x,y)) {
				clicked = Optional.ofNullable(cell);
			}
		}
		 if(clicked.isPresent()) {
			boolean bomb = clicked.get().getBomb();
			if(bomb) {
				this.victory = false;
				this.highlightBombs();
				Save.save(
						"lost",
						"" + this.getSafeSpacesLeft(),
						"" + this.getNumberOfBombs(),
						this.getGridSize() + "x" + this.getGridSize()
						);
				this.setGameRunning(false);
					
			}else {
				this.showBombs(clicked.get());
			}
				
			if(this.getSafeSpacesLeft() == 0) {
				this.victory = true;
				Save.save(
						"Won",
						"" + this.getSafeSpacesLeft(),
						"" + this.getNumberOfBombs(),
						this.getGridSize() + "x" + this.getGridSize()
						);	
				this.setGameRunning(false);
			}
		}
	}
	
	public void mouseRightClicked(int x, int y) {
		if(!this.gameRunning) {
			return;
		}
		Optional<Cell> clicked = Optional.empty();
		for(Cell cell: cells) {
			if(cell.contains(x,y)) {
				clicked = Optional.ofNullable(cell);
			}
		}
		if (clicked.isPresent()) {
			clicked.get().setFlagged(true);
		}
		
		
	}
	
	
	public void paint(Graphics g, Point mousePos) {
		for(Cell cur: this.cells) {
			cur.paint(g, mousePos, this.gameRunning);
		}
	}
	
	public boolean getVictory() {
		return this.victory;
	}
	
	public void setBorderWidth() {
		int boardSize = gridSize * Cell.size;
		this.borderWidth = (this.screenWidth - boardSize)/2;
	}
	
	public void setScreenWidth(int screenWidth) {
		
		this.screenWidth = screenWidth;
	}
	
	public void highlightBombs() {
		for(Cell c : this.cells) {
			if(c.getBomb()) {
				c.setHighlight(true);
			}
		}
	}

}
