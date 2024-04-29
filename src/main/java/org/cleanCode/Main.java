package org.cleanCode;

import org.cleanCode.CrawlerRecord.CrawlerRecord;
import org.cleanCode.CrawlerRecord.CrawlerRecordFactory;
import org.cleanCode.markdownFileCreator.MarkdownFileCreator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("""
                =====================================
                    Clean Code Project S24
                =====================================
                Assignment:       Web-Crawler
                Group:            2
                Members:
                   Manuel Kotzent
                   Filipp Eder
                =====================================
                """);

        System.out.println("Please enter the URL to crawl:");
        String url = sc.nextLine();

        System.out.println("Please enter the depth to be crawled:");
        int depth = sc.nextInt();

        CrawlerRecord crawlerRecord = CrawlerRecordFactory.generateCrawlerRecord(url, depth);

        MarkdownFileCreator markdownFileCreator = new MarkdownFileCreator();
        markdownFileCreator.createMdFile(crawlerRecord.toFormattedString());
    }
}
