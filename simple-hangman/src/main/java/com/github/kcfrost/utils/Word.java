package com.github.kcfrost.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Word {
    private static final char CENSOR_SYMBOL = '*';
    private static final int DEFAULT_WORD_LENGTH = 7;
    
    private String word;
    private StringBuilder censoredWord;
    
    private String definition;

    public Word() {
        word = generateWord(DEFAULT_WORD_LENGTH);
        censoredWord = new StringBuilder(String.valueOf(CENSOR_SYMBOL).repeat(this.getLength()));

    }
    
    public Word(int wordLength, boolean definitionTrigger) {
        word = generateWord(wordLength); // PLACEHOLDER WORD, use wordlength here
        censoredWord = new StringBuilder(String.valueOf(CENSOR_SYMBOL).repeat(this.getLength()));
        definition = (definitionTrigger == true) 
                        ? getDefinition() 
                        : null;
    }
    
    private static String generateWord(int wordLength) {
        Map<String, List<String>> wordsMap = makeMap();
        Random rd = new Random();

        List<String> possibleWords = wordsMap.get(String.valueOf(wordLength));
        
        return possibleWords.get(rd.nextInt(possibleWords.size())).trim();
    }

    private static Map<String, List<String>> makeMap() {
        File file = new File("C:\\Users\\aethk\\MAIN\\Interests\\coding\\java\\" +
                            "simple-hangman\\simple-hangman new\\simple-hangman\\" +
                            "src\\main\\java\\com\\github\\kcfrost\\utils\\example_words.txt");

        Map<String, List<String>> wordsMap = new HashMap<>();
        try {
            Scanner scan = new Scanner(file);

            int lineNumber = 1;
            String key = "";
            List<String> value = new ArrayList<>();
            while (scan.hasNextLine()) {
                if (lineNumber % 2 == 1) {
                    key = scan.nextLine();
                } else {
                    value = Arrays.asList(scan.nextLine().split(","));
                }

                wordsMap.put(key, value);
                lineNumber++;
            }

            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    
        return wordsMap;
    } 

    /**
     * {@return <code>CENSOR_SYMBOL</code>}
     */
    public static char getCensorSymbol() {
        return CENSOR_SYMBOL;
    }

    /**
     * {@return this object's <code>word</code> field}
     */
    public String getWord() {
        return word;
    }

    /**
     * {@return this object's <code>definition</code> field}
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * {@return the length of this object's <code>word</code>}
     */
    public int getLength() {
        return word.length();
    }

    /**
     * Returns this object's <code>censoredWord</code> field. This is used to
     * hide the entire word that the player has to guess, and also indicates 
     * what letters the player has yet to guess.
     * 
     * @return this object's <code>censoredWord</code> field
     */
    public StringBuilder getCensoredVersion() {
        return censoredWord;
    }

    @Override
    public String toString() {
        return word;
    }

}
