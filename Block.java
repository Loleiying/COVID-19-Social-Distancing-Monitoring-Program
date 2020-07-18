/**
 * A Block is an n x m cells of a rectangular grid (we call it grid). A Block
 * represents an area under consideration. Each cell within the block is
 * identified using row number and column number. In order to navigate through a
 * block, we have 4 integer attributes currentCol,currentRow,startCol,startRow.
 * These 4 attributes have an initial value of negative one (-1). A person can
 * initially enter the Block using only one of the cells at the left end of the
 * grid (i.e., cell[i][0] , where i is the row number that ranges from 0 to n-1
 * and exits the Block using one of the cells at the right end, i.e.,
 * cell[i][m-1] where i is the row number that ranges from 0 to n-1. The top
 * left corner is row 0, column 0. When a new Block is created we can assign
 * status values for each cell. Note that cell uses value 0 for for CLEAR and 1
 * for MARKED and true/false for Visited. For example, the effective distancing
 * block shown above can have the following initial status values for
 * CLEAR/MARKED: and false for Visited.
 * 
 * @author Liying Lu
 *
 */
public class Block {
	private Cell[][] grid;
	private int currentCol = -1;
	private int currentRow = -1;
	private int startCol = -1;
	private int startRow = -1;

	/**
	 * A constructor that creates a new block by taking in a two-dimensional array
	 * which represents the rectangular grid.
	 * 
	 * @param grid A n by m array of Cell
	 */
	public Block(Cell[][] grid) {
		this.grid = grid;
	}

	/**
	 * A getter that returns the Grid.
	 * 
	 * @return the grid
	 */
	public Cell[][] getGrid() {
		return grid;
	}

