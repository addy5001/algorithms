package ramesh.aadhavan.bits;

public class MaxProductOfWordLengths {

    /**
     * Naive Approach
     * Compare each word with all the following words one by one.
     * If two words have no common letters, update the maximum product maxProd.
     */

    public int maxProduct(String[] words) {
        int[] bitmasks = new int[words.length];
        int[] lengths = new int[words.length];

        for(int i=0; i<words.length; i++) {
            String word = words[i];
            int bitmask = 0;

            lengths[i] = word.length();
            for(int j=0; j<word.length(); j++) {
                bitmask = bitmask | (1 << getBitNumber(word.charAt(j)));
            }

            bitmasks[i] = bitmask;
        }

        int maxProduct = 0;
        for(int i=0; i<words.length; i++) {
            for(int j=i+1; j<words.length; j++) {
                if((bitmasks[i] & bitmasks[j]) == 0)
                    maxProduct = Math.max(maxProduct, lengths[i] * lengths[j]);
            }
        }

        return maxProduct;
    }

    private int getBitNumber(char c) {
        return c - 'a';
    }
}
