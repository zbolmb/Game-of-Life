/*Display class contains the methods that create a window that contains 
 * a GameOfLife grid with a start/stop button, a nextGen button and a clear board button
 * contains a Grid class that creates a grid using small rectangles. the size of the window is set
 * by the Display constructor
 * action listeners that repaint the grid (nextGen and clearBoard) are inside the grid class
 * along with mouse actions
 * Uses basic BorderLayout, grid centered with buttons at the bottom
 * 
 * JingJing Li
 * Period 6 Java
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Display {
	private GameOfLife board;
	private static int gridWidth;
	private int gridHeight;
	private int windowWidth;
	private int windowHeight;
	private Point2D.Double point;
	Timer timer; // for the start/stop button
	JButton startButton; //allows the background color to change to indicate if its running or not
	boolean running = false; //for start/stop button

	/*Display constructor receives two ints for width and height
	*sets an empty board, the window width and height and sets grid width and height to be 100 less than window size
	*/
	public Display(int windowWidth, int windowHeight){
		this.board = new GameOfLife(new int[30][30]);
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		gridWidth = windowWidth - 100;
		gridHeight = windowHeight - 100;
	}
	/*display method creates a JFrame and the buttons and grid are added to it
	 * the buttons are first added to a separate panel before being added to the frame
	 * button default color is white
	 */
	public void display() {  
		JButton nextGenButton;
		JButton clearButton	;
		Grid grid;

		final JFrame frame = new JFrame();  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setSize(windowHeight, windowWidth); 
		JPanel buttonPanel = new JPanel();

		grid = new Grid();
		grid.addMouseListener(grid); // changes space from filled to empty and vice versa when clicked 

		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!running){
					timer.start();
					startButton.setBackground(Color.GRAY); // gray to indicate that it is running
					running = true;
				}
				else if(running){
					timer.stop();  
					startButton.setBackground(Color.white); 
					running = false;
				}
			}
		}); 
		
		startButton.setBackground(Color.white);
		buttonPanel.add(startButton);

		nextGenButton = new JButton("Next Generation");
		Display.Grid.NextGenListener nextGenL = grid.new NextGenListener();
		nextGenButton.addActionListener(nextGenL);
		nextGenButton.setBackground(Color.white);
		buttonPanel.add(nextGenButton);

		clearButton = new JButton("Clear");
		Display.Grid.ClearListener clearL = grid.new ClearListener();
		clearButton.addActionListener(clearL);
		clearButton.setBackground(Color.white);
		buttonPanel.add(clearButton);
		
		frame.getContentPane().add(BorderLayout.CENTER, grid);
		frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);  

		frame.setVisible(true);  

		int DELAY = 100;
		timer = new Timer(DELAY, new ActionListener() {  
			public void actionPerformed(ActionEvent e){
				board.nextGen();
				frame.repaint();
			}
		});  
	}  
	
	/* Grid class contains instructions to create a grid of rectangles given gridHeight and width
	 * contains MouseListener that has method to change state of grid to filled or empty
	 * contains listeners for the nextGen and Clear buttons
	 */
	public class Grid extends JComponent implements MouseListener { 
		public void paintComponent(Graphics g){
			Graphics2D g2 = (Graphics2D) g;
			for(int x = 0; x < board.getNumRows(); x++){
				for(int y = 0; y < board.getNumCol(); y++){
					Rectangle2D.Double spot = new Rectangle2D.Double
							( (double)y/board.getNumCol()*gridHeight 
									, (double)x/board.getNumRows()*gridWidth
									, gridHeight/board.getNumRows()
									, gridWidth/board.getNumCol());
					g2.draw(spot);
					// if board does not contain 0 at that spot, fill the rectangle that corresponds to that spot
					if(board.isOccupied(x,y))
						g2.fill(new Rectangle2D.Double			
								( (double)y/board.getNumCol()*gridHeight 
										, (double)x/board.getNumRows()*gridWidth
										, gridHeight/board.getNumRows()
										, gridWidth/board.getNumCol()));
				}
			}
		}

		public void mouseClicked(MouseEvent arg0) {					
		}

		public void mouseEntered(MouseEvent arg0) {

		}

		public void mouseExited(MouseEvent arg0) {

		}

		public void mouseReleased(MouseEvent arg0) {

		}

		public void mousePressed(MouseEvent arg0) {
			point = new Point2D.Double(arg0.getX(),arg0.getY());
			int row = (int)(board.getNumRows()*((double)point.getY()/gridHeight));
			int col = (int)(board.getNumCol()*((double)point.getX()/gridWidth));
			if(point.getX() < gridWidth && point.getY() < gridHeight){
				board.changeState(row, col);
				repaint();
			}
		}
		// creates next generation of the board and repaints it
		class NextGenListener implements ActionListener{
			public void actionPerformed(ActionEvent event){
				board.nextGen();
				repaint();
			}
		}
		//clears the board and repaints
		class ClearListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				board.clear();
				repaint();
			}
		}
	}	
}
