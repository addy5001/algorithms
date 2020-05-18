package ramesh.aadhavan.misc;

import java.util.*;

/**
 * Binary strings of length n with non-consecutive 1s
 */
public class BinaryStringsOfN {

    public Collection<String> binaryStrings(int n) {
        if(n <= 0)
            return Collections.emptyList();

        Queue<String> binaryStrings = new LinkedList<>();
        _binaryStrings(n, binaryStrings);
        return binaryStrings;
    }

    private void _binaryStrings(int n, Queue<String> binaryStrings) {
        if(n == 1) {
            binaryStrings.addAll(Arrays.asList("0", "1"));
            return;
        }

        _binaryStrings(n-1, binaryStrings);
        int size = binaryStrings.size();
        for(int i=0; i<size; i++) {
            String binaryString = binaryStrings.poll();
            if(binaryString.charAt(binaryString.length()-1) == '0') {
                binaryStrings.offer(binaryString.concat("0"));
                binaryStrings.offer(binaryString.concat("1"));
            }
            else {
                binaryStrings.offer(binaryString.concat("0"));
            }
        }
    }
}
