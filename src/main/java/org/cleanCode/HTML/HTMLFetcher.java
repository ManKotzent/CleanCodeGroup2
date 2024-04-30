package org.cleanCode.HTML;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class serves as a static factory that takes a URL and returns the found HTML as a String */
public class HTMLFetcher {
    //Note: Depending on the Website the Scanner may not be able to read the HTML properly due to conversion issues
    //Eg.: https://schema.org/WebPage only ever returns noise
    static public String fetchHtmlFromUrl(String url) throws IOException {
        //Solution as per: https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java
        String html = null;
        URLConnection connection;

        connection =  new URL(url).openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());
        scanner.useDelimiter("\\Z");
        while (scanner.hasNext() && !isHtml(html)) html = scanner.next();
        scanner.close();

        if(html == null || html.length() == 0) throw new IOException();
        return html;
    }

    static private boolean isHtml(String str) {
        if(str == null || str.length() == 0) return false;

        Pattern p = Pattern.compile("(\n)*<!DOCTYPE html>[\\s\\S]*</html>");
        Matcher m = p.matcher(str);
        return m.find();
    }
}
