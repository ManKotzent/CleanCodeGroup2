package org.cleanCode.Translation;

import java.util.Map;

public interface JsonParser {
    String getTranslatedText(String text);
    Map<String, String> jsonGetAllLanguages(String json);
}
