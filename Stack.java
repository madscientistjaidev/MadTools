package MadTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Stack<T> implements Iterable
{

    // Stores Stack elements
    private ArrayList<T> a = new ArrayList<>();

    // Maximum size of Stack. 0 for unlimited.
    private int m = 0;

    /**
	 * Constructor to initialize Stack.
	 */
    Stack() {}
    
	/**
	 * Constructor to initialize Stack.
	 * @param a 
	 */
	Stack(ArrayList<T> a) {this.a = a;}
    
	/**
	 * Constructor to initialize Stack.
	 * @param a 
	 */
	Stack(Collection<T> a) {this.a = new ArrayList(a);}
    
	/**
	 * Constructor to initialize Stack.
	 * @param a 
	 */
	Stack(List<T> a) {this.a = new ArrayList(a);}
    
	/**
	 * Constructor to initialize Stack.
	 * @param a 
	 */
	Stack(Stack<T> a) {this.a = reverse(a.popToArrayList());}
    
	/**
	 * Constructor to initialize Stack.
	 * @param a 
	 */
	Stack(T[] a) {this.a = new ArrayList(Arrays.asList(a));}

    /**
	 * Pops and returns element at Stack top.
	 * @return 
	 */
    T pop() {return a.isEmpty() ? null : a.remove(a.size() - 1);}

    /**
	 * Views but does not pop element at Stack top.
	 * @return 
	 */
    T peek() {return a.get(a.size() - 1);}

    /**
	 * Removes all elements from stack.
	 */
    void clear() {a.clear();}

    /**
	 * Compares two Stacks.
	 * @param x
	 * @return 
	 */
    boolean equals(Stack<T> x) {return x.hash() == a.hashCode();}

    /**
	 * Pops elements serially. Allows Stack to be target of Enhanced For Loop.
	 * @return 
	 */
    @Override
    public Iterator iterator() {
        ArrayList<T> x = reverse(a);

        a.clear();

        return x.iterator();
    }

    /**
	 * Pops Stack elements into an array.
	 * @return 
	 */
    T[] popToArray()
    {
        T[] x = (T[]) (reverse(a)).toArray();

        a.clear();
        return x;
    }

    /**
	 * Pops Stack elements into an ArrayList.
	 * @return 
	 */
    ArrayList<T> popToArrayList()
    {
        ArrayList<T> x = reverse(a);

        a.clear();
        return x;
    }

    /**
	 * Checks if Stack is empty.
	 * @return 
	 */
    boolean isEmpty() {return a.isEmpty();}

    /**
	 * Checks is Stack is full.
	 * @return 
	 */
    boolean hasSpace() {return (m == 0) ? true : a.size() < m;}

    /**
	 * Returns Hash code.
	 * @return 
	 */
    int hash() {return a.hashCode();}

    /**
	 * Returns size.
	 * @return 
	 */
    int size() {return a.size();}

    /**
	 * Returns maximum size. 0 for unlimited.
	 * @return 
	 */
    int getMax() {return m;}

    /**
	 * Removes size limit.
	 */
    void clearMax() {m = 0;}

    /**
	 * Sets size limit.
	 * @param m
	 * @return 
	 */
    boolean setMax(int m)
    {
        if (a.size() > m) return false;
        
        this.m = m;
        return true;
    }

    /**
	 * Sets maximum size and discards all overflowing elements.
	 * @param m
	 * @return 
	 */
    int truncate(int m)
    {
        this.m = m;
        
        a.subList(0, m - 1);

        return a.size() - m;
    }

    /**
	 * Pushes onto Stack.
	 * @param s
	 * @return 
	 */
    boolean push(T s) {return (hasSpace()) ? false : a.add(s);}
    
	/**
	 * Pushes onto Stack.
	 * @param s
	 * @return 
	 */
	boolean push(T s[]) {return (hasSpace() && (a.size() + s.length) >= m) ? false : a.addAll(Arrays.asList(s));}
    
	/**
	 * Pushes onto Stack.
	 * @param s
	 * @return 
	 */
	boolean push(ArrayList<T> s) {return (hasSpace() && (a.size() + s.size()) >= m) ? false : a.addAll(s);}
    
	/**
	 * Pushes onto Stack.
	 * @param s
	 * @return 
	 */
	boolean push(List<T> s) {return (hasSpace() && (a.size() + s.size()) >= m) ? false : a.addAll(s);}
    
	/**
	 * Pushes onto Stack.
	 * @param s
	 * @return 
	 */
	boolean push(Collection<T> s) {return (hasSpace() && (a.size() + s.size()) >= m) ? false : a.addAll(s);}

	/**
	 * Pushes onto Stack.
	 * @param s
	 * @return 
	 */
    boolean push(Stack<T> s)
    {
        if (hasSpace() && (a.size() + s.size()) >= m) return false;
        
        for (Object x : s.invert())push(s.pop());
        return true;
    }

	/**
	 * Pushes onto Stack.
	 * @param f
	 * @return
	 * @throws FileNotFoundException 
	 */
    boolean push(File f) throws FileNotFoundException
    {
        Scanner           sc = new Scanner(f);
        ArrayList<String> l  = new ArrayList<>();

        while (sc.hasNext()) l.add(sc.nextLine());
        if ((hasSpace() && (a.size() + l.size()) >= m)) return false;
        
		l.forEach((s) -> {a.add((T) s);});
        return true;
    }

    /**
	 * Fits elements into Stack and returns number of elements pushed.
	 * @param s
	 * @return 
	 */
    int fit(T s[])
    {
        int c = 0;

        for (int i = 0; (i < s.length) && hasSpace() && push(s[i]); c++) {}
        return c;
    }

	/**
	 * Fits elements into Stack and returns number of elements pushed.
	 * @param f
	 * @return
	 * @throws FileNotFoundException 
	 */
    int fit(File f) throws FileNotFoundException
    {
        Scanner           sc = new Scanner(f);
        ArrayList<String> l  = new ArrayList<>();

        while (sc.hasNext()) l.add(sc.nextLine());

        if (hasSpace() || (l.size() + a.size() <= m))
        {
            push(f);
            return l.size();
        }

        int c = 0;

        for (int i = 0; (i < l.size()) && hasSpace(); i++, c++) push((T) l.get(i));
        
        return c;
    }

    /**
	 * Used internally for Stack operations.
	 * @param x
	 * @return 
	 */
    private ArrayList<T> reverse(ArrayList<T> x)
    {
        ArrayList<T> t = new ArrayList<>();

		x.forEach((_item) -> {t.add(x.remove(x.size()-1));});
        return t;
    }

    /**
	 * Inverts Stack.
	 * @return 
	 */
    Stack<T> invert()
    {
        Stack<T> s = new Stack<>();

		a.forEach((l) -> {s.push(l);});
		
        return s;
    }

    /**
	 * Writes elements of Stack to a file.
	 * @param p
	 * @param delimiter
	 * @return
	 * @throws IOException 
	 */
    File toFile(String p, String delimiter) throws IOException
    {
        File f = new File(p);
        if (f.createNewFile() == false) throw new IOException("Unable to create file");
        
        PrintStream s = new PrintStream(f);
		reverse(a).forEach((x) -> {s.print(x.toString()+delimiter);});

        return f;
    }

    /**
	 * Returns Integer Stack with Hash code of each element.
	 * @return 
	 */
    Stack<Integer> hashStack()
    {
        Stack<Integer> s = new Stack<>();
		
		reverse(a).forEach((t) -> {	s.push(t.hashCode());});
		
        return s;
    }
}