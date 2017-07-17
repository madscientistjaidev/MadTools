package MadTools;

import java.util.ArrayList;

/**
 * @author Mad Scientist
 * @param <T>
 */
abstract class QuickSort <T>
{        
    ArrayList <T> ascending(ArrayList <T> a)
    {        
        if(a.size()<=1) return a;
        
        T pivot = a.remove(0);
        
        ArrayList <T> left = new ArrayList<>();
        ArrayList <T> right = new ArrayList<>();
		
		for(T x : a)
        {
            if(!compareTo(x, pivot)) left.add(x);
            else right.add(x);
        }
        
        left = ascending(left);
        right = ascending(right);
        
        left.add(pivot);
        right.forEach(left::add);
        
        return left;
    }
    
    ArrayList <T> descending(ArrayList <T> a)
    {   
        if(a.size()<=1) return a;
                
        T pivot = a.remove(0);
		
        ArrayList <T> left = new ArrayList<>();
        ArrayList <T> right = new ArrayList<>();
        
        for(T x : a)
        {
            if(!compareTo(x, pivot)) right.add(x);
            else left.add(x);
        }
        
        left = descending(left);
        right = descending(right);
        
        left.add(pivot);
        right.forEach(left::add);
        
        return left;
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
	 * Example for ~8 million randomly generated integers of size 0 to Integer.MAX_VALUE.
	 * @param args 
	 */
	public static void main(String args[])
    {		
		//Create anonymous inner class to define compareTo function.
		QuickSort <Integer> q = new QuickSort<Integer>()
		{
			@Override
			boolean compareTo(Integer a, Integer b) {return a>b;}
		};
        
		//Create ArrayList
        ArrayList <Integer> a = new ArrayList<>();
        
        //Size of array
        int size = (int)Math.pow(2, 23);
        
		//Maximum value of elements
		int maxVal = Integer.MAX_VALUE;

		//Generate random integers.
        for(int i = 0; i < size; i++) a.add((int)(maxVal*Math.random()));
        
		//Sort.
        a = q.ascending(a);
        
        System.out.println("Sorted.");
    }
}