package com.github.kcfrost.utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import com.github.kcfrost.visuals.Screen;

public class Scoreboard {
    private static Queue<Score> scoreboard = new LinkedList<>();
    
    public static void update(Score score) {
        if (scoreboard.size() == 5) {
            scoreboard.poll();
        } 

        scoreboard.add(score);
    }

    public static void display(Scanner scan) {
        Screen.scoreboardText();
        Screen.divider();
        int count = 1;
        for (Score score : scoreboard) {
            System.out.printf("Score %d \t %d \t\t %s\n", count, score.getScore(), 
                                                            score.getTime());
            count++;
        }
        
        System.out.print("\nPress any key to continue...");
        scan.nextLine();
        // scan.close();
    }

}
