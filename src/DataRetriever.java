import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.*;

public class DataRetriever
{		
	/**
	 * @param word
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * Returns a Node (word in question; frequency data; minimum possible year)
	 * Searches word usage and sentence structure from 1600 to 2000 (0-400 in code)
	 *  
	 */
	public static Node dataPoints (String word) throws IOException, InterruptedException
	{
		String s = "";
		
		Document doc = Jsoup.connect(
		"https://books.google.com/ngrams/graph?content="
		+word+"&year_start=1600&year_end=2000&corpus=15&smoothing=3&share=&direct_url=t1%3B%2C"
		+word+"%3B%2Cc0").get();

		for (Element tag : doc.getElementsByTag("script")) //outputs the frequency of the word(s)
		{                
	        for (DataNode node : tag.dataNodes()) 
	        {
	        	if (node.getWholeData().contains(word) && node.getWholeData().contains("timeseries"))
	        	{
	        		s = node.getWholeData().substring(node.getWholeData().indexOf(":"), node.getWholeData().indexOf("]"));
	        		s = s.substring(s.indexOf("[")+1);
	        	}
	        }
		}	
		
		String [] input = s.split(",\\s+");	
		
		for(int i = 0; i < input.length; i++) //accounts for empty (0) data entries
		{
			if(input[i].isEmpty())
			{
				input[i] = "0";
			}
		}
		
		double [] data = new double [401];
		
		int counter = 0;
		
		for(int i = 0; i < input.length; i++)  //finds the earliest year the word(s) was used
		{
			if(Double.parseDouble(input[i]) != 0 && counter == 0)
			{
				counter = 1600 + i;
			}
			data[i] = Double.parseDouble(input[i]);
		}	

		return new Node(word, data, counter);        
	}
}