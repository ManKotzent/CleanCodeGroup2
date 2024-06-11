package org.cleanCode;

import org.cleanCode.TestHTMLs.MfWebsiteTestResources;
import org.cleanCode.TestHTMLs.MyHTMLTestResources;
import com.sun.net.httpserver.HttpServer;
import org.cleanCode.CrawlerRecord.CrawlerRecord;
import org.cleanCode.CrawlerRecord.CrawlerRecordFactory;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;

 @DisplayName("CrawlerRecordFactory Test")
public class CrawlerRecordFactoryTest {
     @Nested
     @DisplayName("generateCrawlerRecordTest")
     class generateCrawlerRecordTest {
         @BeforeAll
         public static void hostWebsite() throws IOException {
             // Create an HTTP server on port 8000
             HttpServer server1 = HttpServer.create(new InetSocketAddress(8000), 0);

             // Set up a context handler for the root path "/"
             server1.createContext("/", exchange -> {
                 String response = MyHTMLTestResources.htmlExtraLinks1;
                 exchange.sendResponseHeaders(200, response.getBytes().length);
                 OutputStream outputStream = exchange.getResponseBody();
                 outputStream.write(response.getBytes());
                 outputStream.close();
             });

             // Start the server
             server1.start();

             HttpServer server2 = HttpServer.create(new InetSocketAddress(8001), 0);

             // Set up a context handler for the root path "/"
             server2.createContext("/", exchange -> {
                 String response = MyHTMLTestResources.htmlExtraLinks2;
                 exchange.sendResponseHeaders(200, response.getBytes().length);
                 OutputStream outputStream = exchange.getResponseBody();
                 outputStream.write(response.getBytes());
                 outputStream.close();
             });

             // Start the server
             server2.start();
         }

         private CrawlerRecord generateCrawlerRecord(String url, int depth) {
             CrawlerRecordFactory crawlerRecordFactory = new CrawlerRecordFactory(url, depth);
             crawlerRecordFactory.generateCrawlerRecord();
             return crawlerRecordFactory.getRecord();
         }

         @DisplayName("Simple Test (Depth 0 - MFWebsite")
         @Test
         public void simpleTest() {
             CrawlerRecord crawlerRecord = generateCrawlerRecord(MfWebsiteTestResources.url, 0);
             assertEquals("CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=null, subSites=null, isBroken=false}", crawlerRecord.toString());
         }

         @DisplayName("Complex Test (Depth 1 - Localhost:8000")
         @Test
         public void complexTest1() {
             CrawlerRecord crawlerRecord = generateCrawlerRecord("http://localhost:8000", 1);
             assertEquals("CrawlerRecord{URL='http://localhost:8000', headings=[Heading{heading='Welcome to My Website', headerType=H1}, Heading{heading='About Us', headerType=H2}, Heading{heading='Our Services', headerType=H2}, Heading{heading='Contact Us', headerType=H2}, Heading{heading='Additional Links', headerType=H2}], subSites=[CrawlerRecord{URL='https://www.google.com', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8000/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8000/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8000/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8000/#', headings=null, subSites=null, isBroken=false}], isBroken=false}",
                     crawlerRecord.toString());
         }

         @DisplayName("Complex Test 2 (Depth 1 - MFWebsite")
         @Test
         public void complexTest2() {
             CrawlerRecord crawlerRecord = generateCrawlerRecord(MfWebsiteTestResources.url, 1);
             assertEquals("CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=[Heading{heading='This is a motherfucking website.', headerType=H1}, Heading{heading='Seriously, what the fuck else do you want?', headerType=H2}, Heading{heading='Well guess what, motherfucker:', headerType=H3}, Heading{heading='It's fucking lightweight', headerType=H2}, Heading{heading='It's responsive', headerType=H2}, Heading{heading='It fucking works', headerType=H2}, Heading{heading='This is a website. Look at it.  You've never seen one before.', headerType=H2}, Heading{heading='Yes, this is fucking satire, you fuck', headerType=H3}], subSites=[CrawlerRecord{URL='https://www.vitsoe.com/us/about/good-design', headings=null, subSites=null, isBroken=false}], isBroken=false}",
                     crawlerRecord.toString());
         }

         @DisplayName("Complex Test 3 (Depth 2 - Localhost:8001")
         @Test
         public void complexTest3() {
             CrawlerRecord crawlerRecord = generateCrawlerRecord("http://localhost:8001", 2);
             assertEquals("CrawlerRecord{URL='http://localhost:8001', headings=[Heading{heading='Welcome to My Website', headerType=H1}, Heading{heading='About Us', headerType=H2}, Heading{heading='Our Services', headerType=H2}, Heading{heading='Contact Us', headerType=H2}, Heading{heading='Additional Links', headerType=H2}], subSites=[CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=[Heading{heading='This is a motherfucking website.', headerType=H1}, Heading{heading='Seriously, what the fuck else do you want?', headerType=H2}, Heading{heading='Well guess what, motherfucker:', headerType=H3}, Heading{heading='It's fucking lightweight', headerType=H2}, Heading{heading='It's responsive', headerType=H2}, Heading{heading='It fucking works', headerType=H2}, Heading{heading='This is a website. Look at it.  You've never seen one before.', headerType=H2}, Heading{heading='Yes, this is fucking satire, you fuck', headerType=H3}], subSites=[CrawlerRecord{URL='https://www.vitsoe.com/us/about/good-design', headings=null, subSites=null, isBroken=false}], isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=[Heading{heading='Welcome to My Website', headerType=H1}, Heading{heading='About Us', headerType=H2}, Heading{heading='Our Services', headerType=H2}, Heading{heading='Contact Us', headerType=H2}, Heading{heading='Additional Links', headerType=H2}], subSites=[CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}], isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=[Heading{heading='Welcome to My Website', headerType=H1}, Heading{heading='About Us', headerType=H2}, Heading{heading='Our Services', headerType=H2}, Heading{heading='Contact Us', headerType=H2}, Heading{heading='Additional Links', headerType=H2}], subSites=[CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}], isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=[Heading{heading='Welcome to My Website', headerType=H1}, Heading{heading='About Us', headerType=H2}, Heading{heading='Our Services', headerType=H2}, Heading{heading='Contact Us', headerType=H2}, Heading{heading='Additional Links', headerType=H2}], subSites=[CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}], isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=[Heading{heading='Welcome to My Website', headerType=H1}, Heading{heading='About Us', headerType=H2}, Heading{heading='Our Services', headerType=H2}, Heading{heading='Contact Us', headerType=H2}, Heading{heading='Additional Links', headerType=H2}], subSites=[CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}], isBroken=false}], isBroken=false}",
                     crawlerRecord.toString());
         }

