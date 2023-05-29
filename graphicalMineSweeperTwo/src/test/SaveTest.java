package test;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import graphicalMineSweeper.Save;


public class SaveTest {
	
	@Test
	void createSaveFile_NormalRunningNotInterupted() {
		assertEquals("saveFile.txt", Save.createSaveFile());
	}

}