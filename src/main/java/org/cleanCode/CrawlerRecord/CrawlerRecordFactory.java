package org.cleanCode.CrawlerRecord;

import org.cleanCode.HTML.HTMLExtractor;
import org.cleanCode.HTML.HTMLFetcher;
import org.cleanCode.Heading.Heading;
import org.cleanCode.URLHandler.UrlHandler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class serves as a static factory for the Crawler Record
 * 3 main paths:
 * 1. HTMLFetcher throws IOException - the record for the current URL is marked as broken and returned
 * 2. The maximum depth has been reached, no further scraping is required as this Record only serves as a marker for the
 *    URLs found on the previous site
 * 3. The scraping continues - the HTML page is searched for headings and URLs. Further, deeper records for these URLs
 *    are created and compiled into a list. The current URL, the found headings and the created list of deeper sub-sites
 *    are saved in the new record which is then returned*/
public class CrawlerRecordFactory extends Thread {
    private final String url;
    private final int depth;
    private CrawlerRecord record;

    public CrawlerRecordFactory(String url, int depth) {
        this.url = url;
        this.depth = depth;
    }

    @Override
    public void run() { this.generateCrawlerRecord(); }

    public CrawlerRecord getRecord() {
        return this.record;
    }

    public void generateCrawlerRecord () {
        if(UrlHandler.isRessourceUrl(this.url) || UrlHandler.isLargeSocialMedia(this.url)) {
            this.record = new CrawlerRecord(url, null, null);
            return;
        }

        CrawlerRecord crawlerRecord;

        try {
            String html = HTMLFetcher.fetchHtmlFromUrl(this.url);

            //If the maximum depth is reached, create a simple record
            if(this.depth == 0) {
                crawlerRecord = new CrawlerRecord(this.url, false);

                //If the maximum depth isn't reached, go deeper recursively
            } else {
                HTMLExtractor htmlExtractor = new HTMLExtractor(html, this.url);
                List<Heading> headings = htmlExtractor.getHeadings();
                List<String> urls = htmlExtractor.getUrls();
                List<CrawlerRecord> subSites = new LinkedList<>();

                List<CrawlerRecordFactory> crawlerRecordFactories = new LinkedList<>();

                for(String url_ : urls) {
                    CrawlerRecordFactory crawlerRecordFactory = new CrawlerRecordFactory(url_, this.depth - 1);
                    crawlerRecordFactory.start();
                    crawlerRecordFactories.add(crawlerRecordFactory);
                }

                for(CrawlerRecordFactory crawlerRecordFactory : crawlerRecordFactories) {
                    try {
                        crawlerRecordFactory.join();
                        subSites.add(crawlerRecordFactory.getRecord());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                crawlerRecord = new CrawlerRecord(this.url, headings, subSites);
            }

        } catch (IOException e) {
            //If the url is broken, create a simple record
            crawlerRecord = new CrawlerRecord(this.url, true);
        }

        this.record = crawlerRecord;
    }

    @Deprecated
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
                    if(UrlHandler.isRessourceUrl(url_) || UrlHandler.isLargeSocialMedia(url_)) subSites.add(new CrawlerRecord(url_, null, null));
                    else subSites.add(generateCrawlerRecord(url_, depth - 1));
                }

                crawlerRecord = new CrawlerRecord(url, headings, subSites);
            }

        } catch (IOException e) {
            //If the url is broken, create a simple record
            crawlerRecord = new CrawlerRecord(url, true);
        }

        return crawlerRecord;
    }
}
