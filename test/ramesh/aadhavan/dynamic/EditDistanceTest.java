package ramesh.aadhavan.dynamic;

import org.junit.Assert;
import org.junit.Test;

public class EditDistanceTest {

    final EditDistance editDistance = new EditDistance();

    @Test
    public void testFindMinEditDistance() {
        String s1 = "abcdfr";
        String s2 = "bcdt";

        Assert.assertEquals(3, editDistance.findMinEditDistance(s1, s2));
    }
}
