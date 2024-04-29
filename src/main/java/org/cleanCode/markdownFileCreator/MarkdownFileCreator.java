package org.cleanCode.markdownFileCreator;

import org.cleanCode.CrawlerRecord.CrawlerRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MarkdownFileCreator {


    public  void createMdFile(CrawlerRecord record){
        if(record == null || record.getURL() == null){
            throw new NullPointerException();
        }
        try{
            String filePath = "summary.md";
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);


            buildReport(record,fileWriter);

            printWriter.close();
            fileWriter.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    private void buildReport(CrawlerRecord record, FileWriter writer) throws IOException {
        try{
            writer.write("input: <a><a>\n");
            writer.write("<br>depth: "+record.getURL()+"\n");
            writer.write("<br>source language:\n");
            writer.write("<br>target language:\n");
            writer.write("<br>summary\n");
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
