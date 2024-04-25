package org.cleanCode.translation;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class TranslatorApiCall {
    String url = "";
    Map<String,String> languages = new HashMap<>();

    public void getLanguages(){
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://text-translator2.p.rapidapi.com/getLanguages"))
                    .header("X-RapidAPI-Key", "e458a15ad4msh50589e69c120eebp14b794jsn195a6ee58120")
                    .header("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            mapLanguages(response.body());
            printLanguages();
        }catch(IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    public void printLanguages(){
        for(Map.Entry<String,String> entry : languages.entrySet()){
            System.out.println(entry.getKey() + " -> "+entry.getValue());
        }
    }

    public void mapLanguages(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode languagesNode = jsonNode.get("data").get("languages");
            for(int i = 0; i<languagesNode.size();i++){
                String code = languagesNode.get(i).get("code").asText();
                String name = languagesNode.get(i).get("name").asText();
                languages.put(code,name);
            }
        }catch(JsonProcessingException | NullPointerException e){
            System.out.println(e.getMessage());
        }

    }

    public void makeApiCall(String sourceLng, String targetLng){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://text-translator2.p.rapidapi.com/translate"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", "e458a15ad4msh50589e69c120eebp14b794jsn195a6ee58120")
                .header("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("source_language="+sourceLng+"&target_language="+targetLng+"&text=What%20is%20your%20name%3F"))
                .build();
        try{
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            parseApiCall(response.body());
        }catch (IOException | InterruptedException e){
            System.err.println(e.getMessage());
        }
    }
    public void parseApiCall(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode data = jsonNode.get("data");
            System.out.println(data.get("translatedText").asText());
        }catch(JsonProcessingException | NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
}
