package MadTools;

import java.util.ArrayList;

/**
 * @author Mad Scientist
 * @param <T>
 */
abstract class MergeSort<T>
{
    /**
     * Accepts an ArrayList and returns one sorted in ascending order.
     *
     * @param a
     * @return
     */
    ArrayList<T> ascending(ArrayList<T> a)
    {
        ArrayList<T> left = new ArrayList(a.subList(0,a.size()/2)), right = new ArrayList(a.subList((a.size()/2),a.size())), result = new ArrayList<>();

        if(left.size()>1) left=ascending(left);
        if(right.size()>1) right=ascending(right);

        while(!left.isEmpty() && !right.isEmpty()) result.add(!compareTo(left.get(0),right.get(0)) ? left.remove(0) : right.remove(0));
        
        if(!left.isEmpty()) result.addAll(left);
        if(!right.isEmpty()) result.addAll(right);

        return result;
    }

    /**
     * Accepts an ArrayList and returns one sorted in descending order.
     *
     * @param a
     * @return
     */
    ArrayList<T> descending(ArrayList<T> a)
    {
        ArrayList<T> left = new ArrayList(a.subList(0,a.size()/2)), right = new ArrayList(a.subList((a.size()/2),a.size())), result = new ArrayList<>();

        if(left.size()>1) left=ascending(left);
        if(right.size()>1) right=ascending(right);

        while(!left.isEmpty() && !right.isEmpty()) result.add(!compareTo(left.get(0),right.get(0)) ? left.remove(0) : right.remove(0));
        
        if(!left.isEmpty()) result.addAll(left);
        if(!right.isEmpty()) result.addAll(right);

        return result;
    }

    /**
     * This function returns true if a>b. It is meant to make sorting possible
     * no matter what types or objects are used. It should be implemented in a
     * sub class or anonymous inner class.
     *
     * @param a
     * @param b
     * @return
     */
    abstract boolean compareTo(T a, T b);

    /**
     * Example for ~1 million randomly generated integers of size 0 to
     * Integer.MAX_VALUE.
     *
     * @param args
     */
    public static void main(String args[]) {
        //Create anonymous inner class to define compareTo function.
        MergeSort<Integer> q = new MergeSort<Integer>() {
            @Override
            boolean compareTo(Integer a, Integer b) {
                return a > b;
            }
        };

        //Create ArrayList
        ArrayList<Integer> a = new ArrayList<>();

        //Size of array
        int size = (int) Math.pow(2, 20);

        //Maximum value of elements
        int maxVal = Integer.MAX_VALUE;

        //Generate random integers.
        for (int i = 0; i < size; i++) {
            a.add((int) (maxVal * Math.random()));
        }

        //Sort.
        a = q.ascending(a);

        System.out.println("Sorted.");
    }
}
