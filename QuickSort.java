package MadTools;

import java.util.ArrayList;

/**
 * @author Mad Scientist
 * @param <T>
 */
public class QuickSort <T>
{
    static long comparisons;
    static long levels;
    static long branches;
    static long leaves;
    
    QuickSort()
    {
        comparisons = 0;
        levels = 0;
        branches = 0;
        leaves = 0;
    }
    
    ArrayList <T> ascending(ArrayList <T> a, long lev)
    {
        levels = (lev>levels) ? lev : levels;
        
        if(a.size()==1) leaves++;
        
        if(a.size()<=1) return a;
        
        T pivot = a.remove(0);
        
        ArrayList <T> left = new ArrayList<>();
        ArrayList <T> right = new ArrayList<>();
        
        for(T x : a)
        {
            if(!compareTo(x, pivot)) left.add(x);
            else right.add(x);
        }
        
        left = ascending(left, lev+1);
        right = ascending(right, lev+1);
        
        branches+=2;
        
        left.add(pivot);
        right.forEach(left::add);
        
        return left;
    }
    
    ArrayList <T> descending(ArrayList <T> a)
    {
        ArrayList <T> result = new ArrayList<>();
        
        if(a.isEmpty()) return result;
        if(a.size()==1) return a;
                
        T pivot = a.get(0);
        ArrayList <T> left = new ArrayList<>();
        ArrayList <T> right = new ArrayList<>();
        
        for(int i = 1; i < a.size(); i++)
        {
            if(compareTo(a.get(i), pivot)) left.add(a.get(i));
            else right.add(a.get(i));
        }
        
        left = descending(left);
        right = descending(right);
        
        result = merge(result, left);
        result.add(pivot);
        result = merge(result, right);
        
        return result;
    }
    
    /**
     * @param a
     * @param b
     * @return 
     */
    boolean compareTo(T a, T b)
    {
        comparisons++;
        
        if(a instanceof Integer) return (Integer)a>(Integer)b;
        else if(a instanceof Double) return (Double)a>(Double)b;
        else if(a instanceof Short) return (Short)a>(Short)b;
        else if(a instanceof Byte) return (Byte)a>(Byte)b;
        else if(a instanceof Character) return (Character)a>(Character)b;
        else if(a instanceof Float) return (Float)a>(Float)b;
        else if(a instanceof String) return ((Comparable<String>) a).compareTo((String)b) == 1;
        else return a.toString().compareTo(b.toString()) == 1;
    }
    
    /**
     * @param a
     * @param b
     * @return 
     */
    ArrayList <T> merge(ArrayList <T> a, ArrayList <T> b)
    {   
        b.forEach(a::add);
        return a;
    }
    
    boolean checkSorted(ArrayList<T> a)
    {
        for(int i = 0; i < a.size()-1; i++) if(compareTo(a.get(i),a.get(i+1))) return false;
        
        return true;
    }
    
    void print(ArrayList<T> a)
    {
        a.forEach((T x) -> {System.out.print(x + ",");});
        System.out.println();
    }
    
    public static void main(String args[])
    {
        QuickSort <Integer> q = new QuickSort<>();
        
        ArrayList <Integer> a = new ArrayList<>();
        
        int size = (int)Math.pow(2, 26);
        int max = Integer.MAX_VALUE;

        for(int i = 0; i < size; i++) a.add((int)(max*Math.random()));
        
        long startTime = System.currentTimeMillis();
        
        //q.print(a);
        
        a = q.ascending(a,0);
        
        //q.print(a);
        
        long stopTime = System.currentTimeMillis();
        
        long elapsedTime = stopTime - startTime;
        double seconds = elapsedTime/1000;
        
        System.out.println("Number of Elements = " + size);
        System.out.println("Sorted = " + q.checkSorted(a));
        System.out.println("Comparisons = " + comparisons);
        System.out.println("Recursion Depth = " + levels);
        System.out.println("Branches = " + branches);
        System.out.println("Leaf Nodes = " + leaves);
        System.out.println("Sorting Time = " + seconds + " seconds");
    }
}