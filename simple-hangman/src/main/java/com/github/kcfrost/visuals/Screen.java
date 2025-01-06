package com.github.kcfrost.visuals;

import com.github.kcfrost.utils.Mechanics;
import com.github.kcfrost.utils.Word;

public class Screen {
    private final static String ON = "On";
    private final static String OFF = "Off";

    private static StringBuilder message = new StringBuilder();

    public static void divider() {
        String divider =
            "  ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ ______  \r\n" + //
            " |______|______|______|______|______|______|______|______|______|______|______|______|\r\n" + //
            "";
            
            System.out.println(divider);
        }
        
    public static void titleText() {
        String titleText = 
            "      _                 _        _                                             \r\n" + //
            "     (_)               | |      | |                                            \r\n" + //
            "  ___ _ _ __ ___  _ __ | | ___  | |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  \r\n" + //
            " / __| | '_ ` _ \\| '_ \\| |/ _ \\ | '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ \r\n" + //
            " \\__ \\ | | | | | | |_) | |  __/ | | | | (_| | | | | (_| | | | | | | (_| | | | |\r\n" + //
            " |___/_|_| |_| |_| .__/|_|\\___| |_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|\r\n" + //
            "                 | |                                __/ |                      \r\n" + //
            "                 |_|                               |___/                       \r" + //
            "\r\n" + //
            "";
        System.out.println(titleText);
    }

    public static void winText() {
        String winText = 
            "\r\n" + //
            "\r\n" + //
            "                               _       _ _ _ \r\n" + //
            "                              (_)     | | | |\r\n" + //
            "  _   _  ___  _   _  __      ___ _ __ | | | |\r\n" + //
            " | | | |/ _ \\| | | | \\ \\ /\\ / / | '_ \\| | | |\r\n" + //
            " | |_| | (_) | |_| |  \\ V  V /| | | | |_|_|_|\r\n" + //
            "  \\__, |\\___/ \\__,_|   \\_/\\_/ |_|_| |_(_|_|_)\r\n" + //
            "   __/ |                                     \r\n" + //
            "  |___/                                      \r\n" + //
            "\r\n" + //
            "";

        System.out.println(winText);
    }

    public static void gameOverText() {
        String gameOverText = 
            "\r\n" + //
            "\r\n" + //
            "                                                   \r\n" + //
            "                                                   \r\n" + //
            "   __ _  __ _ _ __ ___   ___    _____   _____ _ __ \r\n" + //
            "  / _` |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '__|\r\n" + //
            " | (_| | (_| | | | | | |  __/ | (_) \\ V /  __/ |   \r\n" + //
            "  \\__, |\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|   \r\n" + //
            "   __/ |                                           \r\n" + //
            "  |___/                                            \r\n" + //
            "\r\n" + //
            "";

        System.out.println(gameOverText);
    }

    public static void scoreboardText() {
        String scoreboardText = 
            "\r\n" + 
            "\r\n" + 
            "                                _                         _        \r\n" + 
            "                               | |                       | |       \r\n" + 
            "  ______ ___  ___ ___  _ __ ___| |__   ___   __ _ _ __ __| |______ \r\n" + 
            " |______/ __|/ __/ _ \\| '__/ _ \\ '_ \\ / _ \\ / _` | '__/ _` |______|\r\n" + 
            "        \\__ \\ (_| (_) | | |  __/ |_) | (_) | (_| | | | (_| |       \r\n" + 
            "        |___/\\___\\___/|_|  \\___|_.__/ \\___/ \\__,_|_|  \\__,_|       \r\n";

        System.out.println(scoreboardText);
    }

    public static void startMenu() { 
        int wordLengthDefault = 7;
        boolean[] startChoicesDefault = {false, false};

        String definitionChoice = (startChoicesDefault[0] == true) ? (ON) : (OFF);
        String hintsChoice = (startChoicesDefault[1] == true) ? (ON) : (OFF);

        System.out.println("[1] Play Game");
        System.out.println("[2] Change Difficulty" + 
                            " (Current Length: " + wordLengthDefault + ")");
        System.out.println("[3] Definitions" + " (" + definitionChoice + ")");
        System.out.println("[4] Hints" + " (" + hintsChoice + ")");

        System.out.println("[5] Scoreboard");
        System.out.println("[Q]uit");
    }

    public static void startMenu(int wordLength, boolean setDefChoice, boolean setHintChoice) { 
        String definitionChoice = (setDefChoice == true) ? ON : OFF;
        String hintsChoice = (setHintChoice == true) ? ON : OFF;

        System.out.println("[1] Play Game");
        System.out.println("[2] Change Difficulty" + " (Current Length: " + wordLength + ")");
        System.out.println("[3] Definitions" + " (" + definitionChoice + ")");
        System.out.println("[4] Hints" + " (" + hintsChoice + ")");

        System.out.println("[5] Scoreboard");
        System.out.println("[Q]uit");
    }

    public static void ingameMenu(Word wd, Mechanics mc, int hintsLeft) {
        System.out.println("CURRENT WORD");
        System.out.println(wd.getCensoredVersion());

        System.out.println("\nPAST GUESSES");
        System.out.println(mc.getPastGuesses());

        System.out.println("\n[H]int" + " (" + hintsLeft + " left)");
        System.out.println("[R]estart");
        System.out.println("[Q]uit");
    }

    public static void endMenu(boolean status, Word wd, Mechanics mc) {
        if (status) {
            winText();
        } else {
            gameOverText();
        }
        
        System.out.printf("\nThe word was: %s", wd.getWord());

        System.out.println("\n\nSTATS");
        System.out.println(mc.stats());

        System.out.println("\n[A]gain?");
        System.out.println("[S]coreboard");
        System.out.println("[Q]uit");
    }

    public static void getMessage() {
        System.out.println(message);
    }

    public static void updateMessage(String input) {
        message.append(input);
    }

    public static void clearMessage() {
        message = new StringBuilder();
    }
}