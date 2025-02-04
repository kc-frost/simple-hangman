package com.github.kcfrost.wordutils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

public class APIWord extends Word {
    private static final char LETTER_SYMBOL = '?'; 

    public APIWord() {
        word = "hello"; // PLACEHOLDER
        censoredWord = setCensoredWord();
    }

    private String generateWordPattern() {
        // tentative method name, not a fan of how it sounds
        // method to generate the latter half of the uri (paramString)
        
    }

    private URI generateURI(String paramString) {
        // method to generate uri
        try {
            URI uri = new URIBuilder("https://api.datamuse.com/words?")
                        .addParameter("sp", paramString)
                        .build();

            return uri;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private String generateWord() {
        // method to generate the whole http process thing

    }

    
}

