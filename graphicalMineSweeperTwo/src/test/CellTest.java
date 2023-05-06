package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import graphicalMineSweeper.Cell;
import graphicalMineSweeper.GridSingleton;

public class CellTest {
	static Cell cell;
	static GridSingleton grid;
	
	@BeforeEach
	void setUpCell() {
		grid = GridSingleton.getGrid();
		grid.setupGrid(0, 20);
		cell = new Cell(10+Cell.size*1, 10+Cell.size*5,1, 5);
	}
	
	@Test
	void getXLoc_ReturnsXLoc() {
		assertEquals(1, cell.getGridXLoc());
	}
	
	@Test
	void getYLoc_ReturnsYLoc() {
		assertEquals(5, cell.getGridYLoc());
	}
	
	@Test
	void getLoc_ReturnsLoc() {
		assertEquals("15", cell.getGridLoc());
	}
	
	@Test
	void getBomb_NoBomb_ReturnsFalse() {
		assertEquals(false, cell.getBomb());
	}
	
	@Test
	void getBomb_Bomb_ReturnsBombState() {
		cell.setBomb();
		assertEquals(true, cell.getBomb());
	}
	
	@Test
	void getBomb_Revealed_ReturnsBombState() {
		cell.setRevealed();
		assertEquals(true, cell.getRevealed());
	}
	
	
	@Test
	void getBomb_NotRevealed_ReturnsBombState() {
		assertEquals(false, cell.getRevealed());
	}
	
	@Test
	void getName_LabelNoBomb_ReturnsBombState() {
		cell.setNear(0);
		cell.setLabel();
		assertEquals("0", cell.getLabel());
	}
	
	@Test
	void getBomb_LabelWThreeBombs_ReturnsBombState() {
		cell.setNear(3);
		cell.setLabel();
		assertEquals("3", cell.getLabel());
	}
	
	@Test
	void canCascade_BomblessGrid_returnsTrue() {
		Optional<Cell> cur = grid.getCell(1,5);
		assertEquals(true, cur.get().canCascade());
	}
	
	
	@Test
	void getNear_BomblessGrid_returnsZero() {
		Optional<Cell> cur = grid.getCell(1,5);
		assertEquals(0, cur.get().getNear());
	}
	
	@Test
	void getNeighbors_GridSurronded_ReturnsArrayListSize8() {
		Optional<Cell> cur = grid.getCell(1,5);
		assertEquals(8, cur.get().getNeighbors().size());
	}
	@Test
	void getNeighbors_GridTopLeft_ReturnsArrayListSize3() {
		Optional<Cell> cur = grid.getCell(0,0);
		assertEquals(3, cur.get().getNeighbors().size());
	}
	
	@Test
	void getNeighbors_GridBottomRight_ReturnsArrayListSize3() {
		Optional<Cell> cur = grid.getCell(19,19);
		assertEquals(3, cur.get().getNeighbors().size());
	}
	
	@Test
	void getNeighbors_GridMidLeft_ReturnsArrayListSize5() {
		Optional<Cell> cur = grid.getCell(0,1);
		assertEquals(5, cur.get().getNeighbors().size());
	}
	
	
	
	

}
