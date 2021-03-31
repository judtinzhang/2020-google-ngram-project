import java.io.IOException;
import java.util.*;

public class DataCollector
{
	private static int stopper = 0;
	
	/**
	 * @param s
	 * @return words
	 * 
	 * Removes all punctuation and digits, then divides up the words into individual strings of word(s)
	 * 
	 */
	public static String [] convert(String s)
	{
		s = s.replaceAll("\\p{P}", "");
		
		s = s.replaceAll("\\d", "");
		
		String [] words = s.split("\\s+");
		
		return words;
	}
	
	/**
	 * @param s
	 * @return 1600 + maxIndex
	 * @throws IOException
	 * @throws InterruptedException
	 * Estimates the year the document was written in using both optimization and general techniques
	 * NOTE: includes a stopper to adhere to Google Ngram's rate limiting feature
	 */
	public static int approximation (String [] s) throws IOException, InterruptedException
	{
		double [] storage = new double [401];		
		ArrayList<Integer> falseNumbers = new ArrayList<>(); 		
		int maxCounter = 0;
		
		for(String word : s)
		{
			Node n = DataRetriever.dataPoints(word);
			
			double [] data = n.getData();
			
			stopper++;		
			//pauses the program for 10 minutes, then resets the stopper
			if(stopper == 140) 
			{
				stopper = 0;
				Thread.sleep(600000);
			}
			
			//finds the earliest year the document could have been written by finding the maximum counter
			if(n.getCounter() > maxCounter) {     maxCounter = n.getCounter();     }
			
			//adds the data for the word(s) in question to a final storage of frequency data
			for(int i = maxCounter-1600; i < 401; i++) 
			{

				storage[i] += data[i];
				//if the frequency is below 0.000001% for a certain year, the year won't be considered as a possible answer
				if(data[i] < Math.pow(10, -6)) {     falseNumbers.add(i);     }
			}
		}
		
		int maxIndex = 0;
		
		double maximum = 0;
		
		for(int i = maxCounter-1600; i < storage.length; i++)
		{
			//finds the maximum value and the year it occurs (only adds when the maximum occurs at a viable year)
			if(storage[i] > maximum && !falseNumbers.contains(i)) 
			{
				maximum = storage[i];
				maxIndex = i;
			}
		}
		
		if(maxIndex == 0)
		{
			//returns the earliest year possible if it has the highest net frequency
			return maxCounter; 
		}
		
		return 1600 + maxIndex;
	}
}