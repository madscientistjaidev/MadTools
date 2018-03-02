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
	private final int size;

	/**
	 * Stores board.
	 */
	private boolean [][] board;

	/**
	 * Initializes blank board.
	 */
	Conway()
	{
		this.size = 0;
		this.board = null;
	}
	
	/**
	 * Initializes blank board with specified size.
	 * @param size
	 */
	Conway(int size)
	{
		this.size = size;
		this.board = new boolean[size][size];
	}

	/**
	 * Initializes board from array.
	 * @param board
	 */
	Conway(boolean [][] board)
	{
		if(board.length != board[0].length) throw new InputMismatchException();

		size = board.length;
		this.board = board;
	}

	/**
	 * Initializes board from array.
	 * @param board
	 */
	Conway(int [][] board)
	{
		if(board.length != board[0].length) throw new InputMismatchException();

		size = board.length;
		this.board = new boolean[size][size];

		for(int i=0; i<size; i++)
			for(int j = 0; j<size; j++)
				this.board[i][j] = board[i][j]>0;
	}

	/**
	 * Returns size of board.
	 * @return
	 */
	int getSize() {return size;}

	/**
	 * Returns board as boolean array.
	 * @return
	 */
	boolean [][] getBoard() {return board;}

	/**
	 * Returns board as integer array.
	 * @return
	 */
	int [][] asIntArray()
	{
		int [][] intBoard = new int [size][size];

		for(int i=0; i<size; i++)
			for(int j = 0; j<size; j++)
				intBoard[i][j] = board[i][j] ? 1 : 0;

		return intBoard;
	}

	/**
	 * Advances simulation by one step.
	 * @return
	 */
	void advance() {board = getNextState();}
	
	/**
	 * Advances simulation by n steps.
	 * @return
	 */
	void advance(int n) {for(int i=0; i<n; i++) board = getNextState();}
	
	/**
	 * Returns next step.
	 * @return 
	 */
	boolean [][] getNextState()
	{
		boolean [][] newBoard = new boolean[size][size];
		int count = 0;
		
		for(int i=0; i<size; i++)
			for(int j = 0; j<size; j++)
			{
				if(i!=0) if(board[i-1][j]) count++;
				if(i!=board.length-1) if(board[i+1][j]) count++;
				if(j!=0) if(board[i][j-1]) count++;
				if(j!=board[0].length-1) if(board[i][j+1]) count++;
				if(i!=0 && j!=0) if(board[i-1][j-1]) count++;
				if(i!=0 && j!=board[0].length-1) if(board[i-1][j+1]) count++;
				if(i!=board.length-1 && j!=0) if(board[i+1][j-1]) count++;
				if(i!=board.length-1 && j!=board[0].length-1) if(board[i+1][j+1]) count++;
				
				if(board[i][j] && (count<2 || count>3)) newBoard[i][j] = false;
				if(!board[i][j] && count==3) newBoard[i][j] = true;
			}

		return newBoard;
	}

	/**
	 * Compares current board to specified one.
	 * @param otherState
	 * @return
	 */
	boolean isEqual(boolean otherState[][])
	{
		if(otherState.length!=size) return false;

		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				if(board[i][j] != otherState[i][j]) return false;

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
	boolean cell(int i, int j) {return board[i][j];}
	
	/**
	 * Returns number of live cells in board.
	 * @return 
	 */
	int liveCount()
	{
		int count = 0;
		for(boolean[] row : board) for(boolean cell : row) if(cell) count++;
		return count;
	}
	
	/**
	 * Returns number of dead cells in board.
	 * @return 
	 */
	int deadCount()
	{
		int count = 0;
		for(boolean[] row : board) for(boolean cell : row) if(!cell) count++;
		return count;
	}
	
	@Override
	public String toString()
	{
		String s = "";
		int b[][] = asIntArray();
		
		for(int [] row : b) s += Arrays.toString(row) + "\n";
		return s;
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
