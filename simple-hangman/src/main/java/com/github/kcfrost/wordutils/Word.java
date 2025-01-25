package com.github.kcfrost.wordutils;

public abstract class Word {
    private static final char CENSOR_SYMBOL = '*';
    private static final int DEFAULT_WORD_LENGTH = 7;

    public String word;
    public StringBuilder censoredWord;
    public String definition;

    public static char getCensorSymbol() {
        return CENSOR_SYMBOL;
    }

    public static int getDefaultWordLength() {
        return DEFAULT_WORD_LENGTH;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public int getLength() {
        return word.length();
    }

    public StringBuilder getCensoredVersion() {
        return censoredWord;
    }

    @Override
    public String toString() {
        return word;
    }
}
