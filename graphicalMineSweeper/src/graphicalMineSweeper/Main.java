package graphicalMineSweeper;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter the board width (<=10 >=2)");
		int gameBoardSize = s.nextInt();
		System.out.println("Please enter the number of Bombs (>=1 <=width^2)");
		int numberOfBombs = s.nextInt();

		
		boolean running = true;
		
		Grid gameGrid = new Grid(numberOfBombs, gameBoardSize);
		gameGrid.initialiseBombs();
		
		
		
		while(running) {
			gameGrid.render();
			
			System.out.println("Enter tile number");
			int input = s.nextInt();
			Cell selected;
			try {
			selected = gameGrid.getCell(input);
			}catch(Exception e) {
				System.out.println("please provide a valid input");
				continue;
			}
			
			boolean bomb = selected.getBomb();
			if(bomb) {
				System.out.println("BOOOM!");
				if(menuHandler(s).equals("r")) {
					gameGrid = new Grid(numberOfBombs, gameBoardSize);
					gameGrid.initialiseBombs();
					continue;
				}
				break;
				
			}else {
				gameGrid.decrementSafeSpacesLeft();
				gameGrid.showBombs(selected);
			}
			
			if(gameGrid.getSafeSpacesLeft() == 0) {
				System.out.println("You Won!!!");
				if(menuHandler(s).equals("r")) {
					gameGrid = new Grid(numberOfBombs, gameBoardSize);
					gameGrid.initialiseBombs();
					continue;
				}
				break;
				
			}
		}
		System.out.println("exited");
	}
	
	
	public static String menuHandler(Scanner s) {
		s.nextLine();// consumes unconsumed next line from the nextInts
		System.out.println("enter r to continue or any other key to exit");
		String menuInput = s.nextLine();
		System.out.println(menuInput);
		return menuInput;
	}

}
