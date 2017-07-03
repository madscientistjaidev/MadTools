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
     * @param a
     * @param b
     * @return 
     */
    abstract boolean compareTo(T a, T b);
    
    boolean checkSorted(ArrayList<T> a)
    {
        for(int i = 0; i < a.size()-1; i++) if(compareTo(a.get(i),a.get(i+1))) return false;
        
        return true;
    }
	
	/**
	 * Example.
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
        
        int size = (int)Math.pow(2, 26);
        int maxVal = Integer.MAX_VALUE;

        for(int i = 0; i < size; i++) a.add((int)(maxVal*Math.random()));
        
        a = q.ascending(a);
        
        System.out.println("Sorted = " + q.checkSorted(a));
    }
}