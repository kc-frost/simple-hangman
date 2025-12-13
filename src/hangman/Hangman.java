package hangman;

import java.util.*;

public class Hangman {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter length of word [2-5]: ");
        int wordLength = Integer.parseInt(scan.nextLine());

        String word = switch (wordLength) {
            case 3 -> Words.length3Words[new Random().nextInt(5)];
            case 4 -> Words.length4Words[new Random().nextInt(5)];
            case 5 -> Words.length5Words[new Random().nextInt(5)];
            default -> Words.length2Words[new Random().nextInt(5)];
        };

        List<String> guesses = new ArrayList<>();
        for (int i = 0; i < wordLength; i++) {
            guesses.add("_");
        }

        int remainingTries = 7;
        while (guesses.contains("_") && remainingTries != 0) {
            for (int i = 0; i < wordLength; i++) {
                System.out.print(guesses.get(i));
            }

            System.out.print("\nEnter guess: ");
            String guess = scan.nextLine();

            while (guess.length() != 1) {
                System.out.println("\nGuess can only be 1 character at a time");
                System.out.print("Enter guess: ");
                guess = scan.nextLine();
            }

            if (word.contains(guess)) {
                int lastBlankIndex = guesses.indexOf("_");
                guesses.set(word.indexOf(guess, lastBlankIndex), guess);
                System.out.println();
            } else {
                remainingTries--;
                System.out.println("Wrong guess! " + remainingTries + " tries remaining.\n");
            }
        }

        if (remainingTries == 0) {
            System.out.println("You lost!");
        } else {
            System.out.println("You won!");
        }
        System.out.println("The word was: " + word);

    }
}
