package MadTools;

import java.util.ArrayList;

public class CollatzConjecture
{
	ArrayList <Integer> find(int n)
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
}