package org.cleanCode.MarkdownFileCreator;

import org.cleanCode.CrawlerRecord.CrawlerRecord;
import org.cleanCode.Parameters.Parameters;
import org.cleanCode.Translation.TranslatorApi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("MarkdownFileCreator Tests")
public class MarkdownFileCreatorTest {

    private MarkdownFileCreator fileCreator;
    private CrawlerRecord crawlerRecordMock;
    private Parameters parameters;
    private TranslatorApi translatorApiMock;

    @BeforeEach
    void setUp() {
        parameters = new Parameters();
        parameters.setUrl("https://www.Test.com");
        parameters.setDepth(1);
        parameters.setLngSource(null);
        parameters.setLngTarget(null);
    }

    @DisplayName("CreateMdFile Test")
    @Test
    void createMdFile() {
        crawlerRecordMock = mock(CrawlerRecord.class);

   //     fileCreator = new MarkdownFileCreator(crawlerRecordMock, parameters);
        fileCreator.createMdFile();

        File file = new File("summary.md");
        assertTrue(file.exists());
        assertTrue(file.isFile());
    }

    @DisplayName("CreateMdFile Fail Test if CrawlerRecord is null")
    @Test
    void createMdFileCrawlerRecordNull() {
        assertThrows(NullPointerException.class, ()-> fileCreator.createMdFile());
    }

    @DisplayName("Test CreateMdFile with translation")
    @Test
    void testTranslation(){
        crawlerRecordMock = mock(CrawlerRecord.class);
        parameters.setLngSource("");
        parameters.setLngTarget("");
       // fileCreator = new MarkdownFileCreator(crawlerRecordMock, parameters);

        translatorApiMock = mock(TranslatorApi.class);
        when(translatorApiMock.getTranslatedText("", "", "")).thenReturn("translated text");
        fileCreator.setTranslatorApi(translatorApiMock);

        fileCreator.createMdFile();
    }

    @AfterEach
    void tearDown() {
        fileCreator = null;
        crawlerRecordMock = null;
        parameters = null;
    }
}
