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
import java.util.Random;

import org.json.JSONArray;

public class APIWord extends Word {
    private static final char LETTER_SYMBOL = '?'; 

    public APIWord() {
        word = generateWord(); // PLACEHOLDER
        censoredWord = setCensoredWord();
    }
    
    private String generateWord() {
        // method to generate the whole http process thing
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(generateURI()))
            .timeout(Duration.ofSeconds(30))
            .build();

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    private String generateURI() {
        StringBuilder uri = new StringBuilder("https://api.datamuse.com/words?sp=");
        Random rd = new Random();
        
        // set length
        StringBuilder parameterValue = new StringBuilder();
        parameterValue.append("?".repeat(7));

        // select letter
        String abc = "abcdefghijklmnopqrstuvwxyz";
        char letter = abc.charAt(rd.nextInt(abc.length()));

        // pick pattern
        String[] pattern = {"*n*", "*n", "n*"};
        char[] newPattern = (pattern[rd.nextInt(pattern.length)]).toCharArray();
        for (int i = 0; i < newPattern.length; i++) {
            if (newPattern[i] == 'n') {
                newPattern[i] = letter;
            }
        }
        
        parameterValue.append("," + new String(newPattern));

        // add to uri
        uri.append(parameterValue);

        return uri.toString();
    }
    
}

