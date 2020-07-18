import java.util.Stack;
import java.util.ArrayList;

/**
 * The RecursivePropagation class recursively checks the effectiveness or the
 * ineffectiveness of the social distancing enforced in a Block viz-a-vis the
 * COVID-19 virus spread. It has the same functionality as the Propagation
 * class. It performs a cell by cell inspection of a block and see if the virus
 * is effectively contained or not in a recursive manner.
 * 
 * @author Liying Lu
 *
 */
public class RecursivePropagation {
	// public static int count = 0;

	/**
	 * This method determines if the area has an effective social distancing barrier
	 * in a recursive manner. The given row and column index are set as the starting
	 * position once if the indexes are valid entry and the starting position of the
	 * area is not set. The algorithm returns false if the given row and column
	 * indexes are of a valid exit. If not, the algorithm recursively try to move to
	 * one of the unvisited cells in either up, right, down, left direction. If the
	 * algorithm hits a wall and has no unvisited cells in neither direction, it
	 * backtracks to the first empty cell in either left, down, right, up direction.
	 * Note that backtracking chooses the direction in a reverse order of how it
	 * would advance. Finally, if the algorithm backtracks to the starting position,
	 * no path is found in the area. The algorithm then returns true because there
	 * is no path from an entry to an exit, indicating effective social distancing.
	 * 
	 * @param area Block to determine its effectiveness.
	 * @param row  Row index of the current row of the area.
	 * @param col  Column index of the current column of the area.
	 * @return True if no path is found and effective, otherwise false.
	 */
	public static boolean recursiveIsEffective(Block area, int row, int col) {

		// sets the starting position of the area
		if ((area.getStartRow() < 0 || area.getStartCol() < 0) && area.isEntry(row, col)) {
			// count = 0;
			area.setStart(row, col);
			// System.out.println("Starting position: " + area.getCurrentRow() + " " +
			// area.getCurrentCol());
		}

		// Step 1: If you are at an exit cell, you are done. If not move to step 2.
		if (area.isExit(row, col)) {
			// System.out.println("A path is found. Ineffective.");
			return false;
		}
		// If not at exit cell, try to move to another cell.
		else {
			if (area.isFree(row - 1, col) && !area.isVisited(row - 1, col)) { // try to move up
				area.moveUp();
			} else if (area.isFree(row, col + 1) && !area.isVisited(row, col + 1)) { // if cannot move up, try to move
																						// to the right
				area.moveRight();
			} else if (area.isFree(row + 1, col) && !area.isVisited(row + 1, col)) { // if cannot move up or right, try
																						// to move down
				area.moveDown();
			} else if (area.isFree(row, col - 1) && !area.isVisited(row, col - 1)) { // if cannot move up, right, or
																						// down, try to move to the left
				area.moveLeft();
			} else {

				// backtracks to the starting position, so no path is found, therefore
				// effective.
				if (area.getCurrentRow() == area.getStartRow() && area.getCurrentCol() == area.getStartCol())
					return true;

				// Backtrack to the first cell available to move into. The order of the
				// direction is reversed
				// as the above statements so as to prevent being stuck between two cells.
				if (area.isFree(row, col - 1)) {
					area.moveLeft();
				} else if (area.isFree(row + 1, col)) {
					area.moveDown();
				} else if (area.isFree(row, col + 1)) {
					area.moveRight();
				} else if (area.isFree(row - 1, col)) {
					area.moveUp();
				}

			} // end if-else loop
				// System.out.println(count + " Intermediate position: " + area.getCurrentRow()
				// + " " + area.getCurrentCol());
			return recursiveIsEffective(area, area.getCurrentRow(), area.getCurrentCol());
		} // end else

	}

