package MadTools;

import java.util.ArrayList;

/**
 * @author Mad Scientist
 * @param <T>
 */
abstract class MergeSort <T>
{
    ArrayList <T> ascending(ArrayList <T> a)
    {
        if(a.size()<=1) return a;
	
		ArrayList <T> result = new ArrayList<>();
        
        int l = a.size();
        ArrayList <T> left = new ArrayList(a.subList(0, l/2));
        ArrayList <T> right = new ArrayList(a.subList((l/2), l));
        
        left = ascending(left);
        right = ascending(right);
        
        for(int i = 0; i < l; i++)
        {
            if(left.isEmpty())
            {
                result.addAll(right);
                return result;
            }
            
            if(right.isEmpty())
            {
                result.addAll(left);
                return result;
            }
            
			result.add(!compareTo(left.get(0), right.get(0)) ? left.remove(0) : right.remove(0));
        }
        
        return result;
    }
	
	/**
	 * This function returns true if a>b.
	 * It is meant to make sorting possible no matter what types or objects are used.
	 * It should be implemented in a sub class or anonymous inner class.
     * @param a
     * @param b
     * @return 
     */
    abstract boolean compareTo(T a, T b);
	
    /**
	 * Example for ~1 million randomly generated integers of size 0 to Integer.MAX_VALUE.
	 * @param args 
	 */
	public static void main(String args[])
    {		
		//Create anonymous inner class to define compareTo function.
		MergeSort <Integer> q = new MergeSort<Integer>()
		{
			@Override
			boolean compareTo(Integer a, Integer b) {return a>b;}
		};
        
		//Create ArrayList
        ArrayList <Integer> a = new ArrayList<>();
        
		//Size of array
        int size = (int)Math.pow(2, 20);
        
		//Maximum value of elements
		int maxVal = Integer.MAX_VALUE;

		//Generate random integers.
        for(int i = 0; i < size; i++) a.add((int)(maxVal*Math.random()));
        
		//Sort.
        a = q.ascending(a);
        
        System.out.println("Sorted.");
    }
}