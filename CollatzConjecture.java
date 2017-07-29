package MadTools;

import java.util.ArrayList;

public class CollatzConjecture
{
	/**
	 * Accepts an integer and returns an ArrayList of the hailstorm numbers rooted at that integer.
	 * @param n
	 * @return 
	 */
	ArrayList <Integer> findSequence(int n)
	{
		ArrayList <Integer> result = new ArrayList<>();
		
		if(n<1) return result;
		
		result.add(n);
		
		do
			result.add(result.get(result.size()-1)%2==0 ?
				result.get(result.size()-1)/2 :
				3*(result.get(result.size()-1))+1);
		while(result.get(result.size()-1)!=1);
		
		return result;
	}
	
	/**
	 * Example.
	 * Generates 25 random numbers between 0 and 2^20.
	 * Prints hailstorm sequence and its length for each of them.
	 * @param args 
	 */
	public static void main(String args[])
	{
		int maxVal = (int)Math.pow(2, 20);
		int size = 25;
		int val;
		
		CollatzConjecture c = new CollatzConjecture();
		ArrayList<Integer> result;
		
		for(int i = 0; i < size; i++)
		{
			val = (int)(maxVal*Math.random());
			result = c.findSequence(val);
			
			System.out.println(val + " : " + result.size() + " -> " + result);
		}
	}
}