package org.drools.games.numberguess;

/**
 * This is the guess object class for the game.
 */

public class Guess {
    private int value;

    public Guess(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Guess{" +
               "value=" + value +
               '}';
    }
}// ENd Class Guess
