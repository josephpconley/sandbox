package joejava.games.crypto;

import java.util.Random;

import joejava.util.LangUtility;

public class CryptoUtility{

	boolean result;
	//joejava.lang.Dictionary d = new Dictionary(wordbank);

//used to produce simple substitution cipher	
	public String Code(String text){
		char cipher[] = singleSubMap();			//Random mapping
		String plaintext,newcrypt;
		String[] cryptogram = new String[27];	//array used as background of enciphering, first entry being the plaintext, each subsequent entry performing encryption

		// Receives phrase and performs encryption
		cryptogram[0]=text;

		int p=1;
		while(p<27){
		cryptogram[p]=cryptogram[p-1].replace(LangUtility.toArray()[p-1],Character.toLowerCase(cipher[p-1]));
		p++;
		}

		newcrypt=cryptogram[26].toUpperCase();

		return newcrypt;
	}
	
    //Creates a single substitution cipher for simple encrypting/decrypting
	public char[] singleSubMap(){
	
		Random generator = new Random();
		int count = 0;
		int c=0;
		char OK = 'n';							// seminal value used to verify uniqueness of mapping choice
		char badCheck = 'n';					//	value used to see if cipherindex is bad(randomly picked resulting in last number matching index) or not
		int cipherindex[] = new int[26];		// numbers from 1-26 used to perform mapping
		char cipher[] = new char[26];		// encipher array

//Initializes cipher array

		for(int i=0;i<26;i++)
			cipherindex[i]=0;

//Creates index of pseudorandom numbers from 1 to 26

	cipherindex[0]=generator.nextInt(26)+1;
	
	while(badCheck=='n')
	{
		for(int j=1;j<26;j++)
		{
			OK = 'n';
			while(OK == 'n')		
			{	cipherindex[j]=generator.nextInt(26)+1;			//Pick random number from 1 to 26
				count=0;

				if(cipherindex[j] == (j+1))	//Prevents Identity mapping!!! (e.g. A to A)		--> This is why we can't use int[] randArray in JMath
					count++;				
				else
					{	for(int k=0;k<j;k++)
						{
						if(cipherindex[j] == cipherindex[k])			//Checks to see if choice matches previous choices
						count++;
						}
					}

				if(count==0 && j!=25)
				OK = 'y';
		
				if(count==0 && j==25 && cipherindex[25]==25)
				OK='y';
				
				if(count==0 && j==25 && cipherindex[25]!=25)
				{	OK='y';
					badCheck='y';}	

				if(count>0 && j==25 && cipherindex[25]==25)
					OK='y';
			
			}			
		}
	}
	

//Creates cipher based on pseudorandomarray

	for(int n=0;n<26;n++)
	cipher[n]=LangUtility.toArray()[cipherindex[n]-1];	


	return cipher;

	}
}
/*	
//Sorts an array of enciphered words of a cryptogram from least possible sols to most//		//amend this to just sort ascending
///////////////////////////////////////////////////////////////////////////////////////	
	
	public String[] LeastToGreat(String[] str){
		JMath math = new JMath();			//	Class used to return the min of an array of numbers
		int index1[]=new int[str.length];		// Array of String lengths for str
		int index2[]=new int[str.length];
		String [] sorted = new String[str.length];		// Sorted array
		int c;

		for(int x=0;x<str.length;x++)
		index2[x]=999;			//used to eliminate numbers once sorted as least
	
		for(int a=0;a<str.length;a++)	
		index1[a]=d.allNWords(str[a]).length;		// Puts number of possible words into an array
	
			
		for(int b=0;b<str.length;b++)
		{		
			c=0;
			while(index2[b]==999)
			{
				if(index1[c]==math.min(index1))
				{
				index2[b]=index1[c];
				index1[c]=999;
				sorted[b]=str[c];
				}				
				
				c++;
			}
		}

	return sorted;		
	}
	
	
//Returns a map used to map solutions of a cryptogram

	public int[] sortedindex(String orig[], String sorted[])		//same lengths
	{
	
	int[] index=new int[orig.length];
	
	for(int i=0;i<orig.length;i++)
	{
		for(int j=0;j<sorted.length;j++)
		{
			if(orig[i].equals(sorted[j]))
			index[i]=j;
	
	
		}
	}
	
	return index;

	}
	
	

//Takes token and removes any punctuation and white space - do we need this?!?!?!
	
	public String trimToken(String text)
	{
		char[] chartext;
		chartext=text.toCharArray();
		String trimtext=" ";
		
		for(int d=0;d<chartext.length;d++)
		{
			if(Character.isLetter(chartext[d])==true)
			trimtext=trimtext.concat(text.substring(d,d+1));				
		}
		
		trimtext=trimtext.trim();
		
		return trimtext;
	}
	
	
//Returns an array trimmed to the first n entries//
///////////////////////////////////////////////////
	
public String[] trimArray(String[] array,int n)
{

String[] words = new String[n];

if(array.length<n+1)
words=array;
else
{
	for(int i=0;i<n;i++)
	words[i]=array[i];
}

return words;

}	
	
	
//Count number of non-word tokens (i.e. puncutation, spaces)
	
	public int numPunc(String text)
	{
		StringTokenizer tokenizer;
		tokenizer = new StringTokenizer(text);
		int index = tokenizer.countTokens();
		
		int punc=0;
		
		for(int f=0;f<index;f++)
		{
			if(trimToken(tokenizer.nextToken()).length()==0)
			punc++;
		}
		
		return punc;
		
	}
	
//Determines if the given string has any punctuation//
//////////////////////////////////////////////////////	
	
	public boolean hasPunc(String text)
	{
	boolean result;
	
	if(text.endsWith(",")==true || text.endsWith(".")==true || text.endsWith("?")==true || text.endsWith(";")==true || text.endsWith("!")==true || text.endsWith(")")==true || text.startsWith("("))
	return result=true;
	else
	return result=false;
		
	}
	
//Concats string to beginning of current string//
/////////////////////////////////////////////////

	public String concatStart(String add, String text)
	{
	
	String word=add;
	word=word.concat(text);
	
	return word;	
	}	
	
		
	
//Determines if the given text is a sentence//
//////////////////////////////////////////////	
	
	public boolean isSentence(String sent)
{
	StringTokenizer token;
	
		token = new StringTokenizer(sent);
		int length = token.countTokens();
		String[] origarray = new String[length];
		


		int b=0;							
												//Reads each word from tokenizer into an array
		while(token.hasMoreTokens())			
		{
			String t=token.nextToken();
		
			origarray[b]=trimToken(t);
							
			b++;		
		}
		
		int count=0;
		boolean result;
		
		for(int i=0;i<origarray.length;i++)
		{
			for(int j=0;j<d.numwords;j++)
			{
				if(d.bank[j].equals(origarray[i]))
				count++;
			}
		}
		
		if(count==origarray.length)
		result=true;
		else
		result=false;
		
		return result;		
		}

}
*/
