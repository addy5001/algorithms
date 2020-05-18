package ramesh.aadhavan.numbers;

import org.junit.Assert;
import org.junit.Test;

public class ValidNumberTest {

    final ValidNumber validNumber = new ValidNumber();

    @Test
    public void testIsNumber() {
        String number = "0";
        Assert.assertTrue(validNumber.isNumber(number));
    }
}
