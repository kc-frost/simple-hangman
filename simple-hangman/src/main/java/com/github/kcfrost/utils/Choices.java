package com.github.kcfrost.utils;

import java.util.Arrays;
import java.util.List;

public class Choices {
    private static final List<String> START_CHOICES =
            Arrays.asList("1", "2", "3", "4", "5", "Q");
    private static final List<String> INGAME_CHOICES =
            Arrays.asList("/H", "/R", "/Q");
    private static final List<String> END_CHOICES = 
            Arrays.asList("A", "S", "Q");
    
    public static final String QUIT = "Q";
    public static final String INGAME_QUIT = "/Q";

    public static final List<String> UPPER_ALPHABET =
            Arrays.asList("A", "B", "C", "D", "E", "F", "G",
                            "H", "I", "J", "K", "L", "M", "N", "O",
                            "P", "Q", "R", "S", "T", "U", "V", "W",
                            "X", "Y", "Z");

    /** 
     * {@return  if <code>START_CHOICES</code> contains <code>input</code>}
     * 
     * @param  input     the <code>String</code> to check, typically has a length of 1
     */
    public static boolean isStartChoice(String input) {
        return START_CHOICES.contains(input);
    }

    /**
     * {@return if <code>INGAME_CHOICES</code> contains <code>input</code>}
     * 
     * @param  input    the <code>String</code> to check, typically has length of 2
     */
    public static boolean isInGameChoice(String input) {
        return INGAME_CHOICES.contains(input);
    }

    /**
     * {@return if <code>END_CHOICES</code> contains <code>input</code>}
     * 
     * @param  input    the <code>String</code> to check, typically has a length of 1
     */
    public static boolean isEndChoice(String input) {
        return END_CHOICES.contains(input);
    }

    /**
     * {@return if <code>UPPER_ALPHABET</code> contains <code>input</code>}
     * 
     * @param  input    the <code>String</code> to check, typically has a length of 1
     */
    public static boolean isUppercaseAlphabet(String input) {
        return UPPER_ALPHABET.contains(input);
    }
}
