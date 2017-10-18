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
}