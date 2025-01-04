package com.github.kcfrost.visuals;

import java.util.ArrayList;
import java.util.List;

public class Hangman {
    private final static int HEAD = 2;
    private final static int UPPER_BODY = 3;
    private final static int LOWER_BODY = 4;
    private final static int LEGS = 5;

    private static List<String> hangman = setUp();
    private static int mistakesMade = 0;

    /**
     * Returns a List<String> object sets up <code>hangman</code>, initially starting
     * out with an empty gallows. 
     * 
     * @return  an empty gallows in List<String> representation
     */
    private static List<String> setUp() {
        List<String> initialHangman = new ArrayList<>();

        initialHangman.add("      __________");
        initialHangman.add("      |      |");
        initialHangman.add("      |      ");
        initialHangman.add("      |     ");
        initialHangman.add("      |      ");
        initialHangman.add("      |     ");
        initialHangman.add("     _|___________");
        initialHangman.add("   /  |          /|");
        initialHangman.add("  /   |         / |");
        initialHangman.add(" /_____________/ /");
        initialHangman.add(" |             |/");
        initialHangman.add("  -------------/");

        return initialHangman;
    }

    /**
     * Updates <code>hangman</code> whenever a mistake is made. The amount of mistakes made
     * determines which part of this component is changed
     */
    public static void update() {
        // consider dynamically adjusting the index instead of hardcoding 
        mistakesMade++;
        switch (mistakesMade) {
            case 1:
                hangman.set(HEAD, "      |      @");
                break;
            case 2:
                hangman.set(UPPER_BODY, "      |      |");
                break;
            case 3:
                hangman.set(LOWER_BODY, "      |      |");
                break;
            case 4:
                hangman.set(UPPER_BODY, "      |     /|");
                break;
            case 5:
                hangman.set(UPPER_BODY, "      |     /|\\");
                break;
            case 6:
                hangman.set(LEGS, "      |     /");
                break;
            case 7:
                hangman.set(LEGS, "      |     / \\");
                break;
        }
    }

    /**
     * Prints <code>hangman</code> to the console. This is used to display
     * the player's current progress
     */
    public static void status() {
        for (String row : hangman) {
            System.out.println(row);
        }
    }

    /**
     * Returns <code>mistakesMade</code>, which is the 
     * amount of mistakes the player has made currently
     * 
     * @return  amount of mistakes made
     */
    public static int getMistakesCount() {
        return mistakesMade;
    }

    /**
     * Restarts <code>hangman</code> to initialize it for new play
     */
    public static void restart() {
        hangman.clear();
        hangman = setUp();
        mistakesMade = 0;
    }
}
