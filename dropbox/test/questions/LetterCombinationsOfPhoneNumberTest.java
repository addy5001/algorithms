package questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LetterCombinationsOfPhoneNumberTest {

    final LetterCombinationsOfPhoneNumber letterCombinationsOfPhoneNumber = new LetterCombinationsOfPhoneNumber();

    @Test
    public void testLetterCombinations() {
        List<String> results = letterCombinationsOfPhoneNumber.letterCombinations("23");
        Assert.assertEquals(List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"), results);
    }
}
