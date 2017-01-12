package joejava.util;



public class LangUtility{
	/*
	public static char[] alpha = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};		// alphabet
	public static char[] num = {'0','1','2','3','4','5','6','7','8','9'};
	public static String type = "english"; //i.e. english
	public static String path="data/lang/";
	public static String[] tables = {"n1","n2","n3","n4","n5","n6","n7","n8","n9","n10","n11","n12","n13","n14","n15",
									"n16","n17","n18","n19","n20","n21","n22","n23"};
	
	//Method of returning nTH numeric value of letter in string	
	public static int alphaNum(String str, int n){
		char[] word = str.toCharArray();
		return Character.getNumericValue(word[n])-10;	
	}
	
	//Word difference
	public static int wordDiff(String str1,String str2)	{
		char[] char1=str1.toCharArray();
		char[] char2=str2.toCharArray();
		int diff=0;

		for(int a=0;a<char1.length;a++){
			if(char1[a]!=char2[a])
				diff++;
		}
		return diff;
	}
	
	//Reducer takes first string, and eliminates all Cap letters of reducer from first string (yet another cryptogram process)
	public static String wordReducer(String str1,String reducer){
		char[] char1=str1.toCharArray();
		char[] char2=reducer.toCharArray();
		int[] goodArray=new int[char1.length];
		int count,index;
		boolean check=false;

		for(int j=0;j<char1.length;j++){
			goodArray[j]=0;
		}
		
		for(int a=0;a<char1.length;a++){
			count=0;
			for(int b=0;b<char2.length;b++){
				if(char1[a]==char2[b] && Character.isUpperCase(char1[a])==true && Character.isUpperCase(char2[b])==true)
					count++;
			}
			if(count>0)
				goodArray[a]=count;
		}
		
		String result = new String("x");
		index=0;

		while(check==false && index < char1.length){				// If whole word is consumed, need backup
			if(goodArray[index]==0){
				result=result.replace('x',char1[index]);
				check=true;
			}else{
				index++;
			}
		}

		for(int c=index+1;c<goodArray.length;c++){
			if(goodArray[c]==0)
				result=result.concat(Character.toString(char1[c]));
		}

		if(result.equals("x"))
			result="none";
		
		return result;
	}


	//Count upper
	public static int countUpper(String str){
		char[] strArray=str.toCharArray();
		int count =0;

		for(int b=0;b<strArray.length;b++){
			if(Character.isUpperCase(strArray[b])==true)
				count++;
		}
		return count;
	}

	//Count lower
	public static int countLower(String str){
		char[] strArray=str.toCharArray();
		int count =0;
		
		for(int b=0;b<strArray.length;b++){
			if(Character.isLowerCase(strArray[b])==true)
				count++;
		}
		return count;
	}

	//Method which returns the numeric value of each letter of a String in an array

	public static int[] alphaMap(String str){
		char[] wordchar;
		int[] index=new int[str.length()];
		wordchar=str.toCharArray();

		for(int j=0;j<str.length();j++)
			index[j]=Character.getNumericValue(wordchar[j])-10;		

		return index;
	}	

	//Returns alphabet		
	public static char[] toArray(){
		return alpha;
	}

	//Determines if given string is a word in a given Dictionary
	public static boolean isWord(String word){
		ArrayList<String> wordList = getWords(word.length());
		if(wordList.indexOf(word) == -1)	//means word is not in the list
			return false;
		else
			return true;
	}

	//Process returning the number of length-n words in a given dictionary
	public static int getWordCount(int n){		
		int num=-1;
		try{
			Integer numInt = (Integer)DAO.client.queryForObject("getWordCount",n);
			num=(int)numInt;
		}
		catch (SQLException e){
			System.out.println(e);
		}
		
		return num;
	}

	//Retrieve an ArrayList of words of length n
	public static ArrayList<String> getWords (int n){
		ArrayList<String> list = new ArrayList<String>();
		try{
			String table ="n"+n;
			list=(ArrayList<String>)DAO.client.queryForList("getWords",table);
		}catch(SQLException e){	
			System.out.println(e);
		}
		
		return list;
	}
	
	//Retrieve an ArrayList of all words
	public static ArrayList<String> getAllWords(){
		ArrayList<String> list = new ArrayList<String>();
		
		for(int i=0;i<tables.length;i++){
			ArrayList<String> temp = getWords(i+1);
			list.addAll(temp);		
		}
		
		return list;
	}

	public static ArrayList<String> getMatches(String input){
		ArrayList<String> bank = getWords(input.length());
		ArrayList<String> matches = new ArrayList<String>();
		
		char[] handArray = input.toCharArray();
		Arrays.sort(handArray);
		String pivot = new String(handArray); 
		for(int i=0;i<bank.size();i++){
			char[] wordArray = bank.get(i).toCharArray();
			Arrays.sort(wordArray);
			String word= new String(wordArray);
			if(word.equals(pivot)){
				matches.add(bank.get(i));
			}
		}
		return matches;
	}
	
	public static int getFreq(String c){
		int count=-1;
		HashMap<String,String> map = new HashMap<String,String>();
		String table = "n"+c.length();
		
		map.put("table",table);
		map.put("word",c);
		
		try{
			count=(int)(Integer)DAO.client.queryForObject("getFreq",map);
		}
		catch(Exception e){	
			//System.out.println(e);
		}
		
		return count;
	}

	public static String getClass(String word){
		word=word.toUpperCase();
		char[] orig = word.toCharArray();
		char[] wordClass = new char[word.length()];
		int index=1;		

		for(int i=0;i<wordClass.length;i++){
			if(i == 0)
				wordClass[i]=alpha[0];
			else{
				if(orig[i] == "'".charAt(0) || orig[i] == "-".charAt(0)){
					wordClass[i]=orig[i];
				}
				else{
					for(int j=0;j<i;j++){
						if(orig[j]==orig[i]){
							wordClass[i]=wordClass[j];
							break;
						}
					
						if((i-j)==1){
							wordClass[i]=alpha[index];
							index++;
						}
					}
				}
			}
		}
	
		return new String(wordClass);	
	}
	
	public static String getClassForCode(String word,HashMap<Character,Character> map){
		word=StringUtility.stripPunc(word.toUpperCase());
		char[] orig = word.toCharArray();
		char[] wordClass = new char[word.length()];
		int index=0;		

		for(int i=0;i<wordClass.length;i++){
			if(map.containsKey(orig[i])){
				wordClass[i]=Character.toLowerCase(map.get(orig[i]));
			}
			else{
				if(i == 0){
					wordClass[i]=alpha[index];
					index++;
				}
				else{
					if(orig[i] == "'".charAt(0) || orig[i] == "-".charAt(0)){
						wordClass[i]=orig[i];
					}
					else{
						for(int j=0;j<i;j++){
							if(orig[j]==orig[i]){
								wordClass[i]=wordClass[j];
								break;
							}
						
							if((i-j)==1){
								wordClass[i]=alpha[index];
								index++;
							}
						}
					}
				}
			}
		}
	
		return new String(wordClass);	
	}

	public static String getClassForWord(String word,HashMap<Character,Character> map){
		word=word.toUpperCase();
		char[] orig = word.toCharArray();
		char[] wordClass = new char[word.length()];
		int index=0;		

		for(int i=0;i<wordClass.length;i++){
			if(map.containsValue(orig[i])){
				wordClass[i]=Character.toLowerCase(orig[i]);
			}
			else{
				if(i == 0){
					wordClass[i]=alpha[index];
					index++;
				}
				else{
					if(orig[i] == "'".charAt(0) || orig[i] == "-".charAt(0)){
						wordClass[i]=orig[i];
					}
					else{
						for(int j=0;j<i;j++){
							if(orig[j]==orig[i]){
								wordClass[i]=wordClass[j];
								break;
							}
						
							if((i-j)==1){
								wordClass[i]=alpha[index];
								index++;
							}
						}
					}
				}
			}
		}
		return new String(wordClass);	
	}		

	public static String applyMap(String cipher, HashMap<Character,Character> map){
		Set<Character> keys = map.keySet();
		Iterator it = keys.iterator();
		
		while(it.hasNext()){
			char letter = (Character)it.next();
			cipher=cipher.replace(letter,map.get(letter));
		}
		
		return cipher;
	}
	
	public static int getMax(){		
		Scanner s = null;
		int max=0;
		try{
			s = new Scanner(new BufferedReader(
					new FileReader(path+type+".txt")));
			s.useDelimiter("\r\n");
		
			String word;
			while(s.hasNext()){
				word=s.next();
			
				if(word.length()>max)
					max=word.length();
				
			}
		}	
		catch(Exception e){}		
				
		return max;
	}

	public static void deleteAll(){
		try{
			for(int i=0;i<tables.length;i++)
				DAO.client.delete("emptyTable",tables[i]);
		}
		catch(Exception e){	
			System.out.println(e);
		}
	}
	
	public static void updateFreq(String str, double n){
		HashMap <String,Object> map = new HashMap<String,Object>();
		map.put("table","n"+str.length());
		map.put("word",str);
		map.put("freq",n);
		
		try{
			DAO.client.update("updateFreq",map);
		}
		catch(SQLException e){
			System.out.println(e);
		}
	}
	
//Returns indices that have matching letters
/*
	public int[][] indexSameLetter(String str){

		char[] char1=str.toCharArray();
		int len = char1.length;
		int index=-1;
		int[][] match=new int[len][len];
		
		for(int x=0;x<len;x++){
			for(int y=0;y<len;y++)
			match[x][y]=0;
		}
			
		while(index<len-1){
			index++;
			int j=1;
				for(int i=index+1;i<len;i++){
				if(char1[index]==char1[i])
				{
				match[index][0]=index;
				match[index][j]=i;
				j++;
				}
			}	
			
			if(j==1)
			match[index][0]=index;	
		}
		
		return match;
	}
	
	
	
/*public String wordForm(String word)
{
	String form = new String(word.length);
	
	form="X";
	
	for(int i=1;i<form.length;i++)
	form=form.concat("X");
	
	for(int j=0;j<form.length;j++)
	{
		if(form.substring(i,i+1).equals("X"))
		{
			for(int k=0;k<form.length-i;k++)
			{

}	
*/

//Determines if a word is mappable (for part of a cryptogram)//	***//No Identity Maps (i.e. X-->X) Allowed!!!//***
///////////////////////////////////////////////////////////////
/*
	public boolean isWordMappable(String crypt, String text){
		int index=0;
	
		int[][]cryptnum=new int[crypt.length()][crypt.length()];
		int[][]textnum=new int[text.length()][text.length()];
	
		char[] cryptchar=crypt.toCharArray();
		char[] textchar=text.toCharArray();
	
		cryptnum=indexSameLetter(crypt);
		textnum=indexSameLetter(text);
	
		for(int k=0;k<crypt.length();k++){
			if(math.arraySum(cryptnum[k])!=math.arraySum(textnum[k]))
			index++;
		
			if(cryptchar[k]==textchar[k])
			index++;	
		}
	
		if(index==0)
		return true;
		else
		return false;
	}

//Create array of potential words based on length and pattern   (EXAMPLE:  XINXGI is almost always PEOPLE)
/////////////////////////////////////////////////////////////

	public String[] allNWords (String str){
		int len = str.length();
		String[] words= new String[numwords];
		int a=0;
	
		for(int i=0;i<numwords;i++){
			if(bank[i].length()==len && isWordMappable(bank[i],str)==true){
			words[a]=bank[i];
			a++;
			}
		}
		String[] allWords = new String[a];

		for(int g=0;g<a;g++)
		allWords[g]=words[g];
		
	return allWords;
	}				

//Used to shorten list of possible words for cryptogram//
/////////////////////////////////////////////////////////

public String[] allPossWords(String crypt1,String core,String crypt2){  //length of crypt should equal sum of lengths of core and text
	String[] words=new String[allNWords(crypt2).length];
	int b=0;
	
	String crypt,text;	
	crypt=crypt1.concat(crypt2);
	
   String[] guess=allNWords(crypt2);
	for(int i=0;i<guess.length;i++){
	text=core.concat(guess[i]);
	
		if(isWordMappable(crypt,text)==true){
		words[b]=guess[i];
		b++;
		}
	}
	
	String[] allWords = new String[b];

		for(int g=0;g<b;g++)
		allWords[g]=words[g];
			
	return allWords;
	}


//Helps organize results of allPossWords//
//////////////////////////////////////////

	public String[] trimPoss(String crypt1,String[] poss,String crypt2){

		String[] test=allPossWords(crypt1,poss[0],crypt2);

		for(int i=1;i<poss.length;i++){
		String[] test2=allPossWords(crypt1,poss[i],crypt2);
	
		if(test2.length!=0)
		test=arrayUnion(test,test2);
		}

		return test;
	} 

//Takes the union of two arrays of strings//
////////////////////////////////////////////

	public String[] arrayUnion(String[] text1, String[] text2){
		String[] test = new String[text1.length + text2.length];
		for(int k=0;k<text1.length;k++)
		test[k]=text1[k];

		int start=text1.length;

		for(int i=0;i<text2.length;i++){
			if(isMember(text1,text2[i])==false){
				test[start]=text2[i];
				start++;
			}
		}

		String[] words = new String[start];

		for(int j=0;j<start;j++)
		words[j]=test[j];

		return words;
	}


//check to see if word is part of an array

	public boolean isMember(String[] array, String text){
		int count=0;
		boolean result;
		
		for(int i=0;i<array.length;i++){
			if(text.equals(array[i]))
			count++;
		}

		if(count>0)
		result=true;
		else
		result=false;
	
		return result;
	}

//Concats all possible entries of two arrays of strings, puts into one array//
//////////////////////////////////////////////////////////////////////////////

	public String[] concatArray(String[] array1, String[] array2){
		String[] array = new String[array1.length * array2.length];
		int index=0;
		for(int i=0;i<array1.length;i++){
			for(int j=0;j<array2.length;j++){
				array[index]=array1[i].concat(array2[j]);
				index++;
			}
		}
	return array;
	}

//Checks to see if given word has any punctuation
	public static boolean hasPunc (String str){
		boolean test=false;

		if(str.substring(str.length()-2,str.length()-1).equals("'"))
		test=true;

		for(int i=0;i<str.length();i++){
			if(str.substring(i,i+1).equals("-"))
			test=true;
		}
	return test;
	}
*/
}

