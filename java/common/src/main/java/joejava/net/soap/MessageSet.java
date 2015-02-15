package joejava.soap;

import java.util.*;

/**
 * Set of messages (original string, censored string)
 * used during the TextFilter process.  Also stores instances
 * of curse words to be given to the DataTable.  Uses StringParser
 * to eliminate punctuation from the original string, allowing for
 * proper comparison of curse words. 
 *
 * @author Joe Conley
 */

public class MessageSet{

	public ArrayList<String> curseWords = new ArrayList<String> ();
	public ArrayList<Integer> curseCount= new ArrayList<Integer> ();
	private String origText,replyText;	
	private DataTable data;	
	
	/**
	 * Empty constructor
	 */	
	public MessageSet(){}
	
	/**
	 * Save results of processStrings() to the DataTable
	 */

	public void add(String origText, String replyText,DataTable data){
		this.origText=origText;
		this.replyText=replyText;
		this.data=data;
		this.processStrings();
		
		for(int i=0;i<curseWords.size();i++)
			data.put(curseWords.get(i),curseCount.get(i));		
	}
	/**
	 * Takes the original and censored strings, processes the strings,
	 * and sends the results to the given DataTable
	 */
	public void processStrings(){
			
		if(replyText.length()>0){
			StringTokenizer oldST = new StringTokenizer(StringParser.removePunc(origText));
			StringTokenizer newST = new StringTokenizer(replyText);
						
			while(newST.hasMoreTokens()){
				String newWord = newST.nextToken();
				if(newWord.substring(0,1).equals("[")==false && StringParser.isPunc(newWord.charAt(0))){	
					//ignore stray punctuation from an expletive
				}
				else{
					String oldWord = oldST.nextToken();  					
					if(newWord.equals("[Explicit]")){
						int index = curseWords.indexOf(oldWord);
						
						//either in the DataTable already or previously detected in this set
						if(data.containsKey(oldWord) || index > -1){
							int num = curseCount.get(index);
							num++;
							curseCount.set(index,num);
						}
						else{
							curseWords.add(oldWord);
							curseCount.add(1);
						}	
					}
				}
			}	
		}
		else
			System.out.println("Invalid message set!");
	}
	
}