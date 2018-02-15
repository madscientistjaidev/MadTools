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
            {
                this.board[i][j] = board[i][j]>0;
            }
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
    int [][] asInt()
    {
        int [][] intBoard = new int [size][size];
        
        for(int i=0; i<size; i++)
            for(int j = 0; j<size; j++)
            {
                intBoard[i][j] = board[i][j] ? 1 : 0;
            }
        
        return intBoard;
    }
    
	/**
	 * Advances simulation by one step.
	 * @return 
	 */
    boolean [][] nextState()
    {
        boolean [][] newBoard = board;
        
        for(int i=0; i<size; i++)
            for(int j = 0; j<size; j++)
            {
                
            }
    
        return null;
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
    boolean isEqual(Conway otherState) {return otherState.getSize()==size;}
    
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
        
        int board[][] = new int[size][size];
        
        for(int[] x : board) for(int i = 0; i<size; i++)
                x[i] = Math.round((float)Math.random());
		
		for(int[] x : board)
			System.out.println(Arrays.toString(x));
		
		Conway c = new Conway(board);
    }
}
