import java.util.ArrayList;

/**
 *
 * @author Mad Scientist
 * @param <T>
 */
abstract class HeapSort <T>
{
    NodeComparator<T> nc;
	
	HeapSort()
	{
	}
	
	HeapSort(NodeComparator<T> nc)
	{
		this.nc = nc;
	}
    
    abstract boolean compareTo(T a, T b);

    ArrayList<T> ascending(ArrayList<T> a)
    {
        Node <T> root = new Node<>(a.remove(0));
        Node <T> curr = root;

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

        return root.getAscending();
    }
    
    ArrayList<T> descending(ArrayList<T> a)
	{
		Node <T> root = new Node<>(a.remove(0),nc);
		a.forEach((x) -> {root.add(x);});
		return root.getDescending();
	}

    public static void main(String args[])
    {
        //Create anonymous inner class to define compareTo function.
        HeapSort<Integer> h = new HeapSort<>()
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

    static abstract class NodeComparator <T>
    {
	abstract boolean compareTo(T a, T b);
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
