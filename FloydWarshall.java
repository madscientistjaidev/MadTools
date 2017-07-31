package MadTools;

import java.util.Arrays;

/**
 *
 * @author Mad Scientist
 */
public class FloydWarshall
{
	int[][] run(int [][] graph)
    {
        for(int k = 0; k < graph.length; k++)
            for (int[] row : graph)
                for (int j = 0; j < graph.length; j++)
                    if (row[j] > row[k] + graph[k][j])
                        row[j] = row[k] + graph[k][j];
        
        return graph;
    }
	
    public static void main(String args[])
    {
		int graph[][] = {
                            {0,1000,-2,1000},
                            {4,0,3,1000},
                            {1000,1000,0,2},
                            {1000,-1,1000,0}
                        };
        
        int [][] dist = new FloydWarshall().run(graph);
        
        for(int[] row : dist) System.out.println(Arrays.toString(row));
    }
}