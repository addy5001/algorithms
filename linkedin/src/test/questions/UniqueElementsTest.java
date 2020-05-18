package questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class UniqueElementsTest {

    @Test
    public void testFindUnique_1() {
        List<Integer> list = List.of(1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,4,4,4,4,4,44);
        final UniqueElements<Integer> uniqueElements = new UniqueElements<>();
        Assert.assertEquals(Set.of(1, 3, 4, 44), uniqueElements.findUniqueElements(list));
    }

    @Test
    public void testFindUnique_2() {
        List<Integer> list = List.of(1,1,1,1,1,1,1,1,1,1,1,3,3,3,3,3,3,3,4,4,4,4,4,44);
        final UniqueElements<Integer> uniqueElements = new UniqueElements<>();
        Assert.assertEquals(List.of(1, 3, 4, 44), uniqueElements.findUniqueElements(list, Integer::compareTo));
    }
}
