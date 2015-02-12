package joejava.mathoms;

import java.util.Arrays;

public class Hoare {
	
	public static void exchange(int[] A, int i, int j){
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
	
	public static int Partition(int[] A, int p, int r){
		int x = A[r];
		int i = p-1;
		for(int j=p;j<r;j++){
			if(A[j] <= x){
				i++;
				exchange(A,i,j);
			}
		}
		exchange(A,i+1,r);
		return i+1;
	}
	
	public static void QuickSort(int[] A, int p, int r){
		if (p < r){
			int q = HoarePartition(A,p,r);
			QuickSort(A,p,q);
			QuickSort(A,q+1,r);
		}
	}
	
	public static int HoarePartition(int[] A,int p, int r){
		int x = A[p];
		int i = p - 1;
		int j = r + 1;
		
		while(true){
			System.out.println("i = "+i+" j = "+j);
			System.out.println(Arrays.toString(A));
			do{
				j--;
			}while(A[j] > x);
			do{
				i++;
			}while(A[i] < x);
			if (i < j){
				exchange(A,i,j);
			}else{
				System.out.println("i = "+i+" j = "+j);
				System.out.println(Arrays.toString(A));
				return j;
			}
		}
	}
	
	public static void StoogeSort(int[] A, int i, int j){
		System.out.println(Arrays.toString(A));
		if(A[i] > A[j]){
			exchange(A,i,j);
		}
		if(i+1 >= j){
			return;
		}
		int k = (int)Math.floor((j-i+1)/3);
		
		StoogeSort(A,i,j-k);
		StoogeSort(A,i+k,j);
		StoogeSort(A,i,j-k);
	}
	
	public static void main(String[] args){
		int A[] = {13,19,9,5,12,8,7,4,11,2,6,21};
		/*
		int j = HoarePartition(A,0,A.length-1);
		System.out.println(Arrays.toString(A));
		*/
		
		QuickSort(A,0,A.length-1);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
