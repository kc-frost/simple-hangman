package com.github.kcfrost.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

import com.github.kcfrost.wordutils.LocalWord;

public class Mechanics {
    private static final int MAX_HINTS = 3;

    private static boolean definitionSwitch = false;
    private static boolean hintSwitch = false;
    private static Random rand = new Random();
    
    private LocalWord currentWord;
    private LinkedHashSet<Character> pastGuesses;
    private int hintsLeft;
    private int attemptsCount;


    public Mechanics(LocalWord word) {
        currentWord = word;
        pastGuesses = new LinkedHashSet<>();
        hintsLeft = MAX_HINTS;
        attemptsCount = 0;
    }
    
    /**
     * Randomly reveals an unguessed letter iff all preconditions are met. If at runtime <code>hintsLeft==0</code> or hints are left disabled, a custom message will be returned to print to the console.
     * 
     * @return A <code>String</code> message that is passed to <code>Screen.updateMessage()</code>
     */
    public String giveHint() {
        if (!hintSwitch) {
            return "\nHints aren't enabled!";
        }
        
        if (hintsLeft == 0) {
            return "\nYou've exhausted your hints!";
        }
        
        List<Integer> unguessedLettersIndices = new ArrayList<>();
        for (int i = 0; i < currentWord.getLength(); i++) {
            char currentLetter = currentWord.getCensoredVersion().charAt(i);
            
            if (currentLetter == LocalWord.getCensorSymbol()) {
                unguessedLettersIndices.add(i);
            }
        }
        
        
        List<Character> unguessedLettersList = new ArrayList<>();
        for (int i : unguessedLettersIndices) {
            char currentLetter = currentWord.toString().charAt(i);
            unguessedLettersList.add(currentLetter);
        }
        
        
        Collections.shuffle(unguessedLettersList);
        int hintIndex = rand.nextInt(unguessedLettersList.size());
        char hint = unguessedLettersList.get(hintIndex);
        
        
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
    
    /**
     * Validates whether the char argument is in the word
     * 
     * @param guess 
     * @return <code>true</code> if <code>guess</code> was correct
     */
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
        .contains(String.valueOf(LocalWord.getCensorSymbol())) || mistakesMade == 7);
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