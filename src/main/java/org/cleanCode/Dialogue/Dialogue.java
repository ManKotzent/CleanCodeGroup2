package org.cleanCode.Dialogue;

import org.cleanCode.CrawlerRecord.CrawlerRecordFactory;
import org.cleanCode.Parameters.Parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dialogue {
    private final List<Parameters> parametersList;
    private final Scanner scanner;
    private final Map<String,String> languages;

    public Dialogue(List<Parameters> parameters, Map<String,String> languages) {
        this.parametersList = parameters;
        this.languages = languages;
        this.scanner = new Scanner(System.in);
    }

    private void urlDialog(Parameters parameters){
        String userInput = "";

        while (userInput.isEmpty()) {
            System.out.println("Enter url: ");
            userInput = scanner.nextLine();
            parameters.setUrl(userInput);

            if (userInput.isEmpty()) {
                System.out.println("url can't be empty");
            }
        }
    }

    private void depthDialog(Parameters parameters){
        String userInput;
        System.out.println("Enter a positive number for depth (1 by default):");
        userInput = scanner.nextLine();
        if (userInput.isEmpty()) {

            System.out.println("You didn't enter depth. Set 1 by default.");
        } else if(Integer.parseInt(userInput) < 1){
            System.out.println("You entered an invalid number. Set 1 by default.");
        } else {
            try{
                parameters.setDepth(Integer.parseInt(userInput));
                System.out.println("Depth set to: " + parameters.getDepth());
            }catch (NumberFormatException e){
                System.out.println("You entered an invalid number. Set 1 by default.");
            }
        }
    }

    private void languageDialog(Parameters parameters){
        String userInput;
        boolean answered = false;
        while (!answered) {
            System.out.println("Do you want to translate headings yes/no");
            userInput = scanner.nextLine().toLowerCase();

            if (userInput.equals("yes")) {
                answered = true;

                while(parameters.getLngSource() == null) {
                    System.out.println("Enter Source Language (i.e. english, german, italian, etc.)");
                    userInput = scanner.nextLine().toLowerCase();
                    parameters.setLngSource(languages.getOrDefault(userInput,null));
                    if(parameters.getLngSource() == null){
                        System.out.println("Source Language was not found. Either the language is not supported or the input contains a misspelling");
                    }
                }
                while(parameters.getLngTarget() == null){
                    System.out.println("Enter Target Language: ");
                    userInput = scanner.nextLine().toLowerCase();
                    parameters.setLngTarget(languages.getOrDefault(userInput,null));
                    if(parameters.getLngTarget() == null){
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

    private String getCard(){
        return """
                =====================================
                    Clean Code Project S24
                =====================================
                Assignment:       Web-Crawler
                Group:            2
                Members:
                   Manuel Kotzent
                   Filipp Eder
                =====================================
                """;
    }

    public void dialogue(Parameters parameters){
        urlDialog(parameters);
        depthDialog(parameters);
        languageDialog(parameters);
        System.out.println("URL: "+parameters.getUrl()+", Depth: "+parameters.getDepth());
        if(parameters.getLngSource() != null && parameters.getLngTarget() != null){
            System.out.println("Source Language: "+parameters.getLngSource()+", Target Language: "+parameters.getLngTarget());
        }
    }

    public int multithreadingDialogueSetNumber(){

        String userInput;
        System.out.println("Enter number of crawlers you want to run (1 by default):");
        userInput = scanner.nextLine();

        if (userInput.isEmpty()) {
            System.out.println("You didn't enter a number. Set 1 by default.");
        } else if(Integer.parseInt(userInput) < 1){
            System.out.println("You entered an invalid number. Set 1 by default.");
        } else {
            try{
                System.out.println("Number of crawlers to run: " + userInput);
                return Integer.parseInt(userInput);
            }catch (NumberFormatException e){
                System.out.println("You entered an invalid number. Set 1 by default.");
            }
        }
        return 1;
    }

    public List<CrawlerRecordFactory> multithreadingDialogue(){
        System.out.println(getCard());
        List<CrawlerRecordFactory> factories = new ArrayList<>();

        int threads = multithreadingDialogueSetNumber();
        for(int i = 0; i < threads; i++){
            Parameters parameters = new Parameters();
            System.out.println("Setting up thread number "+(i+1));
            dialogue(parameters);
            parametersList.add(parameters);
            CrawlerRecordFactory factory = new CrawlerRecordFactory(parameters.getUrl(),parameters.getDepth());
            factories.add(factory);
        }
        scanner.close();
        return factories;
    }
}
