package joejava.soap;

import java.util.*;

/**
 * Represents a Hashtable with curse words as the key and the corresponding word 
 *	count as its value.  Visitors supply the DataTable with these values and
 * handle query requests.
 *
 *	@author Joe Conley
 */
public class DataTable extends Hashtable<String,Integer>{

	/**
	 * Empty constructor
	 */
	public DataTable(){}

	/**
	 * Returns an array of the curse words stored in the Hashtable
	 * sorted alphabetically.
	 */
	public String[] getSortedCurses(){
		return SortedCurses.array(this);
	}		
	
	/**
	 * Returns the unique number of curse words in the DataTable
	 */
	public int getNumUniqCurses(){
		return UniqCurses.getNum(this);
	}
	
	/**
	 * Returns the total number of curse words in the DataTable
	 */
	public int getTotalCurses(){
		return TotalCurses.getNum(this);
	}

	/**
	 * Gets the word count for the given curse word.
	 */
	public int getCurseFreq(String curse){
		return CurseFreq.getFreq(this,curse);
	}
}