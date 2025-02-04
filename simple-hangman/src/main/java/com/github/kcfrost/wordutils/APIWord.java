package com.github.kcfrost.wordutils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Map;

import org.json.JSONArray;

public class APIWord extends Word {
    private static final char LETTER_SYMBOL = '?'; 

    public APIWord() {
        word = "hello"; // PLACEHOLDER
        censoredWord = setCensoredWord();
    }
    
    private String generateWord() {
        // method to generate the whole http process thing
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(generateURI())
            .timeout(Duration.ofSeconds(30))
            .build();

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    private URI generateURI() {
        // method to generate uri
        URI uri = null;
        try {
            uri = new URIBuilder("https://api.datamuse.com/words?")
                        .addParameter("sp", generateWordPattern())
                        .build();
            
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return uri;

    }

    private String generateWordPattern() {
        // tentative method name, not a fan of how it sounds
        // method to generate the latter half of the uri (paramString)

        return null;
    }
    
}

