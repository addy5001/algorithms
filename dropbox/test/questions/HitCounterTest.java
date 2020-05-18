package questions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

public class HitCounterTest {

    HitCounter hitCounter;

    @Before
    public void init() {
        hitCounter = new HitCounter();
    }

    @Test
    public void testHits_withinRange() {
        IntStream.rangeClosed(1, 100).forEach(i -> hitCounter.hit(2));
        IntStream.rangeClosed(1, 1000).forEach(i -> hitCounter.hit(1));
        IntStream.rangeClosed(1, 1000).forEach(i -> hitCounter.hit(3));
        IntStream.rangeClosed(1, 1000).forEach(i -> hitCounter.hit(4));

        Assert.assertEquals(3100, hitCounter.getHits(300));
    }

    @Test
    public void testHits_outsideRange() {
        IntStream.rangeClosed(1, 100).forEach(i -> hitCounter.hit(2));
        IntStream.rangeClosed(1, 1000).forEach(i -> hitCounter.hit(1));
        IntStream.rangeClosed(1, 1000).forEach(i -> hitCounter.hit(3));
        IntStream.rangeClosed(1, 1000).forEach(i -> hitCounter.hit(301));

        Assert.assertEquals(2100, hitCounter.getHits(300));
    }

    @Test
    public void testHits_outsideRange_2() {
        IntStream.rangeClosed(1, 1000).forEach(i -> hitCounter.hit(299));
        IntStream.rangeClosed(1, 1000).forEach(i -> hitCounter.hit(301));
        IntStream.rangeClosed(1, 1000).forEach(i -> hitCounter.hit(301));
        IntStream.rangeClosed(1, 100).forEach(i -> hitCounter.hit(302));
        IntStream.rangeClosed(1, 1000).forEach(i -> hitCounter.hit(303));

        Assert.assertEquals(1000, hitCounter.getHits(300));
        Assert.assertEquals(4100, hitCounter.getHits(400));
    }

    @Test
    public void testHits_3() {
        hitCounter.hit(1);
        hitCounter.hit(2);
        hitCounter.hit(3);
        Assert.assertEquals(3, hitCounter.getHits(4));
        Assert.assertEquals(3, hitCounter.getHits(300));
        Assert.assertEquals(2, hitCounter.getHits(301));
        Assert.assertEquals(1, hitCounter.getHits(302));
        Assert.assertEquals(0, hitCounter.getHits(303));
        Assert.assertEquals(0, hitCounter.getHits(304));

        hitCounter.hit(501);
        Assert.assertEquals(1, hitCounter.getHits(600));
    }
}
