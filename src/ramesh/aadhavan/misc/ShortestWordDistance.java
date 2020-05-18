package ramesh.aadhavan.misc;

public class ShortestWordDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        int m = -1;
        int n = -1;
        int minDistance = Integer.MAX_VALUE;

        for(int i=0; i<words.length; i++) {
            if(word1.equals(words[i])) {
                m = i;
                if(n != -1) {
                    minDistance = Math.min(minDistance, m-n);
                }
            }
            else if(word2.equals(words[i])) {
                n = i;
                if(m != -1) {
                    minDistance = Math.min(minDistance, n-m);
                }
            }
        }

        return minDistance;
    }
}
