package joejava.mathoms;

public class TowerOfHanoi {

	public static void Move(StringBuffer a, StringBuffer b){
		int n = a.length();
		String disk = a.substring(n-1);
		System.out.println("Moving " + disk + " to "+ b.toString());
		a.deleteCharAt(n-1);
		b.append(disk);
		System.out.println("After Move: " + a.toString() + " and " + b.toString());
	}
	
	public static void Hanoi(int n, StringBuffer a, StringBuffer b, StringBuffer c){
		if( n == 1 ){   // base case 
			Move( a, b );

		}else{           // recursion 
			Hanoi( n-1, a, c, b );  // move top n-1 disks to intermediate tower 
			Move( a, b );           // move bottom disk to destination
			Hanoi( n-1, c, b, a);   // move top n-1 disks from intermediate 
		}                         //   tower to destination tower.
		
	}
	
	public static void main(String[] args){
		StringBuffer a = new StringBuffer("321");
		StringBuffer b = new StringBuffer("");
		StringBuffer c = new StringBuffer("");
		
		Hanoi(a.length(),a,b,c);
	}
}
