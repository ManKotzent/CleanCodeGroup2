package org.cleanCode.Dialogue;

import org.cleanCode.Parameters.Parameters;
import java.util.Scanner;

public class Dialogue {
    private final Parameters parameters;
    private final Scanner scanner;

    public Dialogue(Parameters parameters) {
        this.parameters = parameters;
        this.scanner = new Scanner(System.in);
    }

    private void urlDialog(){
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

    private void depthDialog(){
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

    private void languageDialog(){
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
                    parameters.setLngSource(parameters.getLanguages().getOrDefault(userInput,null));
                    if(parameters.getLngSource() == null){
                        System.out.println("Source Language was not found. Either the language is not supported or the input contains a misspelling");
                    }
                }
                while(parameters.getLngTarget() == null){
                    System.out.println("Enter Target Language: ");
                    userInput = scanner.nextLine().toLowerCase();
                    parameters.setLngTarget(parameters.getLanguages().getOrDefault(userInput,null));
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

    public void dialogue(){
        System.out.println(getCard());
        urlDialog();
        depthDialog();
        languageDialog();
        scanner.close();
        System.out.println("URL: "+parameters.getUrl()+", Depth: "+parameters.getDepth());
        if(parameters.getLngSource() != null && parameters.getLngTarget() != null){
            System.out.println("Source Language: "+parameters.getLngSource()+", Target Language: "+parameters.getLngTarget());
        }
        System.out.println("Your request is processing. Please wait...");
    }
}
