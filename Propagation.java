import java.util.ArrayList;
import java.util.Stack;

/**
 * The Propagation task checks the effectiveness or the ineffectiveness of the
 * social distancing enforced in a Block viz-a-vis the COVID-19 virus spread.
 * This needs a cell by cell inspection of a block and see if the virus is
 * effectively contained or not.
 * 
 * @author Liying Lu
 *
 */
public class Propagation {

	/**
	 * Given a Block we need a method that determines if the social distancing
	 * enforced in a Block is effective or not.
	 * 
	 * @param area     A Block to inspect.
	 * @param startRow Starting row number of the Block.
	 * @param startCol Starting column number of the Block.
	 * @return True if the social distancing is effective in the Block, otherwise
	 *         false.
	 */
	public static boolean isEffective(Block area, int startRow, int startCol) {

		// check that there is not a path to get from the given entry to an exit
		if (pathCalc(area, startRow, startCol) == null)
			return true;
		else
			return false; // if there is a path found, the block is not effective

	}

	/**
	 * This method accepts a row and a column index as the starting position. If the
	 * starting position is a valid entry to the area, then checks if there exits a
	 * path from the entry to an exit. There may exit multiple paths between the
	 * given valid entry to one of the exits, and this method would only return one
	 * of the paths. The method returns an ArrayList of each cell's row number and
	 * column number along the path. If there is no path the method returns null.
	 * 
	 * @param area     Block to find the path.
	 * @param startRow Row index of the starting position
	 * @param startCol Column index of the starting position.
	 * @return Returns an ArrayList of each cell's row number and column number
	 *         along the path. Returns null if there is no path.
	 */
	public static Stack<ArrayList<Integer>> pathCalc(Block area, int startRow, int startCol) {
		// No path can be found if the entry is not valid
		if (!area.isEntry(startRow, startCol))
			return null;

		area.setStart(startRow, startCol); // sets the starting position of the area

		// Continue to find a path if the entry is valid.
		Stack<ArrayList<Integer>> path = new Stack<ArrayList<Integer>>(); // keeps track of the path taken

		updatePath(area, path); // add the starting position to the path.

		int currentRow = startRow;
		int currentCol = startCol;
		// int count = 0;
		// System.out.println(count + " Initial path: " + path.toString());
		// boolean pathFound = false;

		while (true) {

			// try one of the direction
			if (area.isFree(currentRow, currentCol - 1) && !area.isVisited(currentRow, currentCol - 1)) {
				area.moveLeft();
				updatePath(area, path);
			} else if (area.isFree(currentRow, currentCol + 1) && !area.isVisited(currentRow, currentCol + 1)) {
				area.moveRight();
				updatePath(area, path);
			} else if (area.isFree(currentRow - 1, currentCol) && !area.isVisited(currentRow - 1, currentCol)) {
				area.moveUp();
				updatePath(area, path);
			} else if (area.isFree(currentRow + 1, currentCol) && !area.isVisited(currentRow + 1, currentCol)) {
				area.moveDown();
				updatePath(area, path);
			} else { // if no other cells around the current cell is not visited.
				// Stopping condition: if no path found at this step, move back to the previous
				// position
				if (currentRow == startRow && currentCol == startCol) {
					// System.out.println("No path is found.");
					return null;
				}

				// backtracking
				// System.out.println("Backtracking");
				path.pop(); // remove the current position in path
				ArrayList<Integer> previousPos = path.peek();

				if (previousPos.get(1) + 1 == currentCol) {
					area.moveLeft();
				} else if (previousPos.get(1) - 1 == currentCol) {
					area.moveRight();
				} else if (previousPos.get(0) + 1 == currentRow) {
					area.moveUp();
				} else if (previousPos.get(0) - 1 == currentRow) {
					area.moveDown();
				}
			}

			// update the current row and column index 
			currentRow = area.getCurrentRow();
			currentCol = area.getCurrentCol();
			// check the path at each iteration.
			// System.out.println(++count + " Intermediate path after push: " +
			// path.toString());

			// stopping condition: if an exit is reached.
			if (area.isExit(area.getCurrentRow(), area.getCurrentCol())) {
				// System.out.println("A path is found.");
				// System.out.println("Final path:" + path.toString()); // check the returned
				// path.
				return path;
			}

		}

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
	 * false), new Cell(0, false) }, { new Cell(0, false), new Cell(1, false), new
	 * Cell(0, false), new Cell(1, false) }, { new Cell(1, false), new Cell(0,
	 * false), new Cell(0, false), new Cell(0, false) }, { new Cell(0, false), new
	 * Cell(1, false), new Cell(0, false), new Cell(1, false) }, { new Cell(1,
	 * false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } }; Block
	 * b = new Block(gP); Propagation.isEffective(b, 1, 0);
	 * 
	 * }
	 */

}
