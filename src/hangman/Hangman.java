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

        System.out.println(wordLength);
        System.out.println(word);

        int remainingTries = 7;
        while (guesses.contains("_") && remainingTries != 0) {
            System.out.print("Enter guess: ");
            String guess = scan.nextLine();
            if (word.contains(guess)) {
                guesses.set(word.indexOf(guess), guess);
            } else {
                remainingTries--;
                System.out.println("Wrong guess! " + remainingTries + " tries remaining.\n");
            }
        }

        for  (int i = 0; i < wordLength; i++) {
            System.out.print(guesses.get(i));
        }

    }
}
