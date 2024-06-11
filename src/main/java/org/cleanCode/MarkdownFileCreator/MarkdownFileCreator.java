package org.cleanCode.MarkdownFileCreator;

import org.cleanCode.CrawlerRecord.CrawlerRecord;
import org.cleanCode.Heading.HeaderType;
import org.cleanCode.Heading.Heading;
import org.cleanCode.Parameters.Parameters;
import org.cleanCode.Translation.TranslatorApi;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MarkdownFileCreator {
    private final CrawlerRecord record;
    private final String url;
    private final String sourceLanguage;
    private final String targetLanguage;
    private final int depth;
    private TranslatorApi translatorApi;

    public void setTranslatorApi(TranslatorApi translatorApi) {
        this.translatorApi = translatorApi;
    }

    public MarkdownFileCreator(CrawlerRecord record, Parameters parameters) {
        this.record = record;
        this.url = parameters.getUrl();
        this.depth = parameters.getDepth();
        this.sourceLanguage = parameters.getLngSource();
        this.targetLanguage = parameters.getLngTarget();
        this.translatorApi = new TranslatorApi();
    }


    public  void createMdFile(){
        if(record == null){
            throw new NullPointerException();
        }
        try{
            String fileName = "summary.md";
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            buildSummaryHead(fileWriter);
            buildSummaryBody(fileWriter);

            printWriter.close();
            fileWriter.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }


    private void buildSummaryHead(FileWriter writer) throws IOException {
        try{
            writer.write("input: <a>"+url+"<a>\n");
            writer.write("<br>depth: "+depth+"\n");
            if(sourceLanguage != null && targetLanguage != null) {
                writer.write("<br>source language: "+ sourceLanguage +"\n");
                writer.write("<br>target language: "+ targetLanguage +"\n");
            }
            writer.write("<br>summary:\n");
            writer.flush();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    private void buildSummaryBody(FileWriter writer) throws IOException {
        try{
            List<Heading> headings = record.getHeadings();
            writeHeadings(writer, headings);

            writer.write("\n");
            writer.flush();

            for(int i = 1; i < depth;i++)
                for(CrawlerRecord recordLayer : record.getSubSites()) {
                    if(!recordLayer.isBroken()) {
                        String text = Formatting.createUrlWithArrowString(i,recordLayer.getURL(),false);
                        writer.write(text);
                        headings = recordLayer.getHeadings();

                        if (headings != null) {
                            for (Heading heading : headings) {
                                writer.write(Formatting.headerTypeToHashtags(heading) + " " + heading.getHeading()+"\n");
                            }
                        }

                    } else{
                        String text = Formatting.createUrlWithArrowString(i,recordLayer.getURL(),true);
                        writer.write(text);
                    }
                    writer.write("\n");
                    writer.flush();
                }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    private void writeHeadings(FileWriter writer, List<Heading> headings) throws IOException {
        for (Heading heading : headings) {
            writer.write(Formatting.headerTypeToHashtags(heading) + " " + parseHeaderHTML(heading.getHeading()));
            writer.write("\n");
        }
    }

    private String parseHeaderHTML(String html){
        HtmlParser jsoupHtmlParser = new JsoupHtmlParser();
        String text = jsoupHtmlParser.parse(html);

        if(sourceLanguage != null && targetLanguage != null) {
            return translatorApi.getTranslatedText(sourceLanguage, targetLanguage, text);
        }
        return text;
    }
}
