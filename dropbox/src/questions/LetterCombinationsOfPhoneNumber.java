package questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfPhoneNumber {

    static Map<Character, List<Character>> numberPad =
            Map.of(
                    '2', List.of('a','b','c'),
                    '3', List.of('d','e','f'),
                    '4', List.of('g','h','i'),
                    '5', List.of('j','k','l'),
                    '6', List.of('m','n','o'),
                    '7', List.of('p','q','r','s'),
                    '8', List.of('t','u','v'),
                    '9', List.of('w','x','y','z')
            );

    public List<String> letterCombinations(String digits) {
        char[] digitChars = digits.toCharArray();
        List<String> results = new ArrayList<>();
        _combinations(0, digitChars, "", results);
        return results;
    }

    private void _combinations(int idx, char[] digitChars, String output, List<String> results) {
        if(idx == digitChars.length) {
            results.add(output);
            return;
        }

        List<Character> characters = numberPad.get(digitChars[idx]);
        for(char c : characters) {
            _combinations(idx+1, digitChars, output.concat(String.valueOf(c)), results);
        }
    }
}
