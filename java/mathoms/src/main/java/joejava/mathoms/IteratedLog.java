package joejava.mathoms;

public class IteratedLog {
	public static int iteratedLog(int n){
		int i = 1;
		n = (int) (Math.log(n)/Math.log(2));		
		
		while (n > 1){
			n = (int) (Math.log(n)/Math.log(2));
			i++;
		}
		return i;
	}
	
	public static void main(String[] args){
		System.out.println(iteratedLog(100000));
	}
}
