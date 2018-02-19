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
				
				switch(count)
				{
					case(0):
					case(1): newBoard[i][j] = false; break;
					case(2): newBoard[i][j] = true; break;
					case(3): newBoard[i][j] = true; break;
					default: newBoard[i][j] = false; break;
				}
				
				if(board[i][j])
				{
					if(count<2) newBoard[i][j] = false;
					else newBoard[i][j] = count <= 3;
				}
				
				else
				{
					if(count>3) newBoard[i][j] = true;
				}
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
	 * Example.
	 * @param args
	 */
	public static void main(String args[])
	{
		int size = 10;
		int steps = 10;

		int board[][] = new int[size][size];

		for(int[] x : board) for(int i = 0; i<size; i++)
			x[i] = Math.round((float)Math.random());

		for(int[] x : board)
			System.out.println(Arrays.toString(x));

		Conway c = new Conway(board);

		for(int i=0; i<steps; i++)
			System.out.println(Arrays.deepToString(new Conway(c.getNextState()).asIntArray()));
	}
}
