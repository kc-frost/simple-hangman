package com.github.kcfrost.wordutils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LocalWord extends Word {
    public LocalWord() {
        word = generateWordFromLocal(getDefaultWordLength());
        censoredWord = setCensoredWord();

    }
    
    public LocalWord(int wordLength) {
        word = generateWordFromLocal(wordLength);
        censoredWord = setCensoredWord();
    }

    private static String generateWordFromLocal(int wordLength) {
        Map<String, List<String>> wordsMap = makeMap();
        Random rd = new Random();

        List<String> possibleWords = wordsMap.get(String.valueOf(wordLength));
        
        return possibleWords.get(rd.nextInt(possibleWords.size())).trim();
    }

    private static Map<String, List<String>> makeMap() {
        File file = new File("simple-hangman\\src\\main\\java\\com\\github\\kcfrost\\" +
                            "\\wordutils\\xxample_words.txt");

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
            System.out.println("\nFile named 'example_words.txt' not found!" + 
                                " Did you change the file path?");
            System.out.println("Check the LocalWord class. The game will end in the meantime!");
        }
    
        return wordsMap;
    } 
}
