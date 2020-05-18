package ramesh.aadhavan.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        return _isIsomorphic(s, t);
    }

    private boolean _isIsomorphic(String s, String t) {
        if(s == null && t == null)
            return true;
        else if(s == null || t == null)
            return false;
        else {
            if(s.length() != t.length())
                return false;

            Map<Character, Character> map = new HashMap<>();
            Set<Character> tSet = new HashSet<>();

            for(int i=0; i<s.length(); i++) {
                if (map.containsKey(s.charAt(i))) {
                    if(map.get(s.charAt(i)) != t.charAt(i))
                        return false;
                }
                else {
                    if(tSet.contains(t.charAt(i)))
                        return false;

                    map.put(s.charAt(i), t.charAt(i));
                    tSet.add(t.charAt(i));
                }
            }

            return true;
        }
    }
}
