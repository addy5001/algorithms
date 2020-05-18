package ramesh.aadhavan.misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOperations {

    public static long powRecursive(long number, int power) {
        if(power == 0)
            return 1;

        if(power < 0)
            return 1/powRecursive(number, -power);
        else if((power & 1) == 1)
            return number *
                    powRecursive(number, (power-1)/2) *
                    powRecursive(number, (power-1)/2);
        else
            return powRecursive(number, power/2) *
                    powRecursive(number, power/2);
    }

    public static long pow(int number, int power, long result) {
        assert power >=0;

        if(power == 1)
            return result;

        return pow(number, power-1, result*number);
    }

    public static String numToBinary(int num) {
        String output = "";
        while(num != 0) {
            int reminder = num%2;
            output = reminder + "" + output;
            num = num/2;
        }

        return "0b"+output;
    }

    public static String numTOctal(int num) {
        String output = "";
        while(num != 0) {
            int reminder = num % 8;
            output = reminder + "" + output;
            num = num/8;
        }
        return "0"+output;
    }

    public static String numToHex(int num) {
        String output = "";
        char[] hexMap = {'A', 'B', 'C', 'D', 'E', 'F'};
        while(num != 0) {
            int reminder = num % 16;
            if(reminder >= 10)
                output = hexMap[reminder%10] + output;
            else
                output = reminder + "" + output;
            num = num/16;
        }

        return "0x"+output;
    }

    public static List<List<Integer>> sumOf1sAnd2s(int num) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> results = new ArrayList<>();
        _sumOf1sAnd2s(num, 0, path, results);
        return results;
    }

    private static void _sumOf1sAnd2s(int num, int index, List<Integer> path, List<List<Integer>> results) {
        if(num<0) {
            return;
        }

        if(num == 0) {
            results.add(new ArrayList<>(path));
            return;
        }

        path.add(index, 1);
        _sumOf1sAnd2s(num-1, index+1, path, results);
        path.remove(index);

        path.add(index, 2);
        _sumOf1sAnd2s(num-2, index+1, path, results);
        path.remove(index);
    }

    public boolean isHappy(int n) {
        return _isHappy(n);
    }

    private boolean _isHappy(int n) {
        Set<Integer> visited = new HashSet<>();

        visited.add(n);
        int nextNum = 0;
        while(n != 0) {
            int rem = n%10;
            n = n/10;
            nextNum += sq(rem);
            if(n == 0) {
                if(nextNum == 1)
                    return true;
                else if(visited.contains(nextNum))
                    break;
                else {
                    visited.add(nextNum);
                    n = nextNum;
                    nextNum = 0;
                }
            }
        }

        return false;
    }

    private int sq(int n) {
        return n * n;
    }

    public boolean isPowerOfFour(int num) {
        if(num <= 0)
            return false;
        else if(num == 1)
            return true;
        else {
            double power = Math.log(num)/Math.log(4);
            return ((power == Math.floor(power)) && (!Double.isInfinite(power)));
        }
    }

    public int climbStairs(int n) {
        int[] cache = new int[n+1];
        return _climb(n, cache);
    }

    private int _climb(int n, int[] cache) {
        if(n<0)
            return 0;

        if(n==0) {
            return 1;
        }

        if(cache[n] != 0)
            return cache[n];
        else {
            cache[n] = _climb(n-1, cache) + _climb(n-2, cache);
            return cache[n];
        }
    }

    public static double sqrt(int x) {
        return _sqrt(1, (double) x, (double) x);
    }

    private static double _sqrt(double low, double high, double x) {
        if(x<=0)
            return 0;

        while(low <= high) {
            double mid = low + (high-low)/2;
            double square = mid*mid;

            if(Math.floor(square) == x)
                return mid;
            else if(square > x)
                high = mid - 1;
            else {
                low = mid + 1;
                x = mid;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        NumberOperations numberOperations = new NumberOperations();
        System.out.println(powRecursive(12, 10));
    }
}
