package com.kcfrost.hangman;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.HashSet;
import java.util.Scanner;
import org.json.JSONArray;

public class SimpleHangman {
    public static void main( String[] args ) throws IOException, InterruptedException {
        Scanner userInput = new Scanner(System.in);
        
        String secretWord = generateRandomWord();
        StringBuilder displayWord = new StringBuilder("_".repeat(secretWord.length()));
        HashSet<String> pastGuesses = new HashSet<String>();
        
        System.out.println("Simple Hangman");
        
        int attempts = 7;
        int attemptsTracker = 0;
        boolean solved = false;

        while (!solved && attempts != 0) {
            System.out.println("Word: " + displayWord);
            System.out.println();

            System.out.println("Attempts left: " + attempts);
            System.out.print("Enter letter: ");
            String guess = userInput.nextLine().toLowerCase();
            
            while (guess.length() > 1) {
                System.out.print("One character at a time only: ");
                guess = userInput.nextLine();
            }

            char guessAsChar = guess.charAt(0);

            if (!pastGuesses.contains(guess)) {
                pastGuesses.add(guess);

                if (isCorrect(guessAsChar, secretWord)) {
                    for (int i = 0; i < secretWord.length(); i++) {
                        if (secretWord.charAt(i) == guessAsChar) {
                            displayWord.setCharAt(i, guessAsChar);
                        }
                    }
                }

                else {
                    attempts--;
                }
            
            }

            else {
                System.out.println("You already guessed this word.");
            }
            
            if (secretWord.contentEquals(displayWord.toString())) {
                solved = true;
            }

            attemptsTracker++;
        }
        
        userInput.close();

        System.out.println();
        if (solved == true) {
            System.out.println("Congrats. You won!");
            System.out.println("Attempts taken: " + attemptsTracker);
        } 
        
        else {
            System.out.println("Too bad, the word was '" + secretWord + "'");
            System.out.println("Try again next time!");
        }
       
    }

    public static boolean isCorrect(char guess, String word) {
        for (char ch : word.toCharArray()) {
            if (guess == ch) {

                return true;
            }
        }

        return false;
    }

    public static String generateRandomWord() throws IOException, InterruptedException {
        final String randomWordSite = "https://random-word-api.herokuapp.com/word";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(randomWordSite))
            .timeout(Duration.ofSeconds(60))
            .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        JSONArray jsonWord = new JSONArray(response.body());

        return jsonWord.getString(0);
    }
}
