package ramesh.aadhavan.misc;

public class StringTrim {

    public String trim(char[] chars) {
        int start = trimBefore(chars, 0);
        int end = trimAfter(chars, chars.length-1);
        int newEnd = trimBetween(chars, start, end);

        StringBuilder s = new StringBuilder();
        for(int i=start; i<=newEnd; i++) {
            s.append(chars[i]);
        }

        return s.toString();
    }

    private int trimAfter(char[] chars, int end) {
        int i=end;
        while(i>=0 && chars[i] == 32) {
            i--;
        }

        return i;
    }

    private int trimBefore(char[] chars, int start) {
        int i=start;
        while (i<chars.length && chars[i] == 32) {
            i++;
        }

        return i;
    }

    private int trimBetween(char[] chars, int start, int end) {

        boolean spaceFound = false;
        int idxChar = 0;
        int idxSpace = 0;
        for(int i=start; i<=end; i++) {
            if(spaceFound) {
                if(chars[i] != 32) {
                    idxChar = i;
                    if(idxChar - idxSpace > 1) {
                        end = moveElements(chars, idxSpace, idxChar, end);
                        i--;
                    }
                    spaceFound = false;
                }
            }
            else {
                if(chars[i] == 32) {
                    spaceFound = true;
                    idxSpace = i;
                }
            }
        }

        return end;
    }

    private int moveElements(char[] chars, int moveTo, int moveFrom, int end) {
        for(int i=moveFrom; i<=end; i++) {
            chars[++moveTo] = chars[i];
            chars[i] = 32;
        }

        return moveTo;
    }

    public static void main(String[] args) {
        String s = "a   ds     goodbye  ! ab  d s d dsd  alexa     b";
        StringTrim stringTrim = new StringTrim();
        System.out.println(stringTrim.trim(s.toCharArray()));
    }
}
