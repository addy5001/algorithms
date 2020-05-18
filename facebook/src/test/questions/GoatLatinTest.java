package questions;

import org.junit.Assert;
import org.junit.Test;

public class GoatLatinTest {
    final GoatLatin goatLatin = new GoatLatin();

    @Test
    public void testToGoatLatin() {
        Assert.assertEquals("Imaa peaksmaaa oatGmaaaa atinLmaaaaa",
                goatLatin.toGoatLatin("I speak Goat Latin"));
    }

    @Test
    public void testToGoatLatin_2() {
        Assert.assertEquals("heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa",
                goatLatin.toGoatLatin("The quick brown fox jumped over the lazy dog"));
    }
}
