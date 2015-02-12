package joejava.games.jumble;

public class WordBean{
	
	public String word;
	public double freq;
	public double probability;
	public int cipherIndex;
	public String codeWord;
	
	public String getWord(){
		return word;
	}
	public void setWord(String word){
		this.word=word;
	}
	public double getFreq(){
		return freq;
	}
	public void setFreq(){
		this.freq=freq;
	}
	public void setProbability(double probability){
		this.probability=probability;
	}
	public void setCipherIndex(int n){
		this.cipherIndex=n;
	}
	public String getCodeWord(){
		return codeWord;
	}
	public void setCodeWord(String word){
		this.codeWord=word;
	}
	public String toString(){
		return word+" "+cipherIndex+" "+probability;
	}
	
}