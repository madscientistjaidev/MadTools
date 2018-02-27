package MadTools;

import java.util.ArrayList;

/**
 * @author Mad Scientist
 * @param <T>
 */
abstract class QuickSort <T>
{
	/**
	 * Accepts an ArrayList and returns it in sorted order.
	 *
	 * @param a
	 * @return
	 */
	ArrayList <T> sort(ArrayList <T> a)
	{
		if(a==null || a.isEmpty()) return new ArrayList<>();
		if(a.size()<=2) 
		{
			if(a.size()==2 && compareTo(a.get(0),a.get(1))) a.add(a.remove(0));
			return a;
		}
		
		final T pivot = a.remove(getMedian(a.get(0),a.get(a.size()/2),a.get(a.size()-1),a.size()));
		ArrayList <T> left = new ArrayList<>(), right = new ArrayList<>();
	
		for (T x : a)
			if(!compareTo(x, pivot)) left.add(x);
			else right.add(x);
		
		if(left.size()>1) left=sort(left);
		left.add(pivot);
		left.addAll((right.size()>1) ? sort(right) : right);
		
		return left;
	}

	/**
	 * Gets median of 3 elements to choose pivot.
	 * Compares first, middle, and last elements, and returns index of median.
	 *
	 * @param a
	 * @param b
	 * @param c
	 * @param size
	 * @return
	 */
	private int getMedian(T a, T b, T c, int size)
	{
		return compareTo(a,b) ? (compareTo(c,a) ? 0 : (compareTo(c,b) ? size-1 : size/2)) : (compareTo(c,b) ? (size/2) : (compareTo(c,a) ? size-1 : 0));
	}

	/**
	 * Returns true if a>b. Should be implemented in a sub class or anonymous inner class.
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	abstract boolean compareTo(T a, T b);

	/**
	 * Example for ~1 million randomly generated integers of size 0 to Integer.MAX_VALUE-1.
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

		//Test mode chooses small input size, max value, and prints input and output.
		boolean test = false;

		//Create ArrayList
		ArrayList<Integer> a = new ArrayList<>();

		//Size of array
		int size = test ? 30 : (int)Math.pow(2, 20);

		//Maximum value of elements
		int maxVal = test ? 100 : Integer.MAX_VALUE;

		//Generate random integers.
		for (int i = 0; i < size; i++) a.add((int)(maxVal*Math.random()));

		//Sort.
		System.out.print(test ? a + "\n" : "");
		a = q.sort(a);
		System.out.print(test ? a + "\n" : "");

		//Check if result is sorted.
		for(int i = 0; i < a.size()-1; i++)
			if(q.compareTo(a.get(i),a.get(i+1)))
			{
				System.out.print("Not Sorted.\n");
				return;
			}

		System.out.print("Sorted.\n");
	}
}
