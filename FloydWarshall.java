package MadTools;

/**
 *
 * @author Mad Scientist
 */
public class FloydWarshall
{
    int[][] run(int [][] graph)
    {
        for(int k = 0; k < graph.length; k++)
            for (int[] graph1 : graph)
                for (int j = 0; j < graph.length; j++)
                    if (graph1[j] > graph1[k] + graph[k][j])
                        graph1[j] = graph1[k] + graph[k][j];
        
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
        
        for(int i = 0; i < graph.length; i++)
        {
            for(int j = 0; j < graph.length; j++) System.out.print(dist[i][j] + ",");
            System.out.println();
        }
    }
}