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

public class TranslatorApi {


    private HttpClient client;
    private final Map<String,String> languages = new HashMap<>();
    private final String getLanguagesUri = "https://text-translator2.p.rapidapi.com/getLanguages";
    private final String translateUri = "https://text-translator2.p.rapidapi.com/translate";
    private final String apiKeyValue = "e458a15ad4msh50589e69c120eebp14b794jsn195a6ee58120";
    private final String apiHostValue ="text-translator2.p.rapidapi.com";

    public TranslatorApi(){
        this.client = HttpClient.newHttpClient();
    }

    public void setClient(HttpClient client){
        this.client = client;
    }

    public String sendGetLanguagesCall() throws IOException, InterruptedException {
        try{
            HttpRequest request = buildGetLanguagesRequest();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch(IOException | InterruptedException e){
            if(e instanceof InterruptedException)
                throw new InterruptedException();
            throw new IOException("Error sending request", e);
        }
    }


    private HttpRequest buildGetLanguagesRequest(){
        return HttpRequest.newBuilder()
                .uri(URI.create(getLanguagesUri))
                .header("X-RapidAPI-Key", apiKeyValue)
                .header("X-RapidAPI-Host", apiHostValue )
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }

    private void getLanguagesAsMap(){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String json = sendGetLanguagesCall();
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode languagesNode = jsonNode.get("data").get("languages");
            for(int i = 0; i<languagesNode.size();i++){
                String code = languagesNode.get(i).get("code").asText();
                String name = languagesNode.get(i).get("name").asText();
                languages.put(code,name);
            }
        }catch(JsonProcessingException | NullPointerException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void printLanguages(){
        for(Map.Entry<String,String> entry : languages.entrySet()){
            System.out.println(entry.getKey() + " -> "+entry.getValue());
        }
    }


    public String sendTranslateCall(String sourceLng, String targetLng, String text) throws IOException, InterruptedException {
        if(sourceLng == null || targetLng == null || text == null){
            throw new NullPointerException();
        }
        HttpRequest request = buildTranslateRequest(sourceLng, targetLng, text);
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch(IOException | InterruptedException e){
            if(e instanceof InterruptedException)
                throw new InterruptedException();
            throw new IOException("Error sending request", e);
        }
    }


    private String parseText(String text){
        text = text.replace(" ","%20");
        text += "%3F";
        return text;
    }


    private HttpRequest buildTranslateRequest(String sourceLng, String targetLng, String text){
        return HttpRequest.newBuilder()
                .uri(URI.create(translateUri))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", apiKeyValue)
                .header("X-RapidAPI-Host", apiHostValue)
                .method("POST", HttpRequest.BodyPublishers.ofString("source_language="+sourceLng+"&target_language="+targetLng+"&text="+parseText(text)))
                .build();
    }


    private String getTranslatedText(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode translatedText = jsonNode.get("data").get("translatedText");
            return translatedText.asText();
        }catch(JsonProcessingException | NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


}
