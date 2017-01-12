package joejava.mathoms;

import java.util.ArrayList;

public class MinMax {

	/*
	 * 
	 * Purpose: Introduce divide and conquer problem solving technique and recursive
	 *   Programming.
	 *
	 * Description:
	 *   The program and function in this file computes the minimum and maximum
	 *   element in a vector of ints.  The min and max elements can be computed
	 *   by scanning the vector twice, first looking for the min and then the
	 *   max.  This requires 2 * (n-1) comparisons for a vector of size n.  The
	 *   divide and conquer algorithm (described below) reduces the number of
	 *   comparisons to approximately (3/2)*n.  It is possible to prove that
	 *   this is is optimal.  i.e. there does not exist an algorithm that uses
	 *   fewer comparisons.
	 *
	 * Notes:   The preprocessor constant TRACING can be set so that the
	 *   recursive calls in the divide and conquer algorithm are traced.
	 */

	static int min( int a, int b ){
		if( a <= b ) 
			return a;
		else
			return b;
	}

	static int max( int a, int b ){
		if( a >= b ) 
			return a;
		else
			return b;
	}

	// Search for the minimum element in a vector of ints.  The min is initially
	// set to the first element in the vector and then the remaining elements
	// are sequentially compared to the min found so far.  If there are n
	// elements in the vector, this procedure requires( n-1 ) comparisons.
	// Assume that n >= 1.

	static int min( int[] V ){
		int currentMin;
		currentMin = V[0];
		for( int i=0; i < V.length;i++ )
			if( V[i] < currentMin )
				currentMin = V[i];
		return currentMin;
	}

	// Search for the maximum element in a vector of ints.  The max is initially
	// set to the first element in the vector and then the remaining elements
	// are sequentially compared to the max found so far.  If there are n
	// elements in the vector, this procedure requires( n-1 ) comparisons.
	// Assume that n >= 1.

	static int max( int[] V ){
		int currentMax;
		currentMax = V[0];
		for( int i=0; i < V.length;i++ )
			if( V[i] > currentMax )
				currentMax = V[i];
		return currentMax;
	}

	// Use Divide and conquer to simultaneously search for the min and max
	// element of a vector of ints.  This procedure uses fewer comparisons than
	// calling the min and max functions separately.  The algorithm recursively
	// computes the min and max of the first and second halves of the vector.
	// In the conquer phase, the min from the first and second halves are
	// compared to determine the min, and the max from the first and second
	// halves are compared to determine the max.  In the base case, if there are
	// two elements the min and max are determined using only one comparison.

	static Object[] minmax( int[] V, int l, int u, int count){
		int m;
		int[] M = new int[2];
		Object[] M1 = new Object[2];
		Object[] M2 = new Object[2];
		Object[] results = new Object[2];
		
		//System.out.println("("+l+","+u+")");		
		
		if( u == l ){	// one element - no comparisons needed
			M[0] = V[l];  M[1] = V[u];
			results[0] = M;
			results[1] = count;
			return results;
		}

		if( u-l == 1 ){  // two elements - only one comparison
			if( V[l] <= V[u] ){
				M[0] = V[l];  M[1] = V[u];
			}else{
				M[0] = V[u];  M[1] = V[l];
			}
			count++;
			results[0] = M;
			results[1] = count;
			return results;
		}

		m =( u+l )/2;
		M1 = minmax( V,l,m,count);
		M2 = minmax( V,m+1,u,(Integer)M1[1] );

		int[] m1 = (int[])M1[0];
		int[] m2 = (int[])M2[0];
		
		//System.out.println("M1 = "+m1[0]+","+m1[1]);
		//System.out.println("M2 = "+m2[0]+","+m2[1]);
		
		M[0] = min( m1[0],m2[0] );
		M[1] = max( m1[1],m2[1] );
		results[0] = M;
		results[1] = (Integer)M2[1];
		return results;
	}

	public static void main(String[] args){
		int n = (int)Math.pow(2,1);
		int[] V = new int[n];
		Object[] M = new Object[2];

		for( int i = 0; i < n; i++ )
			V[i] = i;

		System.out.println("4a)");
		System.out.println();
		System.out.println("Using min and max separately");
		System.out.println("Min = " + min( V ));
		System.out.println("Max = " + max( V ));

		
		System.out.println("Using minmax for n = "+ n);
		M = minmax( V,0,n-1,0);
		int [] m = (int [])M[0];
		
		System.out.println("Min = " + m[0]);
		System.out.println("Max = " + m[1]);
		System.out.println("# Comparisons: "+M[1]);
		
		System.out.println();
		System.out.println();
		System.out.println("4b)");
		//part 2 -- number of comparisons is 2^(k-1) for input of 2^k
		int[] comps = new int[10];
		
		for(int i=1;i<=10;i++){
			int j = (int) Math.pow(2, i);
			int[] A = new int[j];
			M = minmax( A,0,j-1,0);
			comps[i-1] = (Integer)M[1];
			System.out.println("# Comparisons for n = 2^"+i+": "+M[1]);
		}
	}
}
