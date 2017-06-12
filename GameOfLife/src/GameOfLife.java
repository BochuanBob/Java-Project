import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * This class represents the game board. It can draw a graphical rendering of
 * its current state and can calculate its next state.
 * 
 * @author lub, verlaqd. Created Sept. 28, 2015.
 */
public class GameOfLife extends JComponent {
	private static final int MAX_ROWS = 90;
	private static final int MAX_COLUMNS = 90;
	private static final double DOT_SIZE = 7;

	// make false to remove gray ghost dots
	private static boolean GHOSTING = true;

	/*
	 * Data is stored in a 2D int array
	 * 0 = dead
	 * 1 = alive
	 */

	public int[][] cellArray = new int[MAX_ROWS][MAX_COLUMNS];

	public int[][] oldCellArray = cellArray; // for ghost effect

	/**
	 * Constructs a new game board from the list of initially occupied
	 * coordinates. Takes the row and column values of each coordinate in the
	 * given ArrayList of initial cells and changes the corresponding cellArray
	 * entries to 1.
	 * 
	 * @param initialCells
	 */
	public GameOfLife(ArrayList<Coordinates> initialCells) {
		/*
		 * Get the x and y values for each coordinate; change the values of the
		 * corresponding entries in the 2D cellArray
		 */

		for (Coordinates curCoord : initialCells) {
			// wrap around in case some silly person puts in a coordinate
			// outside of the max bounds
			int pointX = curCoord.getColumn() % MAX_COLUMNS;
			int pointY = curCoord.getRow() % MAX_ROWS;

			this.cellArray[pointY][pointX] = 1;

		}

	}

	public int[][] returnCellArray() {
		return this.cellArray; // returns the cellArray, obviously
		//not actually used for anything
	}

	/**
	 * @param row
	 * @param column
	 * @return whether or not the square in the given row and column is occupied
	 */

	public boolean isOccupied(int row, int column) {
		/*
		 * Returns true if cell value is 1; returns false otherwise
		 */
		if (this.cellArray[row][column] == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Counts the number of occupied cells around the given square.
	 * 
	 * @param row
	 * @param column
	 * @return the number of occupied cells
	 */
	public int getNeighborCount(int row, int column) {
		/*
		 * Counts the number of living neighbor cells.
		 * Uses % to efficiently wrap around without if-then statements.
		 */
		int count = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (isOccupied((row + i + MAX_ROWS) % MAX_ROWS,
						(column + j + MAX_COLUMNS) % MAX_COLUMNS)) {
					count++;
				}
			}
		}
		// Subtract one if the cell itself is living, since it would have been
		// counted.
		if (this.isOccupied(row, column)) {
			return count - 1;
		}
		return count;
	}

	/**
	 * Updates the state of this game board for the next generation.
	 */
	public void nextGeneration() {

		/*
		 * Creates another int[][], initially zeroes. Fills the new int[][]
		 * based on the
		 * old one's calculations (using getNeighborCount). Sets this.cellArray
		 * to new int[][].
		 */
		this.oldCellArray = this.cellArray;
		int[][] newCellArray = new int[MAX_ROWS][MAX_COLUMNS];
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				if (this.isOccupied(i, j)) {
					if (getNeighborCount(i, j) >= 2
							&& getNeighborCount(i, j) < 4) {
						newCellArray[i][j] = 1;
					}
				} else {
					if (getNeighborCount(i, j) == 3) {
						newCellArray[i][j] = 1;
					}
				}
			}
		}
		this.cellArray = newCellArray;
	}

	@Override
	protected void paintComponent(Graphics g) {
		/*
		 * Paints the cells, according to the corresponding values in cellArray.
		 */
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				if (this.oldCellArray[i][j] == 1 && !this.isOccupied(i, j)
						&& GHOSTING) {

					Ellipse2D.Double cell = new Ellipse2D.Double(j * DOT_SIZE+1,
							i * DOT_SIZE+1, DOT_SIZE-2, DOT_SIZE-2);
					g2.setColor(Color.gray);
					g2.fill(cell);
				} else if (this.isOccupied(i, j)) {

					Rectangle2D.Double cell = new Rectangle.Double(
							j * DOT_SIZE, i * DOT_SIZE, DOT_SIZE, DOT_SIZE);
					g2.setColor(Color.red);
					g2.fill(cell);
				}

			}
		}

	}

	/**
	 * Prints the cellArray. Mostly for testing purposes, but also good for
	 * exporting configurations.
	 */
	public void printCellArray() {
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				System.out.printf("%5d ", this.cellArray[i][j]);
			}
			System.out.println();
		}
	}

}
