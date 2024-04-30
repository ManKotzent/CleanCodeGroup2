package org.cleanCode.markdownFileCreator;

import org.cleanCode.CrawlerRecord.CrawlerRecord;
import org.cleanCode.Heading.HeaderType;
import org.cleanCode.Heading.Heading;
import org.cleanCode.translation.TranslatorApi;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MarkdownFileCreator {


    public  void createMdFile(CrawlerRecord record, String lngSource, String lngTarget,int depth){
        if(record == null || record.getURL() == null){
            throw new NullPointerException();
        }
        try{
            String filePath = "summary.md";
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            buildSummaryHead(record,fileWriter, lngSource, lngTarget, depth);
            buildSummaryBody(record,fileWriter, lngSource, lngTarget, depth);

            printWriter.close();
            fileWriter.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }


    private void buildSummaryHead(CrawlerRecord record, FileWriter writer, String lngSource, String lngTarget, int depth) throws IOException {
        try{
            writer.write("input: <a>"+record.getURL()+"<a>\n");
            writer.write("<br>depth: "+depth+"\n");
            if(lngSource != null && lngTarget != null) {
                writer.write("<br>source language:"+lngSource+"\n");
                writer.write("<br>target language:"+lngTarget+"\n");
            }
            writer.write("<br>summary\n");
            writer.write("\n");
            writer.flush();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    private void buildSummaryBody(CrawlerRecord record, FileWriter writer, String lngSource, String lngTarget, int depth) throws IOException {
        try{
            List<Heading> headings = record.getHeadings();
            for (Heading heading : headings) {
                writer.write(headerTypeToHashtags(heading) + " " + parseHeaderHTML(heading.getHeading(),lngSource,lngTarget));
                writer.write("\n");
            }
            writer.write("\n");
            writer.flush();

            for(int i =1; i < depth;i++)
                for(CrawlerRecord recordLayer : record.getSubSites()) {
                    if(!recordLayer.isBroken()) {
                        writer.write("<br>--> link to <a>" + recordLayer.getURL() + "<a>\n");
                        headings = recordLayer.getHeadings();
                        if (headings != null) {
                            for (Heading heading : headings) {
                                writer.write(headerTypeToHashtags(heading) + " " + heading.getHeading());
                            }
                        }
                    } else{
                        writer.write("<br>--> broken link <a>"+recordLayer.getURL()+"</a>\n");
                    }
                    writer.write("\n");
                    writer.flush();
                }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    private String parseHeaderHTML(String html, String lngSource, String lngTarget){
        Document doc = Jsoup.parse(html);

        Element element = doc.firstElementChild();

        if(lngSource != null && lngTarget != null && element != null) {
            TranslatorApi api = new TranslatorApi();
            return api.getTranslatedText(lngSource,lngTarget, element.text());
        }

        if(element != null){
            return element.text();
        } else
            return null;
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
