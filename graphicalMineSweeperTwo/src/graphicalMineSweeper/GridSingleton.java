package graphicalMineSweeper;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Optional;

public class GridSingleton {
	
	private static GridSingleton instance = new GridSingleton();
	private  ArrayList<Cell> cells = new ArrayList<Cell>();
	private  int numberOfBombs = 0;
	private  int unassignedBombs = 0;
	private  int gridSize = 2;
	private int gridArea = 4;
	private  int safeSpacesLeft;
	
	private GridSingleton() {
		
	}
	
	public static GridSingleton getGrid() {
		return instance;
	}
	
	public void setupGrid(int bombs, int size) {
		if(size > 10) {
			size = 10;
		}
		this.gridSize = size >= 2 ? size : 2; 
		this.gridArea = (int) Math.pow(this.gridSize, 2);
		this.setUpCells();
		this.setupBombs(bombs);
		this.initialliseBombs();
		this.setUpNeighbors();
		this.safeSpacesLeft = this.gridArea-this.numberOfBombs;

	}
	
	private void setUpNeighbors() {
		for(Cell cell: cells) {
			cell.newInitialiseNeighbors(); 
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
				cells.add(new Cell(xLoc,yLoc));
				xLoc = yLoc != this.gridSize-1 ? 0 : xLoc++; // if not end of last row set to 0
				yLoc++;
			}else {
				cells.add(new Cell(xLoc,yLoc));
				xLoc++;
			}
		}

	}
	
	public Optional<Cell> getCell(int input) {
		if((""+input).length() > 2) {
			return Optional.empty();
		}	
		Optional<Cell> selected = this.cells.stream().filter(s -> s.getLoc() == input).findFirst();
		if(selected.isEmpty()) {
			System.out.println("no cell found");
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
	
	public void paint(Graphics g, Point mousePos) {
		for(Cell cur: this.cells) {
			cur.paint(g, mousePos);
		}
	}

}
