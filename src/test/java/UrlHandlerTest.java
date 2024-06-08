import org.cleanCode.URLHandler.UrlHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UrlHandlerTest {

    @Nested
    @DisplayName("StartsWithHttp/s Test")
    class StartsWithHttp_sTest {
        @Test
        @DisplayName("Simple Positive Test 1")
        void simplePositiveTest1() {
            assertTrue(UrlHandler.startsWithHttp_s("http://example.com"));
        }

        @Test
        @DisplayName("Simple Positive Test 1")
        void simplePositiveTest2() {
            assertTrue(UrlHandler.startsWithHttp_s("https://example.com"));
        }

        @Test
        @DisplayName("Simple Negative Test 1")
        void simpleNegativeTest1() {
            assertFalse(UrlHandler.startsWithHttp_s("ftp://example.com"));
        }

        @Test
        @DisplayName("Simple Negative Test 2")
        void simpleNegativeTest2() {
            assertFalse(UrlHandler.startsWithHttp_s("example.com"));
        }
    }

    @Nested
    @DisplayName("GetWebsiteUrl Test")
    class GetWebsiteUrlTest {
        @Test
        @DisplayName("Regular Return Test 1")
        void regularReturn1() {
            String url = "https://de.wikipedia.org/wiki/Wikipedia:Hauptseite";
            String expected = "https://de.wikipedia.org/";
            assertEquals(expected, UrlHandler.getWebsiteUrl(url));
        }

        @Test
        @DisplayName("Regular Return Test 2")
        void regularReturn2() {
            String url = "http://de.wikipedia.org/wiki/Wikipedia:Hauptseite";
            String expected = "http://de.wikipedia.org/";
            assertEquals(expected, UrlHandler.getWebsiteUrl(url));
        }

        @Test
        @DisplayName("Regular Return Test 3")
        void regularReturn3() {
            String url = "https://www.example.com/";
            String expected = "https://www.example.com/";
            assertEquals(expected, UrlHandler.getWebsiteUrl(url));
        }

        @Test
        @DisplayName("Null Return Test")
        void nullReturn() {
            String url = "ftp://example.com";
            assertNull(UrlHandler.getWebsiteUrl(url));
        }
    }
}

