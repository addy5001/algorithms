package ramesh.aadhavan.strings;

import java.util.Deque;
import java.util.LinkedList;

public class DecodeString {
    public String decodeString(String s) {
        return _decodeString(s);
    }

    private String _decodeString(String s) {
        StringBuilder resultBuilder = new StringBuilder();
        Deque<Integer> countStack = new LinkedList<>();
        Deque<String> resultStack = new LinkedList<>();
        int idx = 0;

        while(idx < s.length()) {
            if(Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while(Character.isDigit(s.charAt(idx))) {
                    count = (10 * count) + Character.getNumericValue(s.charAt(idx));
                    idx++;
                }
                countStack.push(count);
            }
            else if(s.charAt(idx) == '[') {
                resultStack.push(resultBuilder.toString());
                resultBuilder.setLength(0);
                idx++;
            }
            else if(s.charAt(idx) == ']') {
                int count = countStack.pop();
                String repeated = resultBuilder.toString().repeat(count);
                String result = resultStack.pop().concat(repeated);
                resultBuilder = new StringBuilder(result);
                idx++;
            }
            else {
                resultBuilder.append(s.charAt(idx));
                idx++;
            }
        }

        return resultBuilder.toString();
    }
}
