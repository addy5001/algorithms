package ramesh.aadhavan.strings;

import com.google.common.annotations.VisibleForTesting;

public class KmpAlgorithm {

    @VisibleForTesting
    int[] _buildLookupTable(String s) {
        int j=0;
        int[] lookup = new int[s.length()];
        lookup[0] = 0;

        for(int i=1; i<lookup.length; i++) {
            if(s.charAt(i) == s.charAt(j)) {
                lookup[i] = j + 1;
                j++;
            }
            else {
                while(j > 0) {
                    j = lookup[j-1];

                    if(s.charAt(i) == s.charAt(j)) {
                        break;
                    }
                }

                if(s.charAt(i) == s.charAt(j)) {
                    lookup[i] = j+1;
                }
                else {
                    lookup[i] = j;
                }
            }
        }

        return lookup;
    }
}