	/**
	 * This method recursively calculates and returns the path from a given valid
	 * entry to an exit if there is one, otherwise returns null.
	 * 
	 * @param area Block to find a path in.
	 * @param row  Row index of the current position.
	 * @param col  Column index of the current position.
	 * @param path A stack of ArrayLists of the position (row, column) in the path.
	 * @return A path from the valid given entry to an exit, otherwise null.
	 */
	public static Stack<ArrayList<Integer>> recursivePathCalc(Block area, int row, int col,
			Stack<ArrayList<Integer>> path) {
		// count++;
		// sets the starting position of the area and the first position in the path
		if ((area.getStartRow() < 0 || area.getStartCol() < 0) && area.isEntry(row, col)) {
			// count = 0;
			area.setStart(row, col);
			updatePath(area, path);
			// System.out.println("Initial path: " + path);
		}

		// A path is found if the exit cell is reached.
		if (area.isExit(row, col)) {
			// System.out.println("A path is found.");
			// System.out.println("Final path:" + path.toString());
			return path;
		}

		// If not at exit cell, try to move to another cell.
		else {
			if (area.isFree(row - 1, col) && !area.isVisited(row - 1, col)) { // try to move up
				area.moveUp();
				updatePath(area, path);
			} else if (area.isFree(row, col + 1) && !area.isVisited(row, col + 1)) { // if cannot move up, try to move
																						// to the right
				area.moveRight();
				updatePath(area, path);
			} else if (area.isFree(row + 1, col) && !area.isVisited(row + 1, col)) { // if cannot move up or right, try
																						// to move down
				area.moveDown();
				updatePath(area, path);
			} else if (area.isFree(row, col - 1) && !area.isVisited(row, col - 1)) { // if cannot move up, right, or
																						// down, try to move to the left
				area.moveLeft();
				updatePath(area, path);
			} else {

				// backtracks to the starting position, so no path is found
				if (area.getCurrentRow() == area.getStartRow() && area.getCurrentCol() == area.getStartCol())
					return null;

				path.pop(); // remove current position

				// Backtrack to the first cell available to move into. The order of the
				// direction is reversed as the above statements so as to prevent being stuck
				// between two cells.
				if (area.isFree(row, col - 1)) {
					area.moveLeft();
				} else if (area.isFree(row + 1, col)) {
					area.moveDown();
				} else if (area.isFree(row, col + 1)) {
					area.moveRight();
				} else if (area.isFree(row - 1, col)) {
					area.moveUp();
				}

			} // end if-else loop
				// System.out.println(count + " Intermediate path: " + path);
				// call recursivePathCalc to calculate the next position.
			return recursivePathCalc(area, area.getCurrentRow(), area.getCurrentCol(), path);
		} // end else

	}

	/**
	 * This method serves as a helper method that updates the visited cells in the
	 * path.
	 * 
	 * @param area Block to be visited.
	 * @param path A stack of arrayLists of visited positions.
	 */
	private static void updatePath(Block area, Stack<ArrayList<Integer>> path) {
		int currentRow = area.getCurrentRow();
		int currentCol = area.getCurrentCol();
		ArrayList<Integer> currentPos = new ArrayList<Integer>();
		currentPos.add(currentRow);
		currentPos.add(currentCol);
		path.push(currentPos);
	}

	/*
	 * public static void main(String args[]) {
	 * 
	 * Cell[][] gP = { { new Cell(0, false), new Cell(1, false), new Cell(0, false),
	 * new Cell(1, false) }, { new Cell(0, false), new Cell(0, false), new Cell(1,
	 * false), new Cell(0, false) }, { new Cell(0, false), new Cell(0, false), new
	 * Cell(0, false), new Cell(1, false) }, { new Cell(1, false), new Cell(0,
	 * false), new Cell(0, false), new Cell(0, false) }, { new Cell(0, false), new
	 * Cell(1, false), new Cell(0, false), new Cell(1, false) }, { new Cell(1,
	 * false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } }; Block
	 * b = new Block(gP); Stack<ArrayList<Integer>> path = new
	 * Stack<ArrayList<Integer>>();
	 * System.out.println(RecursivePropagation.recursivePathCalc(b, 1, 0, path)); }
	 */

}
