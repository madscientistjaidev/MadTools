package MadTools;

import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * A simple Conway's Game of Life class.
 * @author Mad Scientist
 */
public class Conway
{
	/**
	 * Size of board.
	 */
	private final int s;

	/**
	 * Stores board.
	 */
	private boolean [][] b;

	/**
	 * Initializes blank board.
	 */
	Conway()
	{
		this.s = 0;
		this.b = null;
	}

	/**
	 * Initializes blank board with specified size.
	 * @param s
	 */
	Conway(int s)
	{
		if(s<1) throw new InputMismatchException();
		this.s = s;
		this.b = new boolean[s][s];
	}

	/**
	 * Initializes board from array.
	 * @param b
	 */
	Conway(boolean [][] b)
	{
		if(b.length != b[0].length) throw new InputMismatchException();

		s = b.length;
		this.b = b;
	}

	/**
	 * Initializes board from array.
	 * @param b
	 */
	Conway(int [][] b)
	{
		if(b.length != b[0].length) throw new InputMismatchException();

		s = b.length;
		this.b = new boolean[s][s];

		for(int i=0; i<s; i++)
			for(int j = 0; j<s; j++)
				this.b[i][j] = b[i][j]>0;
	}

	/**
	 * Returns size of board.
	 * @return
	 */
	int getSize() {return s;}

	/**
	 * Returns board as boolean array.
	 * @return
	 */
	boolean [][] getBoard() {return b;}

	/**
	 * Returns board as integer array.
	 * @return
	 */
	int [][] asIntArray()
	{
		int [][] intB = new int [s][s];

		for(int i=0; i<s; i++)
			for(int j = 0; j<s; j++)
				intB[i][j] = b[i][j] ? 1 : 0;

		return intB;
	}

	/**
	 * Advances simulation by one step.
	 * @return
	 */
	void advance() {b = getNextState();}

	/**
	 * Advances simulation by n steps.
	 * @return
	 */
	void advance(int n) {for(int i=0; i<n; i++) b = getNextState();}

	/**
	 * Returns next step.
	 * @return
	 */
	boolean [][] getNextState()
	{
		boolean [][] newB = new boolean[s][s];

		for(int i=0; i<s; i++)
			for(int j = 0; j<s; j++)
				newB[i][j] = getNextCell(i,j);

		return newB;
	}
	
	/**
	 * Return next cell.
	 * @param i
	 * @param j
	 * @return 
	 */
	boolean getNextCell(int i, int j)
	{
		int c = 0, l=s-1;
		
		if(i!=0) if(b[i-1][j]) c++;
		if(i!=l) if(b[i+1][j]) c++;
		if(j!=0) if(b[i][j-1]) c++;
		if(j!=l) if(b[i][j+1]) c++;
		if(i!=0 && j!=0) if(b[i-1][j-1]) c++;
		if(i!=0 && j!=l) if(b[i-1][j+1]) c++;
		if(i!=l && j!=0) if(b[i+1][j-1]) c++;
		if(i!=l && j!=l) if(b[i+1][j+1]) c++;

		if(!b[i][j] && c==3) return true;
		else return b[i][j] && (c==3 || c==2);
	}

	/**
	 * Compares current board to specified one.
	 * @param otherState
	 * @return
	 */
	boolean isEqual(boolean otherState[][])
	{
		if(otherState.length!=s) return false;

		for(int i = 0; i < s; i++)
			for(int j = 0; j < s; j++)
				if(b[i][j] != otherState[i][j]) return false;

		return true;
	}

	/**
	 * Compares current board to specified one.
	 * @param otherState
	 * @return
	 */
	boolean isEqual(Conway otherState) {return isEqual(otherState.getBoard());}

	/**
	 * Gets value of specified cell.
	 * @param i
	 * @param j
	 * @return
	 */
	boolean cell(int i, int j) {return b[i][j];}

	/**
	 * Returns number of live cells in board.
	 * @return
	 */
	int liveCount()
	{
		int c = 0;
		for(boolean[] row : b) for(boolean cell : row) if(cell) c++;
		return c;
	}

	/**
	 * Returns number of dead cells in board.
	 * @return
	 */
	int deadCount()
	{
		int count = 0;
		for(boolean[] row : b) for(boolean cell : row) if(!cell) count++;
		return count;
	}

	@Override
	public String toString()
	{
		String str = "";
		int brd[][] = asIntArray();

		for(int [] row : brd) str += Arrays.toString(row) + "\n";
		return str;
	}

	/**
	 * Example.
	 * @param args
	 */
	public static void main(String args[])
	{
		int size = 10;
		int steps = 1;
		float weight = -0.1f;

		int board[][] = new int[size][size];

		for(int[] x : board) for(int i = 0; i<size; i++)
			x[i] = Math.round(weight+(float)Math.random());
		
		Conway c = new Conway(board);
		
		System.out.println(c);
		c.advance(steps);
		System.out.println(c);
	}
}
