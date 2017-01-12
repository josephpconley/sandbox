package joejava.finalTest;

//Test to see the performance gains in declaring a class final vs. not
public class FinalTest {
    public static void main(String args[]){
		long start = System.currentTimeMillis();
        int N = 500000000;
        int i = N;
        int t = 0;
        B aref = new B();
        while (i-- > 0)
                t = aref.getType();
        
        System.out.println("A took " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
    }
    
    /*
     * int M = 5000000;
        int j = M;
        int s = 0;
        B bref = new B();
        while (i-- > 0)
                s = bref.getType();
        
        System.out.println("B took " + (System.currentTimeMillis() - start));
     */
}