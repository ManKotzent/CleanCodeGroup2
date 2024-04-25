import org.cleanCode.CrawlerRecord;
import org.cleanCode.HeaderType;
import org.cleanCode.Heading;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This test class exists to eliminate warnings for non-use of getters and setters in classes*/
public class ProFormaTests {

    @DisplayName("Pro Forma Test - Crawler Record")
    @Test
    void testCrawlerRecord() {
        // Create test data
        String URL = "https://example.com";
        List<Heading> headings = new ArrayList<>();
        headings.add(new Heading("Heading 1", HeaderType.H1));
        List<CrawlerRecord> subSites = new ArrayList<>();
        subSites.add(new CrawlerRecord("https://example.com/subpage", true));

        // Create a CrawlerRecord instance using the regular constructor
        CrawlerRecord record = new CrawlerRecord(URL, headings, subSites);

        // Test setters and getters
        record.setURL("https://example.org");
        assertEquals("https://example.org", record.getURL());

        List<Heading> newHeadings = new ArrayList<>();
        newHeadings.add(new Heading("New Heading", HeaderType.H2));
        record.setHeadings(newHeadings);
        assertEquals(newHeadings, record.getHeadings());

        List<CrawlerRecord> newSubSites = new ArrayList<>();
        newSubSites.add(new CrawlerRecord("https://example.org/subpage", false));
        record.setSubSites(newSubSites);
        assertEquals(newSubSites, record.getSubSites());

        record.setBroken(true);
        assertTrue(record.isBroken());
    }

    @DisplayName("Pro Forma Test - Heading")
    @Test
    void testHeading() {
        // Create a Heading instance
        Heading heading = new Heading("Example Heading", HeaderType.H1);

        // Test the setHeading method
        heading.setHeading("New Heading");
        assertEquals("New Heading", heading.getHeading());

        // Test the setHeaderType method
        heading.setHeaderType(HeaderType.H2);
        assertEquals(HeaderType.H2, heading.getHeaderType());
    }
}

