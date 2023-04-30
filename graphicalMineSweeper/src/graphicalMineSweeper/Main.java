package graphicalMineSweeper;

import java.util.Scanner;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main extends JFrame {
	
	class Canvas extends JPanel implements MouseListener {
	      Grid grid = new Grid(40, 20);

	      public Canvas() {
	        setPreferredSize(new Dimension(720, 720));
	        this.addMouseListener(this);
	      }

	      @Override
	      public void paint(Graphics g) { 
	        grid.paint(g, getMousePosition()); 
	      }

		@Override
		public void mouseClicked(MouseEvent e) {
			grid.mouseClicked(e.getX(), e.getY());
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	    }

	    private Main() {
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      Canvas canvas = new Canvas();
	      this.setContentPane(canvas);
	      this.pack();
	      this.setVisible(true);
	    }

	    public static void main(String[] args) throws Exception {
	      Main window = new Main();
	      window.run();
	    }

	    public void run() { // continous loop while prgram is running
	      while(true) {
	        try{
	        Thread.sleep(20);
	        }catch (InterruptedException e){
	        }
	        repaint();
	      }
	    }

//	public static void main(String[] args) {
//		
//		Scanner s = new Scanner(System.in);
//		System.out.println("Please enter the board width (<=10 >=2)");
//		int gameBoardSize = s.nextInt();
//		System.out.println("Please enter the number of Bombs (>=1 <=width^2)");
//		int numberOfBombs = s.nextInt();
//
//		
//		boolean running = true;
//		
//		Grid gameGrid = new Grid(numberOfBombs, gameBoardSize);
//		gameGrid.initialiseBombs();
//		
//		
//		
//		while(running) {
//			gameGrid.render();
//			
//			System.out.println("Enter tile number");
//			int input = s.nextInt();
//			Cell selected;
//			try {
//			selected = gameGrid.getCell(input);
//			}catch(Exception e) {
//				System.out.println("please provide a valid input");
//				continue;
//			}
//			
//			boolean bomb = selected.getBomb();
//			if(bomb) {
//				System.out.println("BOOOM!");
//				if(menuHandler(s).equals("r")) {
//					gameGrid = new Grid(numberOfBombs, gameBoardSize);
//					gameGrid.initialiseBombs();
//					continue;
//				}
//				break;
//				
//			}else {
//				gameGrid.decrementSafeSpacesLeft();
//				gameGrid.showBombs(selected);
//			}
//			
//			if(gameGrid.getSafeSpacesLeft() == 0) {
//				System.out.println("You Won!!!");
//				if(menuHandler(s).equals("r")) {
//					gameGrid = new Grid(numberOfBombs, gameBoardSize);
//					gameGrid.initialiseBombs();
//					continue;
//				}
//				break;
//				
//			}
//		}
//		System.out.println("exited");
//	}
	
	
	public static String menuHandler(Scanner s) {
		s.nextLine();// consumes unconsumed next line from the nextInts
		System.out.println("enter r to continue or any other key to exit");
		String menuInput = s.nextLine();
		System.out.println(menuInput);
		return menuInput;
	}

}
