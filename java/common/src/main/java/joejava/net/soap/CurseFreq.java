package joejava.soap;

/**
 * A visitor to DataTable that detetrmines the frequency
 * of a given curse word
 */ 
public class CurseFreq{
	
	/**
	 * Empty constructor
	 */
	public CurseFreq(){}
	
	/**
	 * Returns the frequency of a given curseword from a
	 * given DataTable
	 */
	public static int getFreq(DataTable data,String curse){
		if(data.containsKey(curse))
			return data.get(curse);
		else
			return 0;
	}
}