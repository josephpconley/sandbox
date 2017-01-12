package joejava.soap;

import java.util.*;

/**
 * Handles all queries to the DataTable
 *
 * @author Joe Conley
 */
public class QueryHandler{
	
	/**
	 * Empty constructor.
	 */
	public QueryHandler(){}
	
	/**
	 * Determines if the string is a valid query
	 * (i.e. if it begins with "exit" or "stat")
	 */	
	public static boolean isCommand(String str){
		String command = str.substring(0,4);
		if(command.equals("exit") || command.equals("stat"))
			return true;
		else
			return false;
	}
	/**
	 * Performs the query to the DataTable data described in
	 * origText.  Returns an error message if query is invalid
	 */ 
	public static String executeQuery(String origText,DataTable data){
		StringTokenizer st = new StringTokenizer(origText);
		st.nextToken(); //skip the word "stat"
		String command = st.nextToken();
		String answer=null;
			
		if(command.equals("count")){
			answer ="The filter has detected "+data.getTotalCurses()+
													" total curse words thus far.";
		}
		
		else if(command.equals("uniqcount")){
			answer="The filter has detected "+data.getNumUniqCurses()+
										" unique curse words thus far.";					
		}									
		
		else if(command.equals("sortedlist")){
			answer =Arrays.toString(data.getSortedCurses());
		}
		
		else if(command.equals("freq")){
			try{
				String curse = st.nextToken();
				answer="There are "+data.getCurseFreq(curse)+
									" occurrences of the word "+curse+".";
			}
			catch(NoSuchElementException e){
				System.out.println("You forgot to provide a curse word.");
			}	
		}
		else if(command.equals("hash")){
			answer=data.toString();
		}
		else
			answer="Invalid query.  See manual for proper syntax.";
			
		return answer;		
	}
}