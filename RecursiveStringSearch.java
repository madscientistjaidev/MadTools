package MadTools;

import java.util.ArrayList;

/**
 * Provides various string search functions.
 * Uses Knuth-Morris-Pratt algorithm.
 * @author Mad Scientist
 */
public class RecursiveStringSearch
{    
    /**
     * Accepts two strings "s" and "w", and recursively searches "s" for "w".
     * Returns index of first occurrence of "w" if found, and -1 if not found.
     * @param s
     * @param w
     * @return 
     */
    int matchFirst(String s, String w) {return s.equals(w) ? 0 : doMatchFirst(s, w, s.length(), w.length());}
    
    private int doMatchFirst(String s, String w, int sLen, int wLen)
    {
        if(w==null || s==null) return -1;
        if(w.length()==0 || s.length()==0 || w.length()>s.length()) return -1;
        
        if(w.charAt(0)==s.charAt(0))
            if(w.length()==1) return (1+sLen-wLen-s.length());
            else return doMatchFirst(s.substring(1),w.substring(1),sLen,wLen);
        else return (doMatchFirst(s.substring(1),w,sLen,wLen));
    }
	
	/**
	 * Tail Recursive Form.
	 * @param s
	 * @param w
	 * @param sLen
	 * @param wLen
	 * @return 
	 */
	private int doMatchFirstTR(String s, String w, int sLen, int wLen)
    {
        return (w==null || s==null) ? -1 :
			((w.length()==0 || s.length()==0 || w.length()>s.length()) ?  -1 :
				((w.charAt(0)==s.charAt(0)) ? ((w.length()==1)? (1+sLen-wLen-s.length()) :
					doMatchFirstTR(s.substring(1),w.substring(1),sLen,wLen)) :
						((doMatchFirstTR(s.substring(1),w,sLen,wLen)))));
    }
    
    boolean contains(String s, String w) {return matchFirst(s, w)!=-1;}
    
    boolean containsMultiple(String s, String w) {return contains(s,w) ? matchFirst(s,w)!=matchLast(s,w) : false;}
    
    /**
     * Accepts two strings "s" and "w", and recursively searches "s" for "w".
     * Returns index of last occurrence of "w" if found, and -1 if not found.
     * @param s
     * @param w
     * @return 
     */
    int matchLast(String s, String w) {return s.equals(w) ? 0 : s.length()-w.length()-doMatchFirst(reverse(s), reverse(w), s.length(), w.length());}
    
    ArrayList<Integer> matchAll(String s, String w, int sLen, int wLen)
    {
        ArrayList<Integer> result = new ArrayList<>();
        
        int pos = 0;
        
        while(pos!=-1)
        {
            pos = matchFirst(s,w);
            
            if(pos!=-1)
            {
                result.add(pos+(wLen*(result.size())) + (result.isEmpty() ? 0 : (result.get(result.size()-1))));
                s = s.substring(pos+wLen);
            }
        }
        
        return result;
    }
    
    int count(String s, String w)
    {
        int count = 0;
        int pos = 0;
        
        while(pos!=-1)
        {
            pos = matchFirst(s,w);
            
            if(pos!=-1)
            {
                count++;
                s = s.substring(pos+w.length());
            }
        }
        
        return count;
    }
    
    String reverse(String s)
    {
        char[] sa = s.toCharArray();
        String sr = "";
        for(char x : sa) sr = x + sr;
        
        return sr;
    }
    
    public static void main(String args[])
    {
        String s = "the end of the beginning of the end";
        String w = "end";
        
        RecursiveStringSearch k = new RecursiveStringSearch();
        
        System.out.println(k.matchFirst(s,w));
        System.out.println(k.matchLast(s,w));
        System.out.println(k.containsMultiple(s,w));
        System.out.println(k.count(s,w));
    }
}