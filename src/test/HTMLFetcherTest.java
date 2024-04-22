import TestHTMLs.MfWebsiteHTML;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("HTMLFetcher Test")
class HTMLFetcherTest {
     @Nested
     @DisplayName("fetchHtmlFromUrl Test")
    class fetchHtmlFromUrlTests {
        @Test
        @DisplayName("Simple Test")
        void simpleTest() throws IOException {
            String html = HTMLFetcher.fetchHtmlFromUrl(MfWebsiteHTML.url);
            assertEquals(MfWebsiteHTML.html, html);
        }

         @Test
         @DisplayName("Exception Test")
         void exceptionTest() {
            assertThrows(IOException.class, () -> {
                    String html = HTMLFetcher.fetchHtmlFromUrl("https://laflkfnssjhaklhf.com");
                    System.out.println(html);
            });
         }
    }

}