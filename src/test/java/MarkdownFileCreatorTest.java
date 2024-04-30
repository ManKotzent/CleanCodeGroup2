import org.cleanCode.CrawlerRecord.CrawlerRecord;
import org.cleanCode.markdownFileCreator.MarkdownFileCreator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("MarkdownFileCreator Tests")
public class MarkdownFileCreatorTest {

    private MarkdownFileCreator fileCreator;
    private CrawlerRecord crawlerRecord;

    @BeforeEach
    void setUp() {
        fileCreator = new MarkdownFileCreator();
    }


    @DisplayName("CreateMdFile Test")
    @Test
    void createMdFile() {
        crawlerRecord = mock(CrawlerRecord.class);
        when(crawlerRecord.getURL()).thenReturn("http://www.example.com");

        fileCreator.createMdFile(crawlerRecord, null,null);

        File file = new File("summary.md");
        assertTrue(file.exists());
        assertTrue(file.isFile());
    }

    @DisplayName("CreateMdFile Fail Test")
    @Test
    void createMdFileFail() {
        crawlerRecord = mock(CrawlerRecord.class);
        when(crawlerRecord.getURL()).thenReturn(null);

        assertThrows(NullPointerException.class, ()-> fileCreator.createMdFile(crawlerRecord,null,null));
    }

    @AfterEach
    void tearDown() {
        fileCreator = null;
        crawlerRecord = null;
    }
}
