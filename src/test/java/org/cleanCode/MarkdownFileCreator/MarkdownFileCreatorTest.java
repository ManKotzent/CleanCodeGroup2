package org.cleanCode.MarkdownFileCreator;

import org.cleanCode.CrawlerRecord.CrawlerRecord;
import org.cleanCode.Parameters.Parameters;
import org.cleanCode.Translation.TranslatorApi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@DisplayName("MarkdownFileCreator Tests")
public class MarkdownFileCreatorTest {

    private MarkdownFileCreator fileCreator;
    private TranslatorApi translatorApiMock;
    private List<Parameters> parametersListMock;
    private List<CrawlerRecord> crawlerRecordListMock;

    @BeforeEach
    void setUp() {
        crawlerRecordListMock = new ArrayList<>();
        parametersListMock = new ArrayList<>();
        fileCreator = new MarkdownFileCreator(crawlerRecordListMock,parametersListMock);
    }

    @DisplayName("CreateMdFile Test")
    @Test
    void createMdFile() throws IOException {
        CrawlerRecord crawlerRecord = mock(CrawlerRecord.class);
        Parameters parameters = mock(Parameters.class);
        crawlerRecordListMock.add(crawlerRecord);
        parametersListMock.add(parameters);

        fileCreator.createMdFile();

        File file = new File("summary.md");
        assertTrue(file.exists());
        assertTrue(file.isFile());
    }

    @DisplayName("CreateMdFile Fail Test if CrawlerRecord is empty")
    @Test
    void createMdFileCrawlerRecordNull() {
        fileCreator = new MarkdownFileCreator(null,parametersListMock);
        assertThrows(IllegalArgumentException.class, ()-> fileCreator.createMdFile());
    }

    @DisplayName("CreateMdFile Fail Test if CrawlerRecord is null")
    @Test
    void createMdFileCrawlerRecordIsEmpty() {
        assertThrows(IllegalArgumentException.class, ()-> fileCreator.createMdFile());
    }

    @AfterEach
    void tearDown() {
        fileCreator = null;
        parametersListMock.clear();
        crawlerRecordListMock.clear();
    }
}
