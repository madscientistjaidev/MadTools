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

    // ArrayList stores Stack elements
    private ArrayList<T> a = new ArrayList<>();

    // Maximum size of Stack. 0 for unlimited.
    private int m = 0;

    // Constructors to initialize Stack from various data structures
    Stack() {}
    Stack(ArrayList<T> a) {this.a = a;}
    Stack(Collection<T> a) {this.a = new ArrayList(a);}
    Stack(List<T> a) {this.a = new ArrayList(a);}
    Stack(Stack<T> a) {this.a = reverse(a.popToArrayList());}
    Stack(T[] a) {this.a = new ArrayList(Arrays.asList(a));}

    // Pops and returns element at Stack top.
    T pop() {return a.isEmpty() ? null : a.remove(a.size() - 1);}

    // Views but does not pop element at Stack top.
    T peek() {return a.get(a.size() - 1);}

    // Removes all elements from stack
    void clear() {a.clear();}

    // Compares two Stacks
    boolean equals(Stack<T> x) {return x.hash() == a.hashCode();}

    // Pops elements serially. Allows Stack to be target of Enhanced For Loop
    @Override
    public Iterator iterator() {
        ArrayList<T> x = reverse(a);

        a.clear();

        return x.iterator();
    }

    // Pops Stack elements into an array
    T[] popToArray()
    {
        T[] x = (T[]) (reverse(a)).toArray();

        a.clear();
        return x;
    }

    // Pops Stack elements into an ArrayList
    ArrayList<T> popToArrayList()
    {
        ArrayList<T> x = reverse(a);

        a.clear();
        return x;
    }

    // Checks if Stack is empty
    boolean isEmpty() {return a.isEmpty();}

    // Checks is Stack is full
    boolean hasSpace() {return (m == 0) ? true : a.size() < m;}

    // Returns Hash
    int hash() {return a.hashCode();}

    // Returns size
    int size() {return a.size();}

    // Returns maximum size. 0 for unlimited.
    int getMax() {return m;}

    // Removes size limit
    void clearMax() {m = 0;}

    // Sets size limit
    boolean setMax(int m)
    {
        if (a.size() > m) return false;
        
        this.m = m;
        return true;
    }

    // Sets maximum size and discards all overflowing elements
    int truncate(int m)
    {
        this.m = m;
        
        a.subList(0, m - 1);

        return a.size() - m;
    }

    // Pushes various data structures onto Stack
    boolean push(T s) {return (hasSpace()) ? false : a.add(s);}
    boolean push(T s[]) {return (hasSpace() && (a.size() + s.length) >= m) ? false : a.addAll(Arrays.asList(s));}
    boolean push(ArrayList<T> s) {return (hasSpace() && (a.size() + s.size()) >= m) ? false : a.addAll(s);}
    boolean push(List<T> s) {return (hasSpace() && (a.size() + s.size()) >= m) ? false : a.addAll(s);}
    boolean push(Collection<T> s) {return (hasSpace() && (a.size() + s.size()) >= m) ? false : a.addAll(s);}

    boolean push(Stack<T> s)
    {
        if (hasSpace() && (a.size() + s.size()) >= m) return false;
        
        for (Object x : s.invert())push(s.pop());
        return true;
    }

    boolean push(File f) throws FileNotFoundException
    {
        Scanner           sc = new Scanner(f);
        ArrayList<String> l  = new ArrayList<>();

        while (sc.hasNext()) l.add(sc.nextLine());
        if ((hasSpace() && (a.size() + l.size()) >= m)) return false;
        
        for (String s : l) a.add((T) s);
        return true;
    }

    // Fits elements into Stack and returns number of elements pushed
    int fit(T s[])
    {
        int c = 0;

        for (int i = 0; (i < s.length) && hasSpace() && push(s[i]); c++) {}
        return c;
    }

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

    // Used inernally for Stack operations
    private ArrayList<T> reverse(ArrayList<T> x)
    {
        ArrayList<T> t = new ArrayList<>();

        for (T l : x)t.add(x.remove(x.size() - 1));
        return t;
    }

    // Inverts Stack
    Stack<T> invert()
    {
        Stack<T> s = new Stack<>();

        for (T l : a)s.push(l);
        return s;
    }

    // Writes elements of Stack to a file
    File toFile(String p) throws IOException
    {
        File f = new File(p);
        if (f.createNewFile() == false) throw new IOException("Unable to create file");
        
        PrintStream s = new PrintStream(f);
        for (T x : reverse(a)) s.println(x.toString());

        return f;
    }

    // Returns Integer Stack with Hashcode of each element
    Stack<Integer> hashStack()
    {
        Stack<Integer> s = new Stack<>();
        for (T t : reverse(a)) s.push(t.hashCode());
        return s;
    }
}