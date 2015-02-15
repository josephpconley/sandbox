package joejava.soap;

import java.util.ArrayList;

/**
 * Handles punctuation during the comparison of original
 * and censored strings. 
 *
 * @author Joe Conley
 */
public class StringParser{

	/**
	 * Removes any punctuation from the initial string 
	 * to avoid confusion when identifying the original curse words
	 */
	public static String removePunc(String message){
		char[] oldStr = message.toCharArray();
		ArrayList<Character> newStrList = new ArrayList<Character>();
		for(int i=0;i<oldStr.length;i++){
			if(isPunc(oldStr[i])==false)
				newStrList.add(oldStr[i]);
		}
			
		char[] newStr = new char[newStrList.size()];
		
		for(int i=0;i<newStr.length;i++)
			newStr[i] = newStrList.get(i);
		
		return new String(newStr);
	}
	
	/**
	 * Determines if the given character is punctuation
	 */
	public static boolean isPunc(char ch){

		if(Character.getNumericValue(ch)<0 && ch != (" ").charAt(0) )	//indicates punctuation
			return true;
		else
			return false;
	}
	
}