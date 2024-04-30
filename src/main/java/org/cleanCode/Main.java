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
    private static String lngSource = null;
    private static String lngTarget = null;
    private static  Map<String,String> languages;

    public static void main(String[] args) {
        TranslatorApi translator = new TranslatorApi();
        languages = translator.getLanguages();
        dialog();
        CrawlerRecord record = CrawlerRecordFactory.generateCrawlerRecord(url,depth);

        MarkdownFileCreator fileCreator = new MarkdownFileCreator();
        fileCreator.createMdFile(record,lngSource,lngTarget,depth);
    }


    private static void urlDialog(Scanner scanner){
        String userInput = null;

        while (userInput == null||userInput.isEmpty()) {
            System.out.println("Enter url: ");
            userInput = scanner.nextLine();
            url = userInput;

            if (userInput.isEmpty()) {
                System.out.println("url can't be empty");
            }
        }
    }


    private static void depthDialog(Scanner scanner){
        String userInput;
        System.out.println("Enter a positive number for depth (1 by default):");
        userInput = scanner.nextLine();
        if (userInput.isEmpty()) {

            System.out.println("You didn't enter depth. Set 1 by default.");
        } else if(Integer.parseInt(userInput) < 1){
            System.out.println("You entered an invalid number. Set 1 by default.");
        } else {
            try{
                depth = Integer.parseInt(userInput);
                System.out.println("Depth set to: " + depth);
            }catch (NumberFormatException e){
                System.out.println("You entered an invalid number. Set 1 by default.");
            }
        }
    }


    private static void languageDialog(Scanner scanner){
        String userInput;
        boolean answered = false;
        while (!answered) {
            System.out.println("Do you want to translate headings yes/no");
            userInput = scanner.nextLine().toLowerCase();

            if (userInput.equals("yes")) {
                answered = true;

                while(lngSource == null) {
                    System.out.println("Enter Source Language (i.e. english, german, italian, etc.)");
                    userInput = scanner.nextLine().toLowerCase();
                    lngSource = languages.getOrDefault(userInput,null);
                    if(lngSource == null){
                        System.out.println("Source Language was not found. Either the language is not supported or the input contains a misspelling");
                    }
                }
                while(lngTarget == null){
                    System.out.println("Enter Target Language: ");
                    userInput = scanner.nextLine().toLowerCase();
                    lngTarget = languages.getOrDefault(userInput,null);
                    if(lngTarget == null){
                        System.out.println("Target Language was not found. Either the language is not supported or the input contains a misspelling");
                    }
                }

            } else if (userInput.equals("no")) {
                answered = true;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    private static void dialog(){
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
        Scanner scanner = new Scanner(System.in);
        urlDialog(scanner);
        depthDialog(scanner);
        languageDialog(scanner);
        scanner.close();
        System.out.println("URL: "+url+", Depth: "+depth);
        if(lngSource != null && lngTarget != null){
            System.out.println("Source Language: "+lngSource+", Target Language: "+lngTarget);
        }
        System.out.println("Your request is processing. Please wait...");
    }
}
