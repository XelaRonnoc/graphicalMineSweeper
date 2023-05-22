package test;

	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertFalse;
	import static org.junit.Assert.assertThrows;
	import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	
	import graphicalMineSweeper.Cell;
	import graphicalMineSweeper.GridSingleton;
	
public class GridSingletonTest {
		static GridSingleton grid;
		
		@BeforeEach
		void setUpGrid() {
			grid = GridSingleton.getGrid();
			grid.setupGrid(10, 10);
		}
		
		@Test
		void getCell_XYDigitInt_ReturnsCorrectCell() {
			Optional<Cell> cell = grid.getCell(1,5);
			assertEquals(1, cell.get().getGridXLoc());
			assertEquals(5, cell.get().getGridYLoc());
		}
		
		@Test
		void getCell_invalidXY_ReturnsOptionalEmpty() {
			Optional<Cell> cell = grid.getCell(-1,0);
			assertEquals(Optional.empty(), cell);
		}
		
		@Test
		void getCell_OutOfBoundsXY_ReturnsOptionalEmpty() {
			Optional<Cell> cell = grid.getCell(20,2);
			assertEquals(Optional.empty(), cell);
		}
		
		@Test
		void getCell_StringLoc_ReturnsCorrectCell() {
			Optional<Cell> cell = grid.getCell("15");
			assertEquals(1, cell.get().getGridXLoc());
			assertEquals(5, cell.get().getGridYLoc());
		}
		
		@Test
		void getCell_invalidString_ReturnsOptionalEmpty() {
			Optional<Cell> cell = grid.getCell("A");
			assertEquals(Optional.empty(), cell);
		}
		
		@Test
		void getCell_OutOfBoundsString_ReturnsOptionalEmpty() {
			Optional<Cell> cell = grid.getCell("202");
			assertEquals(Optional.empty(), cell);
		}
		
		@Test
		void getSafeSpacesLeft_ReturnsNumOfCellsMinusNumOfBombs() {
			int result = grid.getSafeSpacesLeft();
			assertEquals(90, result);
		}
		
		@Test
		void decrementSafeSpacesLeft_ReducesSafeSpacesLeftBy1() {
			int initial = grid.getSafeSpacesLeft();
			grid.decrementSafeSpacesLeft();
			int outcome = grid.getSafeSpacesLeft();
			assertEquals(initial-1, outcome);
		}
		
		@Test 
		void getNumberOfBombs_ReturnsNumberOfBombs(){
			assertEquals(10, grid.getNumberOfBombs());
		}
		
		@Test 
		void getGridSize_ReturnsGridDimension(){
			assertEquals(10, grid.getGridSize());
		}
		
		@Test 
		void getGridArea_ReturnsGridArea(){
			assertEquals(100, grid.getGridArea());
		}
		
		@Test 
		void getIsRunning_ReturnsGameStateRunning_ReturnTrue(){
			assertTrue(grid.getGameRunning());
		}
		
		@Test 
		void getIsRunning_ReturnsGameStateNotRunningAfterHittingBomb_ReturnsFalse(){
			grid.setupGrid(2, 4); // full field of bombs
			grid.mouseClicked(20,20);

			assertFalse(grid.getGameRunning());
		}
		
		@Test 
		void getIsRunning_ReturnsGameStateNotRunningNoBombsInstaWin_ReturnsFalse(){
			grid.setupGrid(2, 0); // full field of bombs
				grid.mouseClicked(20,20);

			assertFalse(grid.getGameRunning());
		}

}
