package org.cleanCode;

import org.cleanCode.MarkdownFileCreator.MarkdownFileCreatorPackageAllTests;
import org.cleanCode.Translation.TranslationPackageAllTests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Tests")
@SelectClasses({CrawlerRecordFactoryTest.class, HTMLExtractorTest.class, HTMLFetcherTest.class, ProFormaTests.class, MarkdownFileCreatorPackageAllTests.class, TranslationPackageAllTests.class, UrlHandlerTest.class})
public class AllTests {
}
