package MadTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mad Scientist
 * @param <T>
 */
abstract class TernaryStore<T>
{
	/**
	 * Stores values in inserted order.
	 */
	ArrayList<TerNode<T>> vals;

	/**
	 * Root node of Ternary Tree.
	 */
	TerNode<T> root;
	
	/**
	 * Number of elements in tree.
	 */
	private int size;
	
	/**
	 * Default constructor. Initializes a blank tree.
	 */
	TernaryStore()
	{
		vals = new ArrayList<>();
	}

	/**
	 * Initializes a tree with root node value a.
	 * @param a
	 */
	TernaryStore(T a)
	{
		root = new TerNode<>(a);
		vals = new ArrayList<>();
		vals.add(root);
	}

	/**
	 * Initializes a tree with values in Array.
	 * @param a
	 */
	TernaryStore(T [] a)
	{
		this(Arrays.asList(a));
	}
	
	/**
	 * Initializes a tree with values in List.
	 * @param a
	 */
	TernaryStore(List<T> a)
	{
		if(a==null || a.isEmpty()) return;

		vals = new ArrayList<>();
		root = new TerNode<>(a.remove(0));
		TerNode <T> curr;

		outer:for(T x : a)
		{
			curr = root;

			inner:while(true)
			{
				switch(compareTo(curr.val, x))
				{
					case -1:
						if(curr.left==null)
						{
							vals.add(curr.left=new TerNode<>(x));
							break inner;
						}
						else curr=curr.left;
						break;

					case 0:
						if(curr.center==null)
						{
							vals.add(curr.center=new TerNode<>(x));
							break inner;
						}
						else curr=curr.left;
						break;

					case 1:
						if(curr.right==null)
						{
							vals.add(curr.right=new TerNode<>(x));
							break inner;
						}
						else curr=curr.right;
						break;
				}
			}
		}
	}
	
	/**
	 * Gets size.
	 * @return 
	 */
	int getSize()
	{
		return size;
	}

	/**
	 * Used to compare two values. Returns 1 if a>b, 0 if a=b, and -1 if a<b.
	 * Should be implemented in a sub class or anonymous inner class.
	 * @param a
	 * @param b
	 * @return
	 */
	abstract int compareTo(T a, T b);

	/**
	 * Used to build Ternary Tree.
	 * @param <T>
	 */
	class TerNode <T>
	{
		/**
		 * Value of current node.
		 */
		private final T val;

		/**
		 * Children of current node.
		 */
		TerNode<T> left, center, right;

		/**
		 * Initializes current node
		 * @param val
		 */
		TerNode(T val)
		{
			this.val = val;
			left = null;
			center = null;
			right = null;
		}

		/**
		 * Gets sub-tree elements in ascending order.
		 * @return
		 */
		ArrayList<T> getAscending()
		{
			ArrayList<T> result = new ArrayList<>();
			if(left!=null) result.addAll(left.getAscending());

			result.add(val);
			while(center!=null)
			{
				result.add(center.val);
				center = center.center;
			}

			if(right!=null) result.addAll(right.getAscending());

			return result;
		}

		/**
		 * Gets sub-tree elements in descending order.
		 * @return
		 */
		ArrayList<T> getDescending()
		{
			ArrayList<T> result = new ArrayList<>();
			if(right!=null) result.addAll(right.getDescending());

			result.add(val);
			while(center!=null)
			{
				result.add(center.val);
				center = center.center;
			}

			if(left!=null) result.addAll(left.getDescending());

			return result;
		}
	}

	public static void main(String args[])
	{
		ArrayList <Integer> a = new ArrayList<>();

		TernaryStore <Integer> t = new TernaryStore<Integer>(a)
		{
			@Override
			int compareTo(Integer a, Integer b) {return a==b ? 0 : (a>b ? 1 : -1);}
		};

		System.out.println(t.vals);
	}
}
