package ramesh.aadhavan.misc;

public class StringToInteger {
    public int myAtoi(String str) {
        return _myAtoi(str);
    }

    private int _myAtoi(String str) {
        if(str == null || str.isBlank())
            return 0;

        int idx = 0;
        while(str.charAt(idx) == 32)
            idx++;

        boolean sign = true;
        int num = 0;

        if(str.charAt(idx) == '-')
            sign = false;
        else if(str.charAt(idx) == '+')
            sign = true;
        else if(Character.isDigit(str.charAt(idx)))
            num = Character.getNumericValue(str.charAt(idx));
        else
            return 0;

        idx++;
        for(int i=idx; i<str.length(); i++) {
            if(Character.isDigit(str.charAt(i)))
            {
                if(num > Integer.MAX_VALUE/10 ||
                        (num == Integer.MAX_VALUE/10 && Character.getNumericValue(str.charAt(i)) > 7))
                    return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;

                num = 10 * num + Character.getNumericValue(str.charAt(i));
            }
            else
                break;
        }

        return sign ? num : -num;
    }
}
