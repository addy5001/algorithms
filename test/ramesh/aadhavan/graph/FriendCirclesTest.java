package ramesh.aadhavan.graph;

import org.junit.Assert;
import org.junit.Test;

public class FriendCirclesTest {

    @Test
    public void testFindCircleNum() {
        int[][] M = {{1,0,0},{0,1,0},{0,0,1}};
        final FriendCircles friendCircles = new FriendCircles();
        Assert.assertEquals(3, friendCircles.findCircleNum(M));
    }

}
