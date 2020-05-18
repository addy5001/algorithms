package questions;

import org.junit.Assert;
import org.junit.Test;

public class IntegerToEnglishWordsTest {

    final IntegerToEnglishWords integerToEnglishWords = new IntegerToEnglishWords();

    @Test
    public void testTwoDigits_1() {
        Assert.assertEquals("Ninety Nine", integerToEnglishWords.twoDigits(99));
    }

    @Test
    public void testTwoDigits_2() {
        Assert.assertEquals("Thirty Five", integerToEnglishWords.twoDigits(35));
    }

    @Test
    public void testTwoDigits_3() {
        Assert.assertEquals("Nineteen", integerToEnglishWords.twoDigits(19));
    }

    @Test
    public void testThreeDigits_1() {
        Assert.assertEquals("One Hundred", integerToEnglishWords.threeDigits(100));
    }

    @Test
    public void testThreeDigits_2() {
        Assert.assertEquals("Nine Hundred Ninety Nine", integerToEnglishWords.threeDigits(999));
    }

    @Test
    public void testThreeDigits_3() {
        Assert.assertEquals("Eight Hundred Sixty One", integerToEnglishWords.threeDigits(861));
    }

    @Test
    public void testThousand_1() {
        Assert.assertEquals("Two Thousand Eight Hundred Sixty One", integerToEnglishWords.numberToWords(2861));
    }

    @Test
    public void testThousand_2() {
        Assert.assertEquals("Nine Hundred Ninety Nine Thousand Nine Hundred Ninety Nine", integerToEnglishWords.numberToWords(999999));
    }

    @Test
    public void testBillion_1() {
        Assert.assertEquals("One Billion Nine Hundred Ninety Nine Million Nine Hundred Ninety Nine Thousand Nine Hundred Ninety Nine", integerToEnglishWords.numberToWords(1_999_999_999));
    }
}
