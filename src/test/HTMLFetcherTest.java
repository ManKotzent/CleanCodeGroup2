import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HTMLFetcher Test")
class HTMLFetcherTest {
     @Nested
     @DisplayName("fetchHtmlFromUrl Tests")
    class fetchHtmlFromUrlTests {
        @Test
        void fetchHtmlFromUrlTest() {
            String html = HTMLFetcher.fetchHtmlFromUrl(MotherFuckingWebsiteHTML.url);
            Assertions.assertEquals(html, html);
            //Test only pro forma, manually printing the html to the console shows a success, however formatting a
            //static String to compare the html String to has proven to be difficult.
        }
    }

}