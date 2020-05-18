package ramesh.aadhavan.numbers;

public class ValidNumber {
    public boolean isNumber(String s) {
        boolean signSeen=false;
        boolean numberSeen=false;
        boolean eSeen=false;
        boolean signAfterESeen=false;
        boolean decimalPointSeen=false;
        boolean numberAfterESeen=false;

        s = s.trim();
        if(s.isBlank())
            return false;

        for(int i=0; i<s.length(); i++) {
            if(Character.isAlphabetic(s.charAt(i)) && s.charAt(i) != 'e')
                return false;

            if(Character.isDigit(s.charAt(i))) {
                if(eSeen && !numberAfterESeen)
                    numberAfterESeen = true;

                if(!numberSeen)
                    numberSeen = true;
            }

            if(s.charAt(i) == 'e') {
                if(numberSeen && !eSeen && i!=s.length()-1)
                    eSeen = true;
                else
                    return false;
            }

            if(s.charAt(i) == '+') {
                if(i==0 && s.length()!=1)
                    signSeen = true;
                else
                    return false;
            }

            if(s.charAt(i) == '-') {
                if(i==0 && s.length()!=1)
                    signSeen = true;
                else if(s.charAt(i-1) == 'e' && numberSeen && !signAfterESeen)
                    signAfterESeen = true;
                else
                    return false;
            }

            if(s.charAt(i) == '.') {
                if(decimalPointSeen)
                    return false;
                else if(eSeen)
                    return false;
                else if(!numberSeen)
                    return false;
                else
                    decimalPointSeen = true;
            }

            if(s.charAt(i) == 32)
                return false;
        }

        return true;
    }
}
