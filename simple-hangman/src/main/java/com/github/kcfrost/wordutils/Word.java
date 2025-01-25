package com.github.kcfrost.wordutils;

public abstract class Word {
    private static final char CENSOR_SYMBOL = '*';
    public static final int DEFAULT_WORD_LENGTH = 7;

    public String word;
    public StringBuilder censoredWord;
    public String definition;

    public static char getCensorSymbol() {
        return CENSOR_SYMBOL;
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
}
