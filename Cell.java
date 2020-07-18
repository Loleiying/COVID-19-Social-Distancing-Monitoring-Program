/**
 * A Cell is the smalles plot of land that we consider for this project. A Cell
 * has a status. A value of 0 indicates the cell is CLEAR and a value of 1
 * indicates the cell is MARKED. Moreover, a Cell can tell if it is visited or
 * not. All cells are labelled as not visited initailly. Define a class Cell
 * that has attributes status (int), visited (boolean). It also has the follwing
 * methods.
 * 
 * @author Liying Lu
 *
 */
public class Cell {
	protected int status;
	protected boolean visited;

	/**
	 * A constructor that takes in the status of the cell and a boolean value
	 * indicating whether the cell is visited before.
	 * 
	 * @param status  0 if CLEAR, 1 if MARKED
	 * @param visited True if visited before, false otherwise
	 */
	public Cell(int status, boolean visited) {
		this.status = status;
		this.visited = visited;
	}

	/**
	 * A constructor that takes in the status of the cell and sets it as not visited
	 * before.
	 * 
	 * @param status 0 if CLEAR, 1 if MARKED
	 */
	public Cell(int status) {
		this.status = status;
		this.visited = false;
	}

	/**
	 * A getter that returns the status of the cell.
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * This method checks if the cell is visited.
	 * @return the visited True if the cell is visited before, otherwise false.
	 */
	public boolean getVisited() {
		return visited;
	}

	/**
	 * A setter that sets the status of the cell to 0 as CLEAR, or 1 as MARKED.
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * A setter that sets if the cell is visited before. True if visted, otherwise false.
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
