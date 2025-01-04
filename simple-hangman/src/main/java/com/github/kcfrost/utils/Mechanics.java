package com.github.kcfrost.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

public class Mechanics {
    private static final int MAX_HINTS = 3;

    // implement scoreboard

    private static boolean definitionSwitch = false;
    private static boolean hintSwitch = false;
    private static Random rand = new Random();
    
    private Word currentWord;
    private LinkedHashSet<Character> pastGuesses;
    private int hintsLeft;
    private int attemptsCount;


    public Mechanics(Word word) {
        currentWord = word;
        pastGuesses = new LinkedHashSet<>();
        hintsLeft = MAX_HINTS;
        attemptsCount = 0;
    }
    
    public String giveHint() {
        if (!hintSwitch) {
            // System.out.print("\nHints aren't enabled!");
            return "\nHints aren't enabled!";
        }
        
        if (hintsLeft == 0) {
            // System.out.print("\nYou've exhausted your hints!");
            return "\nYou've exhausted your hints!";
        }
        
        // from a list of all unguessed characters, pick a random one and replace the * in 
        // censoredWord that has the random letter
        // go through censoredword, and save the indices of all the remaining chars
        // who are still *
        List<Integer> unguessedLettersIndices = new ArrayList<>();
        for (int i = 0; i < currentWord.getLength(); i++) {
            char currentLetter = currentWord.getCensoredVersion().charAt(i);
            
            if (currentLetter == Word.getCensorSymbol()) {
                unguessedLettersIndices.add(i);
            }
        }
        
        // then, crossreference this with word, and add those letters to a set
        List<Character> unguessedLettersList = new ArrayList<>();
        for (int i : unguessedLettersIndices) {
            char currentLetter = currentWord.toString().charAt(i);
            unguessedLettersList.add(currentLetter);
        }
        
        // THEN, finally, randomly pick a letter, and then replace all indices in
        // censoredword that contains that letter
        Collections.shuffle(unguessedLettersList);
        int hintIndex = rand.nextInt(unguessedLettersList.size());
        char hint = unguessedLettersList.get(hintIndex);
        
        // this method is void because i have decided to just change what censoredword looks like
        // so ideally, giveHint will just "update" the screen, without needing the player
        // to input the hint itself, wasting a try
        char[] wordAsChars = currentWord.getWord().toCharArray();
        for (int i = 0; i < currentWord.getLength(); i++) {
            boolean wordIsHintChar = (wordAsChars[i] == hint); 
            if (wordIsHintChar) {
                currentWord.getCensoredVersion().setCharAt(i, hint);
            }
        }
        
        hintsLeft--;
        return "";
    }
    
    public boolean guess(char guess) {
        guess = Character.toLowerCase(guess);
        pastGuesses.add(guess);
        attemptsCount++;
        
        if (currentWord.toString().contains(String.valueOf(guess))) {
            char[] wordAsChars = currentWord.getWord().toCharArray();
            for (int i = 0; i < currentWord.getLength(); i++) {
                boolean wordHasGuess = (wordAsChars[i] == guess); 
                if (wordHasGuess) {
                    currentWord.getCensoredVersion().setCharAt(i, guess);
                }
            }
            
            return true;
        } else {
            return false;
        }
    }
    
    public StringBuilder stats() {
        int triesTaken = attemptsCount;
        
        StringBuilder results = new StringBuilder();
        results.append(String.format("Tries taken: %d", triesTaken));
        
        if (hintSwitch) {
            results.append(String.format("\nHints used (if enabled): $d", hintsLeft));
        }
        
        results.append(String.format("\nLetters guessed: %s", pastGuesses));
        
        if (definitionSwitch) {
            results.append(String.format("\nDefinition:" , currentWord.getDefinition()));
        }
        
        return results;
    }
    
    public boolean gameState(int mistakesMade) {
        return (!String.valueOf(currentWord.getCensoredVersion())
        .contains(String.valueOf(Word.getCensorSymbol())) || mistakesMade == 7);
    }
    
    public boolean gameResult(int mistakesMade) {
        return !(mistakesMade == 7); 
    }
    
    public static void toggleHint() {
        hintSwitch = !hintSwitch;
    }
    
    public static void toggleDefinitions() {
        definitionSwitch = !definitionSwitch;
    }

    public LinkedHashSet<Character> getPastGuesses() {
        return pastGuesses;
    }
    
    public static boolean getHintSwitch() {
        return hintSwitch;
    }
    
    public static boolean getDefinitionSwitch() {
        return definitionSwitch;
    }
    
    public int getHintsLeft() {
        return hintsLeft;
    }

}