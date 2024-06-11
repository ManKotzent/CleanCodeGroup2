package org.cleanCode.Translation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JacksonJsonParserTest {

    private JacksonJsonParser jacksonJsonParser;

    @BeforeEach
    public void setUp() {
        jacksonJsonParser = new JacksonJsonParser();
    }

    @Test
    public void testGetTranslatedTextNull() {
        assertThrows(IllegalArgumentException.class, () -> jacksonJsonParser.getTranslatedText(null));
    }

    @Test
    public void testGetTranslatedTextEmpty() {
        assertThrows(IllegalArgumentException.class, () -> jacksonJsonParser.getTranslatedText(""));
    }

    @Test
    public void testGetTranslatedText() {
        String json = "{\n" +
                "  \"data\": {\n" +
                "    \"translatedText\": \"siapa namamu\"\n" +
                "  }\n" +
                "}";
        String expected = "siapa namamu";

        assertEquals(expected, jacksonJsonParser.getTranslatedText(json));
    }

    @Test
    public void testGetAllLanguagesNull() {
        assertThrows(IllegalArgumentException.class, () -> jacksonJsonParser.getAllLanguages(null));
    }

    @Test
    public void testGetAllLanguagesEmpty() {
        assertThrows(IllegalArgumentException.class, () -> jacksonJsonParser.getAllLanguages(""));
    }

    @Test
    public void testGetAllLanguages() {
        Map<String, String> expected = new HashMap<>();
        expected.put("english", "en");
        expected.put("bahasa indonesia","id");
        String json = "{\n" +
                "  \"data\": {\n" +
                "    \"languages\": [\n" +
                "      {\n" +
                "        \"code\": \"en\",\n" +
                "        \"name\": \"english\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"code\": \"id\",\n" +
                "        \"name\": \"Bahasa Indonesia\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        Map<String, String> translatedText = jacksonJsonParser.getAllLanguages(json);

        assertEquals(expected, translatedText);

    }

}

