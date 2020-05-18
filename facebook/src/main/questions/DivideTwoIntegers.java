package questions;

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        return _divisionBySubtraction(dividend, divisor);
    }

    /**
     * Both approaches below are time complexity O(n) and space complexity O(1)
     * @param dividend
     * @param divisor
     * @return
     */
    int _divisionBySubtraction(int dividend, int divisor) {

        if(dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int quotient = 0;
        int negatives = 0;

        if(dividend < 0) {
            negatives++;
            dividend = -dividend;
        }

        if(divisor < 0) {
            negatives++;
            divisor = -divisor;
        }

        while(dividend >= divisor) {
            quotient++;
            dividend = dividend - divisor;
        }

        if(negatives == 1)
            quotient = -quotient;

        return quotient;
    }

    int _divisionBySubtractionWithOverflow(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        /* We need to convert both numbers to negatives
         * for the reasons explained above.
         * Also, we count the number of negatives signs. */
        int negatives = 2;
        if(dividend > 0) {
            negatives--;
            dividend = -dividend;
        }

        if(divisor > 0) {
            negatives--;
            divisor = -divisor;
        }

        /* Count how many times the divisor has to be added
         * to get the dividend. This is the quotient. */
        int quotient = 0;
        while((dividend - divisor) <= 0) {
            quotient--;
            dividend = dividend - divisor;
        }

        /* If there was originally one negative sign, then
         * the quotient remains negative. Otherwise, switch
         * it to positive. */
        if(negatives != 1)
            quotient = -quotient;

        return quotient;
    }

    public int divideExponential(int dividend, int divisor) {
        //Reduce the problem to positive long integer to make it easier.
        //Use long to avoid integer overflow cases.
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
            sign = -1;

        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        //Take care the edge cases.
        if (ldivisor == 0) return Integer.MAX_VALUE;
        if ((ldividend == 0) || (ldividend < ldivisor))	return 0;

        long lans = ldivide(ldividend, ldivisor);

        int ans;
        if (lans > Integer.MAX_VALUE){ //Handle overflow.
            ans = (sign == 1)? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            ans = (int) (sign * lans);
        }
        return ans;
    }

    private long ldivide(long ldividend, long ldivisor) {
        // Recursion exit condition
        if (ldividend < ldivisor) return 0;

        //  Find the largest multiple so that (divisor * multiple <= dividend),
        //  whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
        //  Think this as a binary search.
        long sum = ldivisor;
        long multiple = 1;
        while ((sum+sum) <= ldividend) {
            sum += sum;
            multiple += multiple;
        }
        //Look for additional value for the multiple from the reminder (dividend - sum) recursively.
        return multiple + ldivide(ldividend - sum, ldivisor);
    }
}
