import TestHTMLs.MfWebsiteTestResources;
import TestHTMLs.MyHTMLTestResources;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

 @DisplayName("CrawlerRecordFactory Test")
public class CrawlerRecordFactoryTest {
     @Nested
     @DisplayName("generateCrawlerRecordTest")
     class generateCrawlerRecordTest {
         private static HttpServer server;
         @BeforeAll
         private static void hostWebsite() throws IOException {
             // Create a HTTP server on port 8000
             server = HttpServer.create(new InetSocketAddress(8000), 0);

             // Set up a context handler for the root path "/"
             server.createContext("/", exchange -> {
                 String response = MyHTMLTestResources.htmlExtraLinks;
                 exchange.sendResponseHeaders(200, response.getBytes().length);
                 OutputStream outputStream = exchange.getResponseBody();
                 outputStream.write(response.getBytes());
                 outputStream.close();
             });

             // Start the server
             server.start();
         }

         @DisplayName("Simple Test")
         @Test
         public void simpleTest() {
             CrawlerRecord crawlerRecord = CrawlerRecordFactory.generateCrawlerRecord(MfWebsiteTestResources.url, 0);
             assertEquals("CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=null, subSites=null, isBroken=false}", crawlerRecord.toString());
         }

         @DisplayName("Complex Test")
         @Test
         public void complexTest() {
             CrawlerRecord crawlerRecord = CrawlerRecordFactory.generateCrawlerRecord("http://localhost:8000", 1);
             assertEquals("CrawlerRecord{URL='http://localhost:8000', headings=[Heading{heading='Welcome to My Website', headerType=H1}, Heading{heading='About Us', headerType=H2}, Heading{heading='Our Services', headerType=H2}, Heading{heading='Contact Us', headerType=H2}, Heading{heading='Additional Links', headerType=H2}], subSites=[CrawlerRecord{URL='https://www.google.com', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=null, subSites=null, isBroken=false}], isBroken=false}",
                     crawlerRecord.toString());
         }

         @DisplayName("Complex Test 2")
         @Test
         public void complexTest2() {
             CrawlerRecord crawlerRecord = CrawlerRecordFactory.generateCrawlerRecord(MfWebsiteTestResources.url, 1);
             assertEquals("CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=[Heading{heading='This is a motherfucking website.', headerType=H1}, Heading{heading='Seriously, what the fuck else do you want?', headerType=H2}, Heading{heading='Well guess what, motherfucker:', headerType=H3}, Heading{heading='It's fucking lightweight', headerType=H2}, Heading{heading='It's responsive', headerType=H2}, Heading{heading='It fucking works', headerType=H2}, Heading{heading='This is a website. Look at it.  You've never seen one before.', headerType=H2}, Heading{heading='Yes, this is fucking satire, you fuck', headerType=H3}], subSites=[CrawlerRecord{URL='https://www.vitsoe.com/us/about/good-design', headings=null, subSites=null, isBroken=false}], isBroken=false}",
                     crawlerRecord.toString());
         }

         @DisplayName("Exception Test")
         @Test
         public void exceptionTest() {
             CrawlerRecord crawlerRecord = CrawlerRecordFactory.generateCrawlerRecord("https://laflkfnssjhaklhf.com", 0);
             assertEquals("CrawlerRecord{URL='https://laflkfnssjhaklhf.com', headings=null, subSites=null, isBroken=true}", crawlerRecord.toString());
         }
     }

}
