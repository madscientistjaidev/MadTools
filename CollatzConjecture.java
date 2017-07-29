package MadTools;

import java.util.ArrayList;

public class CollatzConjecture
{
	ArrayList <Integer> findSequence(int n)
	{
		ArrayList <Integer> result = new ArrayList<>();
		
		if(n<1) return result;
		
		while(n!=1)
		{
			result.add(n);
			
			if(n%2==0) n /= 2;
			else n = 3*n+1;
		}
		
		result.add(1);
		return result;
	}
	
	public static void main(String args[])
	{
		int maxVal = (int)Math.pow(2, 22);
		int size = 25;
		int val;
		
		CollatzConjecture c = new CollatzConjecture();
		ArrayList<Integer> result;
		
		for(int i = 0; i < size; i++)
		{
			val = (int) (maxVal*Math.random());
			
			System.out.print(val + " -> ");
			
			result = c.findSequence(val);
			
			for(Integer x : result)
				System.out.print(x + ", ");
			
			System.out.println();
		}
	}
}