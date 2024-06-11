package org.cleanCode.MarkdownFileCreator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsoupHtmlParserTest {

    private JsoupHtmlParser jsoupHtmlParser;

    @BeforeEach
    public void setUp() {
        jsoupHtmlParser = new JsoupHtmlParser();
    }

    @Test
    public void testParseNull() {
        assertThrows(IllegalArgumentException.class, () -> jsoupHtmlParser.parse(null));
    }

    @Test
    public void testParseEmpty() {
        assertThrows(IllegalArgumentException.class, () -> jsoupHtmlParser.parse(""));
    }

    @Test
    public void testParse() {
        String html = "<h1>Test<h1>";
        assertEquals("Test", jsoupHtmlParser.parse(html));
    }
}
