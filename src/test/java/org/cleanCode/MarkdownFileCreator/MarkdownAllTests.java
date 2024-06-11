package org.cleanCode.MarkdownFileCreator;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("MarkdownFile Creation All Tests")
@SelectClasses({MarkdownFileCreatorTest.class, FormattingTests.class})
public class MarkdownAllTests {
}
