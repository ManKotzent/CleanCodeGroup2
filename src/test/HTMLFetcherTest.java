import TestHTMLs.MfWebsiteHTML;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("HTMLFetcher Test")
class HTMLFetcherTest {
     @Nested
     @DisplayName("fetchHtmlFromUrl Test")
    class fetchHtmlFromUrlTests {
        @Test
        void fetchHtmlFromUrlTest() {
            String html = HTMLFetcher.fetchHtmlFromUrl(MfWebsiteHTML.url);
            Assertions.assertEquals(MfWebsiteHTML.html, html);
        }
    }

}