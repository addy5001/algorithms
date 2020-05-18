package ramesh.aadhavan.misc;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The {@code BitOperations} represents
 *
 * @author aadhavan.ramesh
 * @since
 */
public class BitOperations {

    public static int reverseBits(int n) {
        int[] bitArr = convertToBitArray(n);
        reverse(bitArr);
        return convertToInt(bitArr);
    }

    public static int base10Complement(int N) {
        int[] bits = convertToBitArray(N);
        base10ComplementArray(bits);
        return convertToInt(bits);
    }

    public int bitwiseComplement(int N) {
        if (N == 0) return 1;
        if (N == 1) return 0;
        int x = 1;
        while(x<= N){
            x = x << 1;  // equialently written as x*=2;
        }
        return N ^ (x-1);
    }

    private static void base10ComplementArray(int[] bits) {
        boolean flipBits = false;

        for(int i=bits.length-1; i>=0; i--) {
            if(bits[i] == 0 && !flipBits)
                continue;

            if(bits[i] == 1 && !flipBits) {
                bits[i] = 0;
                flipBits = true;
            }
            else {
                bits[i] = bits[i] == 0 ? 1 : 0;
            }
        }

        if(!flipBits)
            bits[0] = 1;
    }

    public static int[] convertToBitArray(int n) {
        int[] bitArray = new int[32];

        for(int i=0; i<bitArray.length; i++) {
            bitArray[i] = (n & 1);
            n = n >> 1;
        }

        return bitArray;
    }

    public static void reverse(int[] n) {
        int mid = n.length/2;
        for(int i=0, j=n.length-1; i<mid; i++, j--) {
            int tmp = n[i];
            n[i] = n[j];
            n[j] = tmp;
        }
    }

    public static int convertToInt(int[] arr) {
        int result = 0;
        for(int i=arr.length-1; i>=0; i--) {
            result = (result << 1) + arr[i];
        }

        return result;
    }

    public String addBinary(String a, String b) {
        int aNum = _convertBinaryStringToInt(a);
        int bNum = _convertBinaryStringToInt(b);

        int result = aNum + bNum;
        return Integer.toString(result, 2);
    }

    private int _convertBinaryStringToInt(String x) {
        IntStream xStream = x.chars().map(c -> c == 48 ? 0 : 1);
        return xStream.reduce(0, (a, b) -> (a << 1) + b);
    }
}
