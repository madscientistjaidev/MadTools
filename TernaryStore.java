package MadTools;

import java.util.ArrayList;

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
	ArrayList<TernaryNode<T>> vals;
	
	/**
	 * Root node of Ternary Tree.
	 */
	TernaryNode<T> root;
	
	/**
	 * Default constructor. Initializes a blank tree.
	 */
	TernaryStore(){}
	
	/**
	 * Initializes a tree with root node value a.
	 * @param a 
	 */
	TernaryStore(T a)
	{
		root = new TernaryNode<>(a);
		vals.add(root);
	}
	
	/**
	 * Initializes a tree with values in List.
	 * @param a 
	 */
	TernaryStore(ArrayList<T> a)
	{
		TernaryNode <T> curr, root = new TernaryNode<>(a.remove(0));

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
							vals.add(curr.left = new TernaryNode<>(x));
							break inner;
						}
						else curr=curr.left;
						break;
					
					case 0:
						if(curr.center==null)
						{
							vals.add(curr.center = new TernaryNode<>(x));
							break inner;
						}
						else curr=curr.left;
						break;
						
					case 1:
						if(curr.right==null)
						{
							vals.add(curr.right = new TernaryNode<>(x));
							break inner;
						}
						else curr=curr.right;
						break;
				}
			}
		}
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
	class TernaryNode <T>
	{
		/**
		 * Value of current node.
		 */
		private final T val;

		/**
		 * Children of current node.
		 */
		TernaryNode<T> left, center, right;

		/**
		 * Initializes current node
		 * @param val
		 */
		TernaryNode(T val)
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
}
