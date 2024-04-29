package org.cleanCode;

import org.cleanCode.CrawlerRecord.CrawlerRecord;
import org.cleanCode.CrawlerRecord.CrawlerRecordFactory;
import org.cleanCode.markdownFileCreator.MarkdownFileCreator;

public class Main {
    private static final String url ="https://stackoverflow.com/";
    private static final int depth = 1;
    private static final String[] domains = {""};

    public static void main(String[] args) {
        CrawlerRecord record = CrawlerRecordFactory.generateCrawlerRecord(url,depth);
        MarkdownFileCreator fileCreator = new MarkdownFileCreator();
        fileCreator.createMdFile(record);

    }
}
