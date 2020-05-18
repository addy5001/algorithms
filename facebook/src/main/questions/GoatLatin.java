package questions;

public class GoatLatin {
    public String toGoatLatin(String S) {
        if(S == null || S.isBlank())
            return "";

        String[] tokens = S.split(" ");
        String[] resultTokens = new String[tokens.length];
        int idx = 0;

        for(String token : tokens) {
            String aStrings = "a".repeat(idx+1);
            if(_isVowel(token.charAt(0))) {
                resultTokens[idx++] = token.concat("ma").concat(aStrings);
            }
            else {
                resultTokens[idx++] = token.substring(1).concat(token.substring(0, 1)).concat("ma").concat(aStrings);
            }
        }

        return String.join(" ", resultTokens);
    }

    private boolean _isVowel(char c) {
        switch (c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                return true;
            default:
                return false;
        }
    }
}
