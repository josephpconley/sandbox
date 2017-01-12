package joejava.mathoms;

public class Binary {

	public static int binary(int n){
		if(n > 0){
			int m = (int) Math.floor(Math.log(n)/Math.log(2));
			int x = (int) (n - Math.pow(2,m));
			return (int) (Math.pow(10,m)+ binary(x));
		}else{
			return 0;
		}
	}
	
	public static void main(String[] args){
		System.out.println(binary(10));
		System.out.println(binary(11));
		System.out.println(binary(15));
	}
}
