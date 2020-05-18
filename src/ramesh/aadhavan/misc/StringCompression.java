package ramesh.aadhavan.misc;

public class StringCompression {
    public int compress(char[] chars) {
        int idx = 0;

        int i=0, j=0;
        int count = 0;

        while(j < chars.length) {
            if(chars[i] == chars[j]) {
                ++count;

                if(j == chars.length-1) {
                    chars[idx++] = chars[j];
                    if(count > 1) {
                        char[] digits = Integer.toString(count).toCharArray();
                        for(char digit : digits) {
                            chars[idx++] = digit;
                        }
                    }
                }

                j++;
            }
            else {
                chars[idx++] = chars[i];
                if(count > 1) {
                    char[] digits = Integer.toString(count).toCharArray();
                    for(char digit : digits) {
                        chars[idx++] = digit;
                    }
                }

                count = 0;
                i = j;
            }
        }

        return idx;
    }
}
