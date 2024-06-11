package org.cleanCode.Translation;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class TranslatorApi {

    private final String getLanguagesUri = "https://text-translator2.p.rapidapi.com/getLanguages";
    private final String translateUri = "https://text-translator2.p.rapidapi.com/translate";
    private final String apiKeyValue = "e458a15ad4msh50589e69c120eebp14b794jsn195a6ee58120";
    private final String apiHostValue ="text-translator2.p.rapidapi.com";


    private HttpClient client;
    private Map<String,String> languages = new HashMap<>();
    private final JsonParser jsonParser;

    public TranslatorApi(){
        this.client = HttpClient.newHttpClient();
        this.jsonParser = new JacksonJsonParser();
    }

    protected void setClient(HttpClient client){
        this.client = client;
    }

    public String getTranslatedText(String sourceLanguage, String targetLanguage, String text){
            String json = sendTranslateCall(sourceLanguage,targetLanguage,text);
            return jsonParser.getTranslatedText(json);
    }

    public Map<String,String> getLanguages(){
        String json = sendGetLanguagesCall();
        languages = jsonParser.jsonGetAllLanguages(json);
        if(languages.isEmpty()){
            throw new NullPointerException("TranslatorApi.getLanguages: no languages found; Possible api failure");
        }
        return languages;
    }

    protected String sendGetLanguagesCall()  {
        try{
            HttpRequest request = buildGetLanguagesRequest();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch(IOException e){
            throw new RuntimeException("TranslatorApi.sendGetLanguagesCall: IOException. "+e.getMessage());
        }catch (InterruptedException e){
            throw new RuntimeException("TranslatorApi.sendGetLanguagesCall: InterruptedException. "+e.getMessage());
        }
    }

    protected String sendTranslateCall(String sourceLanguage, String targetLanguage, String text) {
        if(sourceLanguage == null || targetLanguage == null || text == null){
            throw new NullPointerException("TranslatorApi.sendTranslateCall NullPointerException");
        }
        HttpRequest request = buildTranslateRequest(sourceLanguage, targetLanguage, text);
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch(IOException e){
            throw new RuntimeException("TranslatorApi.sendTranslateCall: IOException. "+e.getMessage());
        }catch(InterruptedException e){
            throw new RuntimeException("TranslatorApi.sendTranslateCall: InterruptedException. "+e.getMessage());
        }
    }


    private String parseText(String text){
        text = text.replace(" ","%20");
        text = text.replace("?","%3F");
        return text;
    }

    private HttpRequest buildGetLanguagesRequest(){
        return HttpRequest.newBuilder()
                .uri(URI.create(getLanguagesUri))
                .header("X-RapidAPI-Key", apiKeyValue)
                .header("X-RapidAPI-Host", apiHostValue )
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
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
}
