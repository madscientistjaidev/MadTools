package MadTools;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Simple orienteering tool.
 * The board is a character array, where O is an empty space, X is a blocked space, * is source, and # is destination.
 * Multiple destinations can exist, but only distance to the closest one is returned.
 * Multiple sources can exist, but only the first one will be fed to the algorithm.
 * Returns distance if path is found, or -1 if not found.
 * @author Mad Scientist
 */
public class Orienteering
{
	/**
	 * Accepts a board and returns the distance from source to destination if it exists, or -1 if it doesn't.
	 * @param grid
	 * @return
	 */
	public int findDist(char[][] grid)
	{
		//Coordinates of source
		int dist;

		//Finding source
		for(int i=0; i < grid.length; i++)
			for(int j=0; j<grid[0].length; j++)
				if(grid[i][j]=='*')
					return ((dist=findDFS(grid,i,j,i,j,0))>grid.length*grid[0].length) ? -1 : dist-1;

		return -1;
	}

	/**
	 * Recursively finds path using DFS.
	 * Returns some value greater than max if path is not found, or if max recursion depth is reached.
	 * @param grid
	 * @param sourceX
	 * @param sourceY
	 * @param currX
	 * @param currY
	 * @param depth
	 * @return 
	 */
	private int findDFS(char[][]grid, int sourceX, int sourceY, int currX, int currY, int depth)
	{
		//If destination found.
		if(grid[currX][currY]=='#') return 1;

		//Theoretical maximum depth of recursion.
		int max = grid.length*grid[0].length;

		//If unpassable.
		if(grid[currX][currY]=='X') return max+1;

		//Check if max depth is reached.
		if(depth>max) return max+1;

		//Stores result of search in each direction. [l,r,u,d]
		int[] res = new int[4];

		//Depth First Search in each direction.
		res[0] = (currX==0) || (sourceX==currX-1 && sourceY==currY) || (grid[currX-1][currY]=='X')
				? max+1 : findDFS(grid, currX, currY, currX-1, currY, depth+1);

		res[1] = (currX==grid.length-1) || (sourceX==currX+1 && sourceY==currY) || (grid[currX+1][currY]=='X')
				? max+1 : findDFS(grid, currX, currY, currX+1, currY, depth+1);

		res[2] = (currY==0) || (sourceX==currX && sourceY==currY-1) || (grid[currX][currY-1]=='X')
				? max+1 : findDFS(grid, currX, currY, currX, currY-1, depth+1);

		res[3] = (currY==grid[0].length-1) || (sourceX==currX && sourceY==currY+1) || (grid[currX][currY+1]=='X')
				? max+1 : findDFS(grid, currX, currY, currX, currY+1, depth+1);

		//Find minimum of four distances.
		Arrays.sort(res);

		//Return.
		return res[0]+1;
	}

	/**
	 * EXPERIMENTAL AND INCOMPLETE
	 * Recursively finds path using BFS.
	 * Returns some value greater than max if path is not found, or if max recursion depth is reached.
	 * @param grid
	 * @param sourceX
	 * @param sourceY
	 * @param currX
	 * @param currY
	 * @param depth
	 * @return 
	 */
	private int findBFS(char[][]grid, int sourceX, int sourceY, int currX, int currY, int depth)
	{
		ArrayList<Integer[]> visited = new ArrayList<>();
		
		Integer currCoord[] = {currX,currY,0};

		visited.add(currCoord);

		while(!visited.isEmpty()) if(grid[currX][currY]=='#') return depth;

		return -1;
	}

	/**
	 * Examples.
	 * @param args
	 */
	public static void main(String args[])
	{
		char[][] grid =		{
								{'*','#'}
							};

		char[][] grid2 =	{
								{'*','O','X','X','X','X','X','O','#'},
								{'X','O','X','O','O','O','X','O','X'},
								{'X','O','X','O','X','O','X','O','X'},
								{'X','O','X','O','X','O','X','O','X'},
								{'X','O','X','O','X','O','X','O','X'},
								{'X','O','X','O','X','O','X','O','X'},
								{'X','O','X','O','X','O','X','O','X'},
								{'X','O','X','O','X','O','X','O','X'},
								{'X','O','O','O','X','O','O','O','X'},
								{'X','X','X','X','X','X','X','X','X'}
							};

		char[][] grid3 =	{
								{'X','X','X','X','X','X'},
								{'X','*','O','O','O','X'},
								{'X','O','O','#','O','X'},
								{'X','X','X','X','X','X'}
							};

		char[][] grid4 =	{
								{'X','X','X','X','X'},
								{'X','*','X','O','X'},
								{'X','O','X','#','X'},
								{'X','X','X','X','X'}
							};

		Orienteering o = new Orienteering();

		System.out.println(o.findDist(grid));
		System.out.println(o.findDist(grid2));
		System.out.println(o.findDist(grid3));
		System.out.println(o.findDist(grid4));
	}
}
