package graphicalMineSweeper;

public class Cell {
	private int xLoc;
	private int yLoc;
	private boolean rowEnd;
	private boolean hasBomb = false;
	private String innerName;
	private String name;
	private boolean revealed = false;
	
	
	public Cell(int x, int y, boolean rowEnd) {
		this.xLoc = x;
		this.yLoc = y;
		this.rowEnd = rowEnd;
		this.name = "| " + this.xLoc + this.yLoc + " |";
		this.innerName = "" + this.xLoc + this.yLoc;
		
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public void setName(int numBombs) {
		if(numBombs == 0) {
			this.name = "|    |";
		}else {
			this.name = "| " + "B" + numBombs + " |";
		}
		
	}
	
	public int getXLoc() {
		return this.xLoc;
	}
	public int getYLoc() {
		return this.yLoc;
	}
	
	public void render() {
		System.out.print(this.name);
		if(this.rowEnd) {
			System.out.printf("\n");
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
