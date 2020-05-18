package questions;

public class IntegerToEnglishWords {

    String singleDigit(int num) {
        switch (num) {
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            default: return "";
        }
    }

    String belowTwenty(int num) {
        switch (num) {
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
            default: return "";
        }
    }

    String aboveTwenty(int num) {
        switch (num) {
            case 20: return "Twenty";
            case 30: return "Thirty";
            case 40: return "Forty";
            case 50: return "Fifty";
            case 60: return "Sixty";
            case 70: return "Seventy";
            case 80: return "Eighty";
            case 90: return "Ninety";
            default: return "";
        }
    }

    String twoDigits(int num) {
        if(num == 0)
            return "";
        else if(num < 10)
            return singleDigit(num);
        else if(num < 20)
            return belowTwenty(num);
        else {
            int div = num/10;
            int digit = num%10;

            if(digit == 0) {
                return aboveTwenty(num);
            }
            else {
                return String.join(" ", aboveTwenty(div*10), singleDigit(digit));
            }
        }
    }

    String threeDigits(int num) {
        String hundred = "Hundred";

        if(num == 0)
            return "";
        else if(num < 100)
            return twoDigits(num);
        else {
            int div = num/100;
            int digit = num%100;

            if(digit == 0) {
                return String.join(" ", singleDigit(div), hundred);
            }
            else {
                return String.join(" ", singleDigit(div), hundred, twoDigits(digit));
            }
        }
    }

    public String numberToWords(int num) {
        String zero = "Zero";
        String thousand = "Thousand";
        String million = "Million";
        String billion = "Billion";

        if(num == 0) {
            return zero;
        }
        else {
            int billionth = num/1_000_000_000;
            int millionth = (num - billionth * 1_000_000_000)/1_000_000;
            int thousandth = (num - billionth * 1_000_000_000 - millionth * 1_000_000)/1_000;
            int rest = (num - billionth * 1_000_000_000 - millionth * 1_000_000 - thousandth * 1_000);

            String result = "";
            if(billionth != 0) {
                result = String.join(" ", threeDigits(billionth), billion);
            }

            if(millionth != 0) {
                result = String.join(" ", result, threeDigits(millionth), million);
            }

            if(thousandth != 0) {
                result = String.join(" ", result, threeDigits(thousandth), thousand);
            }

            if(rest != 0) {
                result = String.join(" ", result, threeDigits(rest));
            }

            return result.trim();
        }
    }
}
