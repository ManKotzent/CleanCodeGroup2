import java.util.LinkedList;
import java.util.List;

public class CrawlerRecordFactory {
    public static CrawlerRecord generateCrawlerRecord (String url, int depth) {
        CrawlerRecord crawlerRecord;

        try {
            String html = HTMLFetcher.fetchHtmlFromUrl(url);

            //If the maximum depth is reached, create a simple record
            if(depth == 0) {
                crawlerRecord = new CrawlerRecord(url, false);

            //If the maximum depth isn't reached, go deeper recursively
            } else {
                HTMLExtractor htmlExtractor = new HTMLExtractor(html);
                List<Heading> headings = htmlExtractor.getHeadings();
                List<String> urls = htmlExtractor.getUrls();
                List<CrawlerRecord> subSites = new LinkedList<>();

                for(String url_ : urls) {
                    subSites.add(generateCrawlerRecord(url_, depth - 1));
                }

                crawlerRecord = new CrawlerRecord(url, headings, subSites);
            }

        } catch (Exception e) {
            //If the url is broken, create a simple record
            crawlerRecord = new CrawlerRecord(url, true);
        }

        return crawlerRecord;
    }
}
