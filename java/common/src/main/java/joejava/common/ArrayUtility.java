package joejava.util;

//Used to perform various methods on arrays

public class ArrayUtility{

//Gets rid of unwanted nulls at end of array (for Strings)
	public static String[] trim (String[] array){
		int len=0;
	
		while(array[len]!=null)
		len++;		
	
		if(len==0)
		len++;
	
		String[] trim=new String[len];
	
		for(int j=0;j<len;j++)
		trim[j]=array[j];
	
		return trim;
	}

//Gets rid of unwanted 0's at end of array (for ints - must specify how much you want trimmed )
	public static int[] trim (int[] array,int cut){
		int[] trim=new int[array.length-cut];
	
		for(int j=0;j<trim.length;j++)
		trim[j]=array[j];
		
		return trim;
	}	

//Produces a given array without repetitions

	public int[] rmCopies(int[] nums){
		int[] finalNums;
		boolean[] copy = new boolean[nums.length];
		int count=0,index=0;
		
		for(int i=0;i<nums.length;i++){
			copy[i]=false;
			int j=0;
			while(copy[i]==false && j<i){
				if(nums[j]==nums[i]){
					copy[i]=true;
					count++;
				}
				j++;
			}
		}

		finalNums=new int[nums.length-count];
		for(int i=0;i<nums.length;i++){
			if(copy[i]==false){
				finalNums[index]=nums[i];
				index++;
			}
		}

		return finalNums;
	}
		
	public String[] sumArrays(String[][] arrays)
	{
	int nArrays = arrays.length;
	int sumLen=0;
	int index=0;

		for(int x=0;x<nArrays;x++)
		{
			if(arrays[x][0]!=null)
			sumLen=sumLen+arrays[x].length;
		}

	String[] sum = new String[sumLen];;
	
		for(int i=0;i<nArrays;i++)
		{
			if(arrays[i][0]!=null)
			{	
				for(int j=0;j<arrays[i].length;j++)
				{
					sum[index]=arrays[i][j];
					index++;
				
				}
			}		
		}
	if(allNull(sum)==true)
	{
		String[] newsum=new String[1];
		return newsum;
	}
	else
	return sum;
	}
	
	
//Checks if all members of an array are null (used in sumArrays)
	public boolean allNull(String[] array){
		boolean test=true;
	
		for(int i=0;i<array.length;i++)
		{
			if(array[i]!=null)
			test=false;
		}
	return test;	
	}
	
	///////////////////////////////////////////////////////////////

}		