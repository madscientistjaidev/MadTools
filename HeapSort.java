package MadTools;

import java.util.ArrayList;

/**
 *
 * @author Mad Scientist
 * @param <T>
 */
abstract class HeapSort <T>
{
	/**
	 * Accepts an ArrayList and returns one sorted in ascending order.
	 *
	 * @param a
	 * @return
	 */
	ArrayList<T> ascending(ArrayList<T> a)
	{
		if(a==null || a.isEmpty()) return new ArrayList<>();
		return buildHeap(a).getAscending();
	}
	
	/**
	 * Accepts an ArrayList and returns one sorted in descending order.
	 *
	 * @param a
	 * @return
	 */
	ArrayList<T> descending(ArrayList<T> a) {return buildHeap(a).getDescending();}
	
	/**
	 * Builds heap from ArrayList and returns root node.
	 * @param a
	 * @return 
	 */
	private Node <T> buildHeap(ArrayList<T> a)
	{
		Node <T> curr, root = new Node<>(a.remove(a.size()<3 ? 0 : getMedian(a.get(0),a.get(a.size()/2),a.get(a.size()-1),a.size())));

		for(T x : a)
		{
			curr = root;

			while(true)
			{
				if(compareTo(curr.val, x))
				{
					if(curr.left==null)
					{
						curr.left = new Node<>(x);
						break;
					}
					else curr=curr.left;
				}

				else
				{
					if(curr.right==null)
					{
						curr.right = new Node<>(x);
						break;
					}
					else curr=curr.right;
				}
			}
		}
		
		a=null;
		return root;
	}
	
	/**
	 * Gets median of 3 elements to choose root.
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
		HeapSort<Integer> h = new HeapSort<Integer>()
		{
			@Override
			boolean compareTo(Integer a, Integer b) {return a > b;}
		};

		//Test mode chooses small input size, max value, and prints input and output.
		boolean test = true;

		//Create ArrayList
		ArrayList<Integer> a = new ArrayList<>();

		//Size of array
		int size = test ? 30 : (int)Math.pow(2, 26);

		//Maximum value of elements
		int maxVal = test ? 100 : Integer.MAX_VALUE;

		//Generate random integers.
		for (int i = 0; i < size; i++) a.add((int)(maxVal*Math.random()));

		//Sort.
		System.out.print(test ? a + "\n" : "");
		a = h.ascending(a);
		System.out.print(test ? a + "\n" : "");

		//Check if result is sorted.
		for(int i = 0; i < size-1; i++)
			if(h.compareTo(a.get(i),a.get(i+1)))
			{
				System.out.print("Not Sorted.\n");
				return;
			}

		System.out.print("Sorted.\n");
	}

	/**
	 * Used to build heap.
	 * @param <T> 
	 */
	class Node <T>
	{
		/**
		 * Value of current node.
		 */
		private final T val;

		/**
		 * Children of current node.
		 */
		Node <T> left, right;

		/**
		 * Initializes current node
		 * @param val 
		 */
		Node(T val)
		{
			this.val = val;
			left = null;
			right = null;
		}

		/**
		 * Gets sub-heap elements in ascending order.
		 * @return 
		 */
		ArrayList<T> getAscending()
		{
			ArrayList<T> result = new ArrayList<>();
			if(left!=null) result.addAll(left.getAscending());
			result.add(val);
			if(right!=null) result.addAll(right.getAscending());
			
			left=null;
			right = null;
			
			return result;
		}

		/**
		 * Gets sub-heap elements in descending order.
		 * @return 
		 */
		ArrayList<T> getDescending()
		{
			ArrayList<T> result = new ArrayList<>();
			if(right!=null) result.addAll(right.getDescending());
			result.add(val);
			if(left!=null) result.addAll(left.getDescending());
			
			left=null;
			right = null;

			return result;
		}
	}
}
