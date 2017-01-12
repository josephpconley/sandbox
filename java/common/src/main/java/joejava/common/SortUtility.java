package joejava.util;
//JSort.java	
//
//Looks at array of numbers and returns desired extrema///////////////////////////////////////
import java.util.Arrays;

public class SortUtility{
		
//Sorts a string into alphabetical order
	public static void sortAlpha(String word)	{
		char[] group=word.toCharArray();
		int[] values=new int[group.length];
		char[] sorted=new char[group.length];
		
			for(int i=0;i<values.length;i++)
				values[i]=Character.getNumericValue(group[i]);	//0-9, then A=10, etc.
			
			for(int i=0;i<group.length;i++){
				for(int j=i;j>0 && values[j-1]>values[j];j--){
					char temp=group[j-1];
					group[j-1]=group[j];
					group[j]=temp;
					
					//update values array for swap
					for(int k=0;k<values.length;k++)
						values[k]=Character.getNumericValue(group[k]);
				}
			}
		word=null;
		word=new String(group);
		System.out.println(word);
	}
	
	
}