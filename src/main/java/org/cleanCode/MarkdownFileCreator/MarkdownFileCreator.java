package org.cleanCode.MarkdownFileCreator;

import org.cleanCode.CrawlerRecord.CrawlerRecord;
import org.cleanCode.Heading.Heading;
import org.cleanCode.Parameters.Parameters;
import org.cleanCode.Translation.TranslatorApi;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.util.List;

public class MarkdownFileCreator {
    private final List<Parameters> parametersList;
    private final List<CrawlerRecord> crawlerRecordList;
    private TranslatorApi translatorApi;
    private FileWriter fileWriter;
    private PrintWriter printWriter;

    public void setTranslatorApi(TranslatorApi translatorApi) {
        this.translatorApi = translatorApi;
    }

    public MarkdownFileCreator(List<CrawlerRecord> recordList, List<Parameters> parametersList) {
        this.crawlerRecordList = recordList;
        this.parametersList = parametersList;
        this.translatorApi = new TranslatorApi();
    }


    public  void createMdFile() throws IOException {
        if(parametersList.isEmpty()){
            throw new IllegalArgumentException("List of parameters is empty");
        }
        if(crawlerRecordList == null || crawlerRecordList.isEmpty()){
            throw new IllegalArgumentException("MarkdownFileCreator.createMdFile: crawlerRecordList is null or empty");
        }
        try{
            String fileName = "summary.md";
            fileWriter = new FileWriter(fileName);
            printWriter = new PrintWriter(fileWriter);
            for(int i = 0; i<crawlerRecordList.size(); i++){
                buildSummaryHead(parametersList.get(i));
                buildSummaryBody(parametersList.get(i),crawlerRecordList.get(i));
            }
            printWriter.close();
            fileWriter.close();
        }catch (IOException e){
            throw new IOException("MarkdownFileCreator.createMdFile: IOException "+e.getMessage());
        }
    }


    private void buildSummaryHead(Parameters parameters) throws IOException {
        try{
            fileWriter.write("input: <a>"+parameters.getUrl()+"<a>\n");
            fileWriter.write("<br>depth: "+parameters.getDepth()+"\n");
            if(parameters.getSourceLanguage() != null && parameters.getTargetLanguage() != null) {
                fileWriter.write("<br>source language: "+ parameters.getSourceLanguage() +"\n");
                fileWriter.write("<br>target language: "+ parameters.getTargetLanguage() +"\n");
            }
            fileWriter.write("<br>summary:\n");
            fileWriter.flush();
        }catch (IOException e){
            throw new IOException("MarkdownFileCreator.buildSummaryHead: IOException "+e.getMessage());
        }
    }

    private void buildSummaryBody(Parameters parameters, CrawlerRecord record) throws IOException {
        try{
            List<Heading> headings = record.getHeadings();
            writeHeadings(headings, parameters);

            fileWriter.write("\n");
            fileWriter.flush();

            for(int i = 1; i < parameters.getDepth();i++)
                for(CrawlerRecord recordLayer : record.getSubSites()) {
                    if(!recordLayer.isBroken()) {
                        String text = Formatting.createUrlWithArrowString(i,recordLayer.getURL(),false);
                        fileWriter.write(text);
                        headings = recordLayer.getHeadings();

                        if (headings != null) {
                            for (Heading heading : headings) {
                                fileWriter.write(Formatting.headerTypeToHashtags(heading) + " " + heading.getHeading()+"\n");
                            }
                        }

                    } else{
                        String text = Formatting.createUrlWithArrowString(i,recordLayer.getURL(),true);
                        fileWriter.write(text);
                    }
                    fileWriter.write("\n");
                    fileWriter.flush();
                }
        } catch (IOException e){
           throw new IOException("MarkdownFileCreator.buildSummaryBody: IOException"+e.getMessage());
        }
    }

    private void writeHeadings(List<Heading> headings, Parameters parameters) throws IOException {
        try {
            for (Heading heading : headings) {
                fileWriter.write(Formatting.headerTypeToHashtags(heading) + " " + parseHeaderHTML(heading.getHeading(), parameters));
                fileWriter.write("\n");
            }
        }catch (IOException e){
            throw new IOException("MarkdownFileCreator.writeHeadings: IOException "+e.getMessage());
        }catch (InterruptedException e){
            throw new InterruptedIOException("MarkdownFileCreator.writeHeadings: InterruptedException "+e.getMessage());
        }
    }

    private String parseHeaderHTML(String html, Parameters parameters) throws IOException, InterruptedException{
        HtmlParser jsoupHtmlParser = new JsoupHtmlParser();
        String text = jsoupHtmlParser.parse(html);

        if(parameters.getSourceLanguage() != null && parameters.getTargetLanguage() != null) {
            try {
                return translatorApi.getTranslatedText(parameters.getSourceLanguage(), parameters.getTargetLanguage(), text);
            }catch (IOException e){
                throw new IOException("MarkdownFileCreator.parseHeaderHTML: IOException "+e.getMessage());
            }catch (InterruptedException e){
                throw new InterruptedException("MarkdownFileCreator.parseHeaderHTML: InterruptedException "+e.getMessage());
            }
        }
        return text;
    }
}
