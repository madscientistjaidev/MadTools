package MadTools;

import java.util.ArrayList;

/**
 *
 * @author Mad Scientist
 * @param <T>
 */
abstract class HeapSort <T>
{
	abstract boolean compareTo(T a, T b);

	ArrayList<T> ascending(ArrayList<T> a)
	{
		Node <T> root = buildHeap(a);
		
		a = null;

		return root.getAscending();
	}
	
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
		
		return root;
	}
	
	private int getMedian(T a, T b, T c, int size)
	{
		return compareTo(a,b) ? (compareTo(c,a) ? 0 : (compareTo(c,b) ? size-1 : size/2)) : (compareTo(c,b) ? (size/2) : (compareTo(c,a) ? size-1 : 0));
	}

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
		for(int i = 0; i < a.size()-1; i++)
			if(h.compareTo(a.get(i),a.get(i+1)))
			{
				System.out.print("Not Sorted.\n");
				return;
			}

		System.out.print("Sorted.\n");
	}

	class Node <T>
	{
		private final T val;

		Node <T> left;
		Node <T> right;

		Node(T val)
		{
			this.val = val;
			left = null;
			right = null;
		}

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
