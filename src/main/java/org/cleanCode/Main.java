package org.cleanCode;

import org.cleanCode.CrawlerRecord.CrawlerRecord;
import org.cleanCode.CrawlerRecord.CrawlerRecordFactory;
import org.cleanCode.markdownFileCreator.MarkdownFileCreator;
import org.cleanCode.translation.TranslatorApi;

import java.util.Map;
import java.util.Scanner;

public class Main {
    private static String url ="https://stackoverflow.com/";
    private static int depth = 1;
    private static String[] domains = {""};
    private static String lngSource;
    private static String lngTarget;
    private static  Map<String,String> languages;

    public static void main(String[] args) {
        TranslatorApi translator = new TranslatorApi();
        languages = translator.getLanguages();
        dialog();
        CrawlerRecord record = CrawlerRecordFactory.generateCrawlerRecord(url,depth);
        MarkdownFileCreator fileCreator = new MarkdownFileCreator();
        fileCreator.createMdFile(record,"en","de");

    }

    private static void dialog(){
        Scanner scanner = new Scanner(System.in);
        String userInput = null;

        while (userInput == null||userInput.isEmpty()) {
        System.out.println("Enter url: ");
        userInput = scanner.nextLine();
        url = userInput;

            if (userInput.isEmpty()) {
                System.out.println("url can't be empty");
            }
        }
        System.out.println("Enter a positive number for depth (1 by default):");
        userInput = scanner.nextLine();
        if (userInput.isEmpty()) {
            depth = 1;
            System.out.println("You didn't enter depth. Set 1 by default.");
        } else {
            try{
                depth = Integer.parseInt(userInput);
                System.out.println("Depth set to: " + depth);
            }catch (NumberFormatException e){
                System.out.println("You entered an invalid number. Set 1 by default.");
            }
        }
        System.out.println("Do you want to translate headings yes/no");

        System.out.println("Enter Source Language ");
        userInput = scanner.nextLine().toLowerCase();

        System.out.println("Enter Target Language: ");
        lngTarget = scanner.nextLine().toLowerCase();
        scanner.close();
    }
}
