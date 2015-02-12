package joejava.soap;

import java.util.*;

/**
 * A visitor to the DataTable which provides a sorted array
 * of curse words
 */

public class SortedCurses{

	/**
	 * Empty constructor
	 */
	public SortedCurses(){}

	/**
	 * Returns an array of curse words from the given
	 * DataTable sorted alphabetically
	 */
	public static String[] array(DataTable data){
		Enumeration<String> e = data.keys();
		String[] keys = new String[data.size()];	
		
		int index=0;
		while(e.hasMoreElements()){
			keys[index]=(String)e.nextElement();
			index++;
		}
		Arrays.sort(keys);
				
		return keys;
	}
}