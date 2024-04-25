package org.cleanCode;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Class serves as a static factory that takes a URL and returns the found HTML as a String */
public class HTMLFetcher {
    static public String fetchHtmlFromUrl(String url) throws IOException {
        //Solution as per: https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java
        String html;
        URLConnection connection;

        connection =  new URL(url).openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());
        scanner.useDelimiter("\\Z");
        html = scanner.next();
        scanner.close();

        return html;
    }
}
