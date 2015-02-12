package joejava.trivial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcurrencyTest {

	public static void main(String[] args) {
		final List<String> list = new ArrayList() {{ add("Hello"); }};
		final Iterator<String> iterator = list.iterator();
		System.out.println(iterator.next());
		list.add("World");
		// FIXME : work here while I'm sunbathing
		for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
            //solution_one              
            ((ArrayList) list).ensureCapacity(1);
 
            //solution_two
            ((ArrayList) list).trimToSize();
 
            //solution_three
            if (!(i == Integer.MIN_VALUE) && !(i % 2 == 0)) {
                ((ArrayList) list).remove(0);
            }
            if (i % 2 == 0) {
                list.add("World");
            }
        }
		
		System.out.println(iterator.next());
	}
}
