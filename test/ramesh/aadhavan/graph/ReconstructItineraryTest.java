package ramesh.aadhavan.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ReconstructItineraryTest {

    final ReconstructItinerary reconstructItinerary = new ReconstructItinerary();

    @Test
    public void testFindItinerary() {
        List<List<String>> tickets = List.of(List.of("JFK","SFO"),
                List.of("JFK","ATL"),
                List.of("SFO","ATL"),
                List.of("ATL","JFK"),
                List.of("ATL","SFO"));

        Assert.assertEquals(List.of("JFK","ATL","JFK","SFO","ATL","SFO"), reconstructItinerary.findItinerary(tickets));
    }

    @Test
    public void testFindItinerary_2() {
        List<List<String>> tickets = List.of(List.of("JFK","KUL"),
                List.of("JFK","NRT"),
                List.of("NRT","JFK"));

        Assert.assertEquals(List.of("JFK","NRT","JFK","KUL"), reconstructItinerary.findItinerary(tickets));
    }
}
