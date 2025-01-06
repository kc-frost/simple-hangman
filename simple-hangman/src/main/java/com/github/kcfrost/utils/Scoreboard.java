package com.github.kcfrost.utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import com.github.kcfrost.visuals.Screen;

public class Scoreboard {
    private static Queue<Integer> scoreboard = new LinkedList<>();
    
    public static void update(Score score) {
        if (scoreboard.size() == 5) {
            scoreboard.poll();
        } 

        scoreboard.add(score.getScore());
    }

    public static void display() {
        Scanner scan = new Scanner(System.in);

        Screen.scoreboardText();
        Screen.divider();
        
        System.out.print("Press any key to continue...");
        scan.nextLine();
        scan.close();
    }

}
