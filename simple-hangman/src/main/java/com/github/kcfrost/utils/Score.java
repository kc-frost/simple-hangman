package com.github.kcfrost.utils;

public class Score {
    private static final int PER_HINT_PENALTY = -2;
    private static final int PER_MISTAKE_PENALTY = -1;

    private int baseScore;
    private int accuracyBonus;
    private int hintsUsed;
    private int mistakesMade;
    private int difficultyMultiplier;

    public Score(int baseScore, int attemptsMade, int hintsUsed, int mistakesMade) {
        this.baseScore = baseScore;
        this.accuracyBonus = (baseScore * 100)/attemptsMade;  
        // this is getting hintsLeft, so its messing with the math
        this.hintsUsed = hintsUsed;
        this.mistakesMade = mistakesMade;
        this.difficultyMultiplier = getDifficultyMultiplier(baseScore);
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
            // TODO Fix this 
            return 0;
        }
    }

}
