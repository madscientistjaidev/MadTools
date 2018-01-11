package MadTools;

import java.util.ArrayList;

/**
 * @author Mad Scientist
 * @param <T>
 */
abstract class JumpSort<T>
{
	/**
	 * Accepts an ArrayList and returns one sorted in ascending order.
	 *
	 * @param a
	 * @return
	 */
	ArrayList <T> ascending(ArrayList<T> a)
	{
		if(a==null) return new ArrayList<>();
		if(a.size()<2) return a;

		ArrayList<ArrayList<T>> runs = new ArrayList<>();
		ArrayList<T> run, x, y;

			while(a.size()>1)
			{
				run = new ArrayList<>();

				run.add(a.remove(0));

				if(compareTo(run.get(0),a.get(0)))
				{
					for(int i = 0; i<a.size(); i++)
						if(compareTo(run.get(0),a.get(i)))
							run.add(0,a.remove(i--));
				}

				else
				{
					for(int i = 0; i<a.size(); i++)
						if(!compareTo(run.get(run.size()-1),a.get(i)))
							run.add(a.remove(i--));
				}

				//Run merging - EXPERIMENTAL
				if(false && !runs.isEmpty())
				{
					for(ArrayList <T> z : runs)
					{
						if(compareTo(run.get(0),z.get(z.size()-1)))
						{
							z.addAll(run);
							break;
						}

						else if(compareTo(z.get(0),run.get(run.size()-1)))
						{
							z.addAll(0, run);
							break;
						}
					}
				}

				runs.add(run);
			}

			if(!a.isEmpty()) runs.add(a);

			while(runs.size()>1)
			{
				x = runs.remove(0);
				y = runs.remove(0);

				if(compareTo(x.get(0),y.get(y.size()-1)))
				{
					y.addAll(x);
					runs.add(y);
				}

				else if(compareTo(y.get(0),x.get(x.size()-1)))
				{
					x.addAll(y);
					runs.add(x);
				}

				else
				{
					run = new ArrayList<>();

					if(compareTo(x.get(0),y.get(0)))
						while(compareTo(x.get(0),y.get(0)))
							run.add(y.remove(0));

					else
						while(!compareTo(x.get(0),y.get(0)))
							run.add(x.remove(0));

					while(!x.isEmpty() && !y.isEmpty())
						run.add(!compareTo(x.get(0),y.get(0)) ? x.remove(0) : y.remove(0));

					run.addAll(x);
					run.addAll(y);

					runs.add(run);
				}
			}

		return runs.get(0);
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
		JumpSort<Integer> j = new JumpSort<Integer>()
		{
			@Override
			boolean compareTo(Integer a, Integer b) {return a > b;}
		};

			boolean test = false;

		//Create ArrayList
		ArrayList<Integer> a = new ArrayList<>();

		//Size of array
		int size = test ? 30 : (int)Math.pow(2, 20);

		//Maximum value of elements
		int maxVal = test ? 100 : Integer.MAX_VALUE;

		//Generate random integers.
		for (int i = 0; i < size; i++)
				a.add((int) (maxVal * Math.random()));

			//Sort.
			System.out.print(test ? a + "\n" : "");
			a = j.ascending(a);
			System.out.print(test ? a + "\n" : "");

			for(int i = 0; i < a.size()-1; i++)
				if(j.compareTo(a.get(i),a.get(i+1)))
				{
					System.out.print("Not Sorted.\n");
					return;
				}

		System.out.print("Sorted.\n");
	}
}
