package questions;

import java.util.*;
import java.util.stream.Stream;

public class StrobogrammaticNumber {

    Map<Character, Character> strobogram = Map.of('0', '0',
            '1', '1',
            '6', '9',
            '9', '6',
            '8', '8');

    public boolean isStrobogrammatic(String num) {
        return _isStrobogrammatic(num);
    }

    private boolean _isStrobogrammatic(String num) {
        int i=0;
        int j=num.length()-1;

        while(i <= j) {
            if(i==j) {
                final int idx = i;
                return Stream.of('0','1','8').anyMatch(c -> c == num.charAt(idx));
            }

            if(!strobogram.containsKey(num.charAt(i)) || !strobogram.containsKey(num.charAt(j)))
                return false;

            if(num.charAt(i) != strobogram.get(num.charAt(j)) || num.charAt(j) != strobogram.get(num.charAt(i)))
                return false;

            i++;
            j--;
        }

        return true;
    }

    public List<String> findStrobogrammatic(int n) {
        return _findStrobogrammaticDfs(n, n);
    }

    private List<String> _findStrobogrammaticDfs(int n, int m) {
        if(n == 0)
            return new ArrayList<>(Collections.singletonList(""));

        if(n == 1)
            return new ArrayList<>(Arrays.asList("0", "1", "8"));

        List<String> result = _findStrobogrammaticDfs(n-2, m);
        List<String> append = new ArrayList<>();

        int size = result.size();
        for(int i=0; i<size; i++) {
            String s = result.get(i);

            if(n != m)
                append.add("0" + s + "0");

            append.add("1" + s + "1");
            append.add("6" + s + "9");
            append.add("9" + s + "6");
            append.add("8" + s + "8");
        }

        return append;
    }
}
