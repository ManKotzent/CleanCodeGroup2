import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Class serves as a static factory that takes a URL and returns the found HTML as a String
 * */
public class HTMLFetcher {
    static public String fetchHtmlFromUrl(String url) {
        //Solution as per: https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java
        String html = null;
        URLConnection connection = null;

        try {
            connection =  new URL(url).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            html = scanner.next();
            scanner.close();
        } catch (Exception e) {
            System.err.println("HTMLFetcher Error: " + e.toString());
            e.printStackTrace();
        }

        return html;
    }
}
