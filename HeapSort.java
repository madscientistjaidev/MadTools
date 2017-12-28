package MadTools;

import java.util.ArrayList;

/**
 *
 * @author Mad Scientist
 * @param <T>
 */
class HeapSort <T>
{
	NodeComparator<T> nc;
	
	HeapSort(NodeComparator<T> nc)
	{
		this.nc = nc;
	}
	
	ArrayList<T> ascending(ArrayList<T> a)
	{
		Node <T> root = new Node<>(a.remove(0),nc);
		a.forEach((x) -> {root.add(x);});
		return root.getAscending();
	}
	
	ArrayList<T> descending(ArrayList<T> a)
	{
		Node <T> root = new Node<>(a.remove(0),nc);
		a.forEach((x) -> {root.add(x);});
		return root.getDescending();
	}
	
	static abstract class NodeComparator <T>
	{
		abstract boolean compareTo(T a, T b);
	}
	
	class Node <T>
	{
		private final T val;
		
		Node <T> left;
		Node <T> right;
		
		final NodeComparator <T> nc;
		
		Node(T val, NodeComparator <T> nc)
		{
			this.val = val;
			left = null;
			right = null;
			this.nc = nc;
		}
		
		void add(T newVal)
		{
			if(nc.compareTo(val, newVal))
			{
				if(left==null) left = new Node<>(newVal,nc);
				else left.add(newVal);
			}
			
			else
			{
				if(right==null) right = new Node<>(newVal,nc);
				else right.add(newVal);
			}
		}
		
		ArrayList<T> getAscending()
		{
			ArrayList<T> result = new ArrayList<>();
			if(left!=null) result.addAll(left.getAscending());
			result.add(val);
			if(right!=null) result.addAll(right.getAscending());
			
			return result;
		}
		
		ArrayList<T> getDescending()
		{
			ArrayList<T> result = new ArrayList<>();
			if(right!=null) result.addAll(right.getDescending());
			result.add(val);
			if(left!=null) result.addAll(left.getDescending());
			
			return result;
		}
	}
}
