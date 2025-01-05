package com.github.kcfrost;

import java.util.Scanner;

import com.github.kcfrost.utils.Choices;
import com.github.kcfrost.utils.Mechanics;
import com.github.kcfrost.utils.Word;
import com.github.kcfrost.visuals.Hangman;
import com.github.kcfrost.visuals.Screen;

public class Game {

    public static Word wd = new Word();
    public static Mechanics mc = new Mechanics(wd);
    public static int wordLength = wd.getLength();

    public static void main(String[] args) {        
        Scanner scan = new Scanner(System.in);

        while (true) {    
            boolean isGameOver = false;
            
            startUp();
            Screen.startMenu(wordLength, Mechanics.getDefinitionSwitch(), 
                                Mechanics.getHintSwitch());
            
            System.out.println();
            System.out.print("Enter (case-insensitive) input: ");
            String input = scan.nextLine().toUpperCase();

            while (!Choices.isStartChoice(input)) {
                refreshScreen();
                startUp();
                Screen.startMenu(wordLength, Mechanics.getDefinitionSwitch(), 
                                Mechanics.getHintSwitch());
    
                System.out.println();
                System.out.print("Invalid choice. Please pick again: ");
                input = scan.nextLine().toUpperCase();
            }

            switch (input) {
                case "1":
                    if (isGameOver) {
                        restartGame(wordLength);
                        isGameOver = false;
                    }

                    while ((!isGameOver)) {
                        refreshScreen();
                        Hangman.status();
                        Screen.divider();
                        
                        if (input.equals("/H")) {
                            Screen.updateMessage(mc.giveHint());
                        }

                        Screen.ingameMenu(wd, mc, mc.getHintsLeft());

                        Screen.getMessage();
                        System.out.println("Prefix with '/' if selecting an option");
                        System.out.print("Enter guess: ");
                        input = scan.nextLine().toUpperCase();
                        Screen.clearMessage();

                        if (input.length() == 2) {
                            while (!Choices.isInGameChoice(input) && 
                                    !Choices.isUppercaseAlphabet(input)) {
                                System.out.println("\nPrefix with '/' if selecting an option");
                                System.out.print("Invalid choice. Please pick again: ");
                                input = scan.nextLine().toUpperCase();
                            }

                            if (input.equals("/R")) {
                                restartGame(wordLength);
                                continue;
                            } else if (input.equals(Choices.INGAME_QUIT)) {
                                restartGame(wordLength);
                                break;
                            }
                        }

                        if (Character.isLetter(input.charAt(0))) {
                            if (!mc.guess(input.charAt(0))) {
                                Hangman.update();
                            }
                        }
                        
                        isGameOver = mc.gameState(Hangman.getMistakesCount()); 

                    }

                    break;
                
                case "2":
                    int newWordLength;
                    
                    do {
                        System.out.print("Set the length of the word (max of 15): "); 
                        try {
                            newWordLength = scan.nextInt();
                            scan.nextLine();

                            if (newWordLength > 15 || newWordLength <= 1) {
                                refreshScreen();
                                startUp();
                                Screen.startMenu(wordLength, Mechanics.getDefinitionSwitch(), 
                                                Mechanics.getHintSwitch());
                                System.out.println("\nInvalid length! Stay within range.");
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                            refreshScreen();
                            startUp();
                            Screen.startMenu(wordLength, Mechanics.getDefinitionSwitch(), 
                                            Mechanics.getHintSwitch());
                            System.out.println("\nInvalid input! Numbers only.");
                            scan.next();
                        }
                    } while (true);
                    
                    wordLength = newWordLength;
                    restartGame(newWordLength);

                    break;

                case "3":
                    Mechanics.toggleDefinitions();
                    break;

                case "4":
                    Mechanics.toggleHint();;
                    break;
                
                case "5":
                    // if option 5: open scoreboard (for that gameplay session)
                    // no input is asked here
                    // scoreboard is just a table of 'scores' 
                    // where scores is the amount of tries per game
                    // listed in descending order
                    // a max of 10 are shown at a time
                        // USE A QUEUE aka FIFO
                        // when you push the 11th score, pop the 1st score
                    break;
            }

            if (isGameOver) {
                refreshScreen();
                Hangman.status();
                Screen.divider();
                Screen.endMenu(mc.gameResult(Hangman.getMistakesCount()), wd, mc);
                System.out.println();
                System.out.print("Enter (case-insensitive) input: ");
                input = scan.nextLine().toUpperCase();

                while (!Choices.isEndChoice(input)) {
                    refreshScreen();
                    Hangman.status();
                    Screen.divider();
                    Screen.endMenu(mc.gameResult(Hangman.getMistakesCount()), wd, mc);

                    System.out.println();
                    System.out.print("Invalid choice. Please pick again: ");
                    input = scan.nextLine().toUpperCase();
                }
             } 
            
            if (input.equals(Choices.QUIT)) {
                break;
            }
            
        }
        
        scan.close();
    }

    public static void startUp() {
        Screen.titleText();
        Hangman.status();
        Screen.divider();
    }

    public static void refreshScreen() {
        for (int i = 0; i <= 20; i++) {
            System.out.println();
        }
    }

    public static void restartGame(int newWordLength) {
        wd = new Word(newWordLength, Mechanics.getDefinitionSwitch());
        mc = new Mechanics(wd);
        Hangman.restart();
    }

}