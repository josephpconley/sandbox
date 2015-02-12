package joejava.games.util;


public class WordUtility {
	/*
	public List<WordBean> getWordBeanList (String codeword, 
		HashMap<Character,Character> map){
		List<String> words = LangUtility.getWords(codeword.length());
		List<WordBean> list=new ArrayList<WordBean>();

		for(String str : words){
			if(LangUtility.getClassForWord(str,map).equals(LangUtility.getClassForCode(codeword,map))){
				HashMap<String,String> input = new HashMap<String,String>();
				input.put("table","n"+str.length());
				input.put("word",str);
				try{
					WordBean bean = (WordBean)DAO.client.queryForObject("getWordBean",input);
					bean.setCodeWord(codeword);
					list.add(bean);
				}catch(SQLException e){
					System.out.println(e);
				}
			}
		}
		return list;
	}

	public void setBeans(ArrayList<ArrayList<WordBean>> words, ArrayList<WordBean> allWords, String[] cipherArray){
		ArrayList<String> codes = new ArrayList<String>();
		
		for(int i=0;i<cipherArray.length;i++){
			codes.add(cipherArray[i]);
		}
		
		//Calculate probabilities
		for(ArrayList<WordBean> list : words){
			double sum=0;
			for(WordBean bean : list){
				sum += bean.freq;
			}
			for(WordBean bean : list){
				bean.setProbability(bean.freq/sum);
				bean.setCipherIndex(codes.indexOf(bean.codeWord));
				allWords.add(bean);
			}
		}
		sortBeans(allWords);
	}

	public static void sortBeans(ArrayList<WordBean> list){
		for(int i=0;i<list.size();i++){
			for(int j=i;j>0;j--){
				if(list.get(j).probability > list.get(j-1).probability)
					Collections.swap(list,j,j-1);
			}
		}
	}
	*/
}
