package org.cleanCode;

import org.cleanCode.MarkdownFileCreator.MarkdownAllTests;
import org.cleanCode.Translation.TranslatorApiTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Tests")
@SelectClasses({CrawlerRecordFactoryTest.class, HTMLExtractorTest.class, HTMLFetcherTest.class, ProFormaTests.class, MarkdownAllTests.class, TranslatorApiTest.class, UrlHandlerTest.class})
public class AllTests {
}
