
/** 
 * On Mac/Linux:
 *  javac -cp .:junit-cs211.jar *.java         # compile everything
 *  java -cp .:junit-cs211.jar P5Tester        # run tests
 * 
 * On windows replace colons with semicolons: (: with ;)
 *  javac -cp .;junit-cs211.jar *.java         # compile everything
 *  java -cp .;junit-cs211.jar P5Tester        # run tests
 */
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class P5Tester {
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("P5Tester");
	}

	private final double ERR = 0.0001;

	// **********************************Test for Cell
	// Class**************************

	@Test(timeout = 1000)
	public void cell_construct_def_01() {
		Cell g = new Cell(1);
		assertEquals("Constructor Cell() is incorrect", 1, g.getStatus());
		assertEquals("Constructor Cell() is incorrect", false, g.getVisited());
	}

	@Test(timeout = 1000)
	public void cell_construct_def_02() {
		Cell g = new Cell(0, false);
		assertEquals("Constructor Cell() is incorrect", 0, g.getStatus());
		assertEquals("Constructor Cell() is incorrect", false, g.getVisited());
	}

	boolean[] dataVisited = { true, false, false, true };

	void cell_CheckSetGetVisited(int a) {
		Cell g = new Cell(0, false);
		g.setVisited(dataVisited[a]);
		String errMsg1 = String.format("Cell get/setVisited (%b)", dataVisited[a]);
		assertEquals(errMsg1, dataVisited[a], g.getVisited());

	}

	@Test(timeout = 1000)
	public void cell_CheckSetGetVisited_00() {
		cell_CheckSetGetVisited(0);
	}

	@Test(timeout = 1000)
	public void cell_CheckSetGetVisited_01() {
		cell_CheckSetGetVisited(1);
	}

	@Test(timeout = 1000)
	public void cell_CheckSetGetVisited_02() {
		cell_CheckSetGetVisited(2);
	}

	@Test(timeout = 1000)
	public void cell_CheckSetGetVisited_03() {
		cell_CheckSetGetVisited(3);
	}

	int[] dataStatus = { 1, 1, 0, 1 };

	void cell_CheckSetGetStatus(int a) {
		Cell g = new Cell(0, false);
		g.setStatus(dataStatus[a]);
		String errMsg1 = String.format("Cell get/setVisited (%d)", dataStatus[a]);
		assertEquals(errMsg1, dataStatus[a], g.getStatus());

	}

	@Test(timeout = 1000)
	public void cell_CheckSetGetStatus_00() {
		cell_CheckSetGetStatus(0);
	}

	@Test(timeout = 1000)
	public void cell_CheckSetGetStatus_01() {
		cell_CheckSetGetStatus(1);
	}

	@Test(timeout = 1000)
	public void cell_CheckSetGetStatus_02() {
		cell_CheckSetGetStatus(2);
	}

	@Test(timeout = 1000)
	public void cell_CheckSetGetStatus_03() {
		cell_CheckSetGetStatus(3);
	}

	// *********************************************TEst for Block
	// Class*************************

	@Test(timeout = 1000)
	public void block_construct_def_01() {
		Cell[][] g1 = { { new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
				{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) },
				{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
				{ new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false) },
				{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
				{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } };
		Block b = new Block(g1);
		assertEquals("Constructor Block() is incorrect", -1, b.getCurrentRow());
		assertEquals("Constructor Block() is incorrect", -1, b.getCurrentCol());
		assertEquals("Constructor Block() is incorrect", -1, b.getStartRow());
		assertEquals("Constructor Block() is incorrect", -1, b.getStartCol());
	}

	@Test(timeout = 1000)
	public void Block_CheckSetGetGrid() {
		Cell[][] g1 = { { new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) } };
		Cell[][] g2 = { { new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) },
				{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
				{ new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false) },
				{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
				{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } };
		Block b = new Block(g1);
		b.setGrid(g2);
		String errMsg1 = String.format("Block get/setGrid () is incorrect");
		assertEquals(errMsg1, g2, b.getGrid());

	}

	Cell[][] g = { { new Cell(0, false), new Cell(1, false), new Cell(0, true), new Cell(1, false) },
			{ new Cell(1, true), new Cell(0, false), new Cell(1, false), new Cell(0, false) },
			{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false) },
			{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } };
	int[] row = { 1, 3, 0, 5 };
	int[] col = { 0, 3, 2, 2 };
	int result[] = { 1, 0, 0, 1 };

	void block_CheckSetGetStatus(int a) {
		Block b = new Block(g);
		String errMsg1 = String.format("Block getStatus (%d,%d) is incorrect", row[a], col[a]);
		assertEquals(errMsg1, result[a], b.getStatus(row[a], col[a]));

	}

	@Test(timeout = 1000)
	public void block_CheckSetGetStatus_00() {
		block_CheckSetGetStatus(0);
	}

	@Test(timeout = 1000)
	public void block_CheckSetGetStatus_01() {
		block_CheckSetGetStatus(1);
	}

	@Test(timeout = 1000)
	public void block_CheckSetGetStatus_02() {
		block_CheckSetGetStatus(2);
	}

	@Test(timeout = 1000)
	public void block_CheckSetGetStatus_03() {
		block_CheckSetGetStatus(3);
	}

	int[] rowValid = { 1, 3, 0, 7, 5 };
	int[] colValid = { 0, 9, -2, 2, 2 };
	boolean resultValid[] = { true, false, false, false, true };

	void block_CheckIsValid(int a) {
		Block b = new Block(gV);
		String errMsg1 = String.format("Block isValid (%d,%d) is incorrect", rowValid[a], colValid[a]);
		assertEquals(errMsg1, resultValid[a], b.isValid(rowValid[a], colValid[a]));

	}

	@Test(timeout = 1000)
	public void block_CheckIsValid_00() {
		block_CheckIsValid(0);
	}

	@Test(timeout = 1000)
	public void block_CheckIsValid_01() {
		block_CheckIsValid(1);
	}

	@Test(timeout = 1000)
	public void block_CheckIsValid_02() {
		block_CheckIsValid(2);
	}

	@Test(timeout = 1000)
	public void block_CheckIsValid_03() {
		block_CheckIsValid(3);
	}

	@Test(timeout = 1000)
	public void block_CheckIsValid_04() {
		block_CheckIsValid(4);
	}

	int[] rowVisited = { 1, 3, 5, 5, 2 };
	int[] colVisited = { 0, 9, 1, 2, 1 };
	boolean resultVisited[] = { true, false, true, false, true };

	Cell[][] gV = { { new Cell(0, false), new Cell(1, false), new Cell(0, true), new Cell(1, false) },
			{ new Cell(1, true), new Cell(0, false), new Cell(1, false), new Cell(0, false) },
			{ new Cell(0, false), new Cell(1, true), new Cell(0, false), new Cell(1, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false) },
			{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
			{ new Cell(1, false), new Cell(0, true), new Cell(1, false), new Cell(0, false) } };

	void block_CheckIsVisited(int a) {
		Block b = new Block(gV);
		String errMsg1 = String.format("Block isVisited (%d,%d) is incorrect", rowVisited[a], colVisited[a]);
		assertEquals(errMsg1, resultVisited[a], b.isVisited(rowVisited[a], colVisited[a]));

	}

	@Test(timeout = 1000)
	public void block_CheckIsVisited_00() {
		block_CheckIsVisited(0);
	}

	@Test(timeout = 1000)
	public void block_CheckIsVisited_01() {
		block_CheckIsVisited(1);
	}

	@Test(timeout = 1000)
	public void block_CheckIsVisited_02() {
		block_CheckIsVisited(2);
	}

	@Test(timeout = 1000)
	public void block_CheckIsVisited_03() {
		block_CheckIsVisited(3);
	}

	@Test(timeout = 1000)
	public void block_CheckIsVisited_04() {
		block_CheckIsVisited(4);
	}

	int[] rowExit = { 1, 3, 5, 4, 3 };
	int[] colExit = { 0, 9, 3, 3, 3 };
	boolean resultExit[] = { false, false, true, false, true };

	void block_CheckIsExit(int a) {
		Block b = new Block(gV);
		String errMsg1 = String.format("Block isExit (%d,%d) is incorrect", rowExit[a], colExit[a]);
		assertEquals(errMsg1, resultExit[a], b.isExit(rowExit[a], colExit[a]));

	}

	@Test(timeout = 1000)
	public void block_CheckIsExit_00() {
		block_CheckIsExit(0);
	}

	@Test(timeout = 1000)
	public void block_CheckIsExit_01() {
		block_CheckIsExit(1);
	}

	@Test(timeout = 1000)
	public void block_CheckIsExit_02() {
		block_CheckIsExit(2);
	}

	@Test(timeout = 1000)
	public void block_CheckIsExit_03() {
		block_CheckIsExit(3);
	}

	@Test(timeout = 1000)
	public void block_CheckIsExit_04() {
		block_CheckIsExit(4);
	}

	int[] rowEntry = { 1, 3, 5, 4, 4 };
	int[] colEntry = { 0, 9, 0, 3, 0 };
	boolean resultEntry[] = { false, false, false, false, true };

	void block_CheckIsEntry(int a) {
		Block b = new Block(gV);
		String errMsg1 = String.format("Block isEntry (%d,%d) is incorrect", rowEntry[a], colEntry[a]);
		assertEquals(errMsg1, resultEntry[a], b.isEntry(rowEntry[a], colEntry[a]));

	}

	@Test(timeout = 1000)
	public void block_CheckIsEntry_00() {
		block_CheckIsEntry(0);
	}

	@Test(timeout = 1000)
	public void block_CheckIsEntry_01() {
		block_CheckIsEntry(1);
	}

	@Test(timeout = 1000)
	public void block_CheckIsEntry_02() {
		block_CheckIsEntry(2);
	}

	@Test(timeout = 1000)
	public void block_CheckIsEntry_03() {
		block_CheckIsEntry(3);
	}

	@Test(timeout = 1000)
	public void block_CheckIsEntry_04() {
		block_CheckIsEntry(4);
	}

	int[] rowFree = { 1, 3, 5, 2, 4 };
	int[] colFree = { 0, 9, 0, 2, 0 };
	boolean resultFree[] = { false, false, false, true, true };

	void block_CheckIsFree(int a) {
		Block b = new Block(gV);
		String errMsg1 = String.format("Block isFree (%d,%d) is incorrect", rowFree[a], colFree[a]);
		assertEquals(errMsg1, resultFree[a], b.isFree(rowFree[a], colFree[a]));

	}

	@Test(timeout = 1000)
	public void block_CheckIsFree_00() {
		block_CheckIsFree(0);
	}

	@Test(timeout = 1000)
	public void block_CheckIsFree_01() {
		block_CheckIsFree(1);
	}

	@Test(timeout = 1000)
	public void block_CheckIsFree_02() {
		block_CheckIsFree(2);
	}

	@Test(timeout = 1000)
	public void block_CheckIsFree_03() {
		block_CheckIsFree(3);
	}

	@Test(timeout = 1000)
	public void block_CheckIsFree_04() {
		block_CheckIsFree(4);
	}

	int[] rowStart = { 1, 3, 5, 2, 4 };
	int[] colStart = { 0, 9, 0, 0, 0 };
	int resultStartR[] = { -1, -1, -1, 2, 4 };
	int resultStartC[] = { -1, -1, -1, 0, 0 };

	void block_CheckSetStart(int a) {
		Block b = new Block(gV);
		b.setStart(rowStart[a], colStart[a]);
		String errMsg1 = String.format("Block setStart (%d,%d) is incorrect", rowStart[a], colStart[a]);
		assertEquals(errMsg1, resultStartR[a], b.getStartRow());
		assertEquals(errMsg1, resultStartC[a], b.getStartCol());
		assertEquals(errMsg1, resultStartR[a], b.getCurrentRow());
		assertEquals(errMsg1, resultStartC[a], b.getCurrentCol());

	}

	@Test(timeout = 1000)
	public void block_CheckSetStart_00() {
		block_CheckSetStart(0);
	}

	@Test(timeout = 1000)
	public void block_CheckSetStart_01() {
		block_CheckSetStart(1);
	}

	@Test(timeout = 1000)
	public void block_CheckSetStart_02() {
		block_CheckSetStart(2);
	}

	@Test(timeout = 1000)
	public void block_CheckSetStart_03() {
		block_CheckSetStart(3);
	}

	@Test(timeout = 1000)
	public void block_CheckSetStart_04() {
		block_CheckSetStart(4);
	}

	Cell[][] gM = { { new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) },
			{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false) },
			{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } };
	int[] rowStartM = { 2, 3, 5, 5, 4 };
	int[] colStartM = { 0, 2, 3, 1, 3 };
	int resultMLR[] = { 2, 3, 5, 5, 4 };
	int resultMLC[] = { 0, 1, 3, 1, 2 };
	boolean resultM[] = { false, true, false, false, true };

	void block_CheckMoveLeft(int a) {
		Block b = new Block(gM);
		b.setCurrentRow(rowStartM[a]);
		b.setCurrentCol(colStartM[a]);
		b.moveLeft();
		String errMsg1 = String.format("Block MoveLeft() is incorrect");
		assertEquals(errMsg1, resultMLR[a], b.getCurrentRow());
		assertEquals(errMsg1, resultMLC[a], b.getCurrentCol());
		assertEquals(errMsg1, resultMLR[a], b.getCurrentRow());
		assertEquals(errMsg1, resultMLC[a], b.getCurrentCol());
		assertEquals(errMsg1, resultM[a], b.isVisited(b.getCurrentRow(), b.getCurrentCol() + 1));

	}

	@Test(timeout = 1000)
	public void block_CheckMoveLeft_00() {
		block_CheckMoveLeft(0);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveLeft_01() {
		block_CheckMoveLeft(1);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveLeft_02() {
		block_CheckMoveLeft(2);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveLeft_03() {
		block_CheckMoveLeft(3);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveLeft_04() {
		block_CheckMoveLeft(4);
	}

	Cell[][] gR = { { new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) },
			{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(0, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false) },
			{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } };
	int[] rowStartMR = { 2, 3, 5, 2, 4 };
	int[] colStartMR = { 0, 2, 3, 2, 3 };
	int resultMRR[] = { 2, 3, 5, 2, 4 };
	int resultMRC[] = { 0, 3, 3, 3, 3 };
	boolean resultMR[] = { false, true, false, true, false };

	void block_CheckMoveRight(int a) {
		Block b = new Block(gR);
		b.setCurrentRow(rowStartMR[a]);
		b.setCurrentCol(colStartMR[a]);
		b.moveRight();
		String errMsg1 = String.format("Block MoveRight() is incorrect");
		assertEquals(errMsg1, resultMRR[a], b.getCurrentRow());
		assertEquals(errMsg1, resultMRC[a], b.getCurrentCol());
		assertEquals(errMsg1, resultMRR[a], b.getCurrentRow());
		assertEquals(errMsg1, resultMRC[a], b.getCurrentCol());
		assertEquals(errMsg1, resultMR[a], b.isVisited(b.getCurrentRow(), b.getCurrentCol() - 1));

	}

	@Test(timeout = 1000)
	public void block_CheckMoveRight_00() {
		block_CheckMoveRight(0);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveRight_01() {
		block_CheckMoveRight(1);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveRight_02() {
		block_CheckMoveRight(2);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveRight_03() {
		block_CheckMoveRight(3);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveRight_04() {
		block_CheckMoveRight(4);
	}

	Cell[][] gD = { { new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) },
			{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(0, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false) },
			{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } };
	int[] rowStartMD = { 2, 3, 5, 2, 4 };
	int[] colStartMD = { 0, 2, 3, 2, 3 };
	int resultMDR[] = { 2, 4, 5, 3, 5 };
	int resultMDC[] = { 0, 2, 3, 2, 3 };
	boolean resultMD[] = { false, true, false, true, true };

	void block_CheckMoveDown(int a) {
		Block b = new Block(gD);
		b.setCurrentRow(rowStartMD[a]);
		b.setCurrentCol(colStartMD[a]);
		b.moveDown();
		String errMsg1 = String.format("Block MoveDown() is incorrect");
		assertEquals(errMsg1, resultMDR[a], b.getCurrentRow());
		assertEquals(errMsg1, resultMDC[a], b.getCurrentCol());
		assertEquals(errMsg1, resultMDR[a], b.getCurrentRow());
		assertEquals(errMsg1, resultMDC[a], b.getCurrentCol());
		assertEquals(errMsg1, resultMD[a], b.isVisited(b.getCurrentRow() - 1, b.getCurrentCol()));

	}

	@Test(timeout = 1000)
	public void block_CheckMoveDown_00() {
		block_CheckMoveDown(0);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveDown_01() {
		block_CheckMoveDown(1);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveDown_02() {
		block_CheckMoveDown(2);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveDown_03() {
		block_CheckMoveDown(3);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveDown_04() {
		block_CheckMoveDown(4);
	}

	Cell[][] gU = { { new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false) },
			{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(0, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false) },
			{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
			{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } };
	int[] rowStartMU = { 2, 3, 5, 2, 0 };
	int[] colStartMU = { 0, 2, 3, 2, 0 };
	int resultMUR[] = { 2, 2, 5, 1, 0 };
	int resultMUC[] = { 0, 2, 3, 2, 0 };
	boolean resultMU[] = { false, true, false, true, false };

	void block_CheckMoveUp(int a) {
		Block b = new Block(gU);
		b.setCurrentRow(rowStartMU[a]);
		b.setCurrentCol(colStartMU[a]);
		b.moveUp();
		String errMsg1 = String.format("Block MoveUp() is incorrect");
		assertEquals(errMsg1, resultMUR[a], b.getCurrentRow());
		assertEquals(errMsg1, resultMUC[a], b.getCurrentCol());
		assertEquals(errMsg1, resultMUR[a], b.getCurrentRow());
		assertEquals(errMsg1, resultMUC[a], b.getCurrentCol());
		assertEquals(errMsg1, resultMU[a], b.isVisited(b.getCurrentRow() + 1, b.getCurrentCol()));

	}

	@Test(timeout = 1000)
	public void block_CheckMoveUp_00() {
		block_CheckMoveUp(0);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveUp_01() {
		block_CheckMoveUp(1);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveUp_02() {
		block_CheckMoveUp(2);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveUp_03() {
		block_CheckMoveUp(3);
	}

	@Test(timeout = 1000)
	public void block_CheckMoveUp_04() {
		block_CheckMoveUp(4);
	}

//****************************************Propagation Class Tests*************************

	private static boolean isSameStack(Stack<ArrayList<Integer>> stack1, Stack<ArrayList<Integer>> stack2) {
		boolean flag = true;
		if (stack1 == null && stack2 == null)
			return flag;
		else if ((stack1 == null && stack2 != null) || (stack1 != null && stack2 == null)) {
			flag = false;
			return flag;
		}
		// Check if size of both stacks are same
		else if (stack1.size() != stack2.size()) {
			flag = false;
			return flag;
		}

		// compare top of both stacks
		while (!stack1.empty()) {
			// If the top elements of both stacks re same
			if (stack1.peek().equals(stack2.peek())) {
				// Pop top of both stacks
				stack1.pop();
				stack2.pop();
			} else {
				// Otherwise, set flag to false
				flag = false;
				break;
			}
		}

		// Return flag
		return flag;
	}

	Stack<ArrayList<Integer>> s1 = new Stack<>();
	Stack<ArrayList<Integer>> s2 = new Stack<>();

	private void loadStack() {
		int num1[] = { 1, 1, 1, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 3, 4, 4, 4, 5, 5 };
		int num2[] = { 0, 1, 2, 2, 3, 4, 4, 5, 6, 6, 7, 8, 9, 9, 9, 10, 11, 11, 12 };

		int num3[] = { 1, 1, 2, 2, 3, 3 };
		int num4[] = { 0, 1, 1, 2, 2, 3 };
		ArrayList<Integer> aList1 = null;
		ArrayList<Integer> aList2 = null;
		for (int i = 0; i < num1.length; i++) {
			for (int j = i; j <= i; j++) {
				aList1 = new ArrayList<>();
				aList1.add(num1[j]);
				aList1.add(num2[j]);
				s2.push(aList1);
			}
			aList1 = null;
		}
		for (int i = 0; i < num3.length; i++) {
			for (int j = i; j <= i; j++) {
				aList2 = new ArrayList<>();
				aList2.add(num3[j]);
				aList2.add(num4[j]);
				s1.push(aList2);
			}
			aList2 = null;
		}
	}

	Cell[][][] gP = {
			{ { new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(0, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) },
					{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false) },
					{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } },

			{ { new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(0, false), new Cell(1, false), new Cell(1, false), new Cell(0, false) },
					{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) },
					{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } },

			{ { new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(0, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) },
					{ new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(0, false) },
					{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } },

			{ { new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
					new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
					new Cell(1, false), new Cell(1, false), new Cell(1, false) },
					{ new Cell(0, false), new Cell(0, false), new Cell(0, false), new Cell(1, false),
							new Cell(0, false), new Cell(0, false), new Cell(0, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(0, false),
							new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(0, false),
							new Cell(0, false), new Cell(0, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(1, false),
							new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(0, false),
							new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false),
							new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(0, false), new Cell(1, false), new Cell(1, false), new Cell(0, false),
							new Cell(0, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(1, false) } },

			{ { new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
					new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
					new Cell(1, false), new Cell(1, false), new Cell(1, false) },
					{ new Cell(0, false), new Cell(0, false), new Cell(0, false), new Cell(1, false),
							new Cell(0, false), new Cell(0, false), new Cell(0, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(0, false),
							new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(0, false),
							new Cell(0, false), new Cell(0, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(1, false),
							new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(0, false),
							new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false),
							new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(0, false), new Cell(0, false), new Cell(0, false), new Cell(0, false),
							new Cell(0, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(1, false) } } };
	boolean[] resultP = { true, true, false, false, true };

	void propagation_CheckIsEffective(int a) {
		Block b = new Block(gP[a]);
		String errMsg1 = String.format("Propagation IsEffective() is incorrect");
		assertEquals(errMsg1, resultP[a], Propagation.isEffective(b, 1, 0));

	}

	@Test(timeout = 1000)
	public void propagation_CheckIsEffective_00() {
		propagation_CheckIsEffective(0);
	}

	@Test(timeout = 1000)
	public void propagation_CheckIsEffective_01() {
		propagation_CheckIsEffective(1);
	}

	@Test(timeout = 1000)
	public void propagation_CheckIsEffective_02() {
		propagation_CheckIsEffective(2);
	}

	@Test(timeout = 1000)
	public void propagation_CheckIsEffective_03() {
		propagation_CheckIsEffective(3);
	}

	@Test(timeout = 1000)
	public void propagation_CheckIsEffective_04() {
		propagation_CheckIsEffective(4);
	}

	@Test(timeout = 1000)
	public void propagation_CheckPathCalc_00() {
		Block b = new Block(gP[0]);
		loadStack();
		Stack<ArrayList<Integer>> stack;
		stack = Propagation.pathCalc(b, 1, 0);
		String errMsg1 = String.format("Propagation pathCalc() is incorrect");
		assertEquals(errMsg1, true, isSameStack(null, stack));

	}

	@Test(timeout = 1000)
	public void propagation_CheckPathCalc_01() {
		Block b = new Block(gP[1]);
		loadStack();
		Stack<ArrayList<Integer>> stack;
		stack = Propagation.pathCalc(b, 1, 0);
		String errMsg1 = String.format("Propagation pathCalc() is incorrect");
		assertEquals(errMsg1, true, isSameStack(null, stack));

	}

	@Test(timeout = 1000)
	public void propagation_CheckPathCalc_02() {
		Block b = new Block(gP[2]);
		loadStack();
		Stack<ArrayList<Integer>> stack;
		stack = Propagation.pathCalc(b, 1, 0);
		String errMsg1 = String.format("Propagation pathCalc() is incorrect");
		assertEquals(errMsg1, true, isSameStack(s1, stack));

	}

	@Test(timeout = 1000)
	public void propagation_CheckPathCalc_03() {
		Block b = new Block(gP[3]);
		loadStack();
		Stack<ArrayList<Integer>> stack;
		stack = Propagation.pathCalc(b, 1, 0);
		String errMsg1 = String.format("Propagation pathCalc() is incorrect");
		assertEquals(errMsg1, true, isSameStack(s2, stack));

	}

	@Test(timeout = 1000)
	public void propagation_CheckPathCalc_04() {
		Block b = new Block(gP[4]);
		loadStack();
		Stack<ArrayList<Integer>> stack;
		stack = Propagation.pathCalc(b, 1, 0);
		String errMsg1 = String.format("Propagation pathCalc() is incorrect");
		assertEquals(errMsg1, true, isSameStack(null, stack));

	}

//**********************************RecursivePropagation Class Tests**********************

	Cell[][][] gRP = {
			{ { new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(0, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) },
					{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false) },
					{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } },

			{ { new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(0, false), new Cell(1, false), new Cell(1, false), new Cell(0, false) },
					{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) },
					{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } },

			{ { new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(0, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) },
					{ new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(0, false) },
					{ new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(1, false) },
					{ new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false) } },

			{ { new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
					new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
					new Cell(1, false), new Cell(1, false), new Cell(1, false) },
					{ new Cell(0, false), new Cell(0, false), new Cell(0, false), new Cell(1, false),
							new Cell(0, false), new Cell(0, false), new Cell(0, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(0, false),
							new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(0, false),
							new Cell(0, false), new Cell(0, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(1, false),
							new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(0, false),
							new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false),
							new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(0, false), new Cell(1, false), new Cell(1, false), new Cell(0, false),
							new Cell(0, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(1, false) } },

			{ { new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
					new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
					new Cell(1, false), new Cell(1, false), new Cell(1, false) },
					{ new Cell(0, false), new Cell(0, false), new Cell(0, false), new Cell(1, false),
							new Cell(0, false), new Cell(0, false), new Cell(0, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(0, false),
							new Cell(0, false), new Cell(1, false), new Cell(0, false), new Cell(0, false),
							new Cell(0, false), new Cell(0, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(1, false),
							new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false), new Cell(0, false), new Cell(1, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(0, false),
							new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false),
							new Cell(1, false), new Cell(0, false), new Cell(0, false), new Cell(0, false),
							new Cell(1, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(0, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(0, false), new Cell(0, false), new Cell(0, false), new Cell(0, false),
							new Cell(0, false) },
					{ new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(1, false), new Cell(1, false), new Cell(1, false), new Cell(1, false),
							new Cell(1, false) } } };

	boolean[] resultRP = { true, true, false, false, true };

	void recursivePropagation_CheckIsEffective(int a) {
		Block b = new Block(gRP[a]);
		String errMsg1 = String.format("RecursivePropagation recursiveIsEffective() is incorrect");
		assertEquals(errMsg1, resultRP[a], RecursivePropagation.recursiveIsEffective(b, 1, 0));

	}

	@Test(timeout = 1000)
	public void recursivePropagation_CheckIsEffective_00() {
		recursivePropagation_CheckIsEffective(0);
	}

	@Test(timeout = 1000)
	public void recursivePropagation_CheckIsEffective_01() {
		recursivePropagation_CheckIsEffective(1);
	}

	@Test(timeout = 1000)
	public void recursivePropagation_CheckIsEffective_02() {
		recursivePropagation_CheckIsEffective(2);
	}

	@Test(timeout = 1000)
	public void recursivePropagation_CheckIsEffective_03() {
		recursivePropagation_CheckIsEffective(3);
	}

	@Test(timeout = 1000)
	public void recursivePropagation_CheckIsEffective_04() {
		recursivePropagation_CheckIsEffective(4);
	}

	@Test(timeout = 1000)
	public void recursivePropagation_CheckPathCalc_00() {
		Block b = new Block(gP[0]);
		loadStack();
		Stack<ArrayList<Integer>> stack = new Stack<>();
		stack = RecursivePropagation.recursivePathCalc(b, 1, 0, stack);
		String errMsg1 = String.format("RecursivePropagation recursivePathCalc() is incorrect");
		assertEquals(errMsg1, true, isSameStack(null, stack));

	}

	@Test(timeout = 1000)
	public void recursivePropagation_CheckRecursivePathCalc_01() {
		Block b = new Block(gP[1]);
		loadStack();
		Stack<ArrayList<Integer>> stack = new Stack<>();
		stack = RecursivePropagation.recursivePathCalc(b, 1, 0, stack);
		String errMsg1 = String.format("RecursivePropagation recursivePathCalc() is incorrect");
		assertEquals(errMsg1, true, isSameStack(null, stack));

	}

	@Test(timeout = 1000)
	public void recursivePropagation_CheckRecursivePathCalc_02() {
		Block b = new Block(gP[2]);
		loadStack();
		Stack<ArrayList<Integer>> stack = new Stack<>();
		stack = RecursivePropagation.recursivePathCalc(b, 1, 0, stack);
		String errMsg1 = String.format("RecursivePropagation recursivePathCalc() is incorrect");
		assertEquals(errMsg1, true, isSameStack(s1, stack));

	}

	@Test(timeout = 1000)
	public void recursivePropagation_CheckRecursivePathCalc_03() {
		Block b = new Block(gP[3]);
		loadStack();
		Stack<ArrayList<Integer>> stack = new Stack<>();
		stack = RecursivePropagation.recursivePathCalc(b, 1, 0, stack);
		String errMsg1 = String.format("RecursivePropagation recursivePathCalc() is incorrect");
		assertEquals(errMsg1, true, isSameStack(s2, stack));

	}

	@Test(timeout = 1000)
	public void recursivePropagation_CheckRecursivePathCalc_04() {
		Block b = new Block(gP[4]);
		loadStack();
		Stack<ArrayList<Integer>> stack = new Stack<>();
		stack = RecursivePropagation.recursivePathCalc(b, 1, 0, stack);
		String errMsg1 = String.format("RecursivePropagation recursivePathCalc() is incorrect");
		assertEquals(errMsg1, true, isSameStack(null, stack));

	}

}