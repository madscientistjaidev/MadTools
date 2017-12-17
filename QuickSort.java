package MadTools;

import java.util.ArrayList;

/**
 * @author Mad Scientist
 * @param <T>
 */
abstract class QuickSort <T>
{        
    /**
     * Accepts an ArrayList and returns one sorted in ascending order.
     *
     * @param a
     * @return
     */
    ArrayList <T> ascending(ArrayList <T> a)
    {
	if(a.size()<=1) return a;
        
        T pivot = a.remove(getMedian(a.get(0),a.get(a.size()/2),a.get(a.size()-1),a.size()));
        ArrayList <T> left = new ArrayList<>(), right = new ArrayList<>();
	
	for (T x : a)
	    if(!compareTo(x, pivot)) left.add(x);
	    else right.add(x);
        
        left = ascending(left);
	left.add(pivot);
        left.addAll(ascending(right));
        
        return left;
    }
    
    /**
     * Accepts an ArrayList and returns one sorted in descending order.
     *
     * @param a
     * @return
     */
    ArrayList <T> descending(ArrayList <T> a)
    {        
        if(a.size()<=1) return a;
        
        T pivot = a.remove(getMedian(a.get(0),a.get(a.size()/2),a.get(a.size()-1),a.size()));
        ArrayList <T> left = new ArrayList<>(), right = new ArrayList<>();
	
	for (T x : a)
	    if(!compareTo(x, pivot)) right.add(x);
	    else left.add(x);
        
        left = ascending(left);
	left.add(pivot);
        left.addAll(ascending(right));
        
        return left;
    }
    
    /**
     * Gets median of 3 elements to choose pivot.
     * @param a
     * @param b
     * @param c
     * @param size
     * @return 
     */
    private int getMedian(T a, T b, T c, int size)
    {
        return size<3 ? 0 : (compareTo(a,b) ? (compareTo(c,a) ? 0 : (compareTo(c,b) ? size-1 : size/2)) : (compareTo(c,b) ? (size/2) : (compareTo(c,a) ? size-1 : 0)));
    }
    
    /**
     * This function returns true if a>b. It is meant to make sorting possible no matter what types or objects are used.
     * It should be implemented in a sub class or anonymous inner class.
     *
     * @param a
     * @param b
     * @return
     */
    abstract boolean compareTo(T a, T b);

    /**
     * Example for randomly generated integers of size 0 to Integer.MAX_VALUE.
     *
     * @param args
     */
    public static void main(String args[])
    {
	//Create anonymous inner class to define compareTo function.
	QuickSort<Integer> q = new QuickSort<Integer>()
	{
	    @Override
	    boolean compareTo(Integer a, Integer b) {return a > b;}
	};

	//Create ArrayList
	ArrayList<Integer> a = new ArrayList<>();

	//Size of array
	int size = (int) Math.pow(2, 26);

	//Maximum value of elements
	int maxVal = Integer.MAX_VALUE;

	//Generate random integers.
	for (int i = 0; i < size; i++) a.add((int) (maxVal * Math.random()));

	//Sort.
	a = q.ascending(a);

	System.out.println("Sorted.");
    }
}