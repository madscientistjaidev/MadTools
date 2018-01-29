package MadTools;

import java.util.ArrayList;

/**
 *
 * @author Mad Scientist
 * @param <T>
 */
public class TernaryStore<T>
{
	ArrayList<T> vals;
	
	/**
	 * Used to build heap.
	 * @param <T> 
	 */
	class TernaryNode<T>
	{
		/**
		 * Value of current node.
		 */
		private final T val;

		/**
		 * Children of current node.
		 */
		TernaryNode<T> left;
		TernaryNode<T> center;
		TernaryNode<T> right;

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
