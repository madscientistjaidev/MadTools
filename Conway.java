package MadTools;

import java.util.Arrays;
import java.util.InputMismatchException;

public class Conway
{
    private final int size;
    private boolean [][] board;
    
    Conway(int size)
    {
        this.size = size;
        this.board = new boolean[size][size];
    }
    
    Conway(boolean [][] board)
    {
        if(board.length != board[0].length) throw new InputMismatchException();
        
        size = board.length;
        this.board = board;
    }
    
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
    
    int getSize() {return size;}
    
    boolean [][] getBoard() {return board;}
    
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
    
    boolean [][] nextState()
    {
        boolean [][] newBoard = board;
        
        for(int i=0; i<size; i++)
            for(int j = 0; j<size; j++)
            {
                
            }
    
        return null;
    }
    
    boolean isEqual(boolean otherState[][])
    {
        if(otherState.length!=size) return false;

        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                if(board[i][j] != otherState[i][j]) return false;
        
        return true;
    }
    
    boolean isEqual(Conway otherState) {return otherState.getSize()==size;}
    
    boolean cell(int i, int j) {return board[i][j];}
    
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
