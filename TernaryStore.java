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
	
	class TernaryNode <T>
	{
		private final T val;

		TernaryNode<T> left;
		TernaryNode<T> center;
		TernaryNode <T> right;

		TernaryNode(T val)
		{
			this.val = val;
			left = null;
			center = null;
			right = null;
		}
	}
}
