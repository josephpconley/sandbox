package joejava.soap;

import java.util.Enumeration;

/**
 * A visitor to the DataTable class which returns
 * a total count of curse words for a given TextFilter session.
 *
 *@author Joe Conley
 */
public class TotalCurses{
	
	/**
	 * Empty constructor
	 */
	public TotalCurses(){}
	
	/**
	 * Returns the total number of curse words from the 
	 * given DataTable
	 */
	public static int getNum(DataTable data){
		int count=0;
		
		Enumeration<Integer> e = data.elements();
		while(e.hasMoreElements())
			count += (int)e.nextElement();
			
		return count;
	}

}