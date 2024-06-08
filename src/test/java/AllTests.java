import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Tests")
@SelectClasses({CrawlerRecordFactoryTest.class,HTMLExtractorTest.class,HTMLFetcherTest.class,ProFormaTests.class,MarkdownFileCreatorTest.class,TranslatorApiTest.class,UrlHandlerTest.class})
public class AllTests {
}
