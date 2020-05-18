package questions;

import java.util.*;

public class WebCrawler {

    public interface HtmlParser {
        List<String> getUrls(String url);
    }

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        List<String> crawledUrls = new ArrayList<>();

        _iterativeCrawl(startUrl, htmlParser, crawledUrls, new HashSet<>());
        return crawledUrls;
    }

    private void _iterativeCrawl(String url, HtmlParser htmlParser, List<String> urls, Set<String> visited) {
        String hostname = getHostName(url);
        Queue<String> bfs = new LinkedList<>();
        bfs.add(url);
        visited.add(url);
        urls.add(url);

        while(!bfs.isEmpty()) {
            String currentUrl = bfs.poll();

            for(String crawl : htmlParser.getUrls(currentUrl)) {
                if(visited.contains(crawl))
                    continue;

                if(crawl.contains(hostname)) {
                    visited.add(crawl);
                    urls.add(crawl);
                    bfs.add(crawl);
                }
            }
        }
    }

    private void _recursiveCrawl(String url, HtmlParser htmlParser, List<String> urls, Set<String> visited) {
        if(visited.contains(url))
            return;

        visited.add(url);
        urls.add(url);
        List<String> crawlingUrls = htmlParser.getUrls(url);

        for(String crawlUrl : crawlingUrls) {
            if(visited.contains(crawlUrl))
                continue;

            if(getHostName(crawlUrl).equals(getHostName(url))) {
                _recursiveCrawl(crawlUrl, htmlParser, urls, visited);
            }
        }
    }

    private String getHostName(String url) {
        return url.split("/")[2];
    }
}
