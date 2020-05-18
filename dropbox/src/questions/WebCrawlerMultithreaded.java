package questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class WebCrawlerMultithreaded {

    interface HtmlParser {
        List<String> getUrls(String url);
    }

    private final Function<String, String> hostname = url -> url.split("/")[2];

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        return _crawlThreads(startUrl, htmlParser);
    }

    private List<String> _crawlThreads(String startUrl, HtmlParser htmlParser) {
        Set<String> visited = ConcurrentHashMap.newKeySet();
        visited.add(startUrl);

        Thread initial = new Thread(() -> _crawl(hostname.apply(startUrl), startUrl, htmlParser, visited));
        initial.start();
        join(initial);

        return List.copyOf(visited);
    }

    private void _crawl(String domain, String toCrawl, HtmlParser htmlParser, Set<String> visited) {
        List<String> crawls = htmlParser.getUrls(toCrawl);
        List<Thread> childCrawlers = new ArrayList<>();

        for(String crawl : crawls) {
            if(!visited.contains(crawl) && crawl.contains(domain)) {
                visited.add(crawl);
                Thread child = new Thread(() -> _crawl(domain, crawl, htmlParser, visited));
                child.start();
                childCrawlers.add(child);
            }
        }

        for(Thread t : childCrawlers) {
            join(t);
        }
    }

    private void join(Thread t) {
        try {
            t.join();
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private List<String> _crawlExecutorService(String startUrl, HtmlParser htmlParser) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Set<String> visited = ConcurrentHashMap.newKeySet();
        CountDownLatch initialLatch = new CountDownLatch(1);
        visited.add(startUrl);
        executorService.execute(() -> _crawl(executorService, visited, startUrl, startUrl, htmlParser, initialLatch));

        try {
            initialLatch.await();
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        executorService.shutdownNow();
        return List.copyOf(visited);
    }

    private void _crawl(ExecutorService executorService, Set<String> visited,
                        String sourceUrl, String toCrawl, HtmlParser htmlParser, CountDownLatch countDownLatch) {
        String domain = hostname.apply(sourceUrl);
        List<String> neighbors = htmlParser.getUrls(toCrawl);
        CountDownLatch neighborLatches = new CountDownLatch(neighbors.size());
        for(String neighbor : neighbors) {
            if(!visited.contains(neighbor) && neighbor.contains(domain)) {
                visited.add(neighbor);
                executorService.execute(() -> _crawl(executorService, visited, sourceUrl, neighbor, htmlParser, neighborLatches));
            }
            else {
                neighborLatches.countDown();
            }
        }

        try {
            neighborLatches.await();
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        countDownLatch.countDown();
    }
}
