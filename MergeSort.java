package MadTools;

import java.util.ArrayList;

/**
 * @author Mad Scientist
 * @param <T>
 */
abstract class MergeSort<T>
{
	/**
	 * Accepts an ArrayList and returns one sorted in ascending order.
	 *
	 * @param a
	 * @return
	 */
	ArrayList<T> ascending(ArrayList<T> a)
	{
		ArrayList<T> left = new ArrayList(a.subList(0,a.size()/2)), right = new ArrayList(a.subList((a.size()/2),a.size())), result = new ArrayList<>();

		if(left.size()>1) left=ascending(left);
		if(right.size()>1) right=ascending(right);

		while(!left.isEmpty() && !right.isEmpty()) result.add(!compareTo(left.get(0),right.get(0)) ? left.remove(0) : right.remove(0));
		
		if(!left.isEmpty()) result.addAll(left);
		if(!right.isEmpty()) result.addAll(right);

		return result;
	}

	/**
	 * Accepts an ArrayList and returns one sorted in descending order.
	 *
	 * @param a
	 * @return
	 */
	ArrayList<T> descending(ArrayList<T> a)
	{
		ArrayList<T> left = new ArrayList(a.subList(0,a.size()/2)), right = new ArrayList(a.subList((a.size()/2),a.size())), result = new ArrayList<>();

		if(left.size()>1) left=ascending(left);
		if(right.size()>1) right=ascending(right);

		while(!left.isEmpty() && !right.isEmpty()) result.add(!compareTo(left.get(0),right.get(0)) ? left.remove(0) : right.remove(0));
		
		if(!left.isEmpty()) result.addAll(left);
		if(!right.isEmpty()) result.addAll(right);

		return result;
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
	 * Example for ~1 million randomly generated integers of size 0 to Integer.MAX_VALUE.
	 *
	 * @param args
	 */
	public static void main(String args[])
	{
		//Create anonymous inner class to define compareTo function.
		MergeSort<Integer> m = new MergeSort<Integer>()
		{
			@Override
			boolean compareTo(Integer a, Integer b) {return a > b;}
		};
		
		//Test mode chooses small input size, max value, and prints input and output.
		boolean test = true;

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
		a = m.ascending(a);
		System.out.print(test ? a + "\n" : "");
		
		//Check if result is sorted.
		for(int i = 0; i < a.size()-1; i++)
			if(m.compareTo(a.get(i),a.get(i+1)))
			{
				System.out.print("Not Sorted.\n");
				return;
			}
		
		System.out.print("Sorted.\n");
	}
}
