import TestHTMLs.MfWebsiteTestResources;
import TestHTMLs.MyHTMLTestResources;
import org.cleanCode.HTML.HTMLExtractor;
import HeaderType;
import org.cleanCode.Heading.Heading;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("HTMLExtractor Test")
public class HTMLExtractorTest {
    @Nested
    @DisplayName("getUrls Test")
    class getUrlsTest {
        @DisplayName("Simple Test")
        @Test
        void simpleTest() {
            String html = "<a href=\"https://www.example.com\">Example</a> " +
                    "<a href='http://anotherexample.com'>Another Example</a>";
            HTMLExtractor htmlExtractor = new HTMLExtractor(html);

            List<String> urls = htmlExtractor.getUrls();

            assertEquals(2, urls.size());

            assertEquals("https://www.example.com", urls.get(0));
            assertEquals("http://anotherexample.com", urls.get(1));
        }

        @DisplayName("Complex Test")
        @Test
        void complexTest() {
            String html = MfWebsiteTestResources.html;
            HTMLExtractor htmlExtractor = new HTMLExtractor(html);

            List<String> urls = htmlExtractor.getUrls();

            assertEquals(1, urls.size());

            assertEquals("https://www.vitsoe.com/us/about/good-design", urls.get(0));
        }
    }

    @Nested
    @DisplayName("getHeadings Test")
    class getHeadingsTest {
        @DisplayName("Simple Test")
        @Test
        void simpleTest() {
            String html = "<h1>Heading 1</h1>" +
                    "<h2>Heading 2</h2>" +
                    "<h3>Heading 3</h3>";
            HTMLExtractor htmlExtractor = new HTMLExtractor(html);

            List<Heading> headings = htmlExtractor.getHeadings();

            assertEquals(3, headings.size());

            assertEquals("Heading 1", headings.get(0).getHeading());
            assertEquals("Heading 2", headings.get(1).getHeading());
            assertEquals("Heading 3", headings.get(2).getHeading());

            assertEquals(HeaderType.H1, headings.get(0).getHeaderType());
            assertEquals(HeaderType.H2, headings.get(1).getHeaderType());
            assertEquals(HeaderType.H3, headings.get(2).getHeaderType());
        }

        @DisplayName("Complex Test")
        @Test
        void complexTest() {
            HTMLExtractor htmlExtractor = new HTMLExtractor(MfWebsiteTestResources.html);

            List<Heading> headings = htmlExtractor.getHeadings();

            assertEquals(8, headings.size());

            assertEquals("This is a motherfucking website.", headings.get(0).getHeading());
            assertEquals("Seriously, what the fuck else do you want?", headings.get(1).getHeading());
            assertEquals("Well guess what, motherfucker:", headings.get(2).getHeading());
            assertEquals("It's fucking lightweight", headings.get(3).getHeading());
            assertEquals("It's responsive", headings.get(4).getHeading());
            assertEquals("It fucking works", headings.get(5).getHeading());
            assertEquals("This is a website. Look at it.  You've never seen one before.", headings.get(6).getHeading());
            assertEquals("Yes, this is fucking satire, you fuck", headings.get(7).getHeading());

            assertEquals(HeaderType.H1, headings.get(0).getHeaderType());
            assertEquals(HeaderType.H2, headings.get(1).getHeaderType());
            assertEquals(HeaderType.H3, headings.get(2).getHeaderType());
            assertEquals(HeaderType.H2, headings.get(3).getHeaderType());
            assertEquals(HeaderType.H2, headings.get(4).getHeaderType());
            assertEquals(HeaderType.H2, headings.get(5).getHeaderType());
            assertEquals(HeaderType.H2, headings.get(6).getHeaderType());
            assertEquals(HeaderType.H3, headings.get(7).getHeaderType());
        }
    }

    @Nested
    @DisplayName("removeComments Test")
    class removeCommentsTest {
        @DisplayName("Simple Test")
        @Test
        void simpleTest() {
            HTMLExtractor htmlExtractor = new HTMLExtractor(MyHTMLTestResources.html);

            assertEquals(MyHTMLTestResources.htmlNoComments, htmlExtractor.getHtmlWithNoComments());
        }
    }
}
