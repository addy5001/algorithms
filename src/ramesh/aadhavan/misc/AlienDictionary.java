package ramesh.aadhavan.misc;

import java.util.HashMap;
import java.util.Map;

public class AlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> index = _index(order);

        for(int i=1; i<words.length; i++) {
            if(!_compare(words[i-1], words[i], index))
                return false;
        }

        return true;
    }

    private boolean _compare(String s, String p, Map<Character, Integer> index) {
        int sLen = s.length();
        int pLen = p.length();

        int i = 0;
        while(i < sLen && i < pLen) {
            int sVal = index.get(s.charAt(i));
            int pVal = index.get(p.charAt(i));

            if(sVal > pVal)
                return false;

            i++;
        }

        return pLen >= sLen;
    }

    private Map<Character, Integer> _index(String order) {
        Map<Character, Integer> index = new HashMap<>();
        for(int i = 0; i<order.length(); i++) {
            index.put(order.charAt(i), i);
        }

        return index;
    }
}
