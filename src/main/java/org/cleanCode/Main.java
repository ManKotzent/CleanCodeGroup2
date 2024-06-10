package org.cleanCode;

import org.cleanCode.CrawlerRecord.CrawlerRecord;
import org.cleanCode.CrawlerRecord.CrawlerRecordFactory;
import org.cleanCode.Dialogue.Dialogue;
import org.cleanCode.MarkdownFileCreator.MarkdownFileCreator;
import org.cleanCode.Parameters.Parameters;
import org.cleanCode.Translation.TranslatorApi;

public class Main {

    public static void main(String[] args) {
        Parameters parameters = new Parameters();
        TranslatorApi translator = new TranslatorApi();

        parameters.setLanguages(translator.getLanguages());
        Dialogue dialogue = new Dialogue(parameters);
        dialogue.dialogue();

        CrawlerRecordFactory crawlerRecordFactory = new CrawlerRecordFactory(parameters.getUrl(), parameters.getDepth());
        crawlerRecordFactory.generateCrawlerRecord();
        CrawlerRecord record = crawlerRecordFactory.getRecord();

        MarkdownFileCreator fileCreator = new MarkdownFileCreator();
        fileCreator.createMdFile(record, parameters.getLngSource(), parameters.getLngTarget(), parameters.getDepth());
        System.out.println("Done");
    }

}
