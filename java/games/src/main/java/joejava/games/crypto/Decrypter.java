package joejava.games.crypto;

//CryptoCracker.java//////////////////////////////////////////////////////////////////////////
//																														  //
//Program designed to provide solutions to cryptograms (A.K.A. single substitution cyphers!)//

import java.util.StringTokenizer;

import joejava.util.LangUtility;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Decrypter{

	public static void main (String[] args){
		String ciphertext,plaintext;
		
		//ciphertext="ATZ ZMX JTMJFT";
		ciphertext="OF AGEB, E GH UOF DJF";	
		
		double startTime; // Starting time of program, in milliseconds.
		double endTime; // Time when computations are done, in milliseconds.
		double time; // Time difference, in seconds.
		
		startTime = System.currentTimeMillis();
		System.out.println(ciphertext);

		//Read each word from tokenizer into an array (cipherarray)
		StringTokenizer st = new StringTokenizer(ciphertext);
		String[] cipherArray = new String[st.countTokens()];
		//List<WordBean>[] words = new ArrayList<WordBean>[st.countTokens()];
		/*
		ArrayList<ArrayList<WordBean>> words = new ArrayList<ArrayList<WordBean>>();
		ArrayList<WordBean> allWords = new ArrayList<WordBean>();
		*/
		HashMap<Character,Character> map = new HashMap<Character,Character>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		ArrayList<String> sols = new ArrayList<String>();
		
		for(int i=0;i<cipherArray.length;i++){
			cipherArray[i]=st.nextToken();	
		}
		
		boolean failure=false;
		
		while(failure==false){
		
			/*
			for(int i=0;i<cipherArray.length;i++){
				if(!order.contains((Integer)i))
					words.add((ArrayList<WordBean>)LangUtility.getWordBeanList(cipherArray[i],map));
			}
		
			LangUtility.setBeans(words,allWords,cipherArray);
			
			WordBean guess = allWords.get(0);
			order.add(guess.cipherIndex);
			System.out.println(guess.toString());
					
			char[] guessArray = guess.word.toUpperCase().toCharArray();
			for(int i=0;i<guessArray.length;i++){
				map.put(cipherArray[guess.cipherIndex].charAt(i),guessArray[i]);
			}
			
			if(order.size()==cipherArray.length)
				failure=true;
			
			words.clear();
			allWords.clear();
			*/
		}
		
		System.out.println(LangUtility.applyMap(ciphertext,map));
		
	}
}
	