         @DisplayName("Exception Test")
         @Test
         public void exceptionTest() {
             CrawlerRecord crawlerRecord = generateCrawlerRecord("https://laflkfnssjhaklhf.com", 0);
             assertEquals("CrawlerRecord{URL='https://laflkfnssjhaklhf.com', headings=null, subSites=null, isBroken=true}", crawlerRecord.toString());
         }

         @DisplayName("Multithreading Test (Depth 1 - Localhost:8000/Depth 2 - Localhost:8001")
         @Test
         public void multithreadingTest() throws InterruptedException {
             CrawlerRecordFactory crawlerRecordFactory1  = new CrawlerRecordFactory("http://localhost:8000", 1);
             CrawlerRecordFactory crawlerRecordFactory2 = new CrawlerRecordFactory("http://localhost:8001", 2);

             crawlerRecordFactory1.start();
             crawlerRecordFactory2.start();

             crawlerRecordFactory1.join();
             crawlerRecordFactory2.join();

             CrawlerRecord crawlerRecord1 = crawlerRecordFactory1.getRecord();
             CrawlerRecord crawlerRecord2 = crawlerRecordFactory2.getRecord();

             assertEquals("CrawlerRecord{URL='http://localhost:8000', headings=[Heading{heading='Welcome to My Website', headerType=H1}, Heading{heading='About Us', headerType=H2}, Heading{heading='Our Services', headerType=H2}, Heading{heading='Contact Us', headerType=H2}, Heading{heading='Additional Links', headerType=H2}], subSites=[CrawlerRecord{URL='https://www.google.com', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8000/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8000/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8000/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8000/#', headings=null, subSites=null, isBroken=false}], isBroken=false}",
                     crawlerRecord1.toString());
             assertEquals("CrawlerRecord{URL='http://localhost:8001', headings=[Heading{heading='Welcome to My Website', headerType=H1}, Heading{heading='About Us', headerType=H2}, Heading{heading='Our Services', headerType=H2}, Heading{heading='Contact Us', headerType=H2}, Heading{heading='Additional Links', headerType=H2}], subSites=[CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=[Heading{heading='This is a motherfucking website.', headerType=H1}, Heading{heading='Seriously, what the fuck else do you want?', headerType=H2}, Heading{heading='Well guess what, motherfucker:', headerType=H3}, Heading{heading='It's fucking lightweight', headerType=H2}, Heading{heading='It's responsive', headerType=H2}, Heading{heading='It fucking works', headerType=H2}, Heading{heading='This is a website. Look at it.  You've never seen one before.', headerType=H2}, Heading{heading='Yes, this is fucking satire, you fuck', headerType=H3}], subSites=[CrawlerRecord{URL='https://www.vitsoe.com/us/about/good-design', headings=null, subSites=null, isBroken=false}], isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=[Heading{heading='Welcome to My Website', headerType=H1}, Heading{heading='About Us', headerType=H2}, Heading{heading='Our Services', headerType=H2}, Heading{heading='Contact Us', headerType=H2}, Heading{heading='Additional Links', headerType=H2}], subSites=[CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}], isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=[Heading{heading='Welcome to My Website', headerType=H1}, Heading{heading='About Us', headerType=H2}, Heading{heading='Our Services', headerType=H2}, Heading{heading='Contact Us', headerType=H2}, Heading{heading='Additional Links', headerType=H2}], subSites=[CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}], isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=[Heading{heading='Welcome to My Website', headerType=H1}, Heading{heading='About Us', headerType=H2}, Heading{heading='Our Services', headerType=H2}, Heading{heading='Contact Us', headerType=H2}, Heading{heading='Additional Links', headerType=H2}], subSites=[CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}], isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=[Heading{heading='Welcome to My Website', headerType=H1}, Heading{heading='About Us', headerType=H2}, Heading{heading='Our Services', headerType=H2}, Heading{heading='Contact Us', headerType=H2}, Heading{heading='Additional Links', headerType=H2}], subSites=[CrawlerRecord{URL='https://motherfuckingwebsite.com/', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}, CrawlerRecord{URL='http://localhost:8001/#', headings=null, subSites=null, isBroken=false}], isBroken=false}], isBroken=false}",
                     crawlerRecord2.toString());
         }
     }
}
