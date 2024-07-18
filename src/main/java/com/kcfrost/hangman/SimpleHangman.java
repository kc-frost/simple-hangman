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

        System.out.println();
        if (solved == true) {
            System.out.println("Congrats. You won!");
            System.out.println("Attempts taken: " + attemptsTracker);
        } 
        
        else {
            System.out.println("Too bad, the word was '" + secretWord + "'");
            System.out.println("Try again next time!");
        }
       
        System.out.print("Define word? Press any key to quit: ");
        String choice = userInput.nextLine();

        if (choice.isEmpty()) {
            System.out.println(defineWord(secretWord));
        }

        userInput.close();
    }

    public static void startMenu(Scanner userInput) {
        
    }
    public static boolean isCorrect(char guess, String word) {
        for (char ch : word.toCharArray()) {
            if (guess == ch) {

                return true;
            }
        }

        return false;
    }
    
    public static JSONArray getSiteBody(String site) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(site))
            .timeout(Duration.ofSeconds(60))
            .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return new JSONArray(response.body());
    }

    public static String generateRandomWord(int wordLength) throws IOException, InterruptedException {
        StringBuilder randomWordSite = new StringBuilder("https://random-word-api.herokuapp.com/word");

        if (wordLength > 0) {
            randomWordSite.append("?length" + wordLength);
        }

        JSONArray response = getSiteBody(randomWordSite.toString());

        return response.getString(0);
    }

    public static String defineWord(String word) throws IOException, InterruptedException {
        StringBuilder dictionarySite = new StringBuilder("https://api.dictionaryapi.dev/api/v2/entries/en/");
        dictionarySite.append(word);

        JSONArray response = getSiteBody(dictionarySite.toString());

        // gets first definition in JSON file
        // goal: eventually be able to iterate through all definitions

        String wordDefinition = response.getJSONObject(0).getJSONArray("meanings").getJSONObject(0).getJSONArray("definitions").getJSONObject(0).getString("definition");

        return String.format("%s definition: %s", word, wordDefinition);
    }

}
