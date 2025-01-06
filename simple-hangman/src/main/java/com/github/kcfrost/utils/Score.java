package com.github.kcfrost.utils;

import java.time.LocalTime;

public class Score {
    private static final int PER_HINT_PENALTY = -2;
    private static final int PER_MISTAKE_PENALTY = -1;

    private int baseScore;
    private int accuracyBonus;
    private int hintsUsed;
    private int mistakesMade;
    private int difficultyMultiplier;
    private LocalTime time;

    public Score(int baseScore, int attemptsMade, int hintsUsed, int mistakesMade) {
        this.baseScore = baseScore;
        this.accuracyBonus = (baseScore * 100)/attemptsMade;

        this.hintsUsed = getHintsUsed(hintsUsed);
        
        this.mistakesMade = mistakesMade;
        this.difficultyMultiplier = getDifficultyMultiplier(baseScore);

        this.time = LocalTime.now();
    }

    public int getScore() {
        int subtotal = (baseScore) + (hintsUsed * PER_HINT_PENALTY) + 
                        (accuracyBonus) + (mistakesMade * PER_MISTAKE_PENALTY);
        
        return (subtotal) * (difficultyMultiplier);
    }

    private int getDifficultyMultiplier(int wordLength) {
        if (wordLength >= 2 && wordLength <= 7) {
            return 2;
        } else if (wordLength >= 8 && wordLength <= 11) {
            return 4;
        } else if (wordLength >= 12 && wordLength <= 15) {
            return 6;
        } else {
            // TODO Find a better way to write this
            return 0;
        }
    }

    private int getHintsUsed(int hintsLeft) {
        if (hintsLeft == 3) {
            return 0;
        } else if (hintsLeft == 2) {
            return 1;
        } else if (hintsLeft == 1) {
            return 2;
        } else if (hintsLeft == 0) {
            return 3;
        } else {
            // TODO Find a better way to write this too
            return 0; 
        }
    }

}
