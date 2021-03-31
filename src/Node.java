public class Node 
{	
	/**
	 * 
	 * word is the word(s) in question
	 * data is the frequency of the word(s) from 1600-2000
	 * counter is the earliest year the word(s) was used
	 * 
	 */
	private String word;
	
	private double [] data = new double [401];
	
	private int counter;
	
	public Node (String w, double [] d, int c)
	{
		word = w;
		
		counter = c;
		
		for(int i = 0; i < 401; i++) //adds values from d to data
		{
			data[i] = d[i];
		}
	}
	
	/**
	 * 
	 * @return word, data, and counter
	 * 
	 * Methods that return the fields
	 * 
	 */
	public String getWord(){	return word;		}
	
	public double [] getData() {	return data;	}
	
	public int getCounter(){	return counter;		}
	
	/**
	 * 
	 * Returns string Node in form:
	 * Word(s):   Counter: 	(1600, x), (1601, y), ...(2000, z) where x, y, and z are percentage frequencies of the word(s)
	 * 
	 */
	public String toString()
	{
		String s = "";
		
		s += "Word(s): " + word + "   Counter: " + counter + "\n";
		
		for(int i = 0; i < 401; i++)
		{
			s += "(" + (1600 + i) + ", " + data[i] + ")\n";
		}
		
		return s;
	}
}