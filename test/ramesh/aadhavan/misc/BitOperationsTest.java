package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * The {@code BitOperationsTest} represents
 *
 * @author aadhavan.ramesh
 * @since
 */
public class BitOperationsTest {

    @Test
    public void testConvertToBitArray_negative() {
        int[] actual = BitOperations.convertToBitArray(Integer.MIN_VALUE);
        BitOperations.reverse(actual);
        int num = -5;
        System.out.println(~num);
        System.out.println(~num+1);

        int num2 = 0;
        System.out.println(~num2);
        System.out.println(~num2+1);
    }

    @Test
    public void testConvertTo10Complement() {
        Assert.assertEquals(2, BitOperations.base10Complement(5));
        Assert.assertEquals(0, BitOperations.base10Complement(7));
        Assert.assertEquals(5, BitOperations.base10Complement(10));
        Assert.assertEquals(1, BitOperations.base10Complement(0));
    }
}