	/**
	 * A setter that sets the Grid.
	 * 
	 * @param grid the grid to set
	 */
	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}

	/**
	 * A method the determines if the given row number and column number of the cell
	 * are within the boundaries of the block.
	 * 
	 * @param row Row number to check.
	 * @param col Column number to check.
	 * @return True if the row and column number are valid, otherwise false;
	 */
	public boolean isValid(int row, int col) {
		// Checks that the row and column numbers are both greater or equals to zero
		// and row is less than n, column is less than m.
		return (row < grid.length) && (row >= 0) && (col < grid[0].length) && (col >= 0);
	}

	/**
	 * A getter that returns the status (CLEAR=0/MARKED=1) of a cell. If the cell is
	 * not valid it returns -1 .
	 * 
	 * @param row Row number of the cell.
	 * @param col Column number of the cell.
	 * @return CLEAR=0/MARKED=1/Invalid=-1
	 */
	public int getStatus(int row, int col) {
		// check that the cell is valid
		if (isValid(row, col)) {
			return grid[row][col].getStatus();
		} else
			return -1;
	}

	/**
	 * A getter that returns the visited value of a specific cell. If the cell is
	 * not valid it returns false.
	 * 
	 * @param row Row number of the cell
	 * @param col Column number of the cell
	 * @return Already visited=True / Not visited=False / Invalid cell=False
	 */
	public boolean isVisited(int row, int col) {
		// check that the cell is valid
		if (isValid(row, col)) {
			return grid[row][col].getVisited();
		} else
			return false;
	}

	/**
	 * A method that returns if a given cell is a proper entry cell. A person can
	 * initially enter the Block using only one of the cells at the left end of the
	 * grid (i.e., cell[i][0] , where i is the row number that ranges from 0 to n-1.
	 * 
	 * @param row Row number of the cell
	 * @param col Column number of the cell
	 * @return True if the cell is a proper entry cell, otherwise false;
	 */
	public boolean isEntry(int row, int col) {

		// check that 0 <= row <= n-1 and column = 0
		// also check that the cell is not visited and CLEAR
		return (row >= 0) && (row < grid.length) && (col == 0) && !isVisited(row, col) && getStatus(row, col) == 0;

	}

	/**
	 * A method that returns if a given cell is a proper exit cell. A person can
	 * exit the Block using one of the cells at the right end, i.e., cell[i][m-1]
	 * where i is the row number that ranges from 0 to n-1.
	 * 
	 * @param row Row number of the cell
	 * @param col Column number of the cell
	 * @return True if the cell is a proper exit cell, otherwise false.
	 */
	public boolean isExit(int row, int col) {
		// check that 0 <= row <= n-1, and column = m-1
		// also check that the cell is not visited and CLEAR
		return (row >= 0) && (row < grid.length) && (col == grid[0].length - 1) && !isVisited(row, col)
				&& getStatus(row, col) == 0;
	}

	/**
	 * A getter that returns the value of currentCol attribute.
	 * 
	 * @return the currentCol
	 */
	public int getCurrentCol() {
		return currentCol;
	}

	/**
	 * A getter that returns the value of currentRow attribute.
	 * 
	 * @return the currentRow
	 */
	public int getCurrentRow() {
		return currentRow;
	}

	/**
	 * A setter that sets the value of currentCol attribute.
	 * 
	 * @param currentCol the currentCol to set
	 */
	public void setCurrentCol(int currentCol) {
		this.currentCol = currentCol;
	}

	/**
	 * A setter that sets the values of currentRow attribute.
	 * 
	 * @param currentRow the currentRow to set
	 */
	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	/**
	 * A method that returns if a given cell is free to move to. If the status is
	 * CLEAR=0, then the cell is free to move in. This method also ensures that the
	 * given cell is valid.
	 * 
	 * @param row Row index of the cell.
	 * @param col Column index of the cell.
	 * @return True if the cell is valid and free to move into, otherwise false.
	 */
	public boolean isFree(int row, int col) {
		// check that the status is clear and the cell is valid.
		return getStatus(row, col) == 0 && isValid(row, col);
	}

	/**
	 * A setter that sets the startRow and startCol attributes of a block and
	 * updates the currentRow and currentCol attributes.
	 * 
	 * @param row Row index of the starting cell to set.
	 * @param col Column index of the starting cell to set.
	 */
	public void setStart(int row, int col) {
		// check that the row and column number is a valid entry point
		if (isEntry(row, col)) {
			startRow = row;
			startCol = col;
			currentRow = row;
			currentCol = col;

		}

	}

	/**
	 * A getter that returns the value of startCol attribute.
	 * 
	 * @return the startCol
	 */
	public int getStartCol() {
		return startCol;
	}

	/**
	 * A getter that returns the value of startRow attribute.
	 * 
	 * @return the startRow
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * This method moves left if the cell on the left is free to move in, marks the
	 * previous cell as visited and CLEAR, and updates its current position.
	 */
	public void moveLeft() {

		if (isFree(currentRow, currentCol - 1)) { // check the next cell is valid
			grid[currentRow][currentCol].setVisited(true); // sets the current cell as visited
			grid[currentRow][currentCol].setStatus(0); // sets the current cell is as CLEAR
			setCurrentCol(currentCol - 1); // move to the next cell by updating the column number;
		}
	}

	/**
	 * This method moves right if the cell on the right is free to move in, marks
	 * the previous cell as visited and CLEAR, and updates its current position.
	 */
	public void moveRight() {

		if (isFree(currentRow, currentCol + 1)) { // check the next cell is valid
			grid[currentRow][currentCol].setVisited(true); // sets the current cell as visited
			grid[currentRow][currentCol].setStatus(0); // sets the current cell is as CLEAR
			setCurrentCol(currentCol + 1); // move to the next cell by updating the column number;
		}
	}

	/**
	 * This method moves up if the cell above is free to move in, marks the previous
	 * cell as visited and CLEAR, and updates its current position.
	 */
	public void moveUp() {

		if (isFree(currentRow - 1, currentCol)) { // check the next cell is valid
			grid[currentRow][currentCol].setVisited(true); // sets the current cell as visited
			grid[currentRow][currentCol].setStatus(0); // sets the current cell is as CLEAR
			setCurrentRow(currentRow - 1); // move to the next cell by updating the column number;
		}
	}

	/**
	 * This method moves down if the cell below is free to move in, marks the
	 * previous cell as visited and CLEAR, and updates its current position.
	 */
	public void moveDown() {

		if (isFree(currentRow + 1, currentCol)) { // check the next cell is valid
			grid[currentRow][currentCol].setVisited(true); // sets the current cell as visited
			grid[currentRow][currentCol].setStatus(0); // sets the current cell is as CLEAR
			setCurrentRow(currentRow + 1); // move to the next cell by updating the column number;
		}
	}

	/*
	 * public static void main(String args[]) { Cell[][] gV = { { new Cell(0,
	 * false), new Cell(1, false), new Cell(0, true), new Cell(1, false) }, { new
	 * Cell(1, true), new Cell(0, false), new Cell(1, false), new Cell(0, false) },
	 * { new Cell(0, false), new Cell(1, true), new Cell(0, false), new Cell(1,
	 * false) }, { new Cell(1, false), new Cell(0, false), new Cell(0, false), new
	 * Cell(0, false) }, { new Cell(0, false), new Cell(1, false), new Cell(0,
	 * false), new Cell(1, false) }, { new Cell(1, false), new Cell(0, true), new
	 * Cell(1, false), new Cell(0, false) } };
	 * 
	 * Block b = new Block(gV); System.out.println(b.isEntry(1, 0)); }
	 */
}
