package MadTools;

import java.util.ArrayList;

/**
 *
 * @author Mad Scientist
 * @param <T>
 */
abstract class TernaryStore<T>
{
	TernaryStore(){}
	
	TernaryStore(T a) {root = new TernaryNode<>(a);}
	
	/**
	 * Stores values in inserted order.
	 */
	ArrayList<TernaryStore<T>> vals;
	
	/**
	 * Root node of Ternary Tree.
	 */
	TernaryNode<T> root;
	
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
