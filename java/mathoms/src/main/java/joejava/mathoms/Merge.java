package joejava.mathoms;

import java.util.ArrayList;
import java.util.Arrays;

public class Merge {

	public static ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2){
		if(list1.size() == 0){
			return list2;
		}else if(list2.size() == 0){
			return list1;
		}else if(list1.get(0) <= list2.get(0)){
			ArrayList<Integer> newList = list1; 
			newList.remove(0);
			cleanList(list1);
			list1.addAll(1,merge(newList,list2));
			return list1;
		}else{
			ArrayList<Integer> newList = list2; 
			newList.remove(0);
			cleanList(list2);
			list2.addAll(1,merge(list1,newList));
			return list2;
		}
	}
	
	public static void cleanList(ArrayList<Integer> list){
		for(int i=1;i<=list.size();i++){
			list.remove(i);
		}
	}
	
	public static void main(String[] args){
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		list1.add(2);list1.add(3);list1.add(5);
		
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(2);list2.add(3);list2.add(5);
		
		Arrays.toString(merge(list1,list2).toArray());
	}
}
