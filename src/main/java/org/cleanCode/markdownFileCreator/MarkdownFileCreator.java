package org.cleanCode.markdownFileCreator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MarkdownFileCreator {
    String filePath = "example.md";


    public void createMdFile(String data){
        try{
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.print(data);

            printWriter.close();
            fileWriter.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
