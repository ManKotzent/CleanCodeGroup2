package org.cleanCode.MarkdownFileCreator;

import org.cleanCode.CrawlerRecord.CrawlerRecord;
import org.cleanCode.Heading.HeaderType;
import org.cleanCode.Heading.Heading;
import org.cleanCode.Parameters.Parameters;
import org.cleanCode.Translation.TranslatorApi;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Parameter;
import java.util.List;

public class MarkdownFileCreator {
    private final List<Parameters> parametersList;
    private final List<CrawlerRecord> crawlerRecordList;
    private TranslatorApi translatorApi;

    public void setTranslatorApi(TranslatorApi translatorApi) {
        this.translatorApi = translatorApi;
    }

    public MarkdownFileCreator(List<CrawlerRecord> recordList, List<Parameters> parametersList) {
        this.crawlerRecordList = recordList;
        this.parametersList = parametersList;
        this.translatorApi = new TranslatorApi();
    }


    public  void createMdFile(){
        if(crawlerRecordList == null || crawlerRecordList.isEmpty()){
            throw new NullPointerException();
        }
        try{
            String fileName = "summary.md";
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(int i = 0; i<crawlerRecordList.size(); i++){
                buildSummaryHead(fileWriter,parametersList.get(i));
                buildSummaryBody(fileWriter,parametersList.get(i),crawlerRecordList.get(i));
            }
            printWriter.close();
            fileWriter.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }


    private void buildSummaryHead(FileWriter writer, Parameters parameters) throws IOException {
        try{
            writer.write("input: <a>"+parameters.getUrl()+"<a>\n");
            writer.write("<br>depth: "+parameters.getDepth()+"\n");
            if(parameters.getLngSource() != null && parameters.getLngTarget() != null) {
                writer.write("<br>source language: "+ parameters.getLngSource() +"\n");
                writer.write("<br>target language: "+ parameters.getLngTarget() +"\n");
            }
            writer.write("<br>summary:\n");
            writer.flush();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    private void buildSummaryBody(FileWriter writer, Parameters parameters, CrawlerRecord record) throws IOException {
        try{
            List<Heading> headings = record.getHeadings();
            writeHeadings(writer, headings, parameters);

            writer.write("\n");
            writer.flush();

            for(int i = 1; i < parameters.getDepth();i++)
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

    private void writeHeadings(FileWriter writer, List<Heading> headings, Parameters parameters) throws IOException {
        for (Heading heading : headings) {
            writer.write(Formatting.headerTypeToHashtags(heading) + " " + parseHeaderHTML(heading.getHeading(), parameters));
            writer.write("\n");
        }
    }

    private String parseHeaderHTML(String html, Parameters parameters){
        HtmlParser jsoupHtmlParser = new JsoupHtmlParser();
        String text = jsoupHtmlParser.parse(html);

        if(parameters.getLngSource() != null && parameters.getLngTarget() != null) {
            return translatorApi.getTranslatedText(parameters.getLngSource(), parameters.getLngTarget(), text);
        }
        return text;
    }
}
