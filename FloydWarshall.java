package MadTools;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Mad Scientist
 */
public class FloydWarshall
{
	/**
	 * Accepts a graph in adjacency matrix form and returns shortest path matrix.
	 * @param graph
	 * @return 
	 */	
	int[][] findWeights(int [][] graph)
    {
        for(int k = 0; k < graph.length; k++)
            for (int[] row : graph)
                for (int j = 0; j < graph.length; j++)
                    if (row[j] > row[k] + graph[k][j])
                        row[j] = row[k] + graph[k][j];
        
        return graph;
    }
	
	boolean negativeCycles(int graph[][])
	{
		int dist[][] = findWeights(graph);
		
		for(int i = 0; i < dist.length; i++)
			if(dist[i][i]<0) return true;
		
		return false;
	}
	
	/**
	 * Example.
	 * @param args 
	 */
    public static void main(String args[])
    {
		//Use some very large value (1000 in this case) for representing infinity.
		int graph[][] = {
                            {0,1000,-2,1000},
                            {4,0,3,1000},
                            {1000,1000,0,2},
                            {1000,-1,1000,0}
                        };
        
		FloydWarshall fw = new FloydWarshall();
        int [][] dist = fw.findWeights(graph);
        
        for(int[] row : dist) System.out.println(Arrays.toString(row));
		
		System.out.println("\nNegative Cycles = " + fw.negativeCycles(graph));
    }
	
	ArrayList<Integer> findPath(int [][] graph, int src, int dest)
    {
        int l = graph.length;
		int [][] next = new int [l][l];
		
		for(int i = 0; i < l; i++)
			for(int j = 0; j < l; j++)
				next[i][j]=j;
		
		for(int k = 0; k < l; k++)
            for (int i = 0; i < l; i++)
                for (int j = 0; j < l; j++)
                    if (graph[i][j] > graph[i][k] + graph[k][j])
					{
						graph[i][j] = graph[i][k] + graph[k][j];
						next[i][j]=next[i][k];
					}
        
		ArrayList<Integer> path = new ArrayList<>();
		path.add(src);
		
		while(src!=dest)
		{
			src = next[src][dest];
			path.add(src);
		}
		
        return path;
    }
}