import java.io.IOException;
import java.util.*;

public class Main 
{
	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * The Main function of the algorithm.
	 * Enter the document into the Console and the method will return the corresponding historical time frame
	 * 
	 * NOTE: 	Google Ngram has a rate limiting feature: 150 words can be accessed every ~10 minutes
	 * 			Thread.sleep is used to pause the code for 10 minutes to adhere to Google Ngram's properties	
	 * 
	 */
	public static void main(String[] args) throws IOException, InterruptedException
	{		
		Scanner kb = new Scanner (System.in);
		
		for(int i = 0; i < 10; i++)
		{
			//prints out the approximated year
			System.out.println(DataCollector.approximation(DataCollector.convert(kb.nextLine())));
			
			// buffer time for Google nGram
			Thread.sleep(600000);
		}
	}
}