package MadTools;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Mad Scientist
 */
public class Orienteering
{
	static ArrayList<Integer[]> visited;
	
	Orienteering()
	{
		visited = new ArrayList<>();
	}
	
	public int findDist(char[][] grid)
    {
        //Coordinates of source
        int sourceX=-1, sourceY=-1;
        
        //Finding source
        for(int i=0; i < grid.length; i++)
        {
            for(int j=0; j<grid[0].length; j++)
            {
                if(grid[i][j]=='*')
                {
                    sourceX=i;
                    sourceY=j;
                    break;
                }
            }
            
            if(sourceX!=-1) break;
        }
        
        //Find distance.
        int dist = find(grid, sourceX, sourceY, sourceX, sourceY, 0);
        
        //Return distance if found.
        return (dist>grid.length*grid[0].length) ? -1 : dist-1;
    }
    
    int find(char[][]grid, int sourceX, int sourceY, int currX, int currY, int depth)
    {
        //Add current coordinates to visited list.
		Integer[] currCoord = {currX,currY};
		visited.add(currCoord);

		//If food found.
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
        res[0] = //(visited.stream().anyMatch((coord) -> (coord[0]==currX-1 && coord[1]==currY)) ||
					(currX==0) || (sourceX==currX-1 && sourceY==currY) || (grid[currX-1][currY]=='X')
					? max+1 : find(grid, currX, currY, currX-1, currY, depth+1);
        
		res[1] = //(visited.stream().anyMatch((coord) -> (coord[0]==currX+1 && coord[1]==currY)) ||
					(currX==grid.length-1) || (sourceX==currX+1 && sourceY==currY) || (grid[currX+1][currY]=='X')
					? max+1 : find(grid, currX, currY, currX+1, currY, depth+1);
        
		res[2] = //(visited.stream().anyMatch((coord) -> (coord[0]==currX && coord[1]==currY-1))  ||
					(currY==0) || (sourceX==currX && sourceY==currY-1) || (grid[currX][currY-1]=='X')
					? max+1 : find(grid, currX, currY, currX, currY-1, depth+1);
        
		res[3] = //(visited.stream().anyMatch((coord) -> (coord[0]==currX && coord[1]==currY+1)) ||
					(currY==grid[0].length-1) || (sourceX==currX && sourceY==currY+1) || (grid[currX][currY+1]=='X')
					? max+1 : find(grid, currX, currY, currX, currY+1, depth+1);
		
        //Find minimum of four distances.
        Arrays.sort(res);
		
        //Return.
        return res[0]+1;
    }
	
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