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
	 * Stores list of visited locations.
	 */
	private static ArrayList<Integer[]> seen;
	
	/**
	 * Board.
	 */
	private char[][] b;

	/**
	 * Accepts a board and returns the distance from source to destination if it exists, or -1 if it doesn't.
	 * @param b
	 * @return
	 */
	public int findDist(char[][] b)
	{
		this.b = b;
		int dist;
		
		seen = new ArrayList<>();

		//Finding source
		for(int i=0; i < b.length; i++) for(int j=0; j<b[0].length; j++)
				if(b[i][j]=='*') return ((dist=findDFS(i,j,0))>b.length*b[0].length) ? -1 : dist-1;
		
		return -1;
	}

	/**
	 * Recursively finds path using DFS.
	 * Returns some value greater than max if path is not found, or if max recursion depth is reached.
	 * @param grid
	 * @param sourceX
	 * @param sourceY
	 * @param x
	 * @param y
	 * @param d
	 * @return 
	 */
	private int findDFS(int x, int y, int d)
	{
		visit(x,y,d);

		//If destination found.
		if(b[x][y]=='#') return 1;

		//Theoretical maximum depth of recursion.
		int max = b.length*b[0].length;

		//If unpassable.
		if(b[x][y]=='X') return max+1;

		//Check if max depth is reached.
		if(d>max) return max+1;

		//Stores result of search in each direction. [l,r,u,d]
		int[] res = new int[4];

		//Depth First Search in each direction.
		res[0] = (x==0) || isSeen(x-1, y, d+1) || (b[x-1][y]=='X')
					? max+1 : findDFS(x-1, y, d+1);

		res[1] = (x==b.length-1) || isSeen(x+1, y, d+1) || (b[x+1][y]=='X')
					? max+1 : findDFS(x+1, y, d+1);

		res[2] = (y==0) || isSeen(x, y-1, d+1) || (b[x][y-1]=='X')
					? max+1 : findDFS(x, y-1, d+1);

		res[3] = (y==b[0].length-1) || isSeen(x, y+1, d+1) || (b[x][y+1]=='X')
					? max+1 : findDFS(x, y+1, d+1);

		//Find minimum of four distances.
		Arrays.sort(res);

		//Return.
		return res[0]+1;
	}
	
	/**
	 * Returns true if point has been visited at a shallower or equal depth.
	 * @param x
	 * @param y
	 * @param d
	 * @return 
	 */
	private boolean isSeen(int x, int y, int d)
	{
		return seen.stream().anyMatch((pt) -> (pt[0]==x && pt[1]==y && pt[2]<=d));
	}
	
	/**
	 * Adds point to visited if unseen, or updates depth if seen.
	 * @param x
	 * @param y
	 * @param d 
	 */
	void visit(int x, int y, int d)
	{
		for(Integer[] pt : seen)
			if(pt[0]==x && pt[1]==y)
			{
				pt[2]=d;
				return;
			}
		
		Integer[] newPt = {x,y,d};
		seen.add(newPt);
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
