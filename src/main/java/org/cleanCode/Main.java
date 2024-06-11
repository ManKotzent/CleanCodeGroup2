package org.cleanCode;

import org.cleanCode.CrawlerRecord.CrawlerRecord;
import org.cleanCode.CrawlerRecord.CrawlerRecordFactory;
import org.cleanCode.Dialogue.Dialogue;
import org.cleanCode.MarkdownFileCreator.MarkdownFileCreator;
import org.cleanCode.Parameters.Parameters;
import org.cleanCode.Translation.TranslatorApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        List<Parameters> parametersList = new ArrayList<>();
        List<CrawlerRecordFactory> crawlerRecordFactories;
        List<CrawlerRecord> crawlerRecords = new ArrayList<>();

        TranslatorApi translator = new TranslatorApi();
        Map<String, String> languages;
        languages = translator.getLanguages();
        Dialogue dialogue = new Dialogue(parametersList, languages);

        crawlerRecordFactories = dialogue.multithreadingDialogue();
        System.out.println("Your request is processing. Please wait...");
        for (CrawlerRecordFactory crawlerRecordFactory : crawlerRecordFactories) {
            crawlerRecordFactory.start();
        }

        for(int i = 0; i < crawlerRecordFactories.size(); i++) {
            try {
                CrawlerRecordFactory crawlerRecordFactory = crawlerRecordFactories.get(i);
                crawlerRecordFactory.join();
                crawlerRecords.add(crawlerRecordFactory.getRecord());

            }catch (InterruptedException e) {
                System.out.println("Thread "+i+1+" was interrupted: "+ e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        MarkdownFileCreator fileCreator = new MarkdownFileCreator(crawlerRecords, parametersList);
        fileCreator.createMdFile();
        System.out.println("Done");

    }

}
