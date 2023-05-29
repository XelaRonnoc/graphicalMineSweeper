package graphicalMineSweeper;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Main extends JFrame {
	
	class Canvas extends JPanel implements MouseListener {
//	    Grid grid = new Grid(40, 20);
		GridSingleton grid = GridSingleton.getGrid();
		Menu menu = Menu.setMenu(true);


	    public Canvas() {
	    	setPreferredSize(new Dimension(720, 720));
	      	this.addMouseListener(this);
	      	grid.setScreenWidth(720);
	    }

		    @Override
		    public void paint(Graphics g) { 
		    	grid.paint(g, getMousePosition()); 
		    	if(!grid.getGameRunning()) {
		    		menu.paint(g, getMousePosition());
		    	}
		    }
	
			@Override
			public void mouseClicked(MouseEvent e) {

			}
	
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					grid.mouseClicked(e.getX(), e.getY());
					if(!menu.hasBegun() || !menu.getStart()) {
						menu.mouseClicked(e.getX(), e.getY());
					}
				}
				if(e.getButton() == MouseEvent.BUTTON3) {
					grid.mouseRightClicked(e.getX(), e.getY());
					System.out.println("right click");
				}
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

	    public void run() {
	    	

	    	Menu menu = Menu.setMenu(true);
	    	GridSingleton grid = GridSingleton.getGrid();
	    	
	    	while(true) {
	    		if(!menu.hasBegun()) {
		        try{
			        Thread.sleep(30);
			        }catch (InterruptedException e){
			        }
	    		repaint();
	    		continue;
	    		}
	    		
	    		
	    		switch(menu.getLevel()) {
	    			case EASY:
	    				grid.setupGrid(2, 10);
	    				break;
	    			case MEDIUM:
	    				grid.setupGrid(40, 16);
	    				break;
	    			case HARD:
	    				grid.setupGrid(99, 22);
	    				break;
	    			default:
	    				grid.setupGrid(10,10);
	    		}
	    		
	    		while(grid.getGameRunning()) {// Continuous loop while program is running
	    			try{
	    				Thread.sleep(30);
	    			}catch (InterruptedException e){
	    			}
	    			repaint();
	    		}
	    		if(!grid.getGameRunning()) {
	    			if(grid.getVictory()) {
	    				menu.setWon(true);
	    			}else {
	    				menu.setWon(false);
	    			}
	    			menu = Menu.setMenu(false);
	    		}

	    	}
	    }

}
