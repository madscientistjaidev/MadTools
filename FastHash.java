package MadTools;

/**
 *
 * @author Mad Scientist
 */
public class FastHash
{
    public String binHash(String s, int l)
    {
	String h = "";
	
	for(int i = 0; i < s.length(); i++)
	    h += Integer.toBinaryString(s.charAt(i));
	
	while(h.length()!=l)
	{
	    if(h.length()<l) h += Integer.toBinaryString(h.length());
	    else h = XOR(h.substring(0, l), h.substring(l+1, h.length()-1));
	}
	
	return XOR(h, reverse(h));
    }
    
    private String reverse(String s)
    {
	String h = "";
	
	for(int i = 0; i < s.length(); i++)
	    h = s.charAt(i) + h;
	
	return h;
    }
    
    private String XOR(String a, String b)
    {
	while(a.length()!=b.length())
	    if(a.length()>b.length()) b = "0" + b;
	    else a = "0" + a;
	
	String c = "";
	
	for(int i = 0; i < a.length(); i++)
	    c += a.charAt(i)=='0' ? b.charAt(i) : (b.charAt(i)=='0' ? "1" : "0");
	
	return c;
    }
	
    public String hexHash(String s, int l)
    {
	return toHex(binHash(s,l));
    }
    
    String toHex(String s)
    {
	String h = "";
	
	while(s.length()%4!=0) s = "0" + s;
	
	char n[] = s.toCharArray();
	
	for(int i = n.length-1; i>=0; i-=4)
	    h=h.concat(hexDigitLookup("" + n[i-3] + n[i-2] + n[i-1] + n[i]));
	
	return h;
    }
    
    private String hexDigitLookup(String d)
    {
	if(d.length()!=4) return "";
	
	String [][] table = {
				{"0000","0"},
				{"0001","1"},
				{"0010","2"},
				{"0011","3"},
				{"0100","4"},
				{"0101","5"},
				{"0110","6"},
				{"0111","7"},
				{"1000","8"},
				{"1001","9"},
				{"1010","A"},
				{"1011","B"},
				{"1100","C"},
				{"1101","D"},
				{"1110","E"},
				{"1111","F"},
			    };
	
	for(String [] pair : table)
	    if(pair[0].equals(d))
		return pair[1];
	
	return "";
    }
	
    public static void main(String args[])
    {
	FastHash f = new FastHash();
	
	int width = 128;
	
	System.out.println(f.hexHash("Hello World", width));
	System.out.println(f.hexHash("Hello Worlds", width));
	System.out.println(f.hexHash("Hello_World", width));
	System.out.println(f.hexHash("hello world", width));
    }
}
