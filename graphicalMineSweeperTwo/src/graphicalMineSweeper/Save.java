package graphicalMineSweeper;

import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException;  

public class Save {

	
	public static String createSaveFile() {
		String fileName = "saveFile.txt";
		try {
			File saveFile = new File(fileName);
			if(saveFile.createNewFile()) {
				System.out.println("File created: " + saveFile.getName());
			}else {
				System.out.println("File already exists.");
			}
			return fileName;
		} catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
		}
		return null;
	}
	
	public static void writeToSave(String fileName, String result, String tilesLeft, String bombs, String mapSize) {
		
	    try {
	        FileWriter myWriter = new FileWriter(fileName, true);
	        myWriter.write(String.format("\n Game Result: %s \n Tiles Remaing: %s \n Bombs in Play: %s \n Map Size: %s \n", result, tilesLeft, bombs, mapSize));
	        myWriter.close();
	        System.out.println("Successfully wrote to the file.");
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
		
	}
	
	public static void save(String result, String tilesLeft, String bombs, String mapSize) {
		String saveFileName = createSaveFile();
		
		if(saveFileName == null) {
			return;
		}
		
		writeToSave(saveFileName, result, tilesLeft, bombs, mapSize);
		

		
	}
}
