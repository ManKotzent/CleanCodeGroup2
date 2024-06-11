package org.cleanCode.Translation;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Translation Package All Tests")
@SelectClasses({TranslatorApiTest.class})
public class TranslationPackageAllTests {
}
