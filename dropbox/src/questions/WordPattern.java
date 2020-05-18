package questions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        return _wordPatternHashMap(pattern, str);
    }

    private boolean _wordPatternHashMap(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> seen = new HashSet<>();

        char[] keys = pattern.toCharArray();
        String[] words = str.split(" ");

        if(keys.length != words.length)
            return false;

        int i = 0;
        while(i < keys.length) {
            if(map.containsKey(keys[i])) {
                if(!map.get(keys[i]).equals(words[i])) {
                    return false;
                }
            }
            else {
                if(!seen.contains(words[i])) {
                    map.put(keys[i], words[i]);
                    seen.add(words[i]);
                }
                else
                    return false;
            }

            i++;
        }

        return true;
    }
}
