package joejava.soap;


/**
 * A visitor to the DataTable class which returns the
 * unique number of curse words
 *
 *@author Joe Conley
 */
public class UniqCurses{

	/**
	 * Empty constructor
	 */
	public UniqCurses(){}
	
	/**
	 * Returns the number of unique curse words
	 * in the given DataTable
	 */ 
	public static int getNum(DataTable data){
		return data.size();
	}
}