package org.cleanCode.markdownFileCreator;

import org.cleanCode.CrawlerRecord.CrawlerRecord;
import org.cleanCode.Heading.HeaderType;
import org.cleanCode.Heading.Heading;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MarkdownFileCreator {


    public  void createMdFile(CrawlerRecord record, String lngSource, String lngTarget){
        if(record == null || record.getURL() == null){
            throw new NullPointerException();
        }
        try{
            String filePath = "summary.md";
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            buildSummaryHead(record,fileWriter, lngSource, lngTarget);
            buildSummaryBody(record,fileWriter, lngSource, lngTarget);

            printWriter.close();
            fileWriter.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }


    private void buildSummaryHead(CrawlerRecord record, FileWriter writer, String lngSource, String lngTarget) throws IOException {
        try{
            writer.write("input: <a>"+record.getURL()+"<a>\n");
            writer.write("<br>depth: "+(record.getSubSites().size()+1)+"\n");
            if(lngSource != null && lngTarget != null) {
                writer.write("<br>source language:"+lngSource+"\n");
                writer.write("<br>target language:"+lngTarget+"\n");

                writer.write("<br>summary\n");
                writer.flush();
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    private void buildSummaryBody(CrawlerRecord record, FileWriter writer, String lngSource, String lngTarget) throws IOException {
        try{
            List<Heading> headings = record.getHeadings();
            for (Heading heading : headings) {
                writer.write(headerTypeToHashtags(heading) + " " + heading.getHeading());
            }
            System.out.println();
            writer.flush();

            for(CrawlerRecord recordLayer : record.getSubSites()) {
                headings = recordLayer.getHeadings();
                if(headings != null) {
                    for (Heading heading : headings) {
                        writer.write(headerTypeToHashtags(heading) + " " + heading.getHeading());
                    }
                    System.out.println();
                    writer.flush();
                }
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    private String headerTypeToHashtags(Heading heading){
        HeaderType headerType = heading.getHeaderType();
        int amount = 0;
        switch (headerType){
            case H1 -> amount = 1;
            case H2 -> amount = 2;
            case H3 -> amount = 3;
            case H4 -> amount = 4;
            case H5 -> amount = 5;
            case H6 -> amount = 6;
        }
        return "#".repeat(Math.max(1, amount));
    }
}
