package org.cleanCode.Translation;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class TranslatorApi {


    private HttpClient client;
    private Map<String,String> languages = new HashMap<>();
    private final String getLanguagesUri = "https://text-translator2.p.rapidapi.com/getLanguages";
    private final String translateUri = "https://text-translator2.p.rapidapi.com/translate";
    private final String apiKeyValue = "e458a15ad4msh50589e69c120eebp14b794jsn195a6ee58120";
    private final String apiHostValue ="text-translator2.p.rapidapi.com";
    private final JsonParser jsonParser;

    public TranslatorApi(){
        this.client = HttpClient.newHttpClient();
        this.jsonParser = new JacksonJsonParser();
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

    public Map<String,String> getLanguages(){
        try{
            String json = sendGetLanguagesCall();

            languages = jsonParser.jsonGetAllLanguages(json);

            return languages;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
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
        text = text.replace("?","%3F");
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


    public String getTranslatedText(String lngSource, String lngTarget, String text){
        try{
            String json = sendTranslateCall(lngSource,lngTarget,text);

            return jsonParser.getTranslatedText(json);
        } catch(NullPointerException | IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
    }


}
