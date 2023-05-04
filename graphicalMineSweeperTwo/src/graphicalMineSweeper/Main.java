package graphicalMineSweeper;

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


}
