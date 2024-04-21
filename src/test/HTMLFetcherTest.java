import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HTMLFetcher Test")
class HTMLFetcherTest {
     @Nested
     @DisplayName("fetchHtmlFromUrl Test")
    class fetchHtmlFromUrlTests {
        @Test
        void fetchHtmlFromUrlTest() {
            String html = HTMLFetcher.fetchHtmlFromUrl(MotherFuckingWebsiteHTML.url);
            Assertions.assertEquals(MotherFuckingWebsiteHTML.html, html);
        }
    }

}