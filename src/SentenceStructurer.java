import java.util.*;

public class SentenceStructurer 
{
	public static void main (String [] args)
	{
		Scanner kb = new Scanner (System.in);
		
		String documents = kb.nextLine();

		ArrayList<String> nouns = new ArrayList<>();		
		ArrayList<String> verbs = new ArrayList<>();		
		ArrayList<String> adjectives = new ArrayList<>();
		ArrayList<String> prepositions = new ArrayList<>();
		
		String initialData = kb.nextLine(); //data comes in the form of: "(word)\(part of speech)"
		
		while(!initialData.equals("completed!"))
		{						
			String word = initialData.substring(0, initialData.indexOf("\\"));			
			String partOfSpeech = initialData.substring(initialData.indexOf("\\")+1);
			
			if(partOfSpeech.equals("N") || partOfSpeech.equals("r"))
			{     nouns.add(word.toLowerCase());     } //adds nouns or pronouns		
			else if (partOfSpeech.equals("V") || partOfSpeech.equals("t") || partOfSpeech.equals("i"))
			{     verbs.add(word.toLowerCase());     } //adds verbs, transitive verbs, and non transitive verbs
			else if(partOfSpeech.equals("A") || partOfSpeech.equals("v"))
			{     adjectives.add(word.toLowerCase());     } //adds adverbs and adjectives
			else if(partOfSpeech.equals("P")) 
			{     prepositions.add(word.toLowerCase());     } //adds prepositions
			
			initialData = kb.nextLine();
		}		
		double [] total = partsOfSpeechCalculator(documents, nouns, verbs, adjectives, prepositions);
		
		total[4] = clauseCounter(documents); //total now contains the number of clauses in the document
		
		for(int i = 0; i < total.length; i++)
		{
			System.out.print(total[i]+" ");
		}
	}
	
	public static double [] partsOfSpeechCalculator (String s, ArrayList<String> nouns, ArrayList<String> verbs, ArrayList<String> adjectives, ArrayList<String> prepositions)
	{
		double [] data = new double [5];
		
		String [] words = DataCollector.convert(s.toLowerCase());
		
		for(int i = 0; i < words.length; i++)
		{
			if(nouns.contains(words[i])){     data[0] += 1;     } //adds if word is a noun
			else if(verbs.contains(words[i])){     data[1] += 1;     } //adds if word is a verb
			else if(adjectives.contains(words[i])){     data[2] += 1;     }	//adds if word is a adjective/adverb
			else if(prepositions.contains(words[i])){     data[3] += 1;     } //adds if word is a preposition
		}

		return data;
	}
	
	public static double clauseCounter (String s)
	{
		double counter = 0;
		for(int i = 0; i < s.length(); i++)
		{
			if(s.substring(i, i+1).equals(",") || s.substring(i, i+1).equals(";"))
			{
				counter++;
			}
		}
		
		return counter;
	}
	
	public static double distanceToCentury (double [] document, double [] century)
	{		
		double counter = 0;
		
		for(int i = 0; i < 4; i++)
		{
			counter += Math.pow(document[i] - century[i], 2);
		}
		return Math.sqrt(counter);
	}
}