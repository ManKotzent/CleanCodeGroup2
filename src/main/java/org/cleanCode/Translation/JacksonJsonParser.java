package org.cleanCode.Translation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JacksonJsonParser implements JsonParser{

    private JsonNode jsonGetLanguageNode(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            return jsonNode.get("data").get("languages");
        }catch (JsonProcessingException e){
            throw new RuntimeException("jsonGetLanguageNode failed");
        }
    }

    @Override
    public String getTranslatedText(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode translatedText = jsonNode.get("data").get("translatedText");
            return translatedText.asText();
        }catch (JsonProcessingException e){
            throw new RuntimeException("getTranslatedText failed");
        }
    }

    @Override
    public Map<String, String> jsonGetAllLanguages(String json) {
        JsonNode languagesNode = jsonGetLanguageNode(json);
        Map<String, String> languages = new HashMap<>();
        for(int i = 0; i<languagesNode.size();i++){
            String code = languagesNode.get(i).get("code").asText();
            String name = languagesNode.get(i).get("name").asText().toLowerCase();
            languages.put(name,code);
        }

        return languages;
    }
}